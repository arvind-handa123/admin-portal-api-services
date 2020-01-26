package co.yabx.admin.portal.app.kyc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.yabx.admin.portal.app.kyc.service.AdminService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.OtpService;

/**
 * 
 * @author Asad.ali
 *
 */
@Controller
@CrossOrigin("*")
@RequestMapping(value = "/v1")
public class AdminController {

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private OtpService otpService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/appConfig/refresh", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> reloadAppConfiguration(
			@RequestParam(value = "secret_key", required = true) String secret_key) {
		if (secret_key.equals(appConfigService.getProperty("APP_CONFIG_RECONCILE_API_PASSWORD", "magic@yabx"))) {
			appConfigService.refresh();
			LOGGER.info("AppConfiguration has been refreshed");
			return new ResponseEntity<>(HttpStatus.OK);

		}
		return new ResponseEntity<>("Invalid secret key", HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/admin/generateAuthToken", method = RequestMethod.GET)
	public ResponseEntity<?> generateAuthTokens(@RequestParam("msisdn") String msisdn,
			@RequestParam(value = "secret_key", required = true) String secret_key) {
		if (secret_key.equals(appConfigService.getProperty("GET_AUTH_TOKEN_API_PASSWORD", "magic@yabx"))) {
			Map<String, String> jsonResponse = adminService.getAuthToken(msisdn);
			if (jsonResponse == null)
				return new ResponseEntity<>("user not found or msisdn is null for this user", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid secret key", HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/admin/otp", method = RequestMethod.GET)
	public ResponseEntity<?> findOTP(@RequestParam("msisdn") String msisdn,
			@RequestParam(value = "secret_key", required = true) String secret_key) {
		if (secret_key.equals(appConfigService.getProperty("GET_AUTH_TOKEN_API_PASSWORD", "magic@yabx"))) {
			Map<String, String> jsonResponse = new HashMap<String, String>();
			jsonResponse.put("OTP", otpService.findOtpByMsisdn(msisdn));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid secret key", HttpStatus.UNAUTHORIZED);

	}

	
}
