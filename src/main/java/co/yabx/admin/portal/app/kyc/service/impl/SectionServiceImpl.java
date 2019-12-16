package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.kyc.dto.GroupsDTO;
import co.yabx.admin.portal.app.kyc.dto.SectionsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.LicenseDetails;
import co.yabx.admin.portal.app.kyc.entities.MonthlyTransactionProfiles;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.WorkEducationDetails;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.FieldService;
import co.yabx.admin.portal.app.kyc.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private FieldService dynamicFieldService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SectionServiceImpl.class);

	@Override
	public void prepareUserDetails(List<SectionsDTO> appPagesSectionsDTOList, User retailer, User nominees,
			Set<AddressDetails> userAddressDetailsSet, Set<BankAccountDetails> userBankAccountDetailsSet,
			Set<AddressDetails> nomineeAddressDetailsSet, Set<BankAccountDetails> nomineeBankAccountDetailsSet,
			Set<BusinessDetails> businessDetailsSet, Set<AddressDetails> businessAddressDetailsSet,
			Set<BankAccountDetails> businessBankAccountDetailsSet, Set<LiabilitiesDetails> liabilitiesDetailsSet,
			Set<WorkEducationDetails> workEducationDetailsSet, Set<IntroducerDetails> introducerDetailsSet,
			Set<MonthlyTransactionProfiles> monthlyTransactionProfilesSet, Set<AttachmentDetails> attachmentDetailsSet,
			Set<LicenseDetails> licenseDetailsSet) {
		for (SectionsDTO appPagesSectionsDTO : appPagesSectionsDTOList) {
			List<GroupsDTO> appPagesSectionGroupsDTOList = appPagesSectionsDTO.getGroups();
			if (appPagesSectionGroupsDTOList != null && !appPagesSectionGroupsDTOList.isEmpty()) {
				for (GroupsDTO appPagesSectionGroupsDTO : appPagesSectionGroupsDTOList) {
					dynamicFieldService.prepareFields(retailer, nominees, appPagesSectionGroupsDTO,
							userAddressDetailsSet, userBankAccountDetailsSet, nomineeAddressDetailsSet,
							nomineeBankAccountDetailsSet, businessDetailsSet, businessAddressDetailsSet,
							businessBankAccountDetailsSet, liabilitiesDetailsSet, appPagesSectionsDTO,
							monthlyTransactionProfilesSet, workEducationDetailsSet, introducerDetailsSet,
							attachmentDetailsSet, licenseDetailsSet);
				}
			}

		}
	}

}
