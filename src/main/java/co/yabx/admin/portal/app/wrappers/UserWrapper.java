package co.yabx.admin.portal.app.wrappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.yabx.admin.portal.app.kyc.dto.AddressDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.BankAccountDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.BusinessDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.IncomeDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.IntroducerDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.LiabilitiesDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.LicenseDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.UserDTO;
import co.yabx.admin.portal.app.kyc.dto.WorkEducationDetailsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.IncomeDetails;
import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.LicenseDetails;
import co.yabx.admin.portal.app.kyc.entities.Retailers;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.WorkEducationDetails;

public class UserWrapper {

	public static Set<AddressDetails> getAddressDetails(List<AddressDetailsDTO> addressDetailsDTO) {
		Set<AddressDetails> addressDetails = new HashSet<AddressDetails>();
		for (AddressDetailsDTO detailsDTO : addressDetailsDTO) {
			AddressDetails addressDetail = new AddressDetails();
			addressDetail.setAddressType(detailsDTO.getAddressType());
			addressDetail.setUpazilaThana(detailsDTO.getArea());
			addressDetail.setAddress(detailsDTO.getHouseNumberOrStreetName());
			addressDetail.setZipCode(detailsDTO.getZipCode());
			addressDetails.add(addressDetail);
		}
		return addressDetails;
	}

	public static Set<WorkEducationDetails> getWorkEducationDetails(
			List<WorkEducationDetailsDTO> workEducationDetailsDTOs) {
		Set<WorkEducationDetails> workEducationDetails = new HashSet<WorkEducationDetails>();
		for (WorkEducationDetailsDTO workEducationDetailsDTO : workEducationDetailsDTOs) {
			WorkEducationDetails details = new WorkEducationDetails();
			details.setDesignation(workEducationDetailsDTO.getDesignation());
			details.setEducationalQualification(workEducationDetailsDTO.getEducationalQualification());
			details.setEmployer(workEducationDetailsDTO.getEmployer());
			details.setOccupation(workEducationDetailsDTO.getOccupation());
			workEducationDetails.add(details);
		}
		return workEducationDetails;
	}

	public static Set<BusinessDetails> getBusinessDetails(List<BusinessDetailsDTO> businessDetailsDtos) {
		Set<BusinessDetails> businessDetailsSet = new HashSet<BusinessDetails>();
		for (BusinessDetailsDTO businessDetailsDTO : businessDetailsDtos) {
			BusinessDetails businessDetails = new BusinessDetails();
			businessDetails.setBusinessName(businessDetailsDTO.getBusinessName());
			businessDetails.setBusinessPhone(businessDetailsDTO.getBusinessPhone());
			businessDetails.setBusinessStartDate(businessDetailsDTO.getBusinessStartDate());
			businessDetails.setBusinessTin(businessDetailsDTO.getBusinessTin());
			businessDetails.setWithdrawls(businessDetailsDTO.getWithdrawls());
			businessDetails.setVatRegistrationNumber(businessDetailsDTO.getVatRegistrationNumber());
			businessDetails.setValueOfFixedAssets(businessDetailsDTO.getValueOfFixedAssets());
			businessDetails.setStockValue(businessDetailsDTO.getStockValue());
			businessDetails.setProposedCollateral(businessDetailsDTO.getProposedCollateral());
			businessDetails.setPrice(businessDetailsDTO.getPrice());
			businessDetails.setOrigin(businessDetailsDTO.getOrigin());
			businessDetails.setNumberOfEmployees(businessDetailsDTO.getNumberOfEmployees());
			businessDetails.setMonthlyTurnOver(businessDetailsDTO.getMonthlyTurnOver());
			businessDetails.setDeposits(businessDetailsDTO.getDeposits());
			businessDetails.setDetailOfBusness(businessDetailsDTO.getDetailOfBusness());
			businessDetails.setDirectorOrPartnerName(businessDetailsDTO.getDirectorOrPartnerName());
			businessDetails.setFixedAssetName(businessDetailsDTO.getFixedAssetName());
			businessDetails.setFixedAssetPurchase(businessDetailsDTO.getFixedAssetPurchase());
			businessDetails.setFundSource(businessDetailsDTO.getFundSource());
			businessDetails.setInitialCapital(businessDetailsDTO.getInitialCapital());
			businessDetails.setInitialDeposit(businessDetailsDTO.getInitialDeposit());
			businessDetails.setAddressDetails(getAddressDetails(businessDetailsDTO.getAddressDetails()));
			businessDetails
					.setBankAccountDetails(prepareBankAccountDetails(businessDetailsDTO.getBankAccountDetails()));
			businessDetailsSet.add(businessDetails);
		}
		return businessDetailsSet;
	}

	public static Set<BankAccountDetails> prepareBankAccountDetails(List<BankAccountDetailsDTO> bankAccountDetails) {
		Set<BankAccountDetails> businessAccountDetailsSet = new HashSet<BankAccountDetails>();
		for (BankAccountDetailsDTO accountDetailsDTO : bankAccountDetails) {
			BankAccountDetails businessBankAccountDetails = new BankAccountDetails();
			businessBankAccountDetails.setAccountNumber(accountDetailsDTO.getAccountNumber());
			businessBankAccountDetails.setBankAccountType(accountDetailsDTO.getBankAccountType());
			businessBankAccountDetails.setBankName(accountDetailsDTO.getBankName());
			businessBankAccountDetails.setBranch(accountDetailsDTO.getBranch());
			businessBankAccountDetails.setBankAccountIdentifier(accountDetailsDTO.getBankAccountIdentifier());
			businessAccountDetailsSet.add(businessBankAccountDetails);
		}
		return businessAccountDetailsSet;
	}

