package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import co.yabx.admin.portal.app.enums.BusinessSector;
import co.yabx.admin.portal.app.enums.BusinessType;
import co.yabx.admin.portal.app.enums.FacilityDetails;
import co.yabx.admin.portal.app.enums.FacilityType;

@Entity
@Table(name = "business_details", indexes = { @Index(name = "business_type", columnList = "business_type"),
		@Index(name = "business_name", columnList = "business_name"),
		@Index(name = "facility_details", columnList = "facility_details") })
public class BusinessDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "business_phone_number")
	private String businessPhone;

	@Column(name = "business_name")
	private String businessName;

	/**
	 * NAME OF THE PROPRIETOR/PARTNERS/MD/DIRECTOR
	 */
	@Column(name = "partner_or_director_name")
	private String directorOrPartnerName;

	@Column(name = "facility_details", columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private FacilityDetails facilityDetails;

	@Column(name = "facility_type", columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private FacilityType facilityType;

	@Column(name = "fixed_asset_purchase")
	private String fixedAssetPurchase;

	@Column(name = "fixed_asset_name")
	private String fixedAssetName;

	@Column(name = "price")
	private double price;

	@Column(name = "origin")
	private String origin;

	@Column(name = "proposed_collateral")
	private String proposedCollateral;

	@Column(name = "business_type", length = 100, columnDefinition = "varchar(32) default 'Proprietorship'")
	@Enumerated(value = EnumType.STRING)
	private BusinessType businessType;

	@Column(name = "sector", length = 100, columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private BusinessSector sector;

	@Column(name = "detail_of_busness", length = 100, columnDefinition = "varchar(32) default 'Telecom Retailer'")
	private String detailOfBusness;

	@Column(name = "initial_capital")
	private double initialCapital;

	@Column(name = "fund_source", length = 100, columnDefinition = "varchar(32) default 'Business'")
	private String fundSource;

	@Column(name = "vat_registration_number")
	private String vatRegistrationNumber;

	@Column(name = "business_start_date")
	private String businessStartDate;

	@Column(name = "business_tin")
	private String businessTin;

	@Column(name = "annual_sales")
	private double annualSales;

	@Column(name = "annual_gross_profit")
	private double annualGrossProfit;

	@Column(name = "annual_expenses")
	private double annualExpenses;

	@Column(name = "fixed_assets_value")
	private double valueOfFixedAssets;

	@Column(name = "employees")
	private int numberOfEmployees;

	@Column(name = "stock_value")
	private double stockValue;

	@Column(name = "monthly_turn_over")
	private double monthlyTurnOver;

	@Column(name = "deposits")
	private double deposits;

	@Column(name = "withdrawls")
	private double withdrawls;

	@Column(name = "initial_deposits")
	private double initialDeposit;

	@OneToMany(mappedBy = "businessDetails", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<LicenseDetails> licenseDetails = new HashSet<LicenseDetails>();

	@OneToMany(mappedBy = "businessDetails", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<AddressDetails> addressDetails = new HashSet<AddressDetails>();

	@OneToMany(mappedBy = "businessDetails", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<BankAccountDetails> bankAccountDetails = new HashSet<BankAccountDetails>();

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	User user;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "receivables")
	private String receivables;

	@Column(name = "payables")
	private String payables;

	public Long getId() {
		return id;
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

	public FacilityDetails getFacilityDetails() {
		return facilityDetails;
	}

	public void setFacilityDetails(FacilityDetails facilityDetails) {
		this.facilityDetails = facilityDetails;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public String getFixedAssetPurchase() {
		return fixedAssetPurchase;
	}

	public void setFixedAssetPurchase(String fixedAssetPurchase) {
		this.fixedAssetPurchase = fixedAssetPurchase;
	}

	public String getFixedAssetName() {
		return fixedAssetName;
	}

	public void setFixedAssetName(String fixedAssetName) {
		this.fixedAssetName = fixedAssetName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProposedCollateral() {
		return proposedCollateral;
	}

	public void setProposedCollateral(String proposedCollateral) {
		this.proposedCollateral = proposedCollateral;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public BusinessSector getSector() {
		return sector;
	}

	public void setSector(BusinessSector sector) {
		this.sector = sector;
	}

	public String getDetailOfBusness() {
		return detailOfBusness;
	}

	public void setDetailOfBusness(String detailOfBusness) {
		this.detailOfBusness = detailOfBusness;
	}

	public double getInitialCapital() {
		return initialCapital;
	}

	public void setInitialCapital(double initialCapital) {
		this.initialCapital = initialCapital;
	}

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getVatRegistrationNumber() {
		return vatRegistrationNumber;
	}

	public void setVatRegistrationNumber(String vatRegistrationNumber) {
		this.vatRegistrationNumber = vatRegistrationNumber;
	}

	public String getBusinessTin() {
		return businessTin;
	}

	public void setBusinessTin(String businessTin) {
		this.businessTin = businessTin;
	}

	public double getAnnualSales() {
		return annualSales;
	}

	public void setAnnualSales(double annualSales) {
		this.annualSales = annualSales;
	}

	public double getAnnualGrossProfit() {
		return annualGrossProfit;
	}

	public void setAnnualGrossProfit(double annualGrossProfit) {
		this.annualGrossProfit = annualGrossProfit;
	}

	public double getAnnualExpenses() {
		return annualExpenses;
	}

	public void setAnnualExpenses(double annualExpenses) {
		this.annualExpenses = annualExpenses;
	}

	public double getValueOfFixedAssets() {
		return valueOfFixedAssets;
	}

	public void setValueOfFixedAssets(double valueOfFixedAssets) {
		this.valueOfFixedAssets = valueOfFixedAssets;
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(short numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}

	public double getMonthlyTurnOver() {
		return monthlyTurnOver;
	}

	public void setMonthlyTurnOver(double monthlyTurnOver) {
		this.monthlyTurnOver = monthlyTurnOver;
	}

	public double getDeposits() {
		return deposits;
	}

	public void setDeposits(double deposits) {
		this.deposits = deposits;
	}

	public double getWithdrawls() {
		return withdrawls;
	}

	public void setWithdrawls(double withdrawls) {
		this.withdrawls = withdrawls;
	}

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public Set<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(Set<AddressDetails> addressDetails) {
		for (AddressDetails details : addressDetails) {
			details.setBusinessDetails(this);
		}
		this.addressDetails = addressDetails;
	}

	public Set<BankAccountDetails> getBankAccountDetails() {
		return bankAccountDetails;
	}

	public void setBankAccountDetails(Set<BankAccountDetails> bankAccountDetails) {
		for (BankAccountDetails details : bankAccountDetails) {
			details.setBusinessDetails(this);
		}
		this.bankAccountDetails = bankAccountDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
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

	@PrePersist
	private void prePersist() {
		if (createdAt == null) {
			createdAt = new Date();
			updatedAt = new Date();
		}
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	@PreUpdate
	private void preUpdate() {
		updatedAt = new Date();

	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public String getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(String businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public String getReceivables() {
		return receivables;
	}

	public void setReceivables(String receivables) {
		this.receivables = receivables;
	}

	public String getPayables() {
		return payables;
	}

	public void setPayables(String payables) {
		this.payables = payables;
	}

	public Set<LicenseDetails> getLicenseDetails() {
		return licenseDetails;
	}

	public void setLicenseDetails(Set<LicenseDetails> licenseDetails) {
		for (LicenseDetails details : licenseDetails) {
			details.setBusinessDetails(this);
		}
		this.licenseDetails = licenseDetails;
	}

	@Override
	public String toString() {
		return "BusinessDetails [id=" + id + ", businessPhone=" + businessPhone + ", businessName=" + businessName
				+ ", directorOrPartnerName=" + directorOrPartnerName + ", facilityDetails=" + facilityDetails
				+ ", facilityType=" + facilityType + ", fixedAssetPurchase=" + fixedAssetPurchase + ", fixedAssetName="
				+ fixedAssetName + ", price=" + price + ", origin=" + origin + ", proposedCollateral="
				+ proposedCollateral + ", businessType=" + businessType + ", sector=" + sector + ", detailOfBusness="
				+ detailOfBusness + ", initialCapital=" + initialCapital + ", fundSource=" + fundSource
				+ ", vatRegistrationNumber=" + vatRegistrationNumber + ", businessStartDate=" + businessStartDate
				+ ", businessTin=" + businessTin + ", annualSales=" + annualSales + ", annualGrossProfit="
				+ annualGrossProfit + ", annualExpenses=" + annualExpenses + ", valueOfFixedAssets="
				+ valueOfFixedAssets + ", numberOfEmployees=" + numberOfEmployees + ", stockValue=" + stockValue
				+ ", monthlyTurnOver=" + monthlyTurnOver + ", deposits=" + deposits + ", withdrawls=" + withdrawls
				+ ", initialDeposit=" + initialDeposit + ", licenseDetails=" + licenseDetails + ", addressDetails="
				+ addressDetails + ", bankAccountDetails=" + bankAccountDetails + ", user=" + user + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", receivables=" + receivables + ", payables=" + payables
				+ "]";
	}

}
