package co.yabx.admin.portal.app.dto.dtoHelper;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.yabx.admin.portal.app.enums.UserType;
import co.yabx.admin.portal.app.kyc.dto.ActionDTO;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.SectionsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.Pages;
import co.yabx.admin.portal.app.kyc.entities.Sections;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.util.SpringUtil;

public class PagesDTOHeper implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PagesDTOHeper.class);

	public static PagesDTO prepareAppPagesDto(Pages pages, User retailers, User nominee,
			Set<AddressDetails> userAddressDetailsSet, Set<AddressDetails> nomineeAddressDetailsSet,
			Set<AddressDetails> businessAddressDetailsSet, Set<BankAccountDetails> userBankAccountDetailsSet,
			Set<BankAccountDetails> nomineeBankAccountDetailsSet, Set<BankAccountDetails> businessBankAccountDetailsSet,
			String type) {
		PagesDTO appPagesDTO = new PagesDTO();
		Map<String, Integer> filledVsUnfilled = new HashMap<String, Integer>();
		filledVsUnfilled.put("filledFields", 0);
		filledVsUnfilled.put("totalFields", 0);
		Set<Sections> appPagesSectionsSet = pages.getSections();
		if (appPagesSectionsSet != null && !appPagesSectionsSet.isEmpty()) {
			List<SectionsDTO> appPagesSectionSet = SectionDtoHelper.getSections(appPagesSectionsSet, retailers,
					filledVsUnfilled, nominee, userAddressDetailsSet, nomineeAddressDetailsSet,
					businessAddressDetailsSet, userBankAccountDetailsSet, nomineeBankAccountDetailsSet,
					businessBankAccountDetailsSet);
			appPagesDTO.setSections(appPagesSectionSet.stream().sorted(Comparator.comparing(SectionsDTO::getSectionId))
					.collect(Collectors.toList()));
			appPagesDTO.setEnable(pages.isEnable());
			appPagesDTO.setPageId(pages.getPageId());
			// appPagesDTO.setPageName(pages.getPageName());
			appPagesDTO.setPageTitle(pages.getPageTitle());
			appPagesDTO.setTotalFields(filledVsUnfilled.get("totalFields"));
			appPagesDTO.setFilledFields(filledVsUnfilled.get("filledFields"));
			appPagesDTO.setDisplayOrder(pages.getDisplayOrder());
			if (UserType.RETAILERS.toString().equals(type))
				appPagesDTO.setRetailerId(retailers != null ? retailers.getId() : null);
			else if (UserType.DISTRIBUTORS.toString().equals(type))
				appPagesDTO.setDsrId(retailers != null ? retailers.getId() : null);
			appPagesDTO.setPageCompletion(appPagesDTO.getTotalFields() != 0
					? ((appPagesDTO.getFilledFields() * 100) / appPagesDTO.getTotalFields()) + "%"
					: "0%");
			if (pages != null && pages.getPageId() != SpringUtil.bean(AppConfigService.class)
					.getLongProperty("ATTACHMENT_PAGE_ID", 7l))
				appPagesDTO.setAction(prepareActions());
		}
		return appPagesDTO;
	}

	public static PagesDTO prepareAppPagesDto(co.yabx.admin.portal.app.admin.entities.Pages pages) {
		PagesDTO appPagesDTO = new PagesDTO();
		Set<co.yabx.admin.portal.app.admin.entities.Sections> appPagesSectionsSet = pages.getSections();
		if (appPagesSectionsSet != null && !appPagesSectionsSet.isEmpty()) {
			List<SectionsDTO> appPagesSectionSet = SectionDtoHelper.getSections(appPagesSectionsSet);
			appPagesDTO.setSections(appPagesSectionSet.stream().sorted(Comparator.comparing(SectionsDTO::getSectionId))
					.collect(Collectors.toList()));
			appPagesDTO.setEnable(pages.isEnable());
			appPagesDTO.setPageId(pages.getPageId());
			// appPagesDTO.setPageName(pages.getPageName());
			appPagesDTO.setPageTitle(pages.getPageTitle());
			appPagesDTO.setDisplayOrder(pages.getDisplayOrder());

		}
		return appPagesDTO;
	}

	private static ActionDTO prepareActions() {
		ActionDTO actionDTO = new ActionDTO();
		actionDTO.setData(SpringUtil.bean(AppConfigService.class).getProperty("KYC_PAGE_ACTION_BUTTON_NAME", "Save"));
		actionDTO.setName("submitForm");
		actionDTO.setType("submit");
		return actionDTO;
	}

}
