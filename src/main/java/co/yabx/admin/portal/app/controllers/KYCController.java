package co.yabx.admin.portal.app.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import co.yabx.admin.portal.app.admin.entities.Pages;
import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.service.AdminPortalService;

@Controller
@CrossOrigin
@RequestMapping(value = "/v1")
public class KYCController {

	@Autowired
	private KYCService kycService;

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AdminPortalService adminPortalService;

	private static final Logger LOGGER = LoggerFactory.getLogger(KYCController.class);

	@RequestMapping(value = "/kyc/status", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getRetailersProfile(@RequestParam KycStatus kycStatus,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/kyc/status request received for status={}", kycStatus);
		if (kycStatus == null) {
			return new ResponseEntity<>(kycService.findAllRetailers(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(kycService.fetchRetailersByKycStatus(kycStatus), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/kyc/pages", method = RequestMethod.GET)
	public ResponseEntity<?> findOTP(@RequestParam("productId") Long productId,
			@RequestParam(value = "secret_key", required = true) String secret_key) {
		if (secret_key.equals(appConfigService.getProperty("GET_AUTH_TOKEN_API_PASSWORD", "magic@yabx-admin-portal"))) {
			List<PagesDTO> pages = adminPortalService.fetchProductDetails(productId);
			return new ResponseEntity<>(pages, HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid secret key", HttpStatus.UNAUTHORIZED);

	}

}
