package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import co.yabx.admin.portal.app.enums.AccountStatus;
import co.yabx.admin.portal.app.enums.AmlCftStatus;
import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.entity.entityListener.AccountStatusesTrackerListener;

@Entity
@EntityListeners({ AccountStatusesTrackerListener.class })
@Table(name = "account_statuses", indexes = { @Index(name = "kyc_verified", columnList = "kyc_verified"),
		@Index(name = "aml_cft_status", columnList = "aml_cft_status"),
		@Index(name = "account_status", columnList = "account_status") })
public class AccountStatuses implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "msisdn", unique = true, nullable = false)
	private String msisdn;

	@Column(name = "is_kyc_available", nullable = false, columnDefinition = "boolean default false")
	private boolean isKycAvailable;

	@Column(name = "kyc_verified", nullable = false, columnDefinition = "varchar(32) default 'NO'")
	@Enumerated(value = EnumType.STRING)
	private KycStatus KycVerified;

	@Column(name = "aml_cft_status", nullable = false, columnDefinition = "varchar(32) default 'NO'")
	@Enumerated(value = EnumType.STRING)
	private AmlCftStatus amlCftStatus;

	@Column(name = "account_status", nullable = false, columnDefinition = "varchar(32) default 'NEW'")
	@Enumerated(value = EnumType.STRING)
	private AccountStatus accountStatus;

	@Column(name = "transaction_status")
	private String transactionStatus;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_by")
	private String updatedBy;

	@Column(name = "reason")
	private String updateReason;

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

	@PrePersist
	protected void insertDates() {
		if (createdAt == null) {
			createdAt = new Date();
		}
		if (updatedAt == null) {
			updatedAt = new Date();
		}
	}

	@PreUpdate
	protected void updateTime() {
		updatedAt = new Date();
	}

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public boolean isKycAvailable() {
		return isKycAvailable;
	}

	public void setKycAvailable(boolean isKycAvailable) {
		this.isKycAvailable = isKycAvailable;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public KycStatus getKycVerified() {
		return KycVerified;
	}

	public void setKycVerified(KycStatus kycVerified) {
		KycVerified = kycVerified;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	public AmlCftStatus getAmlCftStatus() {
		return amlCftStatus;
	}

	public void setAmlCftStatus(AmlCftStatus amlCftStatus) {
		this.amlCftStatus = amlCftStatus;
	}

	@Override
	public String toString() {
		return "AccountStatuses [msisdn=" + msisdn + ", isKycAvailable=" + isKycAvailable + ", KycVerified="
				+ KycVerified + ", amlCftStatus=" + amlCftStatus + ", accountStatus=" + accountStatus
				+ ", transactionStatus=" + transactionStatus + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", updateReason=" + updateReason + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
