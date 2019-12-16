package co.yabx.admin.portal.app.kyc.service;

import co.yabx.admin.portal.app.kyc.dto.BusinessDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.LiabilitiesDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.QuestionAnswerDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.dto.RetailerRequestDTO;
import co.yabx.admin.portal.app.kyc.dto.UserDTO;

public interface RetailerService {

	ResponseDTO retailerDetails(String dsrMsisdn, Long retailerId);

	ResponseDTO submitRetailerProfile(RetailerRequestDTO retailerRequestDTO) throws Exception;

	ResponseDTO submitRetailerNomineeProfile(UserDTO userDTO);

	ResponseDTO submitRetailerBusinessInfo(BusinessDetailsDTO businessDetailsDTO);

	ResponseDTO submitLiabilitiesInfo(LiabilitiesDetailsDTO liabilitiesDetailsDTO);

	ResponseDTO getRetailerQuestion(Integer questionId);

	ResponseDTO getRetailerAllQuestions(String retailerId);

	ResponseDTO persistRetailerAnswer(QuestionAnswerDTO questionAnswerDTO);

	ResponseDTO searchRetailerByDsr(String dsrMsisdn, String retailerId);

	ResponseDTO submitRetailerProfile(UserDTO userDTO);

	ResponseDTO getSummaries(String dsrMSISDN, Integer startIndex, Integer endIndex);

	ResponseDTO submitKyc(String dsrMsisdn, Long retailerId);

}
