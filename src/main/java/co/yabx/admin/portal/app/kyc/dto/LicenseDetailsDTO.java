package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.LicenseType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseDetailsDTO implements Serializable {

	private Long id;

	private String licenseNumber;

	private String licenseExpiryDate;

	private String licenseIssuingAuthority;

	private LicenseType licenseType;

	private Date createdAt;

	private Date updatedAt;

	private String createdBy;

	private String updatedBy;

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

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseExpiryDate() {
		return licenseExpiryDate;
	}

	public void setLicenseExpiryDate(String licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
	}

	public String getLicenseIssuingAuthority() {
		return licenseIssuingAuthority;
	}

	public void setLicenseIssuingAuthority(String licenseIssuingAuthority) {
		this.licenseIssuingAuthority = licenseIssuingAuthority;
	}

	public LicenseType getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	@Override
	public String toString() {
		return "LicenseDetailsDTO [id=" + id + ", licenseNumber=" + licenseNumber + ", licenseExpiryDate="
				+ licenseExpiryDate + ", licenseIssuingAuthority=" + licenseIssuingAuthority + ", licenseType="
				+ licenseType + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}

}
