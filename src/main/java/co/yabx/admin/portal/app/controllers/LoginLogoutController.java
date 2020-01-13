package co.yabx.admin.portal.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.yabx.admin.portal.app.dto.LoginDto;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.service.AdminPortalService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1")
public class LoginLogoutController {

	@Autowired
	private AuthInfoService authInfoService;

	@Autowired
	private AdminPortalService adminPortalService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogoutController.class);

	@RequestMapping(value = "/admin/kyc/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> loginRM(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		LOGGER.info("/admin/kyc/login request received for loginDto={}", loginDto);
		if (loginDto != null) {
			return new ResponseEntity<>(adminPortalService.login(loginDto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/admin/kyc/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> logout(@RequestParam String username, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		LOGGER.info("/admin/kyc/logout request received for username={}", username);
		if (neitherNullNorEmpty(username) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			ResponseDTO loginDTO = adminPortalService.logout(username);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/admin/kyc/password/change", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> resetPassword(@RequestParam String username, @RequestParam String currentPassword,
			@RequestParam String newPassword, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		LOGGER.info("/admin/kyc/password/change request received for username={}", username);
		if (neitherNullNorEmpty(username) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			ResponseDTO loginDTO = adminPortalService.changePassword(username, currentPassword, newPassword);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	private boolean neitherNullNorEmpty(String data) {
		return data != null && !data.isEmpty();
	}

	private boolean isAuthorised(String username, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse);
	}
}
