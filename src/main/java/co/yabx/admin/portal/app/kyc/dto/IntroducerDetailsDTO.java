package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.Relationship;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntroducerDetailsDTO implements Serializable {

	private Long id;

	private String msisdn;

	private String name;

	private Long accountNumber;

	private Relationship relationship;

	private boolean isSignatureVerified;

	private AttachmentDetailsDTO signature;

	private Date createdAt;

	private Date updatedAt;

	private String createdBy;

	private String updatedBy;

	private UserDTO user;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public boolean isSignatureVerified() {
		return isSignatureVerified;
	}

	public void setSignatureVerified(boolean isSignatureVerified) {
		this.isSignatureVerified = isSignatureVerified;
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

	public AttachmentDetailsDTO getSignature() {
		return signature;
	}

	public void setSignature(AttachmentDetailsDTO signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "IntroducerDetailsDTO [id=" + id + ", msisdn=" + msisdn + ", name=" + name + ", accountNumber="
				+ accountNumber + ", relationship=" + relationship + ", isSignatureVerified=" + isSignatureVerified
				+ ", signature=" + signature + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", user=" + user + "]";
	}

}
