package co.yabx.admin.portal.app.dto.dtoHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.yabx.admin.portal.app.kyc.dto.QuestionAnswerDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;

public class QuestionAnswerDTOHelper implements Serializable {

	public static ResponseDTO getquestion(ResponseDTO responseDTO) {
		QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
		questionAnswerDTO.setQuestion("question");
		questionAnswerDTO.setControlType("Radio");
		List<String> options = new ArrayList<String>();
		options.add("Answer 1");
		options.add("Answer 2");
		options.add("Answer 3");
		questionAnswerDTO.setOptions(options);
		responseDTO.setQuestionAnswerDTO(questionAnswerDTO);
		return responseDTO;
	}

	public static ResponseDTO getAllquestion(ResponseDTO responseDTO) {
		List<QuestionAnswerDTO> questionAnswerDTOs = new ArrayList<QuestionAnswerDTO>();
		QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
		questionAnswerDTO.setQuestion("question 1");
		questionAnswerDTO.setControlType("Radio");
		List<String> options = new ArrayList<String>();
		options.add("Answer 1");
		options.add("Answer 2");
		options.add("Answer 3");
		questionAnswerDTO.setOptions(options);
		questionAnswerDTOs.add(questionAnswerDTO);
		QuestionAnswerDTO questionAnswerDTO1 = new QuestionAnswerDTO();
		questionAnswerDTO1.setQuestion("question 2");
		questionAnswerDTO1.setControlType("Radio");
		List<String> options1 = new ArrayList<String>();
		options1.add("Answer 1");
		options1.add("Answer 2");
		options1.add("Answer 3");
		questionAnswerDTO1.setOptions(options1);
		questionAnswerDTOs.add(questionAnswerDTO1);
		responseDTO.setQuestionAnswerDTOs(questionAnswerDTOs);
		return responseDTO;
	}
}
