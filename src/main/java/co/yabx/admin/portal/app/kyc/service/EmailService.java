package co.yabx.admin.portal.app.kyc.service;

import co.yabx.admin.portal.app.enums.ActionType;
import co.yabx.admin.portal.app.kyc.entities.OTP;

public interface EmailService {

	void sendOTP(OTP otp, String mail, ActionType actionType);

}
