package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.UserStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO implements Serializable {
	private String message;
	private String statusCode;
	private UserStatus dsrProfileStatus;
	private List<RetailersDTO> retailers;
	private List<PagesDTO> retailerInfo;
	private List<PagesDTO> dsrInfo;
	private List<PagesDTO> menu;
	private Map<String, String> authInfo;
	private Integer totalCount;
	private QuestionAnswerDTO questionAnswerDTO;
	private List<QuestionAnswerDTO> questionAnswerDTOs;
	private String kycRejectedMessage;
	private String profileCompeltion;
	private String name;
	private String email;
	private Long userId;
	private String username;

	public List<QuestionAnswerDTO> getQuestionAnswerDTOs() {
		return questionAnswerDTOs;
	}

	public void setQuestionAnswerDTOs(List<QuestionAnswerDTO> questionAnswerDTOs) {
		this.questionAnswerDTOs = questionAnswerDTOs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public UserStatus getDsrProfileStatus() {
		return dsrProfileStatus;
	}

	public void setDsrProfileStatus(UserStatus dsrProfileStatus) {
		this.dsrProfileStatus = dsrProfileStatus;
	}

	public List<RetailersDTO> getRetailers() {
		return retailers;
	}

	public void setRetailers(List<RetailersDTO> retailers) {
		this.retailers = retailers;
	}

	@Override
	public String toString() {
		return "ResponseDTO [message=" + message + ", statusCode=" + statusCode + ", dsrProfileStatus="
				+ dsrProfileStatus + ", retailers=" + retailers + ", retailerInfo=" + retailerInfo + "]";
	}

	public Map<String, String> getAuthInfo() {
		return authInfo;
	}

	public void setAuthInfo(Map<String, String> authInfo) {
		this.authInfo = authInfo;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public QuestionAnswerDTO getQuestionAnswerDTO() {
		return questionAnswerDTO;
	}

	public void setQuestionAnswerDTO(QuestionAnswerDTO questionAnswerDTO) {
		this.questionAnswerDTO = questionAnswerDTO;
	}

	public List<PagesDTO> getRetailerInfo() {
		return retailerInfo;
	}

	public void setRetailerInfo(List<PagesDTO> retailerInfo) {
		this.retailerInfo = retailerInfo;
	}

	public List<PagesDTO> getDsrInfo() {
		return dsrInfo;
	}

	public void setDsrInfo(List<PagesDTO> dsrInfo) {
		this.dsrInfo = dsrInfo;
	}

	public String getKycRejectedMessage() {
		return kycRejectedMessage;
	}

	public void setKycRejectedMessage(String kycRejectedMessage) {
		this.kycRejectedMessage = kycRejectedMessage;
	}

	public String getProfileCompeltion() {
		return profileCompeltion;
	}

	public void setProfileCompeltion(String profileCompeltion) {
		this.profileCompeltion = profileCompeltion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<PagesDTO> getMenu() {
		return menu;
	}

	public void setMenu(List<PagesDTO> menu) {
		this.menu = menu;
	}

}
