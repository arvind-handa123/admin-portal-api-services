package co.yabx.admin.portal.app.kyc.service;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author Asad.ali
 *
 */
public interface AndroidPushNotificationsService {

	ResponseEntity<String> sendNotificationToDevice(String deviceId, String title, String message, String dataToSend);

}
