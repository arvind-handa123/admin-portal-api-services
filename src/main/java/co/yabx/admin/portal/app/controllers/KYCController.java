package co.yabx.admin.portal.app.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
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

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.RemarksDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.kyc.service.StorageService;
import co.yabx.admin.portal.app.service.AdminPortalService;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1")
public class KYCController {

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

	@Autowired
	private AuthInfoService authInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(KYCController.class);

	@RequestMapping(value = "/kyc/status", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getRetailersProfile(@RequestParam KycStatus kycStatus,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/kyc/status request received for status={}", kycStatus);
		if (kycStatus == null) {
			return new ResponseEntity<>(kycService.findAllRetailers(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(kycService.fetchRetailersByKycStatus(kycStatus, 0, 50), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/kyc/pages", method = RequestMethod.GET)
	public ResponseEntity<?> pages(@RequestParam("productId") Long productId,
			@RequestParam(value = "username", required = true) String username, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/kyc/pages request received for productId={},username={}", productId, username);
			ResponseDTO statusDto = new ResponseDTO();

			List<PagesDTO> pages = adminPortalService.fetchProductDetails(productId);
			if (pages != null && !pages.isEmpty()) {
				statusDto.setRetailerInfo(pages);
				statusDto.setStatusCode("200");
				statusDto.setMessage("SUCCESS");
				return new ResponseEntity<>(statusDto, HttpStatus.OK);
			}
			/*
			 * } catch (Exception e) { e.printStackTrace();
			 * LOGGER.error("Exception raised while readaing pages for product={},error={}",
			 * productId, e.getMessage()); statusDto.setStatusCode("501");
			 * statusDto.setMessage(e.getMessage()); return new ResponseEntity<>(statusDto,
			 * HttpStatus.OK); }
			 */
			return new ResponseEntity<>(statusDto, HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid authentication", HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/kyc/remark", method = RequestMethod.POST)
	public ResponseEntity<?> postRemark(@RequestParam("userId") Long user_id,
			@RequestParam(value = "remarkBy", required = true) String remarkBy,
			@RequestBody List<RemarksDTO> RemarksDTOList,
			@RequestParam(value = "username", required = true) String username, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/v1/kyc/remark request received for userId={},remarkBy={} and ramark={}", user_id, remarkBy,
					RemarksDTOList);
			ResponseDTO statusDto = new ResponseDTO();
			try {
				boolean status = fieldRemarkService.updateRemark(user_id, username, RemarksDTOList);
				if (status) {
					statusDto.setMessage("SUCCESS");
					statusDto.setStatusCode("200");
				} else {
					statusDto.setMessage("FAILED");
					statusDto.setStatusCode("501");
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Something went wrong while pushing remark for user={}, by ={}, error={}", user_id,
						remarkBy, e.getMessage());
				statusDto.setMessage(e.getMessage());
				statusDto.setStatusCode("501");
			}
			return new ResponseEntity<>(statusDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/kyc/retailer/image/file", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImages(@RequestParam("retailerId") Long retailerId,
			@RequestParam("filename") String filename, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(value = "username", required = true) String username) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/kyc/retailer/image/file request recieved for retailer={},  filename={}", retailerId,
					filename);
			try {
				if (filename != null && !filename.isEmpty()) {
					return new ResponseEntity<>(storageService.getImage(filename, retailerId, false), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("exception raised while fetching retailer={} image={},error={}", retailerId, filename,
						e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}
}
