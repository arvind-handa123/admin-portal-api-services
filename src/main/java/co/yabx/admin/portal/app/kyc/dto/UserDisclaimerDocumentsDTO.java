package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDisclaimerDocumentsDTO implements Serializable {

	private Long userId;

	private String msisdn;

	private boolean isDisclaimerDocRecieved;

	private List<ProductDocumentsDTO> disclaimerDocuments;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public boolean isDisclaimerDocRecieved() {
		return isDisclaimerDocRecieved;
	}

	public void setDisclaimerDocRecieved(boolean isDisclaimerDocRecieved) {
		this.isDisclaimerDocRecieved = isDisclaimerDocRecieved;
	}

	public List<ProductDocumentsDTO> getDisclaimerDocuments() {
		return disclaimerDocuments;
	}

	public void setDisclaimerDocuments(List<ProductDocumentsDTO> disclaimerDocuments) {
		this.disclaimerDocuments = disclaimerDocuments;
	}

}
