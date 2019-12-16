package co.yabx.admin.portal.app.kyc.service;

import co.yabx.admin.portal.app.kyc.dto.DsrProfileDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.dto.VerifyOtpDTO;
import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;

public interface DSRService {

	ResponseDTO login(String msisdn);

	ResponseDTO submitDsrProfile(DsrProfileDTO dsrProfileDTO);

	void updateAuthInfo(DSRUser dsrUser, AuthInfo authInfo);

	ResponseDTO logout(String msisdn);

	ResponseDTO generateMailOTP(String mail);

	ResponseDTO verifyPhoneOTP(VerifyOtpDTO verifyOtpDTO);

	ResponseDTO verifyMail(VerifyOtpDTO verifyOtpDTO);

	ResponseDTO getDsrProfile(String msisdn);

}
