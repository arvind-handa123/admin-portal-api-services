package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.KycStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DsrProfileDTO implements Serializable {
	private KycStatus kycStatus;
	private Long retailerId;
	private int startIndex;
	private int endIndex;
	private String dsrMSISDN;
	private List<AddressDetailsDTO> addressDetailsDTO;
	private WorkEducationDetailsDTO workEducationDetailsDTO;
	private PagesDTO pageResponse;
	private Long userId;

	public String getDsrMSISDN() {
		return dsrMSISDN;
	}

	public void setDsrMSISDN(String dsrMSISDN) {
		this.dsrMSISDN = dsrMSISDN;
	}

	public List<AddressDetailsDTO> getAddressDetailsDTO() {
		return addressDetailsDTO;
	}

	public void setAddressDetailsDTO(List<AddressDetailsDTO> addressDetailsDTO) {
		this.addressDetailsDTO = addressDetailsDTO;
	}

	public WorkEducationDetailsDTO getWorkEducationDetailsDTO() {
		return workEducationDetailsDTO;
	}

	public void setWorkEducationDetailsDTO(WorkEducationDetailsDTO workEducationDetailsDTO) {
		this.workEducationDetailsDTO = workEducationDetailsDTO;
	}

	public PagesDTO getPageResponse() {
		return pageResponse;
	}

	public void setPageResponse(PagesDTO pageResponse) {
		this.pageResponse = pageResponse;
	}

	public KycStatus getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(KycStatus kycStatus) {
		this.kycStatus = kycStatus;
	}

	public Long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
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

	@Override
	public String toString() {
		return "DsrProfileDTO [kycStatus=" + kycStatus + ", retailerId=" + retailerId + ", startIndex=" + startIndex
				+ ", endIndex=" + endIndex + ", dsrMSISDN=" + dsrMSISDN + ", addressDetailsDTO=" + addressDetailsDTO
				+ ", workEducationDetailsDTO=" + workEducationDetailsDTO + ", pageResponse=" + pageResponse + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
