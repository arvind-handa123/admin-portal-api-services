package co.yabx.admin.portal.app.kyc.service;

import java.util.Date;

import co.yabx.admin.portal.app.enums.OtpGroup;
import co.yabx.admin.portal.app.enums.OtpType;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;
import co.yabx.admin.portal.app.kyc.entities.OTP;

/**
 * 
 * @author Asad.ali
 *
 */
public interface OtpService {

	OTP generateAndPersistOTP(Long user, OtpType otpType, Date expiryTime, OtpGroup otpGroup);

	DSRUser verifyOtp(DSRUser dsrUser, String dsrMSISDN, OtpType sms);

	String findOtpByMsisdn(String msisdn);

}
