package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.dto.dtoHelper.PagesDTOHeper;
import co.yabx.admin.portal.app.enums.PageType;
import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.enums.UserStatus;
import co.yabx.admin.portal.app.enums.UserType;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.SectionsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;
import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.LicenseDetails;
import co.yabx.admin.portal.app.kyc.entities.MonthlyTransactionProfiles;
import co.yabx.admin.portal.app.kyc.entities.Nominees;
import co.yabx.admin.portal.app.kyc.entities.Pages;
import co.yabx.admin.portal.app.kyc.entities.Retailers;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.UserRelationships;
import co.yabx.admin.portal.app.kyc.entities.WorkEducationDetails;
import co.yabx.admin.portal.app.kyc.repositories.AddressDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.BankAccountDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.DSRUserRepository;
import co.yabx.admin.portal.app.kyc.repositories.PagesRepository;
import co.yabx.admin.portal.app.kyc.repositories.RetailersRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.SectionService;
import co.yabx.admin.portal.app.kyc.service.UserService;
import co.yabx.admin.portal.app.util.SpringUtil;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressDetailsRepository addressDetailsRepository;

	@Autowired
	private BankAccountDetailsRepository bankAccountDetailsRepository;

	@Autowired
	private RetailersRepository retailersRepository;

	@Autowired
	private DSRUserRepository dsrUserRepository;

	@Autowired
	private SectionService appPagesSectionService;

	@Autowired
	private UserRelationshipsRepository userRelationshipsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public void persistYabxTokenAndSecretKey(User user, String yabxToken, String aPI_SECRET_KEY) {
		/*
		 * if (user != null) { user.setYabxToken(yabxToken);
		 * user.setSecretKey(aPI_SECRET_KEY); userRepository.save(user); }
		 */}

	@Override
	public List<PagesDTO> getUserDetails(User user, PageType type) {

		// Nominee Personal Info
		User nominee = null;
		Set<AddressDetails> userAddressDetailsSet = null;
		Set<AddressDetails> nomineeAddressDetailsSet = null;
		Set<AddressDetails> businessAddressDetailsSet = new HashSet<AddressDetails>();
		Set<BankAccountDetails> userBankAccountDetailsSet = null;
		Set<BankAccountDetails> nomineeBankAccountDetailsSet = null;
		Set<BankAccountDetails> businessBankAccountDetailsSet = new HashSet<BankAccountDetails>();
		/*
		 * Set<LiabilitiesDetails> liabilitiesDetailsSet = null;
		 * Set<MonthlyTransactionProfiles> monthlyTransactionProfilesSet = null;
		 * Set<WorkEducationDetails> workEducationDetailsSet = null;
		 * Set<IntroducerDetails> introducerDetailsSet = null;
		 */
		if (user != null) {
			List<UserRelationships> userRelationships = userRelationshipsRepository
					.findByMsisdnAndRelationship(user.getMsisdn(), Relationship.NOMINEE);
			nominee = userRelationships != null && !userRelationships.isEmpty() ? userRelationships.get(0).getRelative()
					: null;
			userAddressDetailsSet = user.getAddressDetails();
			nomineeAddressDetailsSet = nominee != null ? addressDetailsRepository.findByUser(user) : null;
			userBankAccountDetailsSet = user.getBankAccountDetails();
			nomineeBankAccountDetailsSet = nominee != null ? bankAccountDetailsRepository.findByUser(user) : null;
			/*
			 * liabilitiesDetailsSet = user.getLiabilitiesDetails();
			 * monthlyTransactionProfilesSet = user.getMonthlyTransactionProfiles();
			 * workEducationDetailsSet = user.getWorkEducationDetails();
			 * introducerDetailsSet = user.getIntroducerDetails();
			 */
			if (user.getBusinessDetails() != null) {
				user.getBusinessDetails().forEach(f -> {
					businessAddressDetailsSet.addAll(f.getAddressDetails());
				});
				user.getBusinessDetails().forEach(f -> {
					businessBankAccountDetailsSet.addAll(f.getBankAccountDetails());
				});
			}
		}
		List<Pages> appPages = SpringUtil.bean(PagesRepository.class).findByPageType(type);
		if (appPages == null)
			return null;
		List<PagesDTO> appPagesDTOList = new ArrayList<PagesDTO>();
		for (Pages pages : appPages) {
			appPagesDTOList.add(PagesDTOHeper.prepareAppPagesDto(pages, user, nominee, userAddressDetailsSet,
					nomineeAddressDetailsSet, businessAddressDetailsSet, userBankAccountDetailsSet,
					nomineeBankAccountDetailsSet, businessBankAccountDetailsSet, type.name()));

		}

		return appPagesDTOList;

	}

	@Override
	public DSRUser getDSRByMsisdn(String dsrMsisdn) {
		if (dsrMsisdn != null && !dsrMsisdn.isEmpty())
			return dsrUserRepository.findByMsisdn(dsrMsisdn);
		return null;
	}

	@Override
	public Retailers getRetailerById(Long retailerId) {
		if (retailerId != null) {
			Optional<Retailers> retailer = retailersRepository.findById(retailerId);
			if (retailer.isPresent() && retailer.get().getUserType().equals(UserType.RETAILERS.name()))
				return retailer.get();
		}
		return null;
	}

	@Override
	public User persistOrUpdateUserInfo(PagesDTO appPagesDTO, User dsrUser, User retailer) throws Exception {

		if (appPagesDTO != null && dsrUser != null) {
			Boolean isNew = false;
			Boolean isDsrUser = false;
			User nominees = null;
			Set<AddressDetails> userAddressDetailsSet = new HashSet<AddressDetails>();
			Set<AddressDetails> nomineeAddressDetailsSet = new HashSet<AddressDetails>();
			Set<AddressDetails> businessAddressDetailsSet = new HashSet<AddressDetails>();
			Set<BankAccountDetails> userBankAccountDetailsSet = new HashSet<BankAccountDetails>();
			Set<BankAccountDetails> nomineeBankAccountDetailsSet = new HashSet<BankAccountDetails>();
			Set<BankAccountDetails> businessBankAccountDetailsSet = new HashSet<BankAccountDetails>();
			Set<BusinessDetails> businessDetailsSet = new HashSet<BusinessDetails>();
			Set<LiabilitiesDetails> liabilitiesDetailsSet = new HashSet<LiabilitiesDetails>();
			Set<MonthlyTransactionProfiles> monthlyTransactionProfilesSet = new HashSet<MonthlyTransactionProfiles>();
			Set<WorkEducationDetails> workEducationDetailsSet = new HashSet<>();
			Set<IntroducerDetails> introducerDetailsSet = new HashSet<IntroducerDetails>();
			List<UserRelationships> nomineeRelationship = null;
			Set<AttachmentDetails> attachmentDetailsSet = new HashSet<AttachmentDetails>();
			Set<LicenseDetails> licenseDetailsSet = new HashSet<LicenseDetails>();

			if (retailer == null) {
				// It means DSR profile need to be persisted
				retailer = dsrUser;
				isDsrUser = true;
				if (nominees == null) {
					isNew = true;
				}

			} else {
				nomineeRelationship = userRelationshipsRepository.findByMsisdnAndRelationship(retailer.getMsisdn(),
						Relationship.NOMINEE);
				nominees = nomineeRelationship != null && !nomineeRelationship.isEmpty()
						? nomineeRelationship.get(0).getRelative()
						: null;
				if (nominees == null) {
					isNew = true;
					nominees = new Nominees();
				}
				LOGGER.info("Nominee={} for Retailer={}", nominees != null ? nominees.getId() : null,
						retailer.getMsisdn());
			}
			List<SectionsDTO> appPagesSectionsDTOList = appPagesDTO.getSections();
			if (appPagesSectionsDTOList != null && !appPagesSectionsDTOList.isEmpty()) {
				appPagesSectionService.prepareUserDetails(appPagesSectionsDTOList, retailer, nominees,
						userAddressDetailsSet, userBankAccountDetailsSet, nomineeAddressDetailsSet,
						nomineeBankAccountDetailsSet, businessDetailsSet, businessAddressDetailsSet,
						businessBankAccountDetailsSet, liabilitiesDetailsSet, workEducationDetailsSet,
						introducerDetailsSet, monthlyTransactionProfilesSet, attachmentDetailsSet, licenseDetailsSet);
				return persistUser(retailer, nominees, userAddressDetailsSet, userBankAccountDetailsSet,
						liabilitiesDetailsSet, isNew, nomineeRelationship, nomineeAddressDetailsSet, isDsrUser,
						businessDetailsSet, nomineeBankAccountDetailsSet, monthlyTransactionProfilesSet,
						workEducationDetailsSet, introducerDetailsSet, attachmentDetailsSet, licenseDetailsSet);
			}
		}
		return dsrUser;

	}

	@Transactional
	private User persistUser(User user, User nominees, Set<AddressDetails> userAddressDetailsSet,
			Set<BankAccountDetails> userBankAccountDetailsSet, Set<LiabilitiesDetails> liabilitiesDetails,
			Boolean isNew, List<UserRelationships> nomineeRelationship, Set<AddressDetails> nomineeAddressDetailsSet,
			Boolean isDsrUser, Set<BusinessDetails> businessDetailsSet,
			Set<BankAccountDetails> nomineeBankAccountDetailsSet,
			Set<MonthlyTransactionProfiles> monthlyTransactionProfilesSet,
			Set<WorkEducationDetails> workEducationDetailsSet, Set<IntroducerDetails> introducerDetailsSet,
			Set<AttachmentDetails> attachmentDetailsSet, Set<LicenseDetails> licenseDetailsSet) throws Exception {
		if (isDsrUser) {
			user.setUserType(UserType.DISTRIBUTORS.name());
			user.setUserStatus(UserStatus.ACTIVE);
		} else {
			user.setUserType(UserType.RETAILERS.name());
		}
		if (neitherNullNorEmpty(userAddressDetailsSet)) {
			Set<AddressDetails> userAddressDetails = user.getAddressDetails();
			userAddressDetails.clear();
			userAddressDetails.addAll(userAddressDetailsSet);
			user.setAddressDetails(userAddressDetails);
		}
		if (neitherNullNorEmpty(userBankAccountDetailsSet)) {
			Set<BankAccountDetails> userBankAccountDetails = user.getBankAccountDetails();
			userBankAccountDetails.clear();
			userBankAccountDetails.addAll(userBankAccountDetailsSet);
			user.setBankAccountDetails(userBankAccountDetails);
		}
		if (neitherNullNorEmpty(businessDetailsSet)) {
			Set<BusinessDetails> businessDetails = user.getBusinessDetails();
			businessDetails.clear();
			businessDetailsSet.forEach(f -> f.setLicenseDetails(licenseDetailsSet));
			businessDetails.addAll(businessDetailsSet);
			user.setBusinessDetails(businessDetails);
			LOGGER.info("Business details={} with license details={} is being saved", businessDetailsSet,
					licenseDetailsSet);
		}
		if (neitherNullNorEmpty(liabilitiesDetails)) {
			Set<LiabilitiesDetails> liabilitiesDetailsSet = user.getLiabilitiesDetails();
			liabilitiesDetailsSet.clear();
			liabilitiesDetailsSet.addAll(liabilitiesDetails);
			user.setLiabilitiesDetails(liabilitiesDetailsSet);
		}
		if (neitherNullNorEmpty(workEducationDetailsSet)) {
			Set<WorkEducationDetails> workEducationDetails = user.getWorkEducationDetails();
			workEducationDetails.clear();
			workEducationDetails.addAll(workEducationDetailsSet);
			user.setWorkEducationDetails(workEducationDetails);
		}
		if (neitherNullNorEmpty(monthlyTransactionProfilesSet)) {
			Set<MonthlyTransactionProfiles> monthlyTransactionProfiles = user.getMonthlyTransactionProfiles();
			monthlyTransactionProfiles.clear();
			monthlyTransactionProfiles.addAll(monthlyTransactionProfilesSet);
			user.setMonthlyTransactionProfiles(monthlyTransactionProfiles);
		}
		if (neitherNullNorEmpty(introducerDetailsSet)) {
			Set<IntroducerDetails> introducerDetails = user.getIntroducerDetails();
			introducerDetails.clear();
			introducerDetails.addAll(introducerDetailsSet);
			user.setIntroducerDetails(introducerDetails);
		}
		if (neitherNullNorEmpty(attachmentDetailsSet)) {
			// Not being used till date
			user.setAttachmentDetails(attachmentDetailsSet);
		}
		user = userRepository.save(user);
		if (nominees != null && nominees.getMsisdn() != null && !nominees.getMsisdn().isEmpty()) {
			nominees.setUserType(UserType.NOMINEES.name());
			if (nomineeAddressDetailsSet != null) {
				Set<AddressDetails> userAddressDetails = nominees.getAddressDetails();
				userAddressDetails.clear();
				userAddressDetails.addAll(nomineeAddressDetailsSet);
				nominees.setAddressDetails(userAddressDetails);
				LOGGER.info("Nominees={} Address={} is being saved", nominees.getId(), nomineeAddressDetailsSet);
			}
			if (nomineeBankAccountDetailsSet != null) {
				Set<BankAccountDetails> userBankAccountDetails = nominees.getBankAccountDetails();
				userBankAccountDetails.clear();
				userBankAccountDetails.addAll(nomineeBankAccountDetailsSet);
				nominees.setBankAccountDetails(userBankAccountDetails);
			}
			nominees = userRepository.save(nominees);
			persistUserRelationship(user, nominees, UserType.NOMINEES, isNew, nomineeRelationship);
		}
		return user;
	}

	private boolean neitherNullNorEmpty(Set<?> set) {
		return set != null && !set.isEmpty();
	}

	private void persistUserRelationship(User retailer, User nominees, UserType nominees2, Boolean isNew,
			List<UserRelationships> nomineeRelationship) {
		UserRelationships userRelationships = null;
		if (isNew) {
			userRelationships = new UserRelationships();
		} else {
			userRelationships = nomineeRelationship.get(0);
		}
		userRelationships.setMsisdn(retailer.getMsisdn());
		userRelationships.setRelative(nominees);
		userRelationships.setRelationship(Relationship.NOMINEE);
		userRelationshipsRepository.save(userRelationships);
	}

	@Override
	public List<User> findUserByUserType(UserType retailers) {
		return userRepository.findByUserType(retailers.name());

	}

}
