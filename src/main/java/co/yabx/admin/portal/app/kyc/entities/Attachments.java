package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import co.yabx.admin.portal.app.enums.DocumentSide;

/**
 * 
 * @author Asad Ali
 *
 */
@Entity
@Table(name = "attachments", indexes = { @Index(name = "document_side", columnList = "document_side") })
public class Attachments implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "document_side")
	private DocumentSide documentSide;

	@Column(name = "document_url")
	private String documentUrl;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "attachment_details_id")
	AttachmentDetails attachmentDetails;

	public AttachmentDetails getAttachmentDetails() {
		return attachmentDetails;
	}

	public void setAttachmentDetails(AttachmentDetails attachmentDetails) {
		this.attachmentDetails = attachmentDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentSide getDocumentSide() {
		return documentSide;
	}

	public void setDocumentSide(DocumentSide documentSide) {
		this.documentSide = documentSide;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
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
}
