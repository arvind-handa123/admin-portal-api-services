package co.yabx.admin.portal.app.kyc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.dto.VerifyOtpDTO;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.DSRService;

/**
 * 
 * @author Asad.ali
 *
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/auth")
public class AuthenticationController {

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private DSRService dsrService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(value = "/dsr/otp/verify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> verifyOTP(@RequestBody VerifyOtpDTO verifyOtpDTO) {
		LOGGER.info("/auth/dsr/otp/verify request recieved with request body={}", verifyOtpDTO);
		ResponseDTO loginDTO = dsrService.verifyPhoneOTP(verifyOtpDTO);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/dsr/login/{msisdn}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> login(@PathVariable(value = "msisdn", required = true) String msisdn) {
		LOGGER.info("/auth/dsr/login/ request recieved for msisdn={}", msisdn);
		ResponseDTO loginDTO = dsrService.login(msisdn);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

}
