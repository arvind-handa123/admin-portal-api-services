package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.BankAccountIdentifier;
import co.yabx.admin.portal.app.enums.BankAccountType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountDetailsDTO implements Serializable {

	private Long id;

	private String msisdn;

	private String bankName;

	private Long accountNumber;

	private String branch;

	private BankAccountType bankAccountType;

	private UserDTO user;

	private BusinessDetailsDTO businessDetails;

	private BankAccountIdentifier bankAccountIdentifier;

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

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
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

	public BankAccountIdentifier getBankAccountIdentifier() {
		return bankAccountIdentifier;
	}

	public void setBankAccountIdentifier(BankAccountIdentifier bankAccountIdentifier) {
		this.bankAccountIdentifier = bankAccountIdentifier;
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

}
