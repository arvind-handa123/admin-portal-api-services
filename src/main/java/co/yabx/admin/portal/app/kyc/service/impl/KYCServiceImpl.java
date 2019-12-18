package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.dto.dtoHelper.PagesDTOHeper;
import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.enums.PageType;
import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.enums.UserType;
import co.yabx.admin.portal.app.kyc.dto.GroupsDTO;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.LicenseDetails;
import co.yabx.admin.portal.app.kyc.entities.LoanPurposeDetails;
import co.yabx.admin.portal.app.kyc.entities.MonthlyTransactionProfiles;
import co.yabx.admin.portal.app.kyc.entities.Nominees;
import co.yabx.admin.portal.app.kyc.entities.Pages;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.UserRelationships;
import co.yabx.admin.portal.app.kyc.entities.WorkEducationDetails;
import co.yabx.admin.portal.app.kyc.repositories.AccountStatusesRepository;
import co.yabx.admin.portal.app.kyc.repositories.AddressDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.BankAccountDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.PagesRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.kyc.service.UserService;
import co.yabx.admin.portal.app.util.SpringUtil;

@Service
public class KYCServiceImpl implements KYCService {

	@Autowired
	private AccountStatusesRepository accountStatusesRepository;

	@Autowired
	private UserRelationshipsRepository userRelationshipsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressDetailsRepository addressDetailsRepository;

	@Autowired
	private BankAccountDetailsRepository accountDetailsRepository;
	
	@Autowired
	private PagesRepository pagesRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(KYCServiceImpl.class);

	public List<PagesDTO> findAllRetailers() {
		List<UserRelationships> dsrRetailersRelationships = userRelationshipsRepository
				.findByRelationship(Relationship.RETAILER);
		List<PagesDTO> pagesDTOs = new ArrayList<PagesDTO>();
		for (UserRelationships userRelationships : dsrRetailersRelationships) {
			pagesDTOs.addAll(userService.getUserDetails(userRelationships.getRelative(), PageType.RETAILERS));
		}
		return pagesDTOs;
	}

	@Override
	public List<PagesDTO> fetchRetailersByKycStatus(KycStatus kycStatus) {
		List<User> usersList = userRepository.findByUserType(UserType.RETAILERS.name());
		List<PagesDTO> appPagesDTOList = new ArrayList<PagesDTO>();
		for (User user : usersList) {
			List<PagesDTO> appPagesDTO = new ArrayList<PagesDTO>();
			if (user.getMsisdn() != null && !user.getMsisdn().isEmpty()) {
				AccountStatuses accountStatuses = accountStatusesRepository.findByMsisdn(user.getMsisdn());
				if (accountStatuses != null && accountStatuses.getKycVerified() != null
						&& accountStatuses.getKycVerified() == kycStatus) {
					Set<AddressDetails> addressDetailsSet = user.getAddressDetails();
					Set<BankAccountDetails> bankAccountDetailsSet = user.getBankAccountDetails();
					Set<BusinessDetails> businessDetailsSet = user.getBusinessDetails();
					Set<AttachmentDetails> attachmentDetailsSet = user.getAttachmentDetails();
					Set<LiabilitiesDetails> nominees = user.getLiabilitiesDetails();
					Set<IntroducerDetails> introducerDetails = user.getIntroducerDetails();
					Set<WorkEducationDetails> workEducationDetailsSet = user.getWorkEducationDetails();
					Set<MonthlyTransactionProfiles> monthlyTransactionProfilesSet = user
							.getMonthlyTransactionProfiles();
					LoanPurposeDetails loanPurposeDetails = user.getLoanPurposeDetails();
					Set<BankAccountDetails> nomineeBankAccountDetailsSet = null;
					Set<BankAccountDetails> businessBankAccountDetailsSet = new HashSet<BankAccountDetails>();
					Set<AddressDetails> nomineeAddressDetailsSet = null;
					Set<AddressDetails> businessAddressDetailsSet = new HashSet<AddressDetails>();
					List<UserRelationships> userRelationships = userRelationshipsRepository
							.findByMsisdnAndRelationship(user.getMsisdn(), Relationship.NOMINEE);
					User nominee = userRelationships != null && !userRelationships.isEmpty()
							? userRelationships.get(0).getRelative()
							: null;
					nomineeAddressDetailsSet = nominee != null ? addressDetailsRepository.findByUser(user) : null;
					nomineeBankAccountDetailsSet = nominee != null ? accountDetailsRepository.findByUser(user) : null;

					if (user.getBusinessDetails() != null) {
						user.getBusinessDetails().forEach(f -> {
							businessAddressDetailsSet.addAll(f.getAddressDetails());
						});
						user.getBusinessDetails().forEach(f -> {
							businessBankAccountDetailsSet.addAll(f.getBankAccountDetails());
						});
					}

					List<Pages> appPages = pagesRepository.findByPageType(PageType.RETAILERS);
					if (appPages == null)
						return null;
					for (Pages pages : appPages) {
						appPagesDTO.add(PagesDTOHeper.prepareAppPagesDto(pages, user, nominee, addressDetailsSet,
								nomineeAddressDetailsSet, businessAddressDetailsSet, bankAccountDetailsSet,
								nomineeBankAccountDetailsSet, businessBankAccountDetailsSet,
								PageType.RETAILERS.name()));

					}

					appPagesDTOList.addAll(appPagesDTO);
				}
			}
		}
		return appPagesDTOList;
	}

}
