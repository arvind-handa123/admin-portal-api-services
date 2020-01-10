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
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;
import co.yabx.admin.portal.app.service.AdminPortalService;
import co.yabx.admin.portal.app.service.AdminStorageService;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1")
public class QueryController {

	@Autowired
	private EntityManagerService entityManagerService;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryController.class);

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ResponseEntity<?> pages(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "filename", required = true) String filename, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(entityManagerService.executeQuery(filename), HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid authentication", HttpStatus.UNAUTHORIZED);

	}

}
