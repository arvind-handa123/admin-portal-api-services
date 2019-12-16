package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.kyc.entities.DeviceInformations;
import co.yabx.admin.portal.app.kyc.repositories.DeviceTokenRepository;
import co.yabx.admin.portal.app.kyc.service.UserDeviceService;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class UserDeviceServiceImpl implements UserDeviceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDeviceServiceImpl.class);

	@Autowired
	private DeviceTokenRepository deviceTokenRepository;

	@Override
	@Transactional
	public DeviceInformations saveDeviceTokenInMysql(String token, String deviceType, String deviceId,
			String instanceId, String voipToken, String deviceModel, String deviceBrand, String deviceManufacturer) {
		DeviceInformations deviceToken = deviceTokenRepository.findByDeviceId(deviceId);
		if (deviceToken != null && deviceToken.getDeviceToken() != null && deviceToken.getDeviceToken().equals(token)
				&& deviceToken.isActive()) {
			LOGGER.debug("deviceToken already updated {}", deviceId);
			return deviceToken;
		}

		if (deviceToken == null) {
			LOGGER.debug("request insert deviceToken {}", deviceId);
			deviceToken = new DeviceInformations();
			deviceToken.setDeviceId(deviceId);
		}

		if (instanceId == null) {
			instanceId = "UNKNOWN";
		}

		deviceToken.setVoipToken(voipToken);
		deviceToken.setInstanceId(instanceId);
		deviceToken.setActive(true);
		deviceToken.setDeviceType(DeviceInformations.DeviceType.valueOf(deviceType));
		deviceToken.setDeviceToken(token);
		if (deviceModel != null) {
			deviceToken.setDeviceModel(deviceModel);
		}
		if (deviceBrand != null) {
			deviceToken.setDeviceBrand(deviceBrand);
		}
		if (deviceManufacturer != null) {
			deviceToken.setDeviceManufacturer(deviceManufacturer);
		}

		return deviceTokenRepository.saveAndFlush(deviceToken);
	}

	@Override
	public String updateExpiredDeviceTokenInMysql(String token, String deviceType, long expiredDate) {
		List<DeviceInformations> deviceTokenList = deviceTokenRepository.findByDeviceToken(token);
		String deviceId = "";
		for (DeviceInformations deviceToken : deviceTokenList) {
			if (deviceToken != null && deviceToken.getDeviceToken() != null && deviceToken.isActive()
					&& deviceToken.getUpdatedAt().getTime() < expiredDate) {
				deviceToken.setActive(false);
				deviceTokenRepository.save(deviceToken);
				return deviceToken.getDeviceId();
			} else {
				LOGGER.warn("can not set expired  deviceToken {}| expiredDate {}", token, expiredDate);
			}
			deviceId = deviceToken.getDeviceId();
		}
		return deviceId;
	}

}