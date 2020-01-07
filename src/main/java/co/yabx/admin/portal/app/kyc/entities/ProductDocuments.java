package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.enums.ProductName;

@Entity
@Table(name = "product_documents", indexes = { @Index(name = "product_name", columnList = "product_name") })
public class ProductDocuments implements Serializable {

	private static final long serialVersionUID = -69011363185L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "document_for")
	private String documentFor;

	@Column(name = "document_name")
	String documentName;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "product_name", nullable = false, columnDefinition = "varchar(32)")
	@Enumerated(value = EnumType.STRING)
	private ProductName productName;

	@Column(name = "document_type", length = 100, columnDefinition = "varchar(32) ")
	private String documentType;

	@Column(name = "attachment_type", length = 100, columnDefinition = "varchar(32) ")
	@Enumerated(value = EnumType.STRING)
	private AttachmentType attachmentType;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "display_order")
	private Integer displayOrder;

	@Column(name = "active", columnDefinition = "boolean default true")
	private boolean active;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDocumentFor() {
		return documentFor;
	}

	public void setDocumentFor(String documentFor) {
		this.documentFor = documentFor;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ProductName getProductName() {
		return productName;
	}

	public void setProductName(ProductName productName) {
		this.productName = productName;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
