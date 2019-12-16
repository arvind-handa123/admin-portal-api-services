package co.yabx.admin.portal.app.dto.dtoHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import co.yabx.admin.portal.app.kyc.dto.GroupsDTO;
import co.yabx.admin.portal.app.kyc.dto.SectionsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.Sections;
import co.yabx.admin.portal.app.kyc.entities.User;

public class SectionDtoHelper implements Serializable {
	public static List<SectionsDTO> getSections(Set<Sections> appPagesSectionsSet, User retailers,
			Map<String, Integer> filledVsUnfilled, User nominee, Set<AddressDetails> userAddressDetailsSet,
			Set<AddressDetails> nomineeAddressDetailsSet, Set<AddressDetails> businessAddressDetailsSet,
			Set<BankAccountDetails> userBankAccountDetailsSet, Set<BankAccountDetails> nomineeBankAccountDetailsSet,
			Set<BankAccountDetails> businessBankAccountDetailsSet) {
		List<SectionsDTO> appPagesSectionDTOSet = new ArrayList<SectionsDTO>();
		for (Sections appPagesSections : appPagesSectionsSet) {
			Map<String, Integer> section = new HashMap<String, Integer>();
			section.put("filledFields", 0);
			section.put("totalFields", 0);
			SectionsDTO appPagesSectionsDTO = new SectionsDTO();

			List<GroupsDTO> appPagesSectionGroupSet = GroupDtoHelper.getGroups(retailers, section, appPagesSections,
					nominee, userAddressDetailsSet, nomineeAddressDetailsSet, businessAddressDetailsSet,
					userBankAccountDetailsSet, nomineeBankAccountDetailsSet, businessBankAccountDetailsSet);
			Collections.sort(appPagesSectionGroupSet);
			appPagesSectionsDTO.setGroups(appPagesSectionGroupSet);
			appPagesSectionsDTO.setGroups(appPagesSectionGroupSet);
			appPagesSectionsDTO.setEnable(appPagesSections.isEnable());
			appPagesSectionsDTO.setSectionId(appPagesSections.getSectionId());
			appPagesSectionsDTO.setSectionName(appPagesSections.getSectionName());
			appPagesSectionsDTO.setSectionTitle(appPagesSections.getSectionTitle());
			appPagesSectionsDTO.setFilledFields(section.get("filledFields"));
			appPagesSectionsDTO.setTotalFields(section.get("totalFields"));
			appPagesSectionsDTO.setDisplayOrder(appPagesSections.getDisplayOrder());
			if (appPagesSections.getSectionId() == 2)
				appPagesSectionsDTO.setNomineeId(nominee != null ? nominee.getId() : null);

			appPagesSectionDTOSet.add(appPagesSectionsDTO);
			filledVsUnfilled.put("filledFields", filledVsUnfilled.get("filledFields") + section.get("filledFields"));
			filledVsUnfilled.put("totalFields", filledVsUnfilled.get("totalFields") + section.get("totalFields"));
		}

		return appPagesSectionDTOSet;

	}

}
