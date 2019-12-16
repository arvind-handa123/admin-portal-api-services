package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDetailsDTO implements Serializable {

	private Long id;

	private String retailerId;

	private String businessPhone;

	private String businessName;

	/**
	 * NAME OF THE PROPRIETOR/PARTNERS/MD/DIRECTOR
	 */
	private String directorOrPartnerName;

	private String facilityDetails;

	private String facilityType;

	private String fixedAssetPurchase;

	private String fixedAssetName;

	private double price;

	private String origin;

	private String proposedCollateral;

	private String businessType;

	private String sector;

	private String detailOfBusness;

	private double initialCapital;

	private String fundSource;

	private String vatRegistrationNumber;

	private String businessStartDate;

	private String businessTin;

	private double annualSales;

	private double annualGrossProfit;

	private double annualExpenses;

	private double valueOfFixedAssets;

	private int numberOfEmployees;

	private double stockValue;

	private double monthlyTurnOver;

	private double deposits;

	private double withdrawls;

	private double initialDeposit;

	private LicenseDetailsDTO licenseDetails;

	private List<AddressDetailsDTO> addressDetails;

	private List<BankAccountDetailsDTO> bankAccountDetails;

	private UserDTO user;

	private Date createdAt;

	private Date updatedAt;

	private String createdBy;

	private String updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
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

	public String getFacilityDetails() {
		return facilityDetails;
	}

	public void setFacilityDetails(String facilityDetails) {
		this.facilityDetails = facilityDetails;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
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

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
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

	public String getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(String businessStartDate) {
		this.businessStartDate = businessStartDate;
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

	public void setNumberOfEmployees(int numberOfEmployees) {
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

	public LicenseDetailsDTO getLicenseDetails() {
		return licenseDetails;
	}

	public void setLicenseDetails(LicenseDetailsDTO licenseDetails) {
		this.licenseDetails = licenseDetails;
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

}
