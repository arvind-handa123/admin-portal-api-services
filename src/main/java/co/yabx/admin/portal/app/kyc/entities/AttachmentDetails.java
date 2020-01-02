package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.enums.DocumentType;


/**
 * 
 * @author Asad Ali
 *
 */
@Entity
@Table(name = "attachment_details", indexes = { @Index(name = "attachment_type", columnList = "attachment_type"),
		@Index(name = "document_type", columnList = "document_type") })
public class AttachmentDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "snap_time")
	private Date snapTime;

	@Column(name = "is_Selfie", nullable = false, columnDefinition = "boolean default false")
	private boolean isSelfie;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "document_number")
	private String documentNumber;

	@Column(name = "document_type", length = 100, columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private DocumentType documentType;

	@Column(name = "attachment_type", length = 100, columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private AttachmentType attachmentType;

	@Column(name = "document_issue_date")
	private Long documentIssueDate;

	@Column(name = "issued_at_place")
	private String placeOfIssue;

	@Column(name = "document_expiry_date")
	private Long documentExpiryDate;

	@OneToMany(mappedBy = "attachmentDetails", cascade = {
			CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Attachments> attachments = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	User user;

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

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> attachments) {
		for (Attachments details : attachments) {
			details.setAttachmentDetails(this);
		}
		this.attachments = attachments;
	}

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

}
