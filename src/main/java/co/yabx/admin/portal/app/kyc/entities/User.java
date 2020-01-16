package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.transaction.Transactional;

import co.yabx.admin.portal.app.enums.Cities;
import co.yabx.admin.portal.app.enums.Gender;
import co.yabx.admin.portal.app.enums.MaritalStatuses;
import co.yabx.admin.portal.app.enums.Nationality;
import co.yabx.admin.portal.app.enums.ResidentStatus;
import co.yabx.admin.portal.app.enums.UserStatus;

@Entity
@Table(name = "users", indexes = { @Index(name = "msisdn", columnList = "msisdn"),
		@Index(name = "user_type", columnList = "user_type"),
		@Index(name = "auth_info_id", columnList = "auth_info_id") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Least normalisation strategy
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User implements Serializable {

	private static final long serialVersionUID = -6901150039363185L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "user_type", insertable = false, updatable = false)
	private String userType;

	@Column(name = "user_status", length = 100, nullable = false, columnDefinition = "varchar(32) default 'NEW' ")
	@Enumerated(value = EnumType.STRING)
	private UserStatus userStatus;

	@Column(name = "msisdn")
	private String msisdn;

	@Column(name = "alternate_msisdn")
	private String alternateMobileNumber;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;
	/**
	 * Date of birth
	 */
	@Column(name = "dob")
	private String dob;
	/**
	 * Place of birth
	 */
	@Column(name = "pob", length = 100, columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private Cities pob;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_by")
	private String updatedBy;

	@Column(name = "email")
	private String email;

	@Column(name = "fathers_name")
	private String fathersName;

	@Column(name = "mothers_name")
	private String mothersName;

	@Column(name = "spouse_name")
	private String spouseName;

	@Column(name = "marital_status")
	private MaritalStatuses maritalStatus;

	@Column(name = "dependents", length = 2, columnDefinition = "int(11) default 0 ")
	private int numberOfDependents;

	@Column(name = "sister_concerned_or_allied")
	private String sisterConcernedOrAllied;

	@Column(name = "nationality", length = 100, columnDefinition = "varchar(32) default 'Bengali' ")
	@Enumerated(value = EnumType.STRING)
	private Nationality nationality;

	@Column(name = "resident_status", length = 100, columnDefinition = "varchar(32) default 'Resident' ")
	@Enumerated(value = EnumType.STRING)
	private ResidentStatus residentialStatus;

	@Column(name = "tax_identification_number")
	private String taxIdentificationNumber;

	@Column(name = "natonal_id_number")
	private String nationalIdNumber;

	@Column(name = "birth_registration_number")
	private String birthRegistrationNumber;

	@Column(name = "passport_number")
	private String passportNumber;

	@Column(name = "driving_license_number")
	private String drivingLicenseNumber;

	@Column(name = "passport_expiry_date")
	private String passportExpiryDate;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<MonthlyTransactionProfiles> monthlyTransactionProfiles = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<IntroducerDetails> introducerDetails = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<WorkEducationDetails> workEducationDetails = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<LiabilitiesDetails> liabilitiesDetails = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<BusinessDetails> businessDetails = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<AddressDetails> addressDetails = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<BankAccountDetails> bankAccountDetails = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<AttachmentDetails> attachmentDetails = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user", referencedColumnName = "id")
	private LoanPurposeDetails loanPurposeDetails;

	@Column(name = "minor")
	private String minor;

	@Column(length = 25)
	private String locale;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_info_id", referencedColumnName = "id")
	private AuthInfo authInfo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "device_info", referencedColumnName = "device_id")
	private DeviceInformations deviceInformation;

	public LoanPurposeDetails getLoanPurposeDetails() {
		return loanPurposeDetails;
	}

	public void setLoanPurposeDetails(LoanPurposeDetails loanPurposeDetails) {
		this.loanPurposeDetails = loanPurposeDetails;
	}

	public Set<IntroducerDetails> getIntroducerDetails() {
		return introducerDetails;
	}

	public void setIntroducerDetails(Set<IntroducerDetails> introducerDetails) {

		for (IntroducerDetails details : introducerDetails) {
			details.setUser(this);
		}
		this.introducerDetails = introducerDetails;
	}

	public Set<WorkEducationDetails> getWorkEducationDetails() {
		return workEducationDetails;
	}

	public void setWorkEducationDetails(Set<WorkEducationDetails> workEducationDetails) {
		for (WorkEducationDetails details : workEducationDetails) {
			details.setUser(this);
		}
		this.workEducationDetails = workEducationDetails;
	}

	public Set<LiabilitiesDetails> getLiabilitiesDetails() {
		return liabilitiesDetails;
	}

	public void setLiabilitiesDetails(Set<LiabilitiesDetails> liabilitiesDetails) {
		for (LiabilitiesDetails details : liabilitiesDetails) {
			details.setUser(this);
		}
		this.liabilitiesDetails = liabilitiesDetails;
	}

	public Set<BusinessDetails> getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(Set<BusinessDetails> businessDetails) {
		for (BusinessDetails details : businessDetails) {
			details.setUser(this);
		}
		this.businessDetails = businessDetails;
	}

	public Set<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(Set<AddressDetails> addressDetails) {
		for (AddressDetails details : addressDetails) {
			details.setUser(this);
		}
		this.addressDetails = addressDetails;
	}

	public Set<BankAccountDetails> getBankAccountDetails() {
		return bankAccountDetails;
	}

	public void setBankAccountDetails(Set<BankAccountDetails> bankAccountDetails) {
		for (BankAccountDetails details : bankAccountDetails) {
			details.setUser(this);
		}
		this.bankAccountDetails = bankAccountDetails;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Cities getPob() {
		return pob;
	}

	public void setPob(Cities pob) {
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

	public String getAlternateMobileNumber() {
		return alternateMobileNumber;
	}

	public void setAlternateMobileNumber(String alternateMobileNumber) {
		this.alternateMobileNumber = alternateMobileNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<AttachmentDetails> getAttachmentDetails() {
		return attachmentDetails;
	}

	public void setAttachmentDetails(Set<AttachmentDetails> attachmentDetails) {
		this.attachmentDetails = attachmentDetails;
	}

	@PrePersist
	private void prePersist() {
		if (createdAt == null) {
			createdAt = new Date();
			updatedAt = new Date();
		}
	}

	@PreUpdate
	private void preUpdate() {
		updatedAt = new Date();

	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public AuthInfo getAuthInfo() {
		return authInfo;
	}

	public void setAuthInfo(AuthInfo authInfo) {
		this.authInfo = authInfo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set<MonthlyTransactionProfiles> getMonthlyTransactionProfiles() {
		return monthlyTransactionProfiles;
	}

	public void setMonthlyTransactionProfiles(Set<MonthlyTransactionProfiles> monthlyTransactionProfilesSet) {
		for (MonthlyTransactionProfiles monthlyTransactionProfile : monthlyTransactionProfilesSet) {
			monthlyTransactionProfile.setUser(this);
		}
		this.monthlyTransactionProfiles = monthlyTransactionProfilesSet;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public DeviceInformations getDeviceInformation() {
		return deviceInformation;
	}

	public void setDeviceInformation(DeviceInformations deviceInformation) {
		this.deviceInformation = deviceInformation;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userType=" + userType + ", userStatus=" + userStatus + ", msisdn=" + msisdn
				+ ", alternateMobileNumber=" + alternateMobileNumber + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", dob=" + dob + ", pob=" + pob + ", gender=" + gender + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", email=" + email + ", fathersName=" + fathersName + ", mothersName=" + mothersName
				+ ", spouseName=" + spouseName + ", maritalStatus=" + maritalStatus + ", numberOfDependents="
				+ numberOfDependents + ", sisterConcernedOrAllied=" + sisterConcernedOrAllied + ", nationality="
				+ nationality + ", residentialStatus=" + residentialStatus + ", taxIdentificationNumber="
				+ taxIdentificationNumber + ", nationalIdNumber=" + nationalIdNumber + ", birthRegistrationNumber="
				+ birthRegistrationNumber + ", passportNumber=" + passportNumber + ", drivingLicenseNumber="
				+ drivingLicenseNumber + ", passportExpiryDate=" + passportExpiryDate + ", monthlyTransactionProfiles="
				+ monthlyTransactionProfiles + ", introducerDetails=" + introducerDetails + ", workEducationDetails="
				+ workEducationDetails + ", liabilitiesDetails=" + liabilitiesDetails + ", businessDetails="
				+ businessDetails + ", addressDetails=" + addressDetails + ", bankAccountDetails=" + bankAccountDetails
				+ ", attachmentDetails=" + attachmentDetails + ", loanPurposeDetails=" + loanPurposeDetails + ", minor="
				+ minor + ", locale=" + locale + ", authInfo=" + authInfo + ", deviceInformation=" + deviceInformation
				+ "]";
	}

}
