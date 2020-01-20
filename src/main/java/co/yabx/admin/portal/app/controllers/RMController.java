package co.yabx.admin.portal.app.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.kyc.service.StorageService;
import co.yabx.admin.portal.app.kyc.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1")
public class RMController {

	@Autowired
	private KYCService kycService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthInfoService authInfoService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private AppConfigService appConfigService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RMController.class);

	@RequestMapping(value = "/rm/kyc/profiles/approved", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> approvedProfiles(@RequestParam String username, HttpServletRequest httpServletRequest,
			@RequestParam(value = "page_no", required = false) Integer pageNo,
			@RequestParam(value = "page_size", required = false) Integer pageSize,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/rm/kyc/profiles/approved request received for username={}, pageNo={},pageSize={}", username,
				pageNo, pageSize);
		if (neitherNullNorEmpty(username) && isAuthorised(username, httpServletRequest, httpServletResponse)) {
			JsonNode jsonNode = kycService.fetchRetailersByKycStatus(KycStatus.APPROVED, pageNo, pageSize);
			return new ResponseEntity<>(jsonNode != null && jsonNode.size() != 0 ? jsonNode : Collections.EMPTY_LIST,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/profiles/rejected", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> rejectedProfiles(@RequestParam String username, HttpServletRequest httpServletRequest,
			@RequestParam(value = "page_no", required = false) Integer pageNo,
			@RequestParam(value = "page_size", required = false) Integer pageSize,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/rm/kyc/status/rejected request received for username={}, pageNo={},pageSize={}", username, pageNo,
				pageSize);
		if (neitherNullNorEmpty(username) && isAuthorised(username, httpServletRequest, httpServletResponse)) {
			JsonNode jsonNode = kycService.fetchRetailersByKycStatus(KycStatus.REJECTED, pageNo, pageSize);
			return new ResponseEntity<>(jsonNode != null && jsonNode.size() != 0 ? jsonNode : Collections.EMPTY_LIST,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/profiles/submitted", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> submittedProfiles(@RequestParam String username, HttpServletRequest httpServletRequest,
			@RequestParam(value = "page_no", required = false) Integer pageNo,
			@RequestParam(value = "page_size", required = false) Integer pageSize,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/rm/kyc/profiles/submitted request received for username={}, pageNo={},pageSize={}", username,
				pageNo, pageSize);
		if (neitherNullNorEmpty(username) && isAuthorised(username, httpServletRequest, httpServletResponse)) {
			JsonNode jsonNode = kycService.fetchRetailersByKycStatus(KycStatus.SUBMITTED, pageNo, pageSize);
			return new ResponseEntity<>(jsonNode != null && jsonNode.size() != 0 ? jsonNode : Collections.EMPTY_LIST,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/profiles/re-submitted", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> reSubmittedProfiles(@RequestParam String username, HttpServletRequest httpServletRequest,
			@RequestParam(value = "page_no", required = false) Integer pageNo,
			@RequestParam(value = "page_size", required = false) Integer pageSize,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/rm/kyc/profiles/re-submitted request received for username={}, pageNo={},pageSize={}", username,
				pageNo, pageSize);
		if (neitherNullNorEmpty(username) && isAuthorised(username, httpServletRequest, httpServletResponse)) {
			JsonNode jsonNode = kycService.fetchRetailersByKycStatus(KycStatus.RE_SUBMITTED, pageNo, pageSize);
			return new ResponseEntity<>(jsonNode != null && jsonNode.size() != 0 ? jsonNode : Collections.EMPTY_LIST,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/profiles/under-review", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> underReviewProfiles(@RequestParam String username, HttpServletRequest httpServletRequest,
			@RequestParam(value = "page_no", required = false) Integer pageNo,
			@RequestParam(value = "page_size", required = false) Integer pageSize,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/rm/kyc/profiles/under-review request received for username={}, pageNo={},pageSize={}", username,
				pageNo, pageSize);
		if (neitherNullNorEmpty(username) && isAuthorised(username, httpServletRequest, httpServletResponse)) {
			JsonNode jsonNode = kycService.fetchRetailersByKycStatus(KycStatus.UNDER_REVIEW, pageNo, pageSize);
			return new ResponseEntity<>(jsonNode != null && jsonNode.size() != 0 ? jsonNode : Collections.EMPTY_LIST,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/profiles/loc-issued", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> locIssuedProfiles(@RequestParam String username, HttpServletRequest httpServletRequest,
			@RequestParam(value = "page_no", required = false) Integer pageNo,
			@RequestParam(value = "page_size", required = false) Integer pageSize,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/rm/kyc/profiles/loc-issued request received for username={}, pageNo={},pageSize={}", username,
				pageNo, pageSize);
		if (neitherNullNorEmpty(username) && isAuthorised(username, httpServletRequest, httpServletResponse)) {
			JsonNode jsonNode = kycService.fetchRetailersByKycStatus(KycStatus.LOC_ISSUED, pageNo, pageSize);
			return new ResponseEntity<>(jsonNode != null && jsonNode.size() != 0 ? jsonNode : Collections.EMPTY_LIST,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/kyc/profiles/loc-generated", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> locGeneratedProfiles(@RequestParam String username, HttpServletRequest httpServletRequest,
			@RequestParam(value = "page_no", required = false) Integer pageNo,
			@RequestParam(value = "page_size", required = false) Integer pageSize,
			HttpServletResponse httpServletResponse) throws ClientProtocolException, URISyntaxException, IOException {
		LOGGER.info("/rm/kyc/profiles/loc-generated request received for username={}, pageNo={},pageSize={}", username,
				pageNo, pageSize);
		if (neitherNullNorEmpty(username) && isAuthorised(username, httpServletRequest, httpServletResponse)) {
			JsonNode jsonNode = kycService.fetchRetailersByKycStatus(KycStatus.LOC_GENERATED, pageNo, pageSize);
			return new ResponseEntity<>(jsonNode != null && jsonNode.size() != 0 ? jsonNode : Collections.EMPTY_LIST,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

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
			return new ResponseEntity<>(kycService.updateKycStatus(msisdn, username, KycStatus.RE_UPDATE),
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
			AccountStatuses accountStatuses = kycService.updateKycStatus(msisdn, username, KycStatus.LOC_GENERATED);
			if (accountStatuses != null && KycStatus.LOC_GENERATED.equals(accountStatuses.getKycVerified())) {
				return new ResponseEntity<>(
						storageService.getDisclaimerDocuments(userService.getUser(msisdn).getId(), appConfigService
								.getProperty("GENERATE_LOC_FILE_NAME", "05_General_Loan_Agreement.pdf")),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
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

	@RequestMapping(value = "/rm/kyc/documents/disclaimer", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getDisclaimerDoc(@RequestParam String msisdn, @RequestParam String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOGGER.info("/rm/kyc/documents/disclaimer request received for msisdn={},username={}", msisdn, username);
		if (neitherNullNorEmpty(msisdn) && neitherNullNorEmpty(username)
				&& isAuthorised(username, httpServletRequest, httpServletResponse)) {
			return new ResponseEntity<>(kycService.getDisclaimerDocuments(msisdn), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/rm/doc/disclaimer/file", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getDisclaimerFile(@RequestParam("username") String username,
			@RequestParam(name = "retailerId", required = false) Long retailerId,
			@RequestParam("filename") String filename, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/retailer/image request recieved for retailer={}, dsr={}, filename={}", retailerId, username,
					filename);
			if (filename != null && !filename.isEmpty()) {
				String extension = FilenameUtils.getExtension(filename);
				if ("pdf".equalsIgnoreCase(extension)) {
					/*
					 * if documents extension is pdf, then pick from retailer disclaimer pdf
					 * directory
					 */
					return new ResponseEntity<>(storageService.getDisclaimerDocuments(retailerId, filename),
							HttpStatus.OK);
				} else {
					byte[] doc = storageService.getImage(filename, retailerId, true);
					if (doc == null || doc.length == 0)
						/*
						 * otherwise pick from disclaimer document directory
						 */
						return new ResponseEntity<>(storageService.getDisclaimerDocuments(retailerId, filename),
								HttpStatus.OK);
					else
						return new ResponseEntity<>(doc, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/rm/retailer/image/file", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getRetailersDoc(@RequestParam("username") String username,
			@RequestParam("retailerId") Long retailerId, @RequestParam("filename") String filename,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/rm/retailer/image/file request recieved for retailer={}, dsr={}, filename={}", retailerId,
					username, filename);
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

	@RequestMapping(value = "/rm/upload/image", method = RequestMethod.POST)
	public ResponseEntity<?> uploadImage(@RequestParam("username") String username,
			@RequestParam("retailerId") Long retailerId, @RequestParam("documentType") String documentType,
			@RequestParam("files") MultipartFile files, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/rm/upload/image request recieved for retailer={}, username={},documentType={} file={}",
					retailerId, username, documentType, files != null ? files.getOriginalFilename() : null);
			User user = userService.getRetailerById(retailerId);
			if (user != null) {
				String filename = storageService.uploadImage(files, retailerId, true);
				try {
					AttachmentDetails attachmentDetails = attachmentService.persistInDb(user, files, filename, false,
							documentType);
					if (attachmentDetails != null)
						return new ResponseEntity<>(kycService.getDisclaimerDocuments(user), HttpStatus.OK);
					else {
						return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("exception raised while uploading image={},retailer={},error={}",
							files.getOriginalFilename(), retailerId, e.getMessage());
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}
}
