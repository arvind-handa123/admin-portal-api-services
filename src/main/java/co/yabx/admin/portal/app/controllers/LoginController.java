package co.yabx.admin.portal.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.yabx.admin.portal.app.dto.LoginDto;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.kyc.service.StorageService;
import co.yabx.admin.portal.app.service.AdminPortalService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/v1")
public class LoginController {

	@Autowired
	private KYCService kycService;

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AdminPortalService adminPortalService;

	@Autowired
	private FieldRemarkService fieldRemarkService;

	@Autowired
	private StorageService storageService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/admin/kyc/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> loginRM(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		LOGGER.info("/kyc/login request received for loginDto={}", loginDto);
		if (loginDto != null) {
			return new ResponseEntity<>(adminPortalService.login(loginDto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

}