	public static LicenseDetails prepareLicenseDetails(LicenseDetailsDTO licenseDetailsDTO) {
		if (licenseDetailsDTO != null) {
			LicenseDetails licenseDetails = new LicenseDetails();
			licenseDetails.setLicenseExpiryDate(licenseDetailsDTO.getLicenseExpiryDate());
			licenseDetails.setLicenseIssuingAuthority(licenseDetailsDTO.getLicenseIssuingAuthority());
			licenseDetails.setLicenseNumber(licenseDetailsDTO.getLicenseNumber());
			licenseDetails.setLicenseType(licenseDetailsDTO.getLicenseType());
			return licenseDetails;
		}
		return null;
	}

	public static Set<IncomeDetails> getIncomeDetails(List<IncomeDetailsDTO> incomeDetailsDTO) {
		Set<IncomeDetails> incomeDetailsSet = new HashSet<IncomeDetails>();
		for (IncomeDetailsDTO detailsDTO : incomeDetailsDTO) {
			IncomeDetails incomeDetails = new IncomeDetails();
			incomeDetails.setMonthlyExpenditure(detailsDTO.getMonthlyExpenditure());
			incomeDetails.setMonthlyIncome(detailsDTO.getMonthlyIncome());
			incomeDetails.setMonthlySurplus(detailsDTO.getMonthlySurplus());
			incomeDetails.setTotalAssets(detailsDTO.getTotalAssets());
			incomeDetails.setTotalLiabilities(detailsDTO.getTotalLiabilities());
			incomeDetails.setMonthlyIncomeFromOtherSource(detailsDTO.getMonthlyIncomeFromOtherSource());
			incomeDetailsSet.add(incomeDetails);
		}
		return incomeDetailsSet;
	}

	public static Set<IntroducerDetails> getIntroducerDetails(List<IntroducerDetailsDTO> introducerDetailsDTOList) {
		Set<IntroducerDetails> introducerDetailsSet = new HashSet<IntroducerDetails>();
		for (IntroducerDetailsDTO introducerDetailsDTO : introducerDetailsDTOList) {
			IntroducerDetails introducerDetails = new IntroducerDetails();
			introducerDetails.setAccountNumber(introducerDetailsDTO.getAccountNumber());
			introducerDetails.setName(introducerDetailsDTO.getName());
			introducerDetails.setSignatureVerified(introducerDetailsDTO.isSignatureVerified());
			introducerDetailsSet.add(introducerDetails);
		}
		return introducerDetailsSet;
	}

	public static Set<LiabilitiesDetails> prepareLiabilitiesDetails(
			List<LiabilitiesDetailsDTO> liabilitiesDetailsDTOList) {
		Set<LiabilitiesDetails> liabilitiesDetailsSet = new HashSet<LiabilitiesDetails>();
		for (LiabilitiesDetailsDTO liabilitiesDetailsDTO : liabilitiesDetailsDTOList) {
			LiabilitiesDetails liabilitiesDetails = new LiabilitiesDetails();
			liabilitiesDetails.setBankOrNbfiName(liabilitiesDetailsDTO.getNameOfTheOrganization());
			liabilitiesDetails
					.setLiabilityFromOtherOrganization(liabilitiesDetailsDTO.getLiabilityFromOtherOrganization());
			liabilitiesDetails.setLiabilityType(liabilitiesDetailsDTO.getTypeOfLiablity());
			liabilitiesDetails.setLoanAmount(liabilitiesDetailsDTO.getLoanAmount());
			liabilitiesDetails
					.setLoanAmountFromOtherOrganization(liabilitiesDetailsDTO.getLoanAmountFromOtherOrganization());
			liabilitiesDetailsSet.add(liabilitiesDetails);
		}
		return liabilitiesDetailsSet;
	}

	public static User prepareUserPersonalInfo(UserDTO userDTO) {
		User user = new Retailers();
		user.setFirstName(userDTO.getName());
		user.setDob(userDTO.getDob());
		user.setFathersName(userDTO.getFathersName());
		user.setMothersName(userDTO.getMothersName());
		user.setMaritalStatus(userDTO.getMaritalStatus());
		user.setSpouseName(userDTO.getSpouseName());
		user.setNumberOfDependents(userDTO.getNumberOfDependents());
		// user.setRetailerPhoto(userDTO.getRetailerPhoto());
		user.setAlternateMobileNumber(userDTO.getAlternateMobileNumber());
		user.setBirthRegistrationNumber(userDTO.getBirthRegistrationNumber());
		user.setDrivingLicenseNumber(userDTO.getDrivingLicenseNumber());
		user.setEmail(userDTO.getEmail());
		user.setGender(userDTO.getGender());
		user.setMsisdn(userDTO.getMsisdn());
		user.setSisterConcernedOrAllied(userDTO.getSisterConcernedOrAllied());
		user.setTaxIdentificationNumber(userDTO.getTaxIdentificationNumber());
		user.setResidentialStatus(userDTO.getResidentialStatus());
		user.setPassportNumber(userDTO.getPassportNumber());
		user.setPassportExpiryDate(userDTO.getPassportExpiryDate());
		user.setNationality(userDTO.getNationality());
		user.setNationalIdNumber(userDTO.getNationalIdNumber());
		return user;
	}

	public UserDTO getCompletRetailerInfo(User retailers) {

		return null;
	}
}
