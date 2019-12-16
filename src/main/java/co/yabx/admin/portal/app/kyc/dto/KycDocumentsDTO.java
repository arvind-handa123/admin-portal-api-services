package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KycDocumentsDTO implements Serializable {

	private Long id;

	private String msisdn;

	private String documentSide;

	private String documentUrl;

	private Date snapTime;

	private boolean isSelfie;

	private Date createdAt;

	private Date updatedAt;

	private String documentNumber;

	private String documentType;

	private Long documentIssueDate;

	private Long documentExpiryDate;
	
	private String placeOfIssue;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSnapTime() {
		return snapTime;
	}

	public void setSnapTime(Date snapTime) {
		this.snapTime = snapTime;
	}

	public boolean isSelfie() {
		return isSelfie;
	}

	public void setSelfie(boolean isSelfie) {
		this.isSelfie = isSelfie;
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

	public String getDocumentSide() {
		return documentSide;
	}

	public void setDocumentSide(String documentSide) {
		this.documentSide = documentSide;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Long getDocumentIssueDate() {
		return documentIssueDate;
	}

	public void setDocumentIssueDate(Long documentIssueDate) {
		this.documentIssueDate = documentIssueDate;
	}

	public Long getDocumentExpiryDate() {
		return documentExpiryDate;
	}

	public void setDocumentExpiryDate(Long documentExpiryDate) {
		this.documentExpiryDate = documentExpiryDate;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Override
	public String toString() {
		return "KycDocumentsDTO [id=" + id + ", msisdn=" + msisdn + ", documentSide=" + documentSide + ", documentUrl="
				+ documentUrl + ", snapTime=" + snapTime + ", isSelfie=" + isSelfie + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", documentNumber=" + documentNumber + ", documentType=" + documentType
				+ ", documentIssueDate=" + documentIssueDate + ", documentExpiryDate=" + documentExpiryDate + "]";
	}

	public String getPlaceOfIssue() {
		return placeOfIssue;
	}

	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

}
