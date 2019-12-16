package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.KycStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetailerRequestDTO implements Serializable {
	private String dsrMSISDN;
	private KycStatus kycStatus;
	private Long retailerId;
	private int startIndex;
	private int endIndex;
	private PagesDTO pageResponse;
	private List<MultipartFile> files;
	private Long userId;

	public Long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
	}

	public PagesDTO getPageResponse() {
		return pageResponse;
	}

	public void setPageResponse(PagesDTO pageResponse) {
		this.pageResponse = pageResponse;
	}

	public String getDsrMSISDN() {
		return dsrMSISDN;
	}

	public void setDsrMSISDN(String dsrMSISDN) {
		this.dsrMSISDN = dsrMSISDN;
	}

	public KycStatus getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(KycStatus kycStatus) {
		this.kycStatus = kycStatus;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "RetailerRequestDTO [dsrMSISDN=" + dsrMSISDN + ", kycStatus=" + kycStatus + ", retailerId=" + retailerId
				+ ", startIndex=" + startIndex + ", endIndex=" + endIndex + ", pageResponse=" + pageResponse + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
