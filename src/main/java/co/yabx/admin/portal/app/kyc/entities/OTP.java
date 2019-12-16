package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;

import co.yabx.admin.portal.app.enums.OtpGroup;
import co.yabx.admin.portal.app.enums.OtpType;

@Entity
@Table(name = "otps")
public class OTP implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "user")
	private Long user;

	@Column(name = "otp")
	private String otp;

	@Column(name = "otp_type")
	private OtpType otpType;

	@Column(name = "otp_group")
	private OtpGroup otpGroup;

	@Column(name = "expiry_time")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date expiryTime;

	@Column(name = "error_log")
	private String errorLog = "";

	@Column(name = "success")
	private Boolean success = true;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_by")
	private String updatedBy;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public OtpType getOtpType() {
		return otpType;
	}

	public void setOtpType(OtpType otpType) {
		this.otpType = otpType;
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

	public OtpGroup getOtpGroup() {
		return otpGroup;
	}

	public void setOtpGroup(OtpGroup otpGroup) {
		this.otpGroup = otpGroup;
	}

	@Override
	public String toString() {
		return "OTP [id=" + id + ", user=" + user + ", otp=" + otp + ", otpType=" + otpType + ", otpGroup=" + otpGroup
				+ ", expiryTime=" + expiryTime + ", errorLog=" + errorLog + ", success=" + success + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}

}
