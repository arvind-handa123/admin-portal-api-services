package co.yabx.admin.portal.app.kyc.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.yabx.admin.portal.app.kyc.entities.DeviceInformations;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.UserDeviceService;
import co.yabx.admin.portal.app.kyc.service.UserService;

@Controller
@RequestMapping(value = "/version/v1")
public class UserDeviceDataController {
	private static Logger LOGGER = LoggerFactory.getLogger(UserDeviceDataController.class);

	@Resource
	private UserDeviceService userDeviceService;

	@Resource
	private UserService userService;

	@Resource
	private AuthInfoService authInfoService;

	@Resource
	private UserRepository userRepository;

	@RequestMapping(value = "/device/data", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveDeviceToken(@RequestParam("msisdn") String msisdn,
			@RequestParam("deviceToken") String token, @RequestParam("deviceId") String deviceId,
			@RequestParam("deviceType") String deviceType,
			@RequestParam(value = "voipToken", required = false) String voipToken,
			@RequestParam(value = "instanceId", required = false, defaultValue = "UNKNOWN") String instanceId,
			@RequestParam(value = "source", required = false, defaultValue = "UNKNOWN") String source,
			@RequestParam(value = "utm_source", required = false, defaultValue = "UNKNOWN") String utmSource,
			@RequestParam(value = "utm_medium", required = false, defaultValue = "UNKNOWN") String utmMedium,
			@RequestParam(value = "utm_term", required = false, defaultValue = "UNKNOWN") String utmTerm,
			@RequestParam(value = "utm_content", required = false, defaultValue = "UNKNOWN") String utmContent,
			@RequestParam(value = "utm_campaign", required = false, defaultValue = "UNKNOWN") String utmCampaign,
			@RequestParam(value = "deviceModel", required = false) String deviceModel,
			@RequestParam(value = "deviceBrand", required = false) String deviceBrand,
			@RequestParam(value = "deviceManufacturer", required = false) String deviceManufacturer,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info(
				"Device Data update request for msisdn={},deviceId {}, token {},  deviceType {}, source {}, utm_source {}, utm_medium {}, utm_term {}, utm_content {}, utm_campaign {}, instanceId={} ",
				msisdn, deviceId, token, deviceType, source, utmSource, utmMedium, utmTerm, utmContent, utmCampaign,
				instanceId);
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			User user = userService.getDSRByMsisdn(msisdn);
			if (user != null) {
				DeviceInformations deviceToken = null;
				if (user.getDeviceInformation() == null) {
					deviceToken = userDeviceService.saveDeviceTokenInMysql(token, deviceType, deviceId, instanceId,
							voipToken, deviceModel, deviceBrand, deviceManufacturer);
					user.setDeviceInformation(deviceToken);
					userRepository.save(user);
				} else {
					deviceToken = user.getDeviceInformation();
					DeviceInformations updateDeviceToken = userDeviceService.saveDeviceTokenInMysql(token, deviceType,
							deviceId, instanceId, voipToken, deviceModel, deviceBrand, deviceManufacturer);
					if (!updateDeviceToken.getDeviceToken().equalsIgnoreCase(deviceToken.getDeviceToken())) {
						user.setDeviceInformation(deviceToken);
						userRepository.save(user);
					}

				}
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/device/token/expired/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveDeviceTokenExpired(@RequestParam("msisdn") String msisdn,
			@RequestParam("deviceToken") String token, @RequestParam("expiredDate") long expiredDate,
			@RequestParam("deviceType") String deviceType, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		LOGGER.info("Device Data update request for expiredDate {}, token {}, deviceType {}", expiredDate, token,
				deviceType);
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			userDeviceService.updateExpiredDeviceTokenInMysql(token, deviceType, expiredDate);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.UNAUTHORIZED);

	}

}