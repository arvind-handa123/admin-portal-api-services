package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;



/**
 * 
 * @author Asad Ali
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentDetailsDTO implements Serializable {

	private Long id;

	private Date snapTime;

	private boolean isSelfie;

	private Date createdAt;

	private Date updatedAt;

	private String documentNumber;

	private String documentType;

	private Long documentIssueDate;

	private String placeOfIssue;

	private Long documentExpiryDate;

	private List<AttachmentsDTO> attachments;

	private String attachmentType;

	private UserDTO user;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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

	public String getPlaceOfIssue() {
		return placeOfIssue;
	}

	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<AttachmentsDTO> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentsDTO> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "AttachmentDetailsDTO [id=" + id + ", snapTime=" + snapTime + ", isSelfie=" + isSelfie + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", documentNumber=" + documentNumber + ", documentType="
				+ documentType + ", documentIssueDate=" + documentIssueDate + ", placeOfIssue=" + placeOfIssue
				+ ", documentExpiryDate=" + documentExpiryDate + ", attachments=" + attachments + ", user=" + user
				+ "]";
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

}
