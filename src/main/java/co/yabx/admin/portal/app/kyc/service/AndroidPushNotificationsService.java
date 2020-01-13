package co.yabx.admin.portal.app.kyc.service;

import org.springframework.http.ResponseEntity;

import co.yabx.admin.portal.app.enums.KycStatus;

/**
 * 
 * @author Asad.ali
 *
 */
public interface AndroidPushNotificationsService {

	ResponseEntity<String> sendNotificationToDevice(String deviceId, String title, String message, String dataToSend);

	void notifyDSR(String msisdn, String username, KycStatus status);

	void sendNotificationToUser(String msisdn, String title, String message, String data);

}
