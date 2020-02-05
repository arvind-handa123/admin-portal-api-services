package co.yabx.admin.portal.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.yabx.admin.portal.app.connect.service.EntityManagerService;
import co.yabx.admin.portal.app.connect.service.SupersetService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;
import co.yabx.admin.portal.app.service.AdminPortalService;
import co.yabx.admin.portal.app.service.AdminStorageService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1")
public class SupersetController {

	@Autowired
	private SupersetService supersetService;

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AdminPortalService adminPortalService;

	@Autowired
	private FieldRemarkService fieldRemarkService;

	@Autowired
	private AdminStorageService adminStorageService;

	@Autowired
	private AuthInfoService authInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SupersetController.class);

	@RequestMapping(value = "/superset/url", method = RequestMethod.GET)
	public ResponseEntity<?> pages(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "link", required = true) String link, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/superset/url request recieved for username={}, link={}", username, link);
			return new ResponseEntity<>(supersetService.getSupersetURL(link), HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid authentication", HttpStatus.UNAUTHORIZED);

	}

}
