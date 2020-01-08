package co.yabx.admin.portal.app.dto.dtoHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.dto.GroupsDTO;
import co.yabx.admin.portal.app.kyc.dto.SectionsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.FieldRemarks;
import co.yabx.admin.portal.app.kyc.entities.Sections;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.util.SpringUtil;

public class SectionDtoHelper implements Serializable {
	public static List<SectionsDTO> getSections(Set<Sections> appPagesSectionsSet, User retailers,
			Map<String, Integer> filledVsUnfilled, User nominee, Set<AddressDetails> userAddressDetailsSet,
			Set<AddressDetails> nomineeAddressDetailsSet, Set<AddressDetails> businessAddressDetailsSet,
			Set<BankAccountDetails> userBankAccountDetailsSet, Set<BankAccountDetails> nomineeBankAccountDetailsSet,
			Set<BankAccountDetails> businessBankAccountDetailsSet, List<FieldRemarks> fieldRemarksList) {
		List<SectionsDTO> appPagesSectionDTOSet = new ArrayList<SectionsDTO>();
		for (Sections appPagesSections : appPagesSectionsSet) {
			Map<String, Integer> section = new HashMap<String, Integer>();
			section.put("filledFields", 0);
			section.put("totalFields", 0);
			SectionsDTO appPagesSectionsDTO = new SectionsDTO();

			List<GroupsDTO> appPagesSectionGroupSet = GroupDtoHelper.getGroups(retailers, section, appPagesSections,
					nominee, userAddressDetailsSet, nomineeAddressDetailsSet, businessAddressDetailsSet,
					userBankAccountDetailsSet, nomineeBankAccountDetailsSet, businessBankAccountDetailsSet,
					fieldRemarksList);
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

	public static List<SectionsDTO> getSections(
			Set<co.yabx.admin.portal.app.admin.entities.Sections> appPagesSectionsSet, boolean isKyc) {

		List<SectionsDTO> appPagesSectionDTOSet = new ArrayList<SectionsDTO>();
		for (co.yabx.admin.portal.app.admin.entities.Sections appPagesSections : appPagesSectionsSet) {
			SectionsDTO appPagesSectionsDTO = new SectionsDTO();
			if (isKyc) {
				KycStatus kycStatus = getKycStatus(appPagesSections.getSectionTitle());
				if (kycStatus != null) {
					appPagesSectionsDTO
							.setPagesDTOs(SpringUtil.bean(KYCService.class).fetchRetailersByKycStatus(kycStatus));
				}
			} else {
				appPagesSectionsDTO.setGroups(GroupDtoHelper.getGroups(appPagesSections.getGroups()));
			}
			appPagesSectionsDTO.setEnable(appPagesSections.isEnable());
			appPagesSectionsDTO.setSectionId(appPagesSections.getSectionId());
			appPagesSectionsDTO.setSectionName(appPagesSections.getSectionName());
			appPagesSectionsDTO.setSectionTitle(appPagesSections.getSectionTitle());
			appPagesSectionsDTO.setDisplayOrder(appPagesSections.getDisplayOrder());
			appPagesSectionDTOSet.add(appPagesSectionsDTO);

		}

		return appPagesSectionDTOSet;

	}

	private static KycStatus getKycStatus(String sectionTitle) {
		if (sectionTitle != null && !sectionTitle.isEmpty()) {
			if (sectionTitle.equalsIgnoreCase(KycStatus.IN_PROGRESS.name()))
				return KycStatus.IN_PROGRESS;
			else if (sectionTitle.equalsIgnoreCase(KycStatus.REJECTED.name()))
				return KycStatus.REJECTED;
			else if (sectionTitle.equalsIgnoreCase(KycStatus.APPROVED.name()))
				return KycStatus.APPROVED;
			else if (sectionTitle.equalsIgnoreCase(KycStatus.SUBMITTED.name()))
				return KycStatus.SUBMITTED;
			else if (sectionTitle.equalsIgnoreCase(KycStatus.LOC_GENERATED.name()))
				return KycStatus.LOC_GENERATED;
			else if (sectionTitle.equalsIgnoreCase(KycStatus.LOC_ISSUED.name()))
				return KycStatus.LOC_ISSUED;
			else if (sectionTitle.equalsIgnoreCase(KycStatus.RE_SUBMITTED.name()))
				return KycStatus.RE_SUBMITTED;
			else if (sectionTitle.equalsIgnoreCase(KycStatus.UNDER_REVIEW.name()))
				return KycStatus.UNDER_REVIEW;
			else
				return null;
		}
		return null;
	}

}
