package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "customer_informations", indexes = { @Index(name = "loan_amount", columnList = "loan_amount"),
		@Index(name = "customer_id", columnList = "customer_id") })
public class CustomerInformations implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "business_name")
	private String businessName;

	/**
	 * NAME OF THE PROPRIETOR/PARTNERS/MD/DIRECTOR
	 */
	@Column(name = "partner_or_director_name")
	private String directorOrPartnerName;

	@OneToMany(mappedBy = "customerInformations", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<AddressDetails> addressDetails;

	@OneToMany(mappedBy = "customerInformations", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<BankAccountDetails> bankAccountDetails;

	@Column(name = "unit_office")
	private String unitOffice;

	@Column(name = "unit_code")
	private String unitCode;

	@Column(name = "area")
	private String area;

	@Column(name = "territory")
	private String territory;

	@Column(name = "ro_name")
	private String nameOfRO;

	@Column(name = "am_name")
	private String nameOfAM;

	@Column(name = "tm_name")
	private String nameOfTM;

	@Column(name = "loan_amount")
	private double loanAmount;

	@Column(name = "loan_amount_in_word")
	private String loanAmountInWords;

	@Column(name = "period")
	private String period;

	/**
	 * First or Second loan
	 */
	@Column(name = "relationship")
	private String relationship;
	/**
	 * customer id number to be shared by bank
	 */
	@Column(name = "customer_id")
	private String cusomerId;

	@Column(name = "nature_code")
	private String natureCode;

	@Column(name = "sector_code")
	private String sectorCode;

	@Column(name = "economic_purpose_code")
	private String economicPurposeCode;

	@Column(name = "bank_defined_code")
	private String bankDefinedCode;

	@Column(name = "security_code")
	private String securityCode;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_by")
	private String updatedBy;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	User user;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getDirectorOrPartnerName() {
		return directorOrPartnerName;
	}

	public void setDirectorOrPartnerName(String directorOrPartnerName) {
		this.directorOrPartnerName = directorOrPartnerName;
	}

	public Set<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(Set<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public Set<BankAccountDetails> getBankAccountDetails() {
		return bankAccountDetails;
	}

	public void setBankAccountDetails(Set<BankAccountDetails> bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}

	public String getUnitOffice() {
		return unitOffice;
	}

	public void setUnitOffice(String unitOffice) {
		this.unitOffice = unitOffice;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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

	public String getNameOfRO() {
		return nameOfRO;
	}

	public void setNameOfRO(String nameOfRO) {
		this.nameOfRO = nameOfRO;
	}

	public String getNameOfAM() {
		return nameOfAM;
	}

	public void setNameOfAM(String nameOfAM) {
		this.nameOfAM = nameOfAM;
	}

	public String getNameOfTM() {
		return nameOfTM;
	}

	public void setNameOfTM(String nameOfTM) {
		this.nameOfTM = nameOfTM;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanAmountInWords() {
		return loanAmountInWords;
	}

	public void setLoanAmountInWords(String loanAmountInWords) {
		this.loanAmountInWords = loanAmountInWords;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getCusomerId() {
		return cusomerId;
	}

	public void setCusomerId(String cusomerId) {
		this.cusomerId = cusomerId;
	}

	public String getNatureCode() {
		return natureCode;
	}

	public void setNatureCode(String natureCode) {
		this.natureCode = natureCode;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getEconomicPurposeCode() {
		return economicPurposeCode;
	}

	public void setEconomicPurposeCode(String economicPurposeCode) {
		this.economicPurposeCode = economicPurposeCode;
	}

	public String getBankDefinedCode() {
		return bankDefinedCode;
	}

	public void setBankDefinedCode(String bankDefinedCode) {
		this.bankDefinedCode = bankDefinedCode;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
