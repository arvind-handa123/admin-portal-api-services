package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.DocumentSide;

/**
 * 
 * @author Asad Ali
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentsDTO implements Serializable {

	private Long id;

	private DocumentSide documentSide;

	private byte[] byteArray;

	private Date createdAt;

	private Date updatedAt;

	private AttachmentDetailsDTO attachmentDetails;

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

	public AttachmentDetailsDTO getAttachmentDetails() {
		return attachmentDetails;
	}

	public void setAttachmentDetails(AttachmentDetailsDTO attachmentDetails) {
		this.attachmentDetails = attachmentDetails;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] bs) {
		this.byteArray = bs;
	}

}
