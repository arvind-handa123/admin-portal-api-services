package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.enums.AddressType;
import co.yabx.admin.portal.app.enums.BankAccountType;
import co.yabx.admin.portal.app.enums.BusinessSector;
import co.yabx.admin.portal.app.enums.BusinessType;
import co.yabx.admin.portal.app.enums.Cities;
import co.yabx.admin.portal.app.enums.Countries;
import co.yabx.admin.portal.app.enums.Currency;
import co.yabx.admin.portal.app.enums.Divisions;
import co.yabx.admin.portal.app.enums.EducationalQualification;
import co.yabx.admin.portal.app.enums.FacilityDetails;
import co.yabx.admin.portal.app.enums.FacilityType;
import co.yabx.admin.portal.app.enums.Gender;
import co.yabx.admin.portal.app.enums.LiabilityType;
import co.yabx.admin.portal.app.enums.LicenseType;
import co.yabx.admin.portal.app.enums.MaritalStatuses;
import co.yabx.admin.portal.app.enums.ModeOfOperation;
import co.yabx.admin.portal.app.enums.Nationality;
import co.yabx.admin.portal.app.enums.ResidentStatus;
import co.yabx.admin.portal.app.enums.TypeOfConcern;
import co.yabx.admin.portal.app.kyc.dto.RemarksDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.FieldRemarks;
import co.yabx.admin.portal.app.kyc.entities.Fields;
import co.yabx.admin.portal.app.kyc.entities.Groups;
import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.LicenseDetails;
import co.yabx.admin.portal.app.kyc.entities.MonthlyTransactionProfiles;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.WorkEducationDetails;
import co.yabx.admin.portal.app.kyc.repositories.AddressDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.BankAccountDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.BusinessDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.FieldRemarksRepository;
import co.yabx.admin.portal.app.kyc.repositories.FieldsRepository;
import co.yabx.admin.portal.app.kyc.repositories.IntroducerDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.LiabilitiesDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.LicenseDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.MonthlyTransactionProfilesRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.repositories.WorkEducationDetailsRepository;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class FieldRemarkServiceImpl implements FieldRemarkService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FieldRemarksRepository fieldRemarksRepository;

	@Autowired
	private AddressDetailsRepository addressDetailsRepository;

	@Autowired
	private BankAccountDetailsRepository bankAccountDetailsRepository;

	@Autowired
	private BusinessDetailsRepository businessDetailsRepository;

	@Autowired
	private LicenseDetailsRepository licenseDetailsRepository;

	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	private WorkEducationDetailsRepository workEducationDetailsRepository;

	@Autowired
	private IntroducerDetailsRepository introducerDetailsRepository;

	@Autowired
	private MonthlyTransactionProfilesRepository monthlyTransactionProfilesRepository;

	@Autowired
	private FieldsRepository fieldsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FieldRemarkServiceImpl.class);

	@Override
	public boolean updateRemark(Long user_id, String remarkBy, List<RemarksDTO> remarksDTOList) {
		Optional<User> userOptional = userRepository.findById(user_id);
		if (userOptional.isPresent()) {

			User user = userOptional.get();
			Set<AddressDetails> businessAddressDetailsSet = null;
			BusinessDetails businessDetails = user.getBusinessDetails() != null
					&& user.getBusinessDetails().stream().findFirst().isPresent()
							? user.getBusinessDetails().stream().findFirst().get()
							: null;
			if (businessDetails != null) {
				businessAddressDetailsSet = businessDetails.getAddressDetails();
			}
			BankAccountDetails bankAccountDetails = user.getBankAccountDetails() != null
					&& user.getBankAccountDetails().stream().findFirst().isPresent()
							? user.getBankAccountDetails().stream().findFirst().get()
							: null;
			LiabilitiesDetails liabilitiesDetails = null;
			AddressDetails addressDetails = null;
			LicenseDetails licenseDetails = null;
			MonthlyTransactionProfiles monthlyTransactionProfiles = user.getMonthlyTransactionProfiles() != null
					&& user.getMonthlyTransactionProfiles().stream().findFirst().isPresent()
							? user.getMonthlyTransactionProfiles().stream().findFirst().get()
							: null;
			WorkEducationDetails workEducationDetails = user.getWorkEducationDetails() != null
					&& user.getWorkEducationDetails().stream().findFirst().isPresent()
							? user.getWorkEducationDetails().stream().findFirst().get()
							: null;
			IntroducerDetails introducerDetails = user.getIntroducerDetails() != null
					&& user.getIntroducerDetails().stream().findFirst().isPresent()
							? user.getIntroducerDetails().stream().findFirst().get()
							: null;
			List<FieldRemarks> fieldRemarksList = fieldRemarksRepository.findByUserId(user_id);
			Groups groups = null;
			Fields fields = null;
			Set<co.yabx.admin.portal.app.kyc.entities.filter.SubGroups> subGroupsSet = null;
			co.yabx.admin.portal.app.kyc.entities.filter.SubGroups subGroups = null;
			for (RemarksDTO remarksDTO : remarksDTOList) {
				if (remarksDTO.getRemark() != null && !remarksDTO.getRemark().isEmpty()) {
					try {
						persistRemarks(remarksDTO, user_id, fieldRemarksList, remarkBy);
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("Exception raised while pushing remark for user={},error={}", user_id,
								e.getMessage());
					}
				}
				if (remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()) {
					if (groups == null || !groups.getGroupId().equals(Long.valueOf(remarksDTO.getGroupId()))
							|| (subGroups != null && subGroups.getId().equals(Long.valueOf(remarksDTO.getGroupId()))))
						if (fields == null || !fields.getFieldId().equalsIgnoreCase(remarksDTO.getFieldId())) {
							fields = fieldsRepository.findByFieldId(remarksDTO.getFieldId()).get(0);
							if (fields != null) {
								subGroupsSet = fields.getSubGroups();
								subGroups = getSubGroup(subGroupsSet, remarksDTO);
								groups = fields.getGroups();
							}
						}
					if (groups.getGroupName().equalsIgnoreCase("PERSONAL_INFO")) {
						user = setUserPersonalInfo(user, remarksDTO);
					} else if (groups.getGroupName().equalsIgnoreCase("addresses")) {
						addressDetails = getAddressDetails(user.getAddressDetails(), subGroups);
						if (addressDetails != null) {
							addressDetails = setAddressDetails(addressDetails, remarksDTO);
						} else {
							addressDetails = getAddressDetails(businessAddressDetailsSet, subGroups);
							if (addressDetails != null) {
								setAddressDetails(addressDetails, remarksDTO);
							}
						}
					} else if (groups.getGroupName().equalsIgnoreCase("accountInformations")) {
						if (bankAccountDetails != null) {
							bankAccountDetails = setUserBankAccountDetails(bankAccountDetails, remarksDTO);
						}
					} else if (groups.getGroupName().equalsIgnoreCase("liabilitiesDetails")) {
						liabilitiesDetails = getLiabilitiesDetails(user.getLiabilitiesDetails(), subGroups);
						if (liabilitiesDetails != null) {
							liabilitiesDetails = setUserLiabilitiesDetails(liabilitiesDetails, remarksDTO);
						}
					} else if (groups.getGroupName().equalsIgnoreCase("BUSINESS_DETAILS")) {
						if (businessDetails != null) {
							businessDetails = setUserBusinessDetails(businessDetails, remarksDTO);
						}
					} else if (groups.getGroupName().equalsIgnoreCase("licenseDetails")) {
						if (businessDetails != null) {
							licenseDetails = getLicenseDetails(businessDetails.getLicenseDetails(), subGroups);
							if (licenseDetails != null) {
								licenseDetails = setLicenseDetails(licenseDetails, remarksDTO);
							}
						}
					} else if (groups.getGroupName().equalsIgnoreCase("monthlyTxnProfile")) {
						if (monthlyTransactionProfiles != null) {
							monthlyTransactionProfiles = setUserMonthlyTxnProfiles(monthlyTransactionProfiles,
									remarksDTO);
						}
					} else if (groups.getGroupName().equalsIgnoreCase("WORK_EDUCATION")) {
						if (workEducationDetails != null) {
							workEducationDetails = setUserWorkEducationDetails(workEducationDetails, remarksDTO);
						}
					} else if (groups.getGroupName().equalsIgnoreCase("introducerDetails")) {
						if (introducerDetails != null) {
							introducerDetails = setUserIntroducerDetails(introducerDetails, remarksDTO);
						}
					}
				}
			}
			userRepository.save(user);
			if (addressDetails != null)
				addressDetailsRepository.save(addressDetails);
			if (businessDetails != null)
				businessDetailsRepository.save(businessDetails);
			if (licenseDetails != null)
				licenseDetailsRepository.save(licenseDetails);
			if (workEducationDetails != null)
				workEducationDetailsRepository.save(workEducationDetails);
			if (liabilitiesDetails != null)
				liabilitiesDetailsRepository.save(liabilitiesDetails);
			if (bankAccountDetails != null)
				bankAccountDetailsRepository.save(bankAccountDetails);
			if (introducerDetails != null)
				introducerDetailsRepository.save(introducerDetails);
			if (monthlyTransactionProfiles != null)
				monthlyTransactionProfilesRepository.save(monthlyTransactionProfiles);

			return true;
		}
		return false;

	}

	private LicenseDetails setLicenseDetails(LicenseDetails licenseDetails, RemarksDTO remarksDTO) {
		if (remarksDTO.getFieldId().equals("licenseNumber")) {
			licenseDetails.setLicenseNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("licenseExpiryDate")) {
			licenseDetails.setLicenseExpiryDate(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("licenseIssuingAuthority")) {
			licenseDetails.setLicenseIssuingAuthority(remarksDTO.getFieldValue());
		}
		return licenseDetails;
	}

	private LicenseDetails getLicenseDetails(Set<LicenseDetails> licenseDetailSet,
			co.yabx.admin.portal.app.kyc.entities.filter.SubGroups subGroups) {
		return licenseDetailSet != null && licenseDetailSet.stream()
				.filter(f -> f.getLicenseType().equals(getLicenseType(subGroups))).findFirst().isPresent()
						? licenseDetailSet.stream().filter(f -> f.getLicenseType().equals(getLicenseType(subGroups)))
								.findFirst().get()
						: null;
	}

	private Object getLicenseType(co.yabx.admin.portal.app.kyc.entities.filter.SubGroups subGroups) {
		LicenseType licenseType = null;
		if (subGroups != null) {
			if (subGroups.getGroupType().equalsIgnoreCase("Trade License")) {
				licenseType = LicenseType.TRADE;
			} else if (subGroups.getGroupType().equalsIgnoreCase("Other License")) {
				licenseType = LicenseType.OTHER;
			}
		}
		return licenseType;
	}

	private LiabilitiesDetails getLiabilitiesDetails(Set<LiabilitiesDetails> liabilitiesDetails,
			co.yabx.admin.portal.app.kyc.entities.filter.SubGroups subGroups) {
		return liabilitiesDetails != null && liabilitiesDetails.stream()
				.filter(f -> f.getLiabilityType().equals(getLiabilitiyType(subGroups))).findFirst().isPresent()
						? liabilitiesDetails.stream()
								.filter(f -> f.getLiabilityType().equals(getLiabilitiyType(subGroups))).findFirst()
								.get()
						: null;
	}

	private LiabilityType getLiabilitiyType(co.yabx.admin.portal.app.kyc.entities.filter.SubGroups subGroups) {
		if (subGroups != null) {
			if (subGroups.getGroupType().equalsIgnoreCase("Personal Liabilities")) {
				return LiabilityType.PERSONAL;
			} else if (subGroups.getGroupType().equalsIgnoreCase("Business Liabilities")) {
				return LiabilityType.BUSINESS;
			}
		}
		return null;
	}

	private co.yabx.admin.portal.app.kyc.entities.filter.SubGroups getSubGroup(
			Set<co.yabx.admin.portal.app.kyc.entities.filter.SubGroups> subGroupsSet, RemarksDTO remarksDTO) {
		return subGroupsSet != null ? subGroupsSet.stream()
				.filter(f -> f.getId().equals(Long.valueOf(remarksDTO.getGroupId()))).findFirst().isPresent()
						? subGroupsSet.stream().filter(f -> f.getId().equals(Long.valueOf(remarksDTO.getGroupId())))
								.findFirst().get()
						: null
				: null;
	}

	private AddressDetails setAddressDetails(AddressDetails addressDetails, RemarksDTO remarksDTO) {
		if (remarksDTO.getFieldId().equals("address")) {
			addressDetails.setAddress(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("upazilaThana")) {
			addressDetails.setUpazilaThana(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("cityDsitrict")) {
			try {
				Cities citi = remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()
						? Cities.valueOf(remarksDTO.getFieldValue())
						: null;
				addressDetails.setCityDsitrict(citi);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating cityDsitrict ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("division")) {
			try {
				Divisions Country = remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()
						? Divisions.valueOf(remarksDTO.getFieldValue())
						: null;
				addressDetails.setDivision(Country);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating division ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("country")) {
			try {
				Countries Country = remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()
						? Countries.valueOf(remarksDTO.getFieldValue())
						: null;
				addressDetails.setCountry(Country);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating country ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("mobileNumber")) {
			addressDetails.setMobileNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("phoneNumber")) {
			addressDetails.setPhoneNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("email")) {
			addressDetails.setEmail(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("territory")) {
			addressDetails.setTerritory(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("zipCode")) {
			try {
				Integer zipCode = remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()
						? Integer.valueOf(remarksDTO.getFieldValue())
						: null;
				addressDetails.setZipCode(zipCode);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating zipcode ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("landmark")) {
			try {
				addressDetails.setLandmark(remarksDTO.getFieldValue());
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating landmark ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		}
		return addressDetails;
	}

	private AddressDetails getAddressDetails(Set<AddressDetails> addressDetails,
			co.yabx.admin.portal.app.kyc.entities.filter.SubGroups subGroups) {
		return addressDetails != null && addressDetails.stream()
				.filter(f -> f.getAddressType().equals(getAddressType(subGroups))).findFirst().isPresent()
						? addressDetails.stream().filter(f -> f.getAddressType().equals(getAddressType(subGroups)))
								.findFirst().get()
						: null;
	}

	private Object getAddressType(co.yabx.admin.portal.app.kyc.entities.filter.SubGroups subGroups) {
		AddressType addressType = null;
		if (subGroups != null) {
			if (subGroups.getGroupType().equalsIgnoreCase("Permanent Address")) {
				addressType = AddressType.PERMANNET;
			} else if (subGroups.getGroupType().equalsIgnoreCase("Present Address")) {
				addressType = AddressType.PRESENT;
			} else if (subGroups.getGroupType().equalsIgnoreCase("Registered Address")) {
				addressType = AddressType.BUSINESS_REGISTERED_ADDRESS;
			} else if (subGroups.getGroupType().equalsIgnoreCase("Office Address")) {
				addressType = AddressType.BUSINESS_OFFICE_ADDRESS;
			}
		}
		return addressType;
	}

	private IntroducerDetails setUserIntroducerDetails(IntroducerDetails introducerDetails, RemarksDTO remarksDTO) {

		if (remarksDTO.getFieldId().equals("accountNumber")) {
			try {
				Long accountNumber = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Long.valueOf(remarksDTO.getFieldValue())
						: 0;
				introducerDetails.setAccountNumber(accountNumber);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating accountNumber ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("name")) {
			introducerDetails.setName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("isSignatureVerified")) {
			try {
				introducerDetails.setSignatureVerified(
						remarksDTO.getFieldValue() != null ? Boolean.valueOf(remarksDTO.getFieldValue()) : false);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating isSignatureVerified ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("signature")) {

		} else if (remarksDTO.getFieldId().equals("relationship")) {
			introducerDetails.setRelationship(remarksDTO.getFieldValue());
		}
		return introducerDetails;

	}

	private WorkEducationDetails setUserWorkEducationDetails(WorkEducationDetails workEducationDetails,
			RemarksDTO remarksDTO) {
		if (remarksDTO.getFieldId().equals("experience")) {
			try {
				Integer experience = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Integer.valueOf(remarksDTO.getFieldValue())
						: 0;
				workEducationDetails.setExperience(experience);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating experience ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("occupation")) {
			workEducationDetails.setOccupation(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("designation")) {
			workEducationDetails.setDesignation(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("employer")) {
			workEducationDetails.setEmployer(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("educationalQualification")) {
			try {
				EducationalQualification educationalQualification = EducationalQualification
						.findByValue(remarksDTO.getFieldValue());
				workEducationDetails.setEducationalQualification(educationalQualification);

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while preapring educationalQualification={},error={}",
						remarksDTO.getFieldValue(), e.getMessage());
			}
		}
		return workEducationDetails;
	}

	private MonthlyTransactionProfiles setUserMonthlyTxnProfiles(MonthlyTransactionProfiles monthlyTransactionProfiles,
			RemarksDTO remarksDTO) {
		if (remarksDTO.getFieldId().equals("monthlyTurnOver")) {
			try {
				double monthlyTurnOver = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				monthlyTransactionProfiles.setMonthlyTurnOver(monthlyTurnOver);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating monthlyTurnOver ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("deposits")) {
			try {
				double deposits = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				monthlyTransactionProfiles.setDeposits(deposits);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating deposits ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("withdrawls")) {
			try {
				double withdrawls = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				monthlyTransactionProfiles.setWithdrawls(withdrawls);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating withdrawls ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("initialDeposit")) {
			try {
				double initialDeposit = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				monthlyTransactionProfiles.setInitialDeposit(initialDeposit);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating initialDeposit ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		}
		return monthlyTransactionProfiles;
	}

	private LiabilitiesDetails setUserLiabilitiesDetails(LiabilitiesDetails liabilitiesDetails, RemarksDTO remarksDTO) {

		if (remarksDTO.getFieldId().equals("loanAmount")) {
			try {
				double loanAmount = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				liabilitiesDetails.setLoanAmount(loanAmount);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating loanAmount ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("bankOrNbfiName")) {
			liabilitiesDetails.setBankOrNbfiName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("liabilityFromOtherOrganization")) {
			liabilitiesDetails.setLiabilityFromOtherOrganization(remarksDTO.getFieldValue());

		} else if (remarksDTO.getFieldId().equals("loanAmountFromOtherOrganization")) {
			try {
				double loanAmount = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				liabilitiesDetails.setLoanAmountFromOtherOrganization(loanAmount);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating loanAmountFromOtherOrganization ={}, error={}",
						remarksDTO.getFieldValue(), e.getMessage());
			}
		}
		return liabilitiesDetails;

	}

	private BankAccountDetails setUserBankAccountDetails(BankAccountDetails bankAccountDetails, RemarksDTO remarksDTO) {
		if (remarksDTO.getFieldId().equals("accountTitle")) {
			bankAccountDetails.setAccountTitle(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("accountPurpose")) {
			bankAccountDetails.setAccountPurpose(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("typeOfConcern")) {
			try {
				TypeOfConcern typeOfConcern = TypeOfConcern.valueOf(remarksDTO.getFieldValue());
				bankAccountDetails.setTypeOfConcern(typeOfConcern);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while preapring typeOfConcern={},error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("bankName")) {
			bankAccountDetails.setBankName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("accountNumber")) {
			try {
				Long accountNumber = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Long.valueOf(remarksDTO.getFieldValue())
						: null;
				bankAccountDetails.setAccountNumber(accountNumber);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating accountNumber ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("branch")) {
			bankAccountDetails.setBranch(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("modeOfOperation")) {
			try {
				ModeOfOperation mop = neitherBlankNorNull(remarksDTO.getFieldValue())
						? ModeOfOperation.valueOf(remarksDTO.getFieldValue())
						: null;
				bankAccountDetails.setModeOfOperation(mop);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating modeOfOperation ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("currency")) {
			try {
				Currency currency = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Currency.valueOf(remarksDTO.getFieldValue())
						: null;
				bankAccountDetails.setCurrency(currency);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating currency ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("bankAccountType")) {
			try {
				BankAccountType bankAccountType = neitherBlankNorNull(remarksDTO.getFieldValue())
						? BankAccountType.valueOf(remarksDTO.getFieldValue())
						: null;
				bankAccountDetails.setBankAccountType(bankAccountType);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating account_type ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		}
		return bankAccountDetails;
	}

	private BusinessDetails setUserBusinessDetails(BusinessDetails businessDetails, RemarksDTO remarksDTO) {
		if (remarksDTO.getFieldId().equals("businessPhone")) {
			businessDetails.setBusinessPhone(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("businessName")) {
			businessDetails.setBusinessName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("directorOrPartnerName")) {
			businessDetails.setDirectorOrPartnerName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("facilityDetails")) {
			try {
				FacilityDetails facilityDetails = neitherBlankNorNull(remarksDTO.getFieldValue())
						? FacilityDetails.valueOf(remarksDTO.getFieldValue())
						: null;
				businessDetails.setFacilityDetails(facilityDetails);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating facilityDetails ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("facilityType")) {
			try {
				FacilityType facilityType = neitherBlankNorNull(remarksDTO.getFieldValue())
						? FacilityType.findByValue(remarksDTO.getFieldValue())
						: null;
				businessDetails.setFacilityType(facilityType);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating facilityType ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("fixedAssetPurchase")) {
			businessDetails.setFixedAssetPurchase(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("fixedAssetName")) {
			businessDetails.setFixedAssetName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("price")) {
			try {
				double price = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setPrice(price);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating price ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("origin")) {
			businessDetails.setOrigin(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("proposedCollateral")) {
			businessDetails.setProposedCollateral(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("businessType")) {
			try {
				BusinessType businessType = neitherBlankNorNull(remarksDTO.getFieldValue())
						? BusinessType.valueOf(remarksDTO.getFieldValue())
						: null;
				businessDetails.setBusinessType(businessType);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating businessType ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("sector")) {
			try {
				BusinessSector businessSector = neitherBlankNorNull(remarksDTO.getFieldValue())
						? BusinessSector.valueOf(remarksDTO.getFieldValue())
						: null;
				businessDetails.setSector(businessSector);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating businessSector ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("detailOfBusness")) {
			businessDetails.setDetailOfBusness(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("initialCapital")) {
			try {
				double initialCapital = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setInitialCapital(initialCapital);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating initialCapital ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("fundSource")) {
			businessDetails.setFundSource(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("vatRegistrationNumber")) {
			businessDetails.setVatRegistrationNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("businessStartDate")) {
			businessDetails.setBusinessStartDate(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("businessTin")) {
			businessDetails.setBusinessTin(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("annualSales")) {
			try {
				double annualSales = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setAnnualSales(annualSales);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating annualSales ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("annualGrossProfit")) {
			try {
				double annualGrossProfit = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setAnnualGrossProfit(annualGrossProfit);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating annualGrossProfit ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("annualExpenses")) {
			try {
				double annualExpenses = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setAnnualExpenses(annualExpenses);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating annualExpenses ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("valueOfFixedAssets")) {
			try {
				double valueOfFixedAssets = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setValueOfFixedAssets(valueOfFixedAssets);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating valueOfFixedAssets ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("numberOfEmployees")) {
			try {
				int numberOfEmployees = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Integer.valueOf(remarksDTO.getFieldValue())
						: 0;
				businessDetails.setNumberOfEmployees(numberOfEmployees);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating numberOfEmployees ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("stockValue")) {
			try {
				double stockValue = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setStockValue(stockValue);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating stockValue ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("deposits")) {
			try {
				double deposits = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setDeposits(deposits);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating deposits ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("withdrawls")) {
			try {
				double withdrawls = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setWithdrawls(withdrawls);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating withdrawls ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("initialDeposit")) {
			try {
				double initialDeposit = neitherBlankNorNull(remarksDTO.getFieldValue())
						? Double.valueOf(remarksDTO.getFieldValue())
						: 0.0;
				businessDetails.setInitialDeposit(initialDeposit);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating initialDeposit ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		}
		return businessDetails;
	}

	private User setUserPersonalInfo(User user, RemarksDTO remarksDTO) {
		if (remarksDTO.getFieldId().equals("firstName")) {
			user.setFirstName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("lastName")) {
			user.setLastName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("middleName")) {
			user.setMiddleName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("dob")) {
			user.setDob(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("pob")) {
			try {
				Cities city = remarksDTO.getFieldValue() != null ? Cities.valueOf(remarksDTO.getFieldValue()) : null;
				user.setPob(city);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating city ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("fathersName")) {
			user.setFathersName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("mothersName")) {
			user.setMothersName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("maritalStatus")) {
			try {
				MaritalStatuses maritalStatuses = remarksDTO.getFieldValue() != null
						? MaritalStatuses.valueOf(remarksDTO.getFieldValue())
						: null;
				user.setMaritalStatus(maritalStatuses);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating marital statuses ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("spouseName")) {
			user.setSpouseName(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("numberOfDependents")) {
			try {
				Integer nod = remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()
						? Integer.valueOf(remarksDTO.getFieldValue())
						: 0;
				user.setNumberOfDependents(nod);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating number of dependents ={}, error={}",
						remarksDTO.getFieldValue(), e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("alternateMobileNumber")) {
			user.setAlternateMobileNumber(remarksDTO.getFieldValue());
		}
		// userDto.setRetailerPhoto("");
		else if (remarksDTO.getFieldId().equals("birthRegistrationNumber")) {
			user.setBirthRegistrationNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("drivingLicenseNumber")) {
			user.setDrivingLicenseNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("email")) {
			user.setEmail(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("gender")) {
			try {
				Gender gender = remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()
						? Gender.valueOf(remarksDTO.getFieldValue())
						: null;
				user.setGender(gender);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating gender ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("msisdn")) {
			user.setMsisdn(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("sisterConcernedOrAllied")) {
			user.setSisterConcernedOrAllied(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("taxIdentificationNumber")) {
			user.setTaxIdentificationNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("residentialStatus")) {
			try {
				ResidentStatus residentialStatus = remarksDTO.getFieldValue() != null
						&& !remarksDTO.getFieldValue().isEmpty() ? ResidentStatus.valueOf(remarksDTO.getFieldValue())
								: null;
				user.setResidentialStatus(residentialStatus);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating residential status ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("passportNumber")) {
			user.setPassportNumber(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("passportExpiryDate")) {
			user.setPassportExpiryDate(remarksDTO.getFieldValue());
		} else if (remarksDTO.getFieldId().equals("nationality")) {
			try {
				Nationality nationality = remarksDTO.getFieldValue() != null && !remarksDTO.getFieldValue().isEmpty()
						? Nationality.valueOf(remarksDTO.getFieldValue())
						: null;
				user.setNationality(nationality);
			} catch (Exception e) {
				LOGGER.error("Exception while evaluating nationality ={}, error={}", remarksDTO.getFieldValue(),
						e.getMessage());
			}
		} else if (remarksDTO.getFieldId().equals("nationalIdNumber")) {
			user.setNationalIdNumber(remarksDTO.getFieldValue());
		}
		return user;

	}

	private void persistRemarks(RemarksDTO remarksDTO, Long user_id, List<FieldRemarks> fieldRemarksList,
			String remarkBy) {
		FieldRemarks fieldRemarks = null;
		if (remarksDTO.getSide() != null && !remarksDTO.getSide().isEmpty()) {
			Optional<FieldRemarks> fieldRemarksOptional = fieldRemarksList.stream()
					.filter(f -> f.getFieldId().equalsIgnoreCase(remarksDTO.getFieldId())
							&& remarksDTO.getSide().equalsIgnoreCase(f.getSide()))
					.findFirst();
			fieldRemarks = fieldRemarksOptional.isPresent() ? fieldRemarksOptional.get() : null;
		} else {
			Optional<FieldRemarks> fieldRemarksOptional = fieldRemarksList.stream()
					.filter(f -> f.getGroupId() != null && f.getSectionId() != null && f.getPageId() != null
							&& f.getFieldId() != null && f.getFieldId().equalsIgnoreCase(remarksDTO.getFieldId())
							&& f.getGroupId().equals(remarksDTO.getGroupId())
							&& f.getSectionId().equals(remarksDTO.getSectionId())
							&& f.getPageId().equals(remarksDTO.getPageId()))
					.findFirst();
			if (fieldRemarksOptional.isPresent()) {
				fieldRemarks = fieldRemarksOptional.get();
			}
		}
		if (fieldRemarks == null) {
			fieldRemarks = new FieldRemarks();
			fieldRemarks.setUserId(user_id);
			fieldRemarks.setFieldId(remarksDTO.getFieldId());
			fieldRemarks.setPageId(remarksDTO.getPageId());
			fieldRemarks.setSectionId(remarksDTO.getSectionId());
			fieldRemarks.setGroupId(remarksDTO.getGroupId());
			fieldRemarks.setFieldId(remarksDTO.getFieldValue());
		}
		fieldRemarks.setRemark(remarksDTO.getRemark());
		fieldRemarks.setRemarkBy(remarkBy);
		fieldRemarks.setSide(remarksDTO.getSide());
		fieldRemarks=fieldRemarksRepository.save(fieldRemarks);

	}

	private boolean neitherBlankNorNull(String response) {
		return response != null && !response.isEmpty();
	}

}
