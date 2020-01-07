package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.ProductName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDocumentsDTO implements Serializable {

	private Long id;

	private String documentFor;

	String documentName;

	private String fileName;

	private ProductName productName;

	private Date createdAt;

	private Date updatedAt;

	private Integer displayOrder;

	private String documentType;

	private String attachmentType;

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

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

}
