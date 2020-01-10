package co.yabx.admin.portal.app.connect.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "users", indexes = { @Index(name = "msisdn", columnList = "msisdn"),
		@Index(name = "partner_id", columnList = "partner_id"),
		@Index(name = "customer_id", columnList = "customer_id"), @Index(name = "email", columnList = "email"),
		@Index(name = "reset_password_token", columnList = "reset_password_token") })

public class Users implements Serializable {

	private static final long serialVersionUID = -690139363185L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "reset_password_token", unique = true, columnDefinition = "varchar(255)")
	private String resetPasswordToken;

	@Column(name = "encrypted_password", nullable = false, columnDefinition = "varchar(255) default '' ")
	private String encryptedPassword;

	@Column(name = "msisdn")
	private String msisdn;

	@Column(name = "email")
	private String email;

	@Column(name = "reset_password_sent_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date reset_password_sent_at;

	@Column(name = "remember_created_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date remember_created_at;

	@Column(name = "sign_in_count")
	private int signInCount;

	@Column(name = "provider")
	private String provider;

	@Column(name = "current_sign_in_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date current_sign_in_at;

	@Column(name = "last_sign_in_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date last_sign_in_at;

	@Column(name = "current_sign_in_ip")
	private String currentSignInIp;

	@Column(name = "lastSignInIp")
	private String last_sign_in_ip;

	@Column(name = "uid", columnDefinition = "varchar(255) ")
	private String uid;

	@Column(name = "partner_id")
	private Integer partnerId;

	@Column(name = "created_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(name = "partners")
	private String partners;

	@Column(name = "groups")
	private String groups;

	@Column(name = "name")
	private String name;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "last_session_id")
	private Integer lastSessionId;

	@Column(name = "last_active_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date lastActiveAt;

	@Column(name = "last_session_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date lastSessionAt;

	@Column(name = "dashboard")
	private String dashboard;

	@Column(name = "failed_attempts")
	private int failedAttempts;

	@Column(name = "locked_at")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date lockedAt;

	@Column(name = "username")
	private String username;

	@Column(name = "session_token", length = 255)
	private String sessionToken;

	@Column(name = "two_factor_auth", columnDefinition = "TINYINT(1)")
	private Boolean twoFactorAuth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getReset_password_sent_at() {
		return reset_password_sent_at;
	}

	public void setReset_password_sent_at(Date reset_password_sent_at) {
		this.reset_password_sent_at = reset_password_sent_at;
	}

	public Date getRemember_created_at() {
		return remember_created_at;
	}

	public void setRemember_created_at(Date remember_created_at) {
		this.remember_created_at = remember_created_at;
	}

	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Date getCurrent_sign_in_at() {
		return current_sign_in_at;
	}

	public void setCurrent_sign_in_at(Date current_sign_in_at) {
		this.current_sign_in_at = current_sign_in_at;
	}

	public Date getLast_sign_in_at() {
		return last_sign_in_at;
	}

	public void setLast_sign_in_at(Date last_sign_in_at) {
		this.last_sign_in_at = last_sign_in_at;
	}

	public String getCurrentSignInIp() {
		return currentSignInIp;
	}

	public void setCurrentSignInIp(String currentSignInIp) {
		this.currentSignInIp = currentSignInIp;
	}

	public String getLast_sign_in_ip() {
		return last_sign_in_ip;
	}

	public void setLast_sign_in_ip(String last_sign_in_ip) {
		this.last_sign_in_ip = last_sign_in_ip;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
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

	public String getPartners() {
		return partners;
	}

	public void setPartners(String partners) {
		this.partners = partners;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getLastSessionId() {
		return lastSessionId;
	}

	public void setLastSessionId(Integer lastSessionId) {
		this.lastSessionId = lastSessionId;
	}

	public Date getLastActiveAt() {
		return lastActiveAt;
	}

	public void setLastActiveAt(Date lastActiveAt) {
		this.lastActiveAt = lastActiveAt;
	}

	public Date getLastSessionAt() {
		return lastSessionAt;
	}

	public void setLastSessionAt(Date lastSessionAt) {
		this.lastSessionAt = lastSessionAt;
	}

	public String getDashboard() {
		return dashboard;
	}

	public void setDashboard(String dashboard) {
		this.dashboard = dashboard;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public Date getLockedAt() {
		return lockedAt;
	}

	public void setLockedAt(Date lockedAt) {
		this.lockedAt = lockedAt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public Boolean getTwoFactorAuth() {
		return twoFactorAuth;
	}

	public void setTwoFactorAuth(Boolean twoFactorAuth) {
		this.twoFactorAuth = twoFactorAuth;
	}

}
