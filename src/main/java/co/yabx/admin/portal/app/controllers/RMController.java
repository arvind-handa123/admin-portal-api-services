package co.yabx.admin.portal.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.kyc.service.StorageService;

@RestController
@RequestMapping(value = "/v1")
public class RMController {

	@Autowired
	private KYCService kycService;

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AuthInfoService authInfoService;

	@Autowired
	private FieldRemarkService fieldRemarkService;

	@Autowired
	private StorageService storageService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RMController.class);

	@RequestMapping(value = "/rm/kyc/approve", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> approve(@RequestParam String msisdn, @RequestParam String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/rm/kyc/approve request received for msisdn={},username={}", msisdn, username);
		if (neitherNullNorEmpty(msisdn) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(kycService.updateKycStatus(msisdn, username, KycStatus.APPROVED),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	private boolean isAuthorised(String username, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse);
	}

	private boolean neitherNullNorEmpty(String data) {
		return data != null && !data.isEmpty();
	}

	@RequestMapping(value = "/rm/kyc/reject", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> reject(@RequestParam String msisdn, @RequestParam String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/rm/kyc/reject request received for msisdn={},username={}", msisdn, username);
		if (neitherNullNorEmpty(msisdn) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(kycService.updateKycStatus(msisdn, username, KycStatus.REJECTED),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/review/initiate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> iniateReview(@RequestParam String msisdn, @RequestParam String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/rm/kyc/review/initiate request received for msisdn={},username={}", msisdn, username);
		if (neitherNullNorEmpty(msisdn) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(kycService.updateKycStatus(msisdn, username, KycStatus.UNDER_REVIEW),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/submit/re-send", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> reSend(@RequestParam String msisdn, @RequestParam String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/rm/kyc/submit/re-send request received for msisdn={},username={}", msisdn, username);
		if (neitherNullNorEmpty(msisdn) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(kycService.updateKycStatus(msisdn, username, KycStatus.IN_PROGRESS),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/loc/generate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> generateLOC(@RequestParam String msisdn, @RequestParam String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/rm/kyc/loc/generate request received for msisdn={},username={}", msisdn, username);
		if (neitherNullNorEmpty(msisdn) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(kycService.updateKycStatus(msisdn, username, KycStatus.LOC_GENERATED),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/rm/kyc/loc/issue", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> issueLoc(@RequestParam String msisdn, @RequestParam String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/rm/kyc/loc/issue request received for msisdn={},username={}", msisdn, username);
		if (neitherNullNorEmpty(msisdn) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(kycService.updateKycStatus(msisdn, username, KycStatus.LOC_ISSUED),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}
}
