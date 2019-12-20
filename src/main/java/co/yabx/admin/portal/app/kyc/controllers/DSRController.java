package co.yabx.admin.portal.app.kyc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.yabx.admin.portal.app.kyc.dto.DsrProfileDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.dto.VerifyOtpDTO;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.DSRService;
import co.yabx.admin.portal.app.kyc.service.StorageService;
import co.yabx.admin.portal.app.kyc.service.UserService;

/**
 * 
 * @author Asad.ali
 *
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/version/v1")
public class DSRController {

	@Autowired
	private AuthInfoService authInfoService;

	@Autowired
	private DSRService dsrService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private UserService userService;

	@Autowired
	private AttachmentService attachmentService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DSRController.class);

	@RequestMapping(value = "/dsr/profile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> submitDSRProfile(@RequestBody DsrProfileDTO dsrProfileDTO,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(dsrProfileDTO != null ? dsrProfileDTO.getDsrMSISDN() : null,
				httpServletRequest, httpServletResponse)) {
			LOGGER.info("/dsr/profile request received with dsrProfileDTO={}", dsrProfileDTO);
			ResponseDTO loginDTO = dsrService.submitDsrProfile(dsrProfileDTO);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/dsr/profile/{dsrMsisdn}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> fetchDSRProfile(@PathVariable String dsrMsisdn, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(dsrMsisdn, httpServletRequest, httpServletResponse)) {
			ResponseDTO loginDTO = dsrService.getDsrProfile(dsrMsisdn);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/dsr/logout/{msisdn}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> logout(@PathVariable String msisdn, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			ResponseDTO loginDTO = dsrService.logout(msisdn);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/dsr/verify/otp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> verifyMail(@RequestBody VerifyOtpDTO verifyOtpDTO, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(verifyOtpDTO != null ? verifyOtpDTO.getDsrMSISDN() : null, httpServletRequest,
				httpServletResponse)) {
			ResponseDTO loginDTO = dsrService.verifyMail(verifyOtpDTO);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/dsr/verify/mail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> generateOTP(@RequestBody VerifyOtpDTO verifyOtpDTO, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(verifyOtpDTO != null ? verifyOtpDTO.getDsrMSISDN() : null, httpServletRequest,
				httpServletResponse)) {
			ResponseDTO loginDTO = dsrService.generateMailOTP(verifyOtpDTO.getEmail());
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dsr/upload/image", method = RequestMethod.POST)
	public ResponseEntity<?> uploadImage(@RequestParam("msisdn") String msisdn, @RequestParam MultipartFile files,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		LOGGER.info("/dsr/upload/image request recieved for msisdn={}", msisdn);
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			User user = userService.getDSRByMsisdn(msisdn);
			if (user != null) {
				try {
					String saveFileName = storageService.uploadImage(files, user.getId());
					AttachmentDetails attachmentDetails = attachmentService.persistDsrProfilePicInDb(user, files,
							saveFileName);
					if (attachmentDetails != null) {
						return new ResponseEntity<>(storageService.getImage(saveFileName, user.getId()), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("exception raised while uploading image={},msisdn={},error={}",
							files.getOriginalFilename(), msisdn, e.getMessage());
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dsr/image", method = RequestMethod.GET)
	public ResponseEntity<?> fetchImage(@RequestParam("msisdn") String msisdn, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		LOGGER.info("/dsr/image request recieved for msisdn={}", msisdn);
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			User user = userService.getDSRByMsisdn(msisdn);
			if (user != null) {
				try {
					String filename = attachmentService.fetchDsrProfilePic(user);
					if (filename != null && !filename.isEmpty()) {
						return new ResponseEntity<>(storageService.getImage(filename, user.getId()), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("exception raised while reading profile pic for user={},error={}", msisdn,
							e.getMessage());
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
