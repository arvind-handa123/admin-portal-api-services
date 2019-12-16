package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import co.yabx.admin.portal.app.enums.AddressType;
import co.yabx.admin.portal.app.enums.Cities;
import co.yabx.admin.portal.app.enums.Countries;
import co.yabx.admin.portal.app.enums.Divisions;

@Entity
@Table(name = "address_details", indexes = { @Index(name = "division", columnList = "division"),
		@Index(name = "address_type", columnList = "address_type"),
		@Index(name = "city_district", columnList = "city_district") })
public class AddressDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "mobile_no")
	private String mobileNumber;

	@Column(name = "phone_no")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "upazila_thana")
	private String upazilaThana;

	@Column(name = "city_district", length = 100, columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private Cities cityDsitrict;

	@Column(name = "division", length = 100, columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private Divisions division;

	@Column(name = "postal_code")
	private Integer zipCode;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "email")
	private String email;

	@Column(name = "country", length = 100, columnDefinition = "varchar(32) default 'Bangladesh'")
	@Enumerated(value = EnumType.STRING)
	private Countries country;

	@Column(name = "area")
	private String area;

	@Column(name = "territory")
	private String territory;

	@Column(name = "address_type")
	private AddressType addressType;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	User user;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BusinessDetails.class)
	BusinessDetails businessDetails;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CustomerInformations.class)
	CustomerInformations customerInformations;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUpazilaThana() {
		return upazilaThana;
	}

	public void setUpazilaThana(String upazilaThana) {
		this.upazilaThana = upazilaThana;
	}

	public Cities getCityDsitrict() {
		return cityDsitrict;
	}

	public void setCityDsitrict(Cities cityDsitrict) {
		this.cityDsitrict = cityDsitrict;
	}

	public Divisions getDivision() {
		return division;
	}

	public void setDivision(Divisions division) {
		this.division = division;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public CustomerInformations getCustomerInformations() {
		return customerInformations;
	}

	public void setCustomerInformations(CustomerInformations customerInformations) {
		this.customerInformations = customerInformations;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	@Override
	public String toString() {
		return "AddressDetails [id=" + id + ", mobileNumber=" + mobileNumber + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", upazilaThana=" + upazilaThana + ", cityDsitrict=" + cityDsitrict
				+ ", division=" + division + ", zipCode=" + zipCode + ", landmark=" + landmark + ", email=" + email
				+ ", country=" + country + ", addressType=" + addressType + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", user=" + user + ", businessDetails=" + businessDetails + ", customerInformations="
				+ customerInformations + "]";
	}

}
