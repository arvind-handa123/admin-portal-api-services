package co.yabx.admin.portal.app.kyc.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.JsonNode;

import co.yabx.admin.portal.app.documents.pdf.ARRANGEMENT_7;
import co.yabx.admin.portal.app.documents.pdf.COUNTER_GUARANTEE_15;
import co.yabx.admin.portal.app.documents.pdf.DECLARATION_FORM_10;
import co.yabx.admin.portal.app.documents.pdf.DISBURSEMENT_6;
import co.yabx.admin.portal.app.documents.pdf.DP_NOTE_4;
import co.yabx.admin.portal.app.documents.pdf.EFT_AUTHORIZATION_FORM_14;
import co.yabx.admin.portal.app.documents.pdf.FATCA_FORM_INDIVIDUAL_AND_PROPRIETOR_17;
import co.yabx.admin.portal.app.documents.pdf.GENERAL_INSTRUCTION_20;
import co.yabx.admin.portal.app.documents.pdf.GENERAL_LOAN_AGREEMENT_8;
import co.yabx.admin.portal.app.documents.pdf.IGPA_FIXED_AND_FLOATING_1;
import co.yabx.admin.portal.app.documents.pdf.LETTER_OF_CONTINUITY_AND_REVIVAL_5;
import co.yabx.admin.portal.app.documents.pdf.LETTER_OF_DEBIT_AUTHORITY_9;
import co.yabx.admin.portal.app.documents.pdf.LETTER_OF_LIEN_AND_SET_OFF_DEPOSIT_ACCOUNT_OR_MARGIN_13;
import co.yabx.admin.portal.app.documents.pdf.PERFORMANCE_SECURITY_FORMAT_2;
import co.yabx.admin.portal.app.documents.pdf.PERSONAL_GUARANTEE_INDIVIDUAL_11;
import co.yabx.admin.portal.app.documents.pdf.PERSONAL_GUARANTEE_JOINT_12;
import co.yabx.admin.portal.app.documents.pdf.PG_MEMO_ENGINEERS_3;
import co.yabx.admin.portal.app.documents.pdf.SBS_FORMS_16;
import co.yabx.admin.portal.app.documents.pdf.SIGNATURE_CARD_18;
import co.yabx.admin.portal.app.documents.pdf.SME_BUSINESS_SEGMENTATION_FORM_REVISED_19;
import co.yabx.admin.portal.app.dto.dtoHelper.PagesDTOHeper;
import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.enums.PageType;
import co.yabx.admin.portal.app.enums.ProductName;
import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.enums.UserType;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.ProductDocumentsDTO;
import co.yabx.admin.portal.app.kyc.dto.UserDisclaimerDocumentsDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.Attachments;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.FieldRemarks;
import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.LoanPurposeDetails;
import co.yabx.admin.portal.app.kyc.entities.MonthlyTransactionProfiles;
import co.yabx.admin.portal.app.kyc.entities.Pages;
import co.yabx.admin.portal.app.kyc.entities.ProductDocuments;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.UserRelationships;
import co.yabx.admin.portal.app.kyc.entities.WorkEducationDetails;
import co.yabx.admin.portal.app.kyc.repositories.AccountStatusesRepository;
import co.yabx.admin.portal.app.kyc.repositories.AddressDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.BankAccountDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.FieldRemarksRepository;
import co.yabx.admin.portal.app.kyc.repositories.ProductDocumentsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.AndroidPushNotificationsService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.kyc.service.UserService;
import co.yabx.admin.portal.app.util.JsonUtilService;

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
	private co.yabx.admin.portal.app.kyc.repositories.PagesRepository kycPagesRepository;

	@Autowired
	private FieldRemarksRepository fieldRemarksRepository;

	@Autowired
	private ProductDocumentsRepository productDocumentsRepository;

	@Autowired
	private AndroidPushNotificationsService androidPushNotificationsService;

	@Autowired
	private AppConfigService appConfigService;

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
	public JsonNode fetchRetailersByKycStatus(KycStatus kycStatus, Integer pageNo, Integer pageSize)
			throws URISyntaxException, ClientProtocolException, IOException {
		List<AccountStatuses> accountStatuses = accountStatusesRepository.findByKycVerified(kycStatus);
		List<PagesDTO> appPagesDTOList = new ArrayList<PagesDTO>();
		for (AccountStatuses accountStatus : accountStatuses) {
			User user = userRepository.findBymsisdnAndUserType(accountStatus.getMsisdn(), UserType.RETAILERS.name());
			if (user != null) {
				List<PagesDTO> appPagesDTO = new ArrayList<PagesDTO>();
				if (appConfigService.getBooleanProperty("IS_TO_FETCH_FROM_KYC", true)) {
					HttpClient httpclient = HttpClients.createDefault();
					HttpGet request = new HttpGet(appConfigService.getProperty("KYC_END_POINT",
							"http://kyc.yabx.co:8080/v1/retailers/profiles"));
					List<NameValuePair> params = new ArrayList<NameValuePair>(3);
					NameValuePair nv1 = new BasicNameValuePair("status", kycStatus.name());
					NameValuePair nv2 = new BasicNameValuePair("secret_key",
							appConfigService.getProperty("RETAILER_PROFILE_KYC_API_SECRET_KEY", "magic@yabx"));
					NameValuePair nv3 = new BasicNameValuePair("page_no", String.valueOf(pageNo));
					NameValuePair nv4 = new BasicNameValuePair("page_size", String.valueOf(pageSize));
					params.add(nv1);
					params.add(nv2);
					params.add(nv3);
					params.add(nv4);
					// Execute and get the response.
					HttpResponse response = null;

					URI uri = new URIBuilder(request.getURI()).addParameters(params).build();
					request.setURI(uri);
					response = httpclient.execute(request);
					LOGGER.info("Response for kycStatus={} is ={}", kycStatus, response);
					if (response.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = response.getEntity();
						String responseString = EntityUtils.toString(entity, "UTF-8");
						// LOGGER.info("Response for kycStatus={} in string is ={}", kycStatus,
						// responseString);
						JsonNode jsonNode = JsonUtilService.deserializeEntity(responseString, JsonNode.class);
						// LOGGER.info("Response for kycStatus={} in jsonNode is ={}", kycStatus,
						// jsonNode);
						// String responseString = EntityUtils.toString(entity, "UTF-8");
						return jsonNode;
					}

				} else {
					List<FieldRemarks> fieldRemarksList = fieldRemarksRepository.findByUserId(user.getId());
					Set<AddressDetails> addressDetailsSet = user.getAddressDetails();
					Set<BankAccountDetails> bankAccountDetailsSet = user.getBankAccountDetails();
					/*
					 * Set<BusinessDetails> businessDetailsSet = user.getBusinessDetails();
					 * Set<AttachmentDetails> attachmentDetailsSet = user.getAttachmentDetails();
					 * Set<LiabilitiesDetails> nominees = user.getLiabilitiesDetails();
					 * Set<IntroducerDetails> introducerDetails = user.getIntroducerDetails();
					 * Set<WorkEducationDetails> workEducationDetailsSet =
					 * user.getWorkEducationDetails(); Set<MonthlyTransactionProfiles>
					 * monthlyTransactionProfilesSet = user.getMonthlyTransactionProfiles();
					 * LoanPurposeDetails loanPurposeDetails = user.getLoanPurposeDetails();
					 */
					Set<BankAccountDetails> nomineeBankAccountDetailsSet = null;
					Set<BankAccountDetails> businessBankAccountDetailsSet = new HashSet<BankAccountDetails>();
					Set<AddressDetails> nomineeAddressDetailsSet = null;
					Set<AddressDetails> businessAddressDetailsSet = new HashSet<AddressDetails>();
					List<UserRelationships> userRelationships = userRelationshipsRepository
							.findByMsisdnAndRelationship(user.getMsisdn(), Relationship.NOMINEE);
					User nominee = userRelationships != null && !userRelationships.isEmpty()
							? userRelationships.get(0).getRelative()
							: null;
					nomineeAddressDetailsSet = nominee != null ? nominee.getAddressDetails() : null;
					nomineeBankAccountDetailsSet = nominee != null ? accountDetailsRepository.findByUser(user) : null;

					if (user.getBusinessDetails() != null) {
						user.getBusinessDetails().forEach(f -> {
							businessAddressDetailsSet.addAll(f.getAddressDetails());
						});
						user.getBusinessDetails().forEach(f -> {
							businessBankAccountDetailsSet.addAll(f.getBankAccountDetails());
						});
					}

					List<Pages> appPages = kycPagesRepository.findByPageType(PageType.RETAILERS);
					if (appPages == null)
						return null;
					for (Pages pages : appPages) {
						appPagesDTO.add(PagesDTOHeper.prepareAppPagesDto(pages, user, nominee, addressDetailsSet,
								nomineeAddressDetailsSet, businessAddressDetailsSet, bankAccountDetailsSet,
								nomineeBankAccountDetailsSet, businessBankAccountDetailsSet, PageType.RETAILERS.name(),
								fieldRemarksList));

					}
					appPagesDTOList.addAll(appPagesDTO);
				}
			}
		}
		return null;

	}

	@Override
	@Transactional
	public AccountStatuses updateKycStatus(String msisdn, String username, KycStatus status) {
		AccountStatuses accountStatuses = accountStatusesRepository.findByMsisdn(msisdn);
		if (accountStatuses != null) {
			accountStatuses.setKycVerified(status);
			accountStatuses.setUpdatedBy(username);
			accountStatuses = accountStatusesRepository.save(accountStatuses);
			if (appConfigService.getBooleanProperty("FCM_NOTIFICATION_ENABLED", false)) {
				androidPushNotificationsService.notifyDSR(msisdn, username, status);
			}
			generateDisclaimerDocs(msisdn);
			return accountStatuses;
		}
		return null;
	}

	@Override
	public HttpResponse updateStatus(String msisdn, String username, KycStatus kycStatus)
			throws URISyntaxException, ClientProtocolException, IOException {

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(appConfigService.getProperty("KYC_UPDATE_STATUS_END_POINT",
				"http://kyc.yabx.co:8080/v1/update/kyc/status"));
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		NameValuePair nv1 = new BasicNameValuePair("status", kycStatus.name());
		NameValuePair nv2 = new BasicNameValuePair("secret_key",
				appConfigService.getProperty("RETAILER_PROFILE_KYC_API_SECRET_KEY", "magic@yabx"));
		NameValuePair nv3 = new BasicNameValuePair("username", username);
		NameValuePair nv4 = new BasicNameValuePair("msisdn", msisdn);
		params.add(nv1);
		params.add(nv2);
		params.add(nv3);
		params.add(nv4);
		// Execute and get the response.
		HttpResponse response = null;
		URI uri = new URIBuilder(request.getURI()).addParameters(params).build();
		request.setURI(uri);
		response = httpclient.execute(request);
		LOGGER.info("Response for updating kycStatus={} is ={}", kycStatus, response);
		return response;

	}

	@Async
	private void generateDisclaimerDocs(String msisdn) {
		User user = userRepository.findBymsisdnAndUserType(msisdn, UserType.RETAILERS.toString());
		List<ProductDocuments> productDocumentLists = productDocumentsRepository.findByProductName(ProductName.KYC);
		for (ProductDocuments productDocuments : productDocumentLists) {
			if ("IGPA_FIXED_AND_FLOATING".equalsIgnoreCase(productDocuments.getDocumentType())) {
				IGPA_FIXED_AND_FLOATING_1.getDocuments(user);
			} else if ("PERFORMANCE_SECURITY_FORMAT".equalsIgnoreCase(productDocuments.getDocumentType())) {
				PERFORMANCE_SECURITY_FORMAT_2.getDocuments(user);
			} else if ("PG_MEMO_ENGINEERS".equalsIgnoreCase(productDocuments.getDocumentType())) {
				PG_MEMO_ENGINEERS_3.getDocuments(user);
			} else if ("DP_NOTE".equalsIgnoreCase(productDocuments.getDocumentType())) {
				DP_NOTE_4.getDocuments(user);
			} else if ("LETTER_OF_CONTINUITY_AND_REVIVAL".equalsIgnoreCase(productDocuments.getDocumentType())) {
				LETTER_OF_CONTINUITY_AND_REVIVAL_5.getDocuments(user);
			} else if ("DISBURSEMENT".equalsIgnoreCase(productDocuments.getDocumentType())) {
				DISBURSEMENT_6.getDocuments(user);
			} else if ("ARRANGEMENT".equalsIgnoreCase(productDocuments.getDocumentType())) {
				ARRANGEMENT_7.getDocuments(user);
			} else if ("GENERAL_LOAN_AGREEMENT".equalsIgnoreCase(productDocuments.getDocumentType())) {
				GENERAL_LOAN_AGREEMENT_8.getDocuments(user);
			} else if ("LETTER_OF_DEBIT_AUTHORITY".equalsIgnoreCase(productDocuments.getDocumentType())) {
				LETTER_OF_DEBIT_AUTHORITY_9.getDocuments(user);
			} else if ("DECLARATION_FORM".equalsIgnoreCase(productDocuments.getDocumentType())) {
				DECLARATION_FORM_10.getDocuments(user);
			} else if ("PERSONAL_GUARANTEE_INDIVIDUAL".equalsIgnoreCase(productDocuments.getDocumentType())) {
				PERSONAL_GUARANTEE_INDIVIDUAL_11.getDocuments(user);
			} else if ("PERSONAL_GUARANTEE_JOINT".equalsIgnoreCase(productDocuments.getDocumentType())) {
				PERSONAL_GUARANTEE_JOINT_12.getDocuments(user);
			} else if ("LETTER_OF_LIEN_AND_SET_OFF_DEPOSIT_ACCOUNT_OR_MARGIN"
					.equalsIgnoreCase(productDocuments.getDocumentType())) {
				LETTER_OF_LIEN_AND_SET_OFF_DEPOSIT_ACCOUNT_OR_MARGIN_13.getDocuments(user);
			} else if ("EFT_AUTHORIZATION_FORM".equalsIgnoreCase(productDocuments.getDocumentType())) {
				EFT_AUTHORIZATION_FORM_14.getDocuments(user);
			} else if ("COUNTER_GUARANTEE".equalsIgnoreCase(productDocuments.getDocumentType())) {
				COUNTER_GUARANTEE_15.getDocuments(user);
			} else if ("SBS_FORMS".equalsIgnoreCase(productDocuments.getDocumentType())) {
				SBS_FORMS_16.getDocuments(user);
			} else if ("FATCA_FORM_INDIVIDUAL_AND_PROPRIETOR".equalsIgnoreCase(productDocuments.getDocumentType())) {
				FATCA_FORM_INDIVIDUAL_AND_PROPRIETOR_17.getDocuments(user);
			} else if ("SIGNATURE_CARD".equalsIgnoreCase(productDocuments.getDocumentType())) {
				SIGNATURE_CARD_18.getDocuments(user);
			} else if ("SME_BUSINESS_SEGMENTATION_FORM_REVISED".equalsIgnoreCase(productDocuments.getDocumentType())) {
				SME_BUSINESS_SEGMENTATION_FORM_REVISED_19.getDocuments(user);
			} else if ("GENERAL_INSTRUCTION".equalsIgnoreCase(productDocuments.getDocumentType())) {
				GENERAL_INSTRUCTION_20.getDocuments(user);
			}
		}

	}

	@Override
	public UserDisclaimerDocumentsDTO getDisclaimerDocuments(String msisdn) {
		User user = userRepository.findBymsisdnAndUserType(msisdn, UserType.RETAILERS.toString());
		if (user != null) {
			return getDisclaimerDocuments(user);
		}
		return null;
	}

	@Override
	public UserDisclaimerDocumentsDTO getDisclaimerDocuments(User user) {
		if (user != null) {
			UserDisclaimerDocumentsDTO disclaimerDocumentsDTO = new UserDisclaimerDocumentsDTO();
			disclaimerDocumentsDTO.setUserId(user.getId());
			disclaimerDocumentsDTO.setMsisdn(user.getMsisdn());
			List<ProductDocumentsDTO> productDocumentsDTOs = getUserDisclaimerDocuments(user, disclaimerDocumentsDTO);
			disclaimerDocumentsDTO.setDisclaimerDocuments(productDocumentsDTOs);
			return disclaimerDocumentsDTO;
		}
		return null;
	}

	private List<ProductDocumentsDTO> getUserDisclaimerDocuments(User user,
			UserDisclaimerDocumentsDTO disclaimerDocumentsDTO) {
		Set<AttachmentDetails> attachmentDetailsSet = user.getAttachmentDetails();
		List<ProductDocuments> productDocuments = productDocumentsRepository.findByProductName(ProductName.KYC);
		List<ProductDocumentsDTO> productDocumentsDTOs = new ArrayList<ProductDocumentsDTO>();
		for (ProductDocuments documents : productDocuments) {
			ProductDocumentsDTO productDocumentsDTO = new ProductDocumentsDTO();
			productDocumentsDTO.setDisplayOrder(documents.getDisplayOrder());
			productDocumentsDTO.setDocumentFor(documents.getDocumentFor());
			productDocumentsDTO.setDocumentName(documents.getDocumentName());
			productDocumentsDTO.setAttachmentType(
					documents.getAttachmentType() != null ? documents.getAttachmentType().toString() : null);
			productDocumentsDTO.setDocumentType(documents.getDocumentType());
			setFileName(user, productDocumentsDTO, disclaimerDocumentsDTO, attachmentDetailsSet, documents);
			productDocumentsDTOs.add(productDocumentsDTO);
		}
		return productDocumentsDTOs;
	}

	private void setFileName(User user, ProductDocumentsDTO productDocumentsDTO,
			UserDisclaimerDocumentsDTO disclaimerDocumentsDTO, Set<AttachmentDetails> attachmentDetailsSet,
			ProductDocuments documents) {
		Optional<AttachmentDetails> optional = attachmentDetailsSet.stream()
				.filter(f -> f.getDocumentType().equalsIgnoreCase(documents.getDocumentType())).findFirst();
		if (optional.isPresent()) {
			AttachmentDetails attachmentDetails = optional.get();
			if (attachmentDetails != null) {
				Set<Attachments> attachments = attachmentDetails.getAttachments();
				Optional<Attachments> optionalAttachments = attachments.stream().findFirst();
				if (optionalAttachments.isPresent()) {
					productDocumentsDTO.setFileName(optionalAttachments.get().getDocumentUrl());
				} else {
					productDocumentsDTO.setFileName(documents.getFileName());
					disclaimerDocumentsDTO.setDisclaimerDocRecieved(false);
				}
			} else {
				productDocumentsDTO.setFileName(documents.getFileName());
				disclaimerDocumentsDTO.setDisclaimerDocRecieved(false);
			}
		} else {
			productDocumentsDTO.setFileName(documents.getFileName());
			disclaimerDocumentsDTO.setDisclaimerDocRecieved(false);
		}
	}

}
