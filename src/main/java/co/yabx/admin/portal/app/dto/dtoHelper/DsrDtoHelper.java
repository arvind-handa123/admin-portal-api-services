package co.yabx.admin.portal.app.dto.dtoHelper;

import java.io.Serializable;

import co.yabx.admin.portal.app.enums.UserStatus;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;

public class DsrDtoHelper implements Serializable {

	public static ResponseDTO getLoginDTO(String msisdn, String status, String statusCode,
			UserStatus dsrProfileStatus) {
		ResponseDTO loginDTO = new ResponseDTO();
		loginDTO.setMessage(status);
		loginDTO.setStatusCode(statusCode);
		loginDTO.setDsrProfileStatus(dsrProfileStatus);
		return loginDTO;
	}
}
