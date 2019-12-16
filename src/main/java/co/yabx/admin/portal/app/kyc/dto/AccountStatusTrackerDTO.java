package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.AccountStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountStatusTrackerDTO implements Serializable {

	private String msisdn;

	private AccountStatus fromStatus;

	private AccountStatus toStatus;

	private String reason;

	private Date changedAt;

	private String createdBy;

	private String updatedBy;

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public AccountStatus getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(AccountStatus fromStatus) {
		this.fromStatus = fromStatus;
	}

	public AccountStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(AccountStatus toStatus) {
		this.toStatus = toStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
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

	@Override
	public String toString() {
		return "AccountStatusTrackerDTO [msisdn=" + msisdn + ", fromStatus=" + fromStatus + ", toStatus=" + toStatus
				+ ", reason=" + reason + ", changedAt=" + changedAt + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + "]";
	}

}
