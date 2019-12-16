package co.yabx.admin.portal.app.kyc.service;

import java.util.Map;

import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;

public interface AdminService {

	Map<String, String> getAuthToken(String token);

	Map<String, String> prepareTokenAndKey(DSRUser dsrUser, String msisdn);

	void resetYabxToken(AuthInfo authInfo);

}
