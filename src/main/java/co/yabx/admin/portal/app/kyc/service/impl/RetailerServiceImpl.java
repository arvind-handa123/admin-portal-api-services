package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.dto.dtoHelper.QuestionAnswerDTOHelper;
import co.yabx.admin.portal.app.dto.dtoHelper.RetailersDtoHelper;
import co.yabx.admin.portal.app.enums.AccountStatus;
import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.enums.PageType;
import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.enums.UserStatus;
import co.yabx.admin.portal.app.enums.UserType;
import co.yabx.admin.portal.app.kyc.dto.BusinessDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.LiabilitiesDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.QuestionAnswerDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.dto.RetailerRequestDTO;
import co.yabx.admin.portal.app.kyc.dto.RetailersDTO;
import co.yabx.admin.portal.app.kyc.dto.UserDTO;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;
import co.yabx.admin.portal.app.kyc.entities.Nominees;
import co.yabx.admin.portal.app.kyc.entities.Retailers;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.UserRelationships;
import co.yabx.admin.portal.app.kyc.repositories.RetailersRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.AccountStatusService;
import co.yabx.admin.portal.app.kyc.service.AndroidPushNotificationsService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.RetailerService;
import co.yabx.admin.portal.app.kyc.service.UserService;
import co.yabx.admin.portal.app.wrappers.UserWrapper;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountStatusService accountStatusService;

	@Autowired
	private RetailersRepository retailersRepository;

	@Autowired
	private UserRelationshipsRepository userRelationshipsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AndroidPushNotificationsService androidPushNotificationsService;

	@Autowired
	AppConfigService appConfigService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerServiceImpl.class);

	@Override
	public ResponseDTO getSummaries(String dsrMSISDN, Integer startIndex, Integer endIndex) {
		if (dsrMSISDN != null && !dsrMSISDN.isEmpty()) {
			User dsr = userRepository.findBymsisdnAndUserType(dsrMSISDN, UserType.DISTRIBUTORS.toString());
			if (dsr != null) {
				List<UserRelationships> dsrRetailersRelationships = userRelationshipsRepository.findByMsisdn(dsrMSISDN);
				if (dsrRetailersRelationships != null && !dsrRetailersRelationships.isEmpty()) {
					List<User> retailers = dsrRetailersRelationships.stream()
							.filter(f -> Relationship.RETAILER.equals(f.getRelationship())).map(f -> f.getRelative())
							.collect(Collectors.toList());
					ResponseDTO responseDTO = RetailersDtoHelper.getSummary(retailers);
					responseDTO.setName(dsr.getFirstName());
					responseDTO.setEmail(dsr.getEmail());
					if (appConfigService.getBooleanProperty("IS_TO_PUSH_SUMMARY_NOTIFICATION", false))
						notifyKycStatusToDSR(dsr, responseDTO);
					return responseDTO;
				}
			} else {
				ResponseDTO responseDTO = RetailersDtoHelper.getResponseDTO(null, "No Retailers Found for the DSR",
						"404", null);
				return responseDTO;
			}

		}
		return RetailersDtoHelper.getResponseDTO(null, "Either DSR msisdn is missing or no data passed", "403", null);
	}

	@Async
	private void notifyKycStatusToDSR(User dsr, ResponseDTO responseDTO) {
		if (responseDTO != null && dsr != null && dsr.getDeviceInformation() != null
				&& dsr.getDeviceInformation().getDeviceId() != null) {
			List<RetailersDTO> retailersDTOs = responseDTO.getRetailers();
			int submitted = 0;
			int rejected = 0;
			int approved = 0;
			int inProgress = 0;
			for (RetailersDTO retailersDTO : retailersDTOs) {
				KycStatus kycStatus = retailersDTO.getKycStatus();
				if (KycStatus.IN_PROGRESS == kycStatus) {
					inProgress++;
				} else if (KycStatus.REJECTED == kycStatus) {
					rejected++;
				} else if (KycStatus.APPROVED == kycStatus) {
					approved++;
				} else if (KycStatus.SUBMITTED == kycStatus) {
					submitted++;
				}
			}
			androidPushNotificationsService.sendNotificationToDevice(dsr.getDeviceInformation().getDeviceId(),
					"Retailers KYC Status Update",
					"Total Retailers:" + submitted + inProgress + approved + rejected + "Submitted:" + submitted
							+ " ,In-Progress:" + inProgress + " ,Rejected:" + rejected + " ,Approved:" + approved,
					"");
		}
	}

	@Override
	public ResponseDTO retailerDetails(String dsrMsisdn, Long retailerId) {
		Optional<Retailers> user = retailersRepository.findById(retailerId);
		if (user.isPresent()) {
			UserRelationships dsrRetailersRelationships = userRelationshipsRepository.findByMsisdnAndRelative(dsrMsisdn,
					user.get());
			if (dsrRetailersRelationships != null) {
				ResponseDTO responseDTO = RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
				responseDTO.setRetailerInfo(userService.getUserDetails(user.get(), PageType.RETAILERS));
				return responseDTO;
			}
		}
		ResponseDTO responseDTO = RetailersDtoHelper.getResponseDTO(null, "Retailer Not Found", "404", null);
		// responseDTO.setRetailerInfo(userService.getUserDetails(null,
		// PageType.RETAILERS));
		return responseDTO;

		// return RetailersDtoHelper.getCompletRetailerInfo(dsrMsisdn, retailerId);
	}

	@Override
	public ResponseDTO submitRetailerProfile(UserDTO userDTO) {
		Retailers retailers = (Retailers) UserWrapper.prepareUserPersonalInfo(userDTO);
		retailers.setBusinessDetails(UserWrapper.getBusinessDetails(userDTO.getBusinessDetails()));
		retailers.setAddressDetails(UserWrapper.getAddressDetails(userDTO.getAddressDetails()));
		retailers.setBankAccountDetails(UserWrapper.prepareBankAccountDetails(userDTO.getBankAccountDetails()));
		// retailers.setIncomeDetails(UserWrapper.getIncomeDetails(userDTO.getIncomeDetails()));
		retailers.setIntroducerDetails(UserWrapper.getIntroducerDetails(userDTO.getIntroducerDetails()));
		retailers.setLiabilitiesDetails(UserWrapper.prepareLiabilitiesDetails(userDTO.getLiabilitiesDetails()));
		retailers.setWorkEducationDetails(UserWrapper.getWorkEducationDetails(userDTO.getWorkEducationDetails()));
		userRepository.save(retailers);
		return RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
	}

	@Override
	public ResponseDTO submitRetailerNomineeProfile(UserDTO userDTO) {
		Nominees retailers = (Nominees) UserWrapper.prepareUserPersonalInfo(userDTO);
		userRepository.save(retailers);
		return RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
	}

	@Override
	public ResponseDTO submitRetailerBusinessInfo(BusinessDetailsDTO businessDetailsDTO) {
		Retailers retailers = retailersRepository.findByRetailerId(businessDetailsDTO.getRetailerId());

		return RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
	}

	@Override
	public ResponseDTO submitLiabilitiesInfo(LiabilitiesDetailsDTO liabilitiesDetailsDTO) {

		// retailersRepository.findByRetailerId(liabilitiesDetailsDTO.getRetailerId());
		// TODO Auto-generated method stub
		return RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
	}

	@Override
	public ResponseDTO submitKyc(String dsrMsisdn, Long retailerId) {
		if (dsrMsisdn != null && retailerId != null && !dsrMsisdn.isEmpty()) {
			List<UserRelationships> userRelationshipsList = userRelationshipsRepository
					.findByMsisdnAndRelationship(dsrMsisdn, Relationship.RETAILER);
			if (userRelationshipsList != null && !userRelationshipsList.isEmpty()) {
				Optional<UserRelationships> userRelationships = userRelationshipsList.stream()
						.filter(f -> f.getRelative().getId().equals(retailerId)).findFirst();
				if (userRelationships.isPresent()) {
					if (userRelationships.get().getRelative() != null
							&& userRelationships.get().getRelative().getMsisdn() != null
							&& !userRelationships.get().getRelative().getMsisdn().isEmpty()) {
						accountStatusService.createAccountStatus(userRelationships.get().getRelative().getMsisdn(),
								dsrMsisdn, true, KycStatus.SUBMITTED);
						return RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
					} else {
						return RetailersDtoHelper.getResponseDTO(null, "No retailer mapping found", "404", null);
					}
				}
			}
			return RetailersDtoHelper.getResponseDTO(null, "No DSR and retailer mapping found", "404", null);

		}
		return RetailersDtoHelper.getResponseDTO(null, "Either DSR msisdn or retailers id is missing", "403", null);
	}

	@Override
	public ResponseDTO getRetailerQuestion(Integer questionId) {
		// TODO Auto-generated method stub
		ResponseDTO responseDTO = RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
		return QuestionAnswerDTOHelper.getquestion(responseDTO);

	}

	@Override
	public ResponseDTO getRetailerAllQuestions(String retailerId) {
		ResponseDTO responseDTO = RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
		return QuestionAnswerDTOHelper.getAllquestion(responseDTO);
	}

	@Override
	public ResponseDTO persistRetailerAnswer(QuestionAnswerDTO questionAnswerDTO) {
		// TODO Auto-generated method stub
		return RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
	}

	@Override
	public ResponseDTO searchRetailerByDsr(String dsrMsisdn, String retailerId) {
		if (dsrMsisdn != null && retailerId != null && !dsrMsisdn.isEmpty() && !retailerId.isEmpty()) {
			List<UserRelationships> dsrRetailersRelationships = userRelationshipsRepository.findByMsisdn(dsrMsisdn);
			if (dsrRetailersRelationships != null && !dsrRetailersRelationships.isEmpty()) {
				List<User> retailers = dsrRetailersRelationships.stream()
						.filter(f -> Relationship.RETAILER.equals(f.getRelationship())).map(f -> f.getRelative())
						.collect(Collectors.toList());
				return RetailersDtoHelper.getSummary(retailers);
			}
			return RetailersDtoHelper.getResponseDTO(null, "No Retailers Found for the DSR", "404", null);
		}
		return RetailersDtoHelper.getResponseDTO(null, "Either DSR msisdn or retailers id is missing", "403", null);
	}

	@Override
	public ResponseDTO submitRetailerProfile(RetailerRequestDTO retailerRequestDTO) throws Exception {
		if (retailerRequestDTO != null) {
			String dsrMsisdn = retailerRequestDTO.getDsrMSISDN();
			DSRUser dsrUser = userService.getDSRByMsisdn(dsrMsisdn);
			Retailers retailers = null;
			if (dsrUser == null)
				return RetailersDtoHelper.getResponseDTO(null, "DSR not found", "404", null);
			Long retailerId = retailerRequestDTO.getRetailerId();
			if (retailerId != null) {
				retailers = userService.getRetailerById(retailerId);
				if (retailers == null)
					return RetailersDtoHelper.getResponseDTO(null, "Retailer not found", "404", null);
			}

			userService.persistOrUpdateUserInfo(retailerRequestDTO.getPageResponse(), dsrUser, retailers);
			return RetailersDtoHelper.getResponseDTO(null, "SUCCESS", "200", null);
			/*
			 * } catch (Exception e) { e.printStackTrace(); LOGGER.
			 * error("Something went wrong while persisting user={} info={}, error={}",
			 * retailers.getMsisdn(), retailerRequestDTO, e.getMessage()); return
			 * RetailersDtoHelper.getResponseDTO(null, "Internal Server Error", "500",
			 * null);
			 * 
			 * }
			 */
		}
		return null;
	}

}
