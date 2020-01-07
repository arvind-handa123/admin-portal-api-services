package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.enums.AddressProof;
import co.yabx.admin.portal.app.enums.AddressType;
import co.yabx.admin.portal.app.enums.BankAccountType;
import co.yabx.admin.portal.app.enums.BusinessSector;
import co.yabx.admin.portal.app.enums.BusinessType;
import co.yabx.admin.portal.app.enums.Cities;
import co.yabx.admin.portal.app.enums.Countries;
import co.yabx.admin.portal.app.enums.Currency;
import co.yabx.admin.portal.app.enums.Divisions;
import co.yabx.admin.portal.app.enums.DocumentSide;
import co.yabx.admin.portal.app.enums.DocumentType;
import co.yabx.admin.portal.app.enums.EducationalQualification;
import co.yabx.admin.portal.app.enums.FacilityDetails;
import co.yabx.admin.portal.app.enums.FacilityType;
import co.yabx.admin.portal.app.enums.Gender;
import co.yabx.admin.portal.app.enums.IdentityProof;
import co.yabx.admin.portal.app.enums.LiabilityType;
import co.yabx.admin.portal.app.enums.LicenseType;
import co.yabx.admin.portal.app.enums.MaritalStatuses;
import co.yabx.admin.portal.app.enums.ModeOfOperation;
import co.yabx.admin.portal.app.enums.Nationality;
import co.yabx.admin.portal.app.enums.ResidentStatus;
import co.yabx.admin.portal.app.enums.TypeOfConcern;
import co.yabx.admin.portal.app.kyc.dto.FieldsDTO;
import co.yabx.admin.portal.app.kyc.dto.GroupsDTO;
import co.yabx.admin.portal.app.kyc.dto.SectionsDTO;
import co.yabx.admin.portal.app.kyc.dto.SubFieldsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.Attachments;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.LicenseDetails;
import co.yabx.admin.portal.app.kyc.entities.MonthlyTransactionProfiles;
import co.yabx.admin.portal.app.kyc.entities.Nominees;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.WorkEducationDetails;
import co.yabx.admin.portal.app.kyc.repositories.AddressDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.LiabilitiesDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.LicenseDetailsRepository;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.FieldService;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class FieldServiceImpl implements FieldService {

	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AddressDetailsRepository addressDetailsRepository;

	@Autowired
	private LicenseDetailsRepository licenseDetailsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FieldServiceImpl.class);

	@Override
	public void prepareFields(User retailerOrDsrUser, User nominees, GroupsDTO appPagesSectionGroupsDTO,
			Set<AddressDetails> userAddressDetailsSet, Set<BankAccountDetails> userBankAccountDetailsSet,
			Set<AddressDetails> nomineeAddressDetailsSet, Set<BankAccountDetails> nomineeBankAccountDetailsSet,
			Set<BusinessDetails> businessDetailsSet, Set<AddressDetails> businessAddressDetailsSet,
			Set<BankAccountDetails> businessBankAccountDetailsSet, Set<LiabilitiesDetails> liabilitiesDetailsSet,
			SectionsDTO appPagesSectionsDTO, Set<MonthlyTransactionProfiles> monthlyTransactionProfilesSet,
			Set<WorkEducationDetails> workEducationDetailsSet, Set<IntroducerDetails> introducerDetailsSet,
			Set<AttachmentDetails> attachmentDetailsSet, Set<LicenseDetails> licenseDetailsSet) {

		List<FieldsDTO> appDynamicFieldsDTOList = appPagesSectionGroupsDTO.getFields();
		AddressDetails addressDetails = null;
		BankAccountDetails bankAccountDetails = null;
		if ((appPagesSectionGroupsDTO.getGroupId() == 2
				|| "addresses".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName()))
				&& (appPagesSectionsDTO.getSectionId() == 1 || appPagesSectionsDTO.getSectionId() == 3)) {
			// user address details
			AddressType addressType = getAddressType(appPagesSectionGroupsDTO.getGroupTitle());
			if (addressType != null) {
				addressDetails = addressDetailsRepository.findByUserAndAddressType(retailerOrDsrUser, addressType);
				if (addressDetails == null) {
					addressDetails = new AddressDetails();
					addressDetails.setAddressType(addressType);
				}
				addressDetails = prepareAddress(appDynamicFieldsDTOList, addressDetails);
				if (addressDetails != null) {
					if (userAddressDetailsSet == null) {
						userAddressDetailsSet = new HashSet<AddressDetails>();
					}
					userAddressDetailsSet.add(addressDetails);
				}
			}
			return;
		} else if ((appPagesSectionGroupsDTO.getGroupId() == 2
				|| "addresses".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName()))
				&& appPagesSectionsDTO.getSectionId() == 2)

		{
			// nominee address details
			AddressType addressType = getAddressType(appPagesSectionGroupsDTO.getGroupTitle());
			if (addressType != null) {
				if (nominees != null && nominees.getId() != null)
					addressDetails = addressDetailsRepository.findByUserAndAddressType(nominees, addressType);
				if (addressDetails == null) {
					addressDetails = new AddressDetails();
				}
				addressDetails.setAddressType(addressType);
				addressDetails = prepareAddress(appDynamicFieldsDTOList, addressDetails);
				if (addressDetails != null) {
					if (nomineeAddressDetailsSet == null) {
						nomineeAddressDetailsSet = new HashSet<AddressDetails>();
					}
					nomineeAddressDetailsSet.add(addressDetails);
				}
				LOGGER.info("Nominee={} Address={} for addressType={}", nominees != null ? nominees.getId() : null,
						nomineeAddressDetailsSet, addressType);
			}
			return;
		} else if ((appPagesSectionGroupsDTO.getGroupId() == 2
				|| "addresses".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName()))
				&& appPagesSectionsDTO.getSectionId() == 5) {
			// Business address details
			AddressType addressType = getAddressType(appPagesSectionGroupsDTO.getGroupTitle());
			if (addressType != null) {
				if (businessAddressDetailsSet == null) {
					businessAddressDetailsSet = new HashSet<AddressDetails>();
				} else {
					Optional<BusinessDetails> optional = retailerOrDsrUser.getBusinessDetails().stream().findFirst();
					if (optional.isPresent()) {
						BusinessDetails businessDetails = optional.get();
						if (businessDetails != null) {
							addressDetails = addressDetailsRepository
									.findByBusinessDetailsAndAddressType(businessDetails, addressType);
						}
					}
				}
				if (addressDetails == null) {
					addressDetails = new AddressDetails();
					addressDetails.setAddressType(addressType);
				}
				addressDetails = prepareAddress(appDynamicFieldsDTOList, addressDetails);
				if (addressDetails != null) {
					businessAddressDetailsSet.clear();
					businessAddressDetailsSet.add(addressDetails);
				}
			}
			return;
		} else if ((appPagesSectionGroupsDTO.getGroupId() == 3
				|| "accountInformations".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName()))
				&& (appPagesSectionsDTO.getSectionId() == 1 || appPagesSectionsDTO.getSectionId() == 3)) {
			bankAccountDetails = new BankAccountDetails();
			bankAccountDetails = preparebankAccounts(appDynamicFieldsDTOList, bankAccountDetails);
			if (userBankAccountDetailsSet == null) {
				userBankAccountDetailsSet = new HashSet<BankAccountDetails>();
			}
			userBankAccountDetailsSet.add(bankAccountDetails);
			return;

		} else if ((appPagesSectionGroupsDTO.getGroupId() == 3
				|| "accountInformations".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName()))
				&& appPagesSectionsDTO.getSectionId() == 2) {
			bankAccountDetails = new BankAccountDetails();
			bankAccountDetails = preparebankAccounts(appDynamicFieldsDTOList, bankAccountDetails);
			if (nomineeBankAccountDetailsSet == null) {
				nomineeBankAccountDetailsSet = new HashSet<BankAccountDetails>();
			}
			nomineeBankAccountDetailsSet.add(bankAccountDetails);
			return;

		} else if ((appPagesSectionGroupsDTO.getGroupId() == 3
				|| "accountInformations".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName()))
				&& appPagesSectionsDTO.getSectionId() == 5) {
			bankAccountDetails = new BankAccountDetails();
			bankAccountDetails = preparebankAccounts(appDynamicFieldsDTOList, bankAccountDetails);
			if (businessBankAccountDetailsSet == null) {
				businessBankAccountDetailsSet = new HashSet<BankAccountDetails>();
			}
			businessBankAccountDetailsSet.add(bankAccountDetails);
			return;
		} else if (appPagesSectionGroupsDTO.getGroupId() == appConfigService
				.getLongProperty("GROUP_ID_FOR_LIABILITIES_DETAILS", 4l)
				|| "liabilitiesDetails".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName())) {
			LiabilityType liabilityType = getLiabilitesType(appPagesSectionGroupsDTO.getGroupTitle());
			if (liabilityType != null) {
				LiabilitiesDetails liabilitiesDetails = liabilitiesDetailsRepository
						.findByUserAndLiabilityType(retailerOrDsrUser, liabilityType);
				if (liabilitiesDetails == null) {
					liabilitiesDetails = new LiabilitiesDetails();
					liabilitiesDetails.setLiabilityType(liabilityType);
				}
				liabilitiesDetails = prepareLiabilities(appDynamicFieldsDTOList, liabilitiesDetails);
				if (liabilitiesDetailsSet == null) {
					liabilitiesDetailsSet = new HashSet<LiabilitiesDetails>();
				}
				liabilitiesDetailsSet.add(liabilitiesDetails);
			}
			return;
		} else if (appPagesSectionGroupsDTO.getGroupId() == appConfigService
				.getLongProperty("GROUP_ID_FOR_BUSINESS_DETAILS", 5l)
				|| "businessDetails".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName())) {
			BusinessDetails businessDetails = null;
			if (businessDetailsSet == null || businessDetailsSet.isEmpty()) {
				businessDetails = new BusinessDetails();
			} else {
				businessDetails = getBusinessDetails(businessDetailsSet);
			}
			businessDetails = prepareBusinessInformation(appDynamicFieldsDTOList, businessDetails);
			if (businessDetails != null) {
				if (businessDetailsSet == null) {
					businessDetailsSet = new HashSet<BusinessDetails>();
				}
				businessDetailsSet.add(businessDetails);
			}
			return;
		} else if (appPagesSectionGroupsDTO.getGroupId() == appConfigService
				.getLongProperty("GROUP_ID_FOR_BUSINESS_LICENSE_DETAILS", 6l)
				|| "licenseDetails".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName())) {
			LicenseType licenseType = getLicenseType(appPagesSectionGroupsDTO.getGroupTitle());
			if (licenseType != null) {
				LicenseDetails licenseDetails = null;
				BusinessDetails businessDetails = getBusinessDetails(retailerOrDsrUser.getBusinessDetails());
				if (businessDetails != null && businessDetails.getId() != null)
					licenseDetails = licenseDetailsRepository.findByBusinessDetailsAndLicenseType(businessDetails,
							licenseType);
				if (licenseDetails == null) {
					licenseDetails = new LicenseDetails();
				}
				licenseDetails.setLicenseType(licenseType);
				licenseDetails = prepareLicenseDetails(appDynamicFieldsDTOList, licenseDetails);
				licenseDetailsSet.add(licenseDetails);
				LOGGER.info("License details={} is being added for licenseType={}", licenseDetailsSet, licenseType);
				// businessDetails.setLicenseDetails(licenseDetailsSet);
			}
			return;
		} else if (appPagesSectionGroupsDTO.getGroupId() == appConfigService
				.getLongProperty("GROUP_ID_FOR_MONTHLY_TRANSACTION_PROFILE", 7l)
				|| "monthlyTxnProfile".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName()))

		{
			MonthlyTransactionProfiles monthlyTransactionProfiles = null;
			monthlyTransactionProfiles = prepareMonthlyTxnProfiles(appDynamicFieldsDTOList, monthlyTransactionProfiles);
			if (monthlyTransactionProfiles != null) {
				if (monthlyTransactionProfilesSet == null) {
					monthlyTransactionProfilesSet = new HashSet<MonthlyTransactionProfiles>();
					monthlyTransactionProfilesSet.add(monthlyTransactionProfiles);
				} else {
					monthlyTransactionProfilesSet.add(monthlyTransactionProfiles);
				}
			}
			return;
		} else if (appPagesSectionGroupsDTO.getGroupId() == appConfigService
				.getLongProperty("GROUP_ID_FOR_WORK_EDUCATION_DETAILS", 8l)
				|| "WORK_EDUCATION".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName())) {
			WorkEducationDetails workEducationDetails = null;
			workEducationDetails = prepareWorkEducationDetails(appDynamicFieldsDTOList, workEducationDetails);
			if (workEducationDetails != null) {
				if (workEducationDetailsSet == null) {
					workEducationDetailsSet = new HashSet<WorkEducationDetails>();
					workEducationDetailsSet.add(workEducationDetails);
				} else {
					workEducationDetailsSet.add(workEducationDetails);
				}
			}
			return;
		} else if (appPagesSectionGroupsDTO.getGroupId() == appConfigService
				.getLongProperty("GROUP_ID_FOR_INTRODUCER_DETAILS", 9l)
				|| "introducerDetails".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName())) {
			IntroducerDetails introducerDetails = null;
			introducerDetails = prepareIntroducerDetails(appDynamicFieldsDTOList, introducerDetails);
			if (introducerDetails != null) {
				if (introducerDetailsSet == null) {
					introducerDetailsSet = new HashSet<IntroducerDetails>();
					introducerDetailsSet.add(introducerDetails);
				} else {
					introducerDetailsSet.add(introducerDetails);
				}
			}
			return;
		} else if (appPagesSectionGroupsDTO.getGroupId() == appConfigService.getLongProperty("GROUP_ID_FOR_ATTACHMENT",
				10l) || "documentDetails".equalsIgnoreCase(appPagesSectionGroupsDTO.getGroupName())) {
			prepareAttachmentDetails(appDynamicFieldsDTOList, attachmentDetailsSet);
			return;

		}
		for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
			if (appPagesSectionGroupsDTO.getGroupId() == 1
					&& (appPagesSectionsDTO.getSectionId() == 1 || appPagesSectionsDTO.getSectionId() == 3)) {
				// User personal Info
				retailerOrDsrUser = prepareProfileInformation(appDynamicFieldsDTO, retailerOrDsrUser);
			} else if (appPagesSectionGroupsDTO.getGroupId() == 1 && appPagesSectionsDTO.getSectionId() == 2) {
				// nominee
				if (nominees == null)
					nominees = new Nominees();
				nominees = prepareProfileInformation(appDynamicFieldsDTO, nominees);
			}

		}

	}

	private BusinessDetails getBusinessDetails(Set<BusinessDetails> businessDetailsSet) {
		Optional<BusinessDetails> businessDetailsOptional = businessDetailsSet.stream().findFirst();
		if (!businessDetailsOptional.isPresent())
			return new BusinessDetails();
		else
			return businessDetailsOptional.get();
	}

	private LiabilityType getLiabilitesType(String groupTitle) {
		if ("Personal Liabilities".equalsIgnoreCase(groupTitle)) {
			return LiabilityType.PERSONAL;
		} else if ("Business Liabilities".equalsIgnoreCase(groupTitle)) {
			return LiabilityType.BUSINESS;
		}
		return null;
	}

	private AddressType getAddressType(String groupTitle) {
		if (groupTitle != null && !groupTitle.isEmpty()) {
			if (groupTitle.equalsIgnoreCase("Permanent Address"))
				return AddressType.PERMANNET;
			else if (groupTitle.equalsIgnoreCase("Present Address"))
				return AddressType.PRESENT;
			else if (groupTitle.equalsIgnoreCase("Registered Address"))
				return AddressType.BUSINESS_REGISTERED_ADDRESS;
			else if (groupTitle.equalsIgnoreCase("Office Address"))
				return AddressType.BUSINESS_OFFICE_ADDRESS;
			else if (groupTitle.equalsIgnoreCase("Factory Address"))
				return AddressType.BUSINESS_FACTORY_ADDRESS;
			else if (groupTitle.equalsIgnoreCase("Other Address"))
				return AddressType.BUSINESS_OTHER_ADDRESS;
		}
		return null;
	}

	private static LicenseType getLicenseType(String groupTitle) {
		if (groupTitle != null && !groupTitle.isEmpty()) {
			if ("Trade License".equalsIgnoreCase(groupTitle))
				return LicenseType.TRADE;
			else
				return LicenseType.OTHER;

		}
		return null;

	}

	/**
	 * 
	 * @param appDynamicFieldsDTOList
	 * @param attachmentDetailsSet
	 * @return
	 */
	private Set<AttachmentDetails> prepareAttachmentDetails(List<FieldsDTO> appDynamicFieldsDTOList,
			Set<AttachmentDetails> attachmentDetailsSet) {
		if (appDynamicFieldsDTOList != null && !appDynamicFieldsDTOList.isEmpty()) {
			if (attachmentDetailsSet == null)
				attachmentDetailsSet = new HashSet<AttachmentDetails>();
			else
				attachmentDetailsSet.clear();
			for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
				AttachmentDetails attachmentDetails = new AttachmentDetails();
				if (appDynamicFieldsDTO.getFieldId().equals("idProof")) {
					String idProof = appDynamicFieldsDTO.getResponse();
					try {
						IdentityProof identityProof = idProof != null && !idProof.isEmpty()
								? IdentityProof.valueOf(idProof)
								: null;
						addBothSideAttachmentDetails(identityProof != null ? identityProof.name() : null,
								appDynamicFieldsDTO, attachmentDetails, attachmentDetailsSet);
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("Exception raised while perapring Identity proof={}, error={}", idProof,
								e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("addressProof")) {

					String adProof = appDynamicFieldsDTO.getResponse();
					try {
						AddressProof addressProof = adProof != null && !adProof.isEmpty()
								? AddressProof.valueOf(adProof)
								: null;
						addBothSideAttachmentDetails(addressProof != null ? addressProof.name() : null,
								appDynamicFieldsDTO, attachmentDetails, attachmentDetailsSet);
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("Exception raised while perapring address proof={}, error={}", adProof,
								e.getMessage());
					}

				} else if (appDynamicFieldsDTO.getFieldId().equals("tinCertificates")) {
					try {
						DocumentType documentType = DocumentType.TIN_CERTIFICATE;
						addSingleSideAttachments(documentType, attachmentDetails, appDynamicFieldsDTO);
					} catch (Exception e) {
						LOGGER.error("Exception while preparing tinCertificates ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("tradeLicense")) {
					try {
						DocumentType documentType = DocumentType.TRADE_LICENSE;
						addSingleSideAttachments(documentType, attachmentDetails, appDynamicFieldsDTO);
					} catch (Exception e) {
						LOGGER.error("Exception while preparing tradeLicense ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("nomineePhoto")) {
					try {
						DocumentType documentType = DocumentType.NOMINEE_PHOTO;
						addSingleSideAttachments(documentType, attachmentDetails, appDynamicFieldsDTO);
					} catch (Exception e) {
						LOGGER.error("Exception while preparing nomineePhoto ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("signature")) {
					try {
						DocumentType documentType = DocumentType.SIGNATURE;
						addSingleSideAttachments(documentType, attachmentDetails, appDynamicFieldsDTO);
					} catch (Exception e) {
						LOGGER.error("Exception while preparing signature ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				}
				attachmentDetailsSet.add(attachmentDetails);
			}
		}
		return attachmentDetailsSet;

	}

	private void addSingleSideAttachments(DocumentType documentType, AttachmentDetails attachmentDetails,
			FieldsDTO appDynamicFieldsDTO) {
		Set<Attachments> attachmentsSet = new HashSet<Attachments>();
		addAttachement(null, appDynamicFieldsDTO.getResponse(), attachmentsSet);
		attachmentDetails.setDocumentType(documentType != null ? documentType.toString() : null);
		attachmentDetails.setAttachments(attachmentsSet);
	}

	private void addBothSideAttachmentDetails(String proof, FieldsDTO appDynamicFieldsDTO,
			AttachmentDetails attachmentDetails, Set<AttachmentDetails> attachmentDetailsSet) {
		DocumentType documentType = proof != null ? DocumentType.valueOf(proof) : null;
		if (documentType != null) {
			List<SubFieldsDTO> subFieldsDTOList = appDynamicFieldsDTO.getSubFields();
			attachmentDetails.setAttachments(getAttachmentsSet(subFieldsDTOList));
			attachmentDetails.setDocumentType(documentType != null ? documentType.toString() : null);
			attachmentDetailsSet.add(attachmentDetails);
		}
	}

	private Set<Attachments> getAttachmentsSet(List<SubFieldsDTO> subFieldsDTOList) {
		Set<Attachments> attachmentsSet = new HashSet<Attachments>();
		for (SubFieldsDTO subFieldsDTO : subFieldsDTOList) {
			FieldsDTO fieldsDTO = subFieldsDTO.getFields();
			try {
				DocumentSide documentSide = fieldsDTO.getFieldId() != null && !fieldsDTO.getFieldId().isEmpty()
						? DocumentSide.valueOf(fieldsDTO.getFieldId().toUpperCase())
						: null;
				String image = fieldsDTO.getResponse() != null ? fieldsDTO.getResponse() : null;
				addAttachement(documentSide, image, attachmentsSet);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception while retrieving fieldId={} for attcahment, error={}", fieldsDTO.getFieldId(),
						e.getMessage());
			}
		}
		return attachmentsSet;
	}

	private void addAttachement(DocumentSide documentSide, String url, Set<Attachments> attachmentsSet) {
		Attachments attachments = new Attachments();
		attachments.setDocumentSide(documentSide);
		attachments.setDocumentUrl(url);
		attachmentsSet.add(attachments);
	}

	private IntroducerDetails prepareIntroducerDetails(List<FieldsDTO> appDynamicFieldsDTOList,
			IntroducerDetails introducerDetails) {
		if (appDynamicFieldsDTOList != null && !appDynamicFieldsDTOList.isEmpty()) {
			introducerDetails = new IntroducerDetails();
			for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
				if (appDynamicFieldsDTO.getFieldId().equals("accountNumber")) {
					try {
						Long accountNumber = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Long.valueOf(appDynamicFieldsDTO.getResponse())
								: 0;
						introducerDetails.setAccountNumber(accountNumber);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating accountNumber ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("name")) {
					introducerDetails.setName(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("isSignatureVerified")) {
					try {
						introducerDetails.setSignatureVerified(appDynamicFieldsDTO.getResponse() != null
								? Boolean.valueOf(appDynamicFieldsDTO.getResponse())
								: false);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating isSignatureVerified ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("signature")) {

				} else if (appDynamicFieldsDTO.getFieldId().equals("relationship")) {
					introducerDetails.setRelationship(appDynamicFieldsDTO.getResponse());
				}
			}
		}
		return introducerDetails;

	}

	private WorkEducationDetails prepareWorkEducationDetails(List<FieldsDTO> appDynamicFieldsDTOList,
			WorkEducationDetails workEducationDetails) {
		if (appDynamicFieldsDTOList != null && !appDynamicFieldsDTOList.isEmpty()) {
			workEducationDetails = new WorkEducationDetails();
			for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
				if (appDynamicFieldsDTO.getFieldId().equals("experience")) {
					try {
						Integer experience = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Integer.valueOf(appDynamicFieldsDTO.getResponse())
								: 0;
						workEducationDetails.setExperience(experience);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating experience ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("occupation")) {
					workEducationDetails.setOccupation(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("designation")) {
					workEducationDetails.setDesignation(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("employer")) {
					workEducationDetails.setEmployer(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("educationalQualification")) {
					try {
						EducationalQualification educationalQualification = EducationalQualification
								.findByValue(appDynamicFieldsDTO.getResponse());
						workEducationDetails.setEducationalQualification(educationalQualification);

					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("Exception raised while preapring educationalQualification={},error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				}
			}
		}
		return workEducationDetails;

	}

	private MonthlyTransactionProfiles prepareMonthlyTxnProfiles(List<FieldsDTO> appDynamicFieldsDTOList,
			MonthlyTransactionProfiles monthlyTransactionProfiles) {
		if (appDynamicFieldsDTOList != null) {
			monthlyTransactionProfiles = new MonthlyTransactionProfiles();
			for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
				if (appDynamicFieldsDTO.getFieldId().equals("monthlyTurnOver")) {
					try {
						double monthlyTurnOver = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						monthlyTransactionProfiles.setMonthlyTurnOver(monthlyTurnOver);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating monthlyTurnOver ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("deposits")) {
					try {
						double deposits = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						monthlyTransactionProfiles.setDeposits(deposits);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating deposits ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("withdrawls")) {
					try {
						double withdrawls = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						monthlyTransactionProfiles.setWithdrawls(withdrawls);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating withdrawls ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("initialDeposit")) {
					try {
						double initialDeposit = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						monthlyTransactionProfiles.setInitialDeposit(initialDeposit);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating initialDeposit ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				}
			}
		}
		return monthlyTransactionProfiles;

	}

	private LiabilitiesDetails prepareLiabilities(List<FieldsDTO> appDynamicFieldsDTOList,
			LiabilitiesDetails liabilitiesDetails) {
		if (appDynamicFieldsDTOList != null && !appDynamicFieldsDTOList.isEmpty()) {
			for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
				if (appDynamicFieldsDTO.getFieldId().equals("loanAmount")) {
					try {
						double loanAmount = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						liabilitiesDetails.setLoanAmount(loanAmount);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating loanAmount ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("bankOrNbfiName")) {
					liabilitiesDetails.setBankOrNbfiName(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("liabilityFromOtherOrganization")) {
					liabilitiesDetails.setLiabilityFromOtherOrganization(appDynamicFieldsDTO.getResponse());

				} else if (appDynamicFieldsDTO.getFieldId().equals("loanAmountFromOtherOrganization")) {
					try {
						double loanAmount = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						liabilitiesDetails.setLoanAmountFromOtherOrganization(loanAmount);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating loanAmountFromOtherOrganization ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				}
			}
		}
		return liabilitiesDetails;
	}

	private BankAccountDetails preparebankAccounts(List<FieldsDTO> appDynamicFieldsDTOList,
			BankAccountDetails bankAccountDetails) {
		for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
			if (appDynamicFieldsDTO != null) {
				if (appDynamicFieldsDTO.getFieldId().equals("accountTitle")) {
					bankAccountDetails.setAccountTitle(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("accountPurpose")) {
					bankAccountDetails.setAccountPurpose(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("typeOfConcern")) {
					try {
						TypeOfConcern typeOfConcern = TypeOfConcern.valueOf(appDynamicFieldsDTO.getResponse());
						bankAccountDetails.setTypeOfConcern(typeOfConcern);
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("Exception raised while preapring typeOfConcern={},error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("bankName")) {
					bankAccountDetails.setBankName(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("accountNumber")) {
					try {
						Long accountNumber = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Long.valueOf(appDynamicFieldsDTO.getResponse())
								: null;
						bankAccountDetails.setAccountNumber(accountNumber);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating accountNumber ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("branch")) {
					bankAccountDetails.setBranch(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("modeOfOperation")) {
					try {
						ModeOfOperation mop = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? ModeOfOperation.valueOf(appDynamicFieldsDTO.getResponse())
								: null;
						bankAccountDetails.setModeOfOperation(mop);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating modeOfOperation ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("currency")) {
					try {
						Currency currency = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Currency.valueOf(appDynamicFieldsDTO.getResponse())
								: null;
						bankAccountDetails.setCurrency(currency);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating currency ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("bankAccountType")) {
					try {
						BankAccountType bankAccountType = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? BankAccountType.valueOf(appDynamicFieldsDTO.getResponse())
								: null;
						bankAccountDetails.setBankAccountType(bankAccountType);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating account_type ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				}
			}

		}
		return bankAccountDetails;
	}

	private AddressDetails prepareAddress(List<FieldsDTO> appDynamicFieldsDTOList, AddressDetails addressDetails) {
		for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
			if (appDynamicFieldsDTO != null) {
				if (appDynamicFieldsDTO.getFieldId().equals("address")) {
					addressDetails.setAddress(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("upazilaThana")) {
					addressDetails.setUpazilaThana(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("cityDsitrict")) {
					try {
						Cities citi = appDynamicFieldsDTO.getResponse() != null
								&& !appDynamicFieldsDTO.getResponse().isEmpty()
										? Cities.valueOf(appDynamicFieldsDTO.getResponse())
										: null;
						addressDetails.setCityDsitrict(citi);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating cityDsitrict ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("division")) {
					try {
						Divisions Country = appDynamicFieldsDTO.getResponse() != null
								&& !appDynamicFieldsDTO.getResponse().isEmpty()
										? Divisions.valueOf(appDynamicFieldsDTO.getResponse())
										: null;
						addressDetails.setDivision(Country);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating division ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("country")) {
					try {
						Countries Country = appDynamicFieldsDTO.getResponse() != null
								&& !appDynamicFieldsDTO.getResponse().isEmpty()
										? Countries.valueOf(appDynamicFieldsDTO.getResponse())
										: null;
						addressDetails.setCountry(Country);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating country ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("mobileNumber")) {
					addressDetails.setMobileNumber(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("phoneNumber")) {
					addressDetails.setPhoneNumber(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("email")) {
					addressDetails.setEmail(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("territory")) {
					addressDetails.setTerritory(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("zipCode")) {
					try {
						Integer zipCode = appDynamicFieldsDTO.getResponse() != null
								&& !appDynamicFieldsDTO.getResponse().isEmpty()
										? Integer.valueOf(appDynamicFieldsDTO.getResponse())
										: null;
						addressDetails.setZipCode(zipCode);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating zipcode ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("landmark")) {
					try {
						addressDetails.setLandmark(appDynamicFieldsDTO.getResponse());
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating landmark ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				}

			}

		}
		return addressDetails;
	}

	private BusinessDetails prepareBusinessInformation(List<FieldsDTO> appDynamicFieldsDTOList,
			BusinessDetails businessDetails) {
		if (appDynamicFieldsDTOList != null && !appDynamicFieldsDTOList.isEmpty()) {
			for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
				if (appDynamicFieldsDTO.getFieldId().equals("businessPhone")) {
					businessDetails.setBusinessPhone(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("businessName")) {
					businessDetails.setBusinessName(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("directorOrPartnerName")) {
					businessDetails.setDirectorOrPartnerName(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("facilityDetails")) {
					try {
						FacilityDetails facilityDetails = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? FacilityDetails.valueOf(appDynamicFieldsDTO.getResponse())
								: null;
						businessDetails.setFacilityDetails(facilityDetails);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating facilityDetails ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("facilityType")) {
					try {
						FacilityType facilityType = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? FacilityType.findByValue(appDynamicFieldsDTO.getResponse())
								: null;
						businessDetails.setFacilityType(facilityType);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating facilityType ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("fixedAssetPurchase")) {
					businessDetails.setFixedAssetPurchase(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("fixedAssetName")) {
					businessDetails.setFixedAssetName(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("price")) {
					try {
						double price = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setPrice(price);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating price ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("origin")) {
					businessDetails.setOrigin(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("proposedCollateral")) {
					businessDetails.setProposedCollateral(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("businessType")) {
					try {
						BusinessType businessType = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? BusinessType.valueOf(appDynamicFieldsDTO.getResponse())
								: null;
						businessDetails.setBusinessType(businessType);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating businessType ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("sector")) {
					try {
						BusinessSector businessSector = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? BusinessSector.valueOf(appDynamicFieldsDTO.getResponse())
								: null;
						businessDetails.setSector(businessSector);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating businessSector ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("detailOfBusness")) {
					businessDetails.setDetailOfBusness(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("initialCapital")) {
					try {
						double initialCapital = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setInitialCapital(initialCapital);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating initialCapital ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("fundSource")) {
					businessDetails.setFundSource(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("vatRegistrationNumber")) {
					businessDetails.setVatRegistrationNumber(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("businessStartDate")) {
					businessDetails.setBusinessStartDate(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("businessTin")) {
					businessDetails.setBusinessTin(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("annualSales")) {
					try {
						double annualSales = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setAnnualSales(annualSales);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating annualSales ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("annualGrossProfit")) {
					try {
						double annualGrossProfit = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setAnnualGrossProfit(annualGrossProfit);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating annualGrossProfit ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("annualExpenses")) {
					try {
						double annualExpenses = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setAnnualExpenses(annualExpenses);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating annualExpenses ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("valueOfFixedAssets")) {
					try {
						double valueOfFixedAssets = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setValueOfFixedAssets(valueOfFixedAssets);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating valueOfFixedAssets ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("numberOfEmployees")) {
					try {
						int numberOfEmployees = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Integer.valueOf(appDynamicFieldsDTO.getResponse())
								: 0;
						businessDetails.setNumberOfEmployees(numberOfEmployees);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating numberOfEmployees ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("stockValue")) {
					try {
						double stockValue = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setStockValue(stockValue);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating stockValue ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("deposits")) {
					try {
						double deposits = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setDeposits(deposits);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating deposits ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("withdrawls")) {
					try {
						double withdrawls = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setWithdrawls(withdrawls);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating withdrawls ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				} else if (appDynamicFieldsDTO.getFieldId().equals("initialDeposit")) {
					try {
						double initialDeposit = neitherBlankNorNull(appDynamicFieldsDTO.getResponse())
								? Double.valueOf(appDynamicFieldsDTO.getResponse())
								: 0.0;
						businessDetails.setInitialDeposit(initialDeposit);
					} catch (Exception e) {
						LOGGER.error("Exception while evaluating initialDeposit ={}, error={}",
								appDynamicFieldsDTO.getResponse(), e.getMessage());
					}
				}
			}
		}
		return businessDetails;
	}

	private LicenseDetails prepareLicenseDetails(List<FieldsDTO> appDynamicFieldsDTOList,
			LicenseDetails licenseDetails) {
		if (appDynamicFieldsDTOList != null && !appDynamicFieldsDTOList.isEmpty()) {
			if (licenseDetails == null)
				licenseDetails = new LicenseDetails();
			for (FieldsDTO appDynamicFieldsDTO : appDynamicFieldsDTOList) {
				if (appDynamicFieldsDTO.getFieldId().equals("licenseNumber")) {
					licenseDetails.setLicenseNumber(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("licenseExpiryDate")) {
					licenseDetails.setLicenseExpiryDate(appDynamicFieldsDTO.getResponse());
				} else if (appDynamicFieldsDTO.getFieldId().equals("licenseIssuingAuthority")) {
					licenseDetails.setLicenseIssuingAuthority(appDynamicFieldsDTO.getResponse());
				}
			}
		}
		return licenseDetails;

	}

	private boolean neitherBlankNorNull(String response) {
		return response != null && !response.isEmpty();
	}

	private User prepareProfileInformation(FieldsDTO appDynamicFieldsDTO, User user) {
		if (appDynamicFieldsDTO != null && user != null) {
			if (appDynamicFieldsDTO.getFieldId().equals("firstName")) {
				user.setFirstName(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("lastName")) {
				user.setLastName(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("middleName")) {
				user.setMiddleName(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("dob")) {
				user.setDob(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("pob")) {
				try {
					Cities city = appDynamicFieldsDTO.getResponse() != null
							? Cities.valueOf(appDynamicFieldsDTO.getResponse())
							: null;
					user.setPob(city);
				} catch (Exception e) {
					LOGGER.error("Exception while evaluating city ={}, error={}", appDynamicFieldsDTO.getResponse(),
							e.getMessage());
				}
			} else if (appDynamicFieldsDTO.getFieldId().equals("fathersName")) {
				user.setFathersName(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("mothersName")) {
				user.setMothersName(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("maritalStatus")) {
				try {
					MaritalStatuses maritalStatuses = appDynamicFieldsDTO.getResponse() != null
							? MaritalStatuses.valueOf(appDynamicFieldsDTO.getResponse())
							: null;
					user.setMaritalStatus(maritalStatuses);
				} catch (Exception e) {
					LOGGER.error("Exception while evaluating marital statuses ={}, error={}",
							appDynamicFieldsDTO.getResponse(), e.getMessage());
				}
			} else if (appDynamicFieldsDTO.getFieldId().equals("spouseName")) {
				user.setSpouseName(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("numberOfDependents")) {
				try {
					Integer nod = appDynamicFieldsDTO.getResponse() != null
							&& !appDynamicFieldsDTO.getResponse().isEmpty()
									? Integer.valueOf(appDynamicFieldsDTO.getResponse())
									: 0;
					user.setNumberOfDependents(nod);
				} catch (Exception e) {
					LOGGER.error("Exception while evaluating number of dependents ={}, error={}",
							appDynamicFieldsDTO.getResponse(), e.getMessage());
				}
			} else if (appDynamicFieldsDTO.getFieldId().equals("alternateMobileNumber")) {
				user.setAlternateMobileNumber(appDynamicFieldsDTO.getResponse());
			}
			// userDto.setRetailerPhoto("");
			else if (appDynamicFieldsDTO.getFieldId().equals("birthRegistrationNumber")) {
				user.setBirthRegistrationNumber(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("drivingLicenseNumber")) {
				user.setDrivingLicenseNumber(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("email")) {
				user.setEmail(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("gender")) {
				try {
					Gender gender = appDynamicFieldsDTO.getResponse() != null
							&& !appDynamicFieldsDTO.getResponse().isEmpty()
									? Gender.valueOf(appDynamicFieldsDTO.getResponse())
									: null;
					user.setGender(gender);
				} catch (Exception e) {
					LOGGER.error("Exception while evaluating gender ={}, error={}", appDynamicFieldsDTO.getResponse(),
							e.getMessage());
				}
			} else if (appDynamicFieldsDTO.getFieldId().equals("msisdn")) {
				user.setMsisdn(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("sisterConcernedOrAllied")) {
				user.setSisterConcernedOrAllied(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("taxIdentificationNumber")) {
				user.setTaxIdentificationNumber(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("residentialStatus")) {
				try {
					ResidentStatus residentialStatus = appDynamicFieldsDTO.getResponse() != null
							&& !appDynamicFieldsDTO.getResponse().isEmpty()
									? ResidentStatus.valueOf(appDynamicFieldsDTO.getResponse())
									: null;
					user.setResidentialStatus(residentialStatus);
				} catch (Exception e) {
					LOGGER.error("Exception while evaluating residential status ={}, error={}",
							appDynamicFieldsDTO.getResponse(), e.getMessage());
				}
			} else if (appDynamicFieldsDTO.getFieldId().equals("passportNumber")) {
				user.setPassportNumber(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("passportExpiryDate")) {
				user.setPassportExpiryDate(appDynamicFieldsDTO.getResponse());
			} else if (appDynamicFieldsDTO.getFieldId().equals("nationality")) {
				try {
					Nationality nationality = appDynamicFieldsDTO.getResponse() != null
							&& !appDynamicFieldsDTO.getResponse().isEmpty()
									? Nationality.valueOf(appDynamicFieldsDTO.getResponse())
									: null;
					user.setNationality(nationality);
				} catch (Exception e) {
					LOGGER.error("Exception while evaluating nationality ={}, error={}",
							appDynamicFieldsDTO.getResponse(), e.getMessage());
				}
			} else if (appDynamicFieldsDTO.getFieldId().equals("nationalIdNumber")) {
				user.setNationalIdNumber(appDynamicFieldsDTO.getResponse());
			}

		}
		return user;
	}
}
