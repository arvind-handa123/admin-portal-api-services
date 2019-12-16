package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.Gender;
import co.yabx.admin.portal.app.enums.MaritalStatuses;
import co.yabx.admin.portal.app.enums.Nationality;
import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.enums.ResidentStatus;
import co.yabx.admin.portal.app.kyc.entities.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

	private Long id;

	private String retailerId;

	private String msisdn;

	private String alternateMobileNumber;

	private String firstName;

	private String name;

	private String middleName;

	private String lastName;

	private Date createdAt;

	private Date updatedAt;

	private Integer age;
	/**
	 * Date of birth
	 */
	private String dob;
	/**
	 * Place of birth
	 */
	private String pob;

	private Gender gender;

	private String createdBy;

	private String updatedBy;

	private String email;

	private String fathersName;

	private String mothersName;

	private String spouseName;

	private MaritalStatuses maritalStatus;

	private int numberOfDependents;

	private String sisterConcernedOrAllied;

	private Nationality nationality;

	private ResidentStatus residentialStatus;

	private String taxIdentificationNumber;

	private String nationalIdNumber;

	private String birthRegistrationNumber;

	private String passportNumber;

	private String drivingLicenseNumber;

	private String passportExpiryDate;

	private String experience;

	private String businessAddress;

	private List<IncomeDetailsDTO> incomeDetails;

	private List<IntroducerDetailsDTO> introducerDetails;

	private List<WorkEducationDetailsDTO> workEducationDetails;

	private List<LiabilitiesDetailsDTO> liabilitiesDetails;

	private List<BusinessDetailsDTO> businessDetails;

	private List<AddressDetailsDTO> addressDetails;

	private List<BankAccountDetailsDTO> bankAccountDetails;

	private List<AttachmentDetailsDTO> attachmentDetails;

	private List<UserDTO> nomineesDetails;

	private String retailerPhoto;

	private Relationship relationship;

	private User info;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAlternateMobileNumber() {
		return alternateMobileNumber;
	}

	public void setAlternateMobileNumber(String alternateMobileNumber) {
		this.alternateMobileNumber = alternateMobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPob() {
		return pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public MaritalStatuses getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatuses maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getNumberOfDependents() {
		return numberOfDependents;
	}

	public void setNumberOfDependents(int numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}

	public String getSisterConcernedOrAllied() {
		return sisterConcernedOrAllied;
	}

	public void setSisterConcernedOrAllied(String sisterConcernedOrAllied) {
		this.sisterConcernedOrAllied = sisterConcernedOrAllied;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public ResidentStatus getResidentialStatus() {
		return residentialStatus;
	}

	public void setResidentialStatus(ResidentStatus residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	public String getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	public String getBirthRegistrationNumber() {
		return birthRegistrationNumber;
	}

	public void setBirthRegistrationNumber(String birthRegistrationNumber) {
		this.birthRegistrationNumber = birthRegistrationNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(String passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public List<IncomeDetailsDTO> getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(List<IncomeDetailsDTO> incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	public List<IntroducerDetailsDTO> getIntroducerDetails() {
		return introducerDetails;
	}

	public void setIntroducerDetails(List<IntroducerDetailsDTO> introducerDetails) {
		this.introducerDetails = introducerDetails;
	}

	public List<WorkEducationDetailsDTO> getWorkEducationDetails() {
		return workEducationDetails;
	}

	public void setWorkEducationDetails(List<WorkEducationDetailsDTO> workEducationDetails) {
		this.workEducationDetails = workEducationDetails;
	}

	public List<LiabilitiesDetailsDTO> getLiabilitiesDetails() {
		return liabilitiesDetails;
	}

	public void setLiabilitiesDetails(List<LiabilitiesDetailsDTO> liabilitiesDetails) {
		this.liabilitiesDetails = liabilitiesDetails;
	}

	public List<BusinessDetailsDTO> getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(List<BusinessDetailsDTO> businessDetails) {
		this.businessDetails = businessDetails;
	}

	public List<AddressDetailsDTO> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetailsDTO> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public List<BankAccountDetailsDTO> getBankAccountDetails() {
		return bankAccountDetails;
	}

	public void setBankAccountDetails(List<BankAccountDetailsDTO> bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}

	public List<AttachmentDetailsDTO> getAttachmentDetails() {
		return attachmentDetails;
	}

	public void setAttachmentDetails(List<AttachmentDetailsDTO> attachmentDetails) {
		this.attachmentDetails = attachmentDetails;
	}

	public String getRetailerPhoto() {
		return retailerPhoto;
	}

	public void setRetailerPhoto(String retailerPhoto) {
		this.retailerPhoto = retailerPhoto;
	}

	public List<UserDTO> getNomineesDetails() {
		return nomineesDetails;
	}

	public void setNomineesDetails(List<UserDTO> nomineesDetails) {
		this.nomineesDetails = nomineesDetails;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public User getInfo() {
		return info;
	}

	public void setInfo(User info) {
		this.info = info;
	}

}
