package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Asad.ali
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KycDetailsDTO implements Serializable {
	private String msisdn;
	private String firstName;
	private String middleName;
	private String lastName;
	private String houseNumberOrStreetName;
	private String area;
	private String city;
	private String region;
	private Integer zipCode;
	private Long dob;
	private String pob;
	private String gender;
	private String userId;
	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;
	private String email;

	private List<KycDocumentsDTO> kycDocuments;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getHouseNumberOrStreetName() {
		return houseNumberOrStreetName;
	}

	public void setHouseNumberOrStreetName(String houseNumberOrStreetName) {
		this.houseNumberOrStreetName = houseNumberOrStreetName;
	}

	public Long getDob() {
		return dob;
	}

	public void setDob(Long dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<KycDocumentsDTO> getKycDocuments() {
		return kycDocuments;
	}

	public void setKycDocuments(List<KycDocumentsDTO> kycDocuments) {
		this.kycDocuments = kycDocuments;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPob() {
		return pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "KycDetailsDTO [msisdn=" + msisdn + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", houseNumberOrStreetName=" + houseNumberOrStreetName + ", area=" + area
				+ ", city=" + city + ", region=" + region + ", zipCode=" + zipCode + ", dob=" + dob + ", pob=" + pob
				+ ", gender=" + gender + ", userId=" + userId + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", email=" + email + ", kycDocuments="
				+ kycDocuments + "]";
	}

}
