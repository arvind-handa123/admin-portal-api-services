package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.AddressType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDetailsDTO implements Serializable {

	private Long id;

	private String msisdn;

	private String houseNumberOrStreetName;

	private String area;

	private String city;

	private String region;

	private Integer zipCode;

	private Date createdAt;

	private Date updatedAt;

	private String createdBy;

	private String updatedBy;

	private AddressType addressType;

	private UserDTO user;

	private BusinessDetailsDTO businessDetails;

	private String address;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public BusinessDetailsDTO getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetailsDTO businessDetails) {
		this.businessDetails = businessDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
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

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "AddressDetailsDTO [id=" + id + ", msisdn=" + msisdn + ", houseNumberOrStreetName="
				+ houseNumberOrStreetName + ", area=" + area + ", city=" + city + ", region=" + region + ", zipCode="
				+ zipCode + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", addressType=" + addressType + ", user=" + user + ", businessDetails="
				+ businessDetails + ", address=" + address + "]";
	}

}
