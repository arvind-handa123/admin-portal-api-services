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

import co.yabx.admin.portal.app.enums.BankAccountIdentifier;
import co.yabx.admin.portal.app.enums.BankAccountType;
import co.yabx.admin.portal.app.enums.Currency;
import co.yabx.admin.portal.app.enums.ModeOfOperation;
import co.yabx.admin.portal.app.enums.TypeOfConcern;


@Entity
@Table(name = "bank_account_details", indexes = { @Index(name = "bank_name", columnList = "bank_name"),
		@Index(name = "account_number", columnList = "account_number") })
public class BankAccountDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "msisdn")
	private String msisdn;

	@Column(name = "account_title")
	private String accountTitle;

	@Column(name = "concern_type", length = 100, columnDefinition = "varchar(32) default 'Proprietorship' ")
	@Enumerated(value = EnumType.STRING)
	private TypeOfConcern typeOfConcern;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "account_number")
	private Long accountNumber;

	@Column(name = "branch")
	private String branch;

	@Column(name = "purpose_of_account", length = 100, columnDefinition = "varchar(32) default 'Business'")
	private String accountPurpose;

	@Column(name = "currency", length = 100, columnDefinition = "varchar(32) default 'BDT'")
	@Enumerated(value = EnumType.STRING)
	private Currency currency;

	@Column(name = "operation_mode", length = 100, columnDefinition = "varchar(32) default 'Single'")
	@Enumerated(value = EnumType.STRING)
	private ModeOfOperation modeOfOperation;

	@Column(name = "account_type", length = 100, columnDefinition = "varchar(32) default 'Current'")
	@Enumerated(value = EnumType.STRING)
	private BankAccountType bankAccountType;

	@Column(name = "account_identifier")
	private BankAccountIdentifier bankAccountIdentifier;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	User user;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = BusinessDetails.class)
	BusinessDetails businessDetails;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerInformations.class)
	CustomerInformations customerInformations;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_by")
	private String updatedBy;

	public Long getId() {
		return id;
	}

	public BankAccountIdentifier getBankAccountIdentifier() {
		return bankAccountIdentifier;
	}

	public void setBankAccountIdentifier(BankAccountIdentifier bankAccountIdentifier) {
		this.bankAccountIdentifier = bankAccountIdentifier;
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

	@PrePersist
	private void prePersist() {
		if (createdAt == null) {
			createdAt = new Date();
			updatedAt = new Date();
		}
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public BankAccountType getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(BankAccountType bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

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

	@PreUpdate
	private void preUpdate() {
		updatedAt = new Date();

	}

	public String getAccountTitle() {
		return accountTitle;
	}

	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}

	public TypeOfConcern getTypeOfConcern() {
		return typeOfConcern;
	}

	public void setTypeOfConcern(TypeOfConcern typeOfConcern) {
		this.typeOfConcern = typeOfConcern;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public ModeOfOperation getModeOfOperation() {
		return modeOfOperation;
	}

	public void setModeOfOperation(ModeOfOperation modeOfOperation) {
		this.modeOfOperation = modeOfOperation;
	}

	public CustomerInformations getCustomerInformations() {
		return customerInformations;
	}

	public void setCustomerInformations(CustomerInformations customerInformations) {
		this.customerInformations = customerInformations;
	}

	public String getAccountPurpose() {
		return accountPurpose;
	}

	public void setAccountPurpose(String accountPurpose) {
		this.accountPurpose = accountPurpose;
	}

}
