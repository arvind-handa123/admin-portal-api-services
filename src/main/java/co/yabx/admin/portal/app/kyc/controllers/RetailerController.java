package co.yabx.admin.portal.app.kyc.controllers;

import java.util.List;

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

import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.kyc.dto.AttachmentDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.BusinessDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.LiabilitiesDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.QuestionAnswerDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.dto.RetailerRequestDTO;
import co.yabx.admin.portal.app.kyc.dto.UserDTO;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.UserRelationships;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.RetailerService;
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
public class RetailerController {

	@Autowired
	private AuthInfoService authInfoService;

	@Autowired
	private RetailerService retailerService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private UserService userService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private UserRelationshipsRepository relationshipsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerController.class);

	@RequestMapping(value = "/retailer/summary", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> fetchRetailersSummary(@RequestParam("dsrMSISDN") String dsrMSISDN,
			@RequestParam("startIndex") Integer startIndex, @RequestParam("endIndex") Integer endIndex,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(dsrMSISDN, httpServletRequest, httpServletResponse)) {
			ResponseDTO loginDTO = retailerService.getSummaries(dsrMSISDN, startIndex, endIndex);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

	}

	@RequestMapping(value = "/retailer/{dsrMsisdn}/{retailerId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> fetchRetailerDetails(@PathVariable String dsrMsisdn,
			@PathVariable Long retailerId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(dsrMsisdn, httpServletRequest, httpServletResponse)) {
			ResponseDTO loginDTO = retailerService.retailerDetails(dsrMsisdn, retailerId);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/retailer/profile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> submitRetailerProfile(@RequestBody RetailerRequestDTO retailerRequestDTO,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		if (authInfoService.isAuthorized(retailerRequestDTO != null ? retailerRequestDTO.getDsrMSISDN() : null,
				httpServletRequest, httpServletResponse)) {
			LOGGER.info("/retailer/personal-information request received with retailerRequestDTO={}",
					retailerRequestDTO);
			ResponseDTO loginDTO = retailerService.submitRetailerProfile(retailerRequestDTO);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/retailer/nominee-information", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> submitRetailerNomineeInfo(@RequestBody UserDTO UserDTO) {
		ResponseDTO loginDTO = retailerService.submitRetailerNomineeProfile(UserDTO);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/retailer/business-information", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> submitRetailerBusinessInfo(@RequestBody BusinessDetailsDTO businessDetailsDTO) {
		ResponseDTO loginDTO = retailerService.submitRetailerBusinessInfo(businessDetailsDTO);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/retailer/liabilities-information", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> submitLiabilitiesInfo(@RequestBody LiabilitiesDetailsDTO liabilitiesDetailsDTO) {
		ResponseDTO loginDTO = retailerService.submitLiabilitiesInfo(liabilitiesDetailsDTO);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/retailer/kyc-submit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> submitKyc(@RequestParam(value = "dsrMsisdn") String dsrMsisdn,
			@RequestParam(value = "retailerId") Long retailerId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(dsrMsisdn != null && !dsrMsisdn.isEmpty() ? dsrMsisdn : null,
				httpServletRequest, httpServletResponse)) {
			ResponseDTO loginDTO = retailerService.submitKyc(dsrMsisdn, retailerId);
			return new ResponseEntity<>(loginDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

	}

	@RequestMapping(value = "/retailer/question/{questionId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> fetchRetailerQuestion(@PathVariable Integer questionId) {
		ResponseDTO loginDTO = retailerService.getRetailerQuestion(questionId);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/retailer/all/question", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> fetchRetailerAllQuestion(
			@RequestParam(name = "retailerId", required = false) String retailerId) {
		ResponseDTO loginDTO = retailerService.getRetailerAllQuestions(retailerId);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/retailer/answer", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> postRetailerAnswer(@RequestBody QuestionAnswerDTO questionAnswerDTO) {
		ResponseDTO loginDTO = retailerService.persistRetailerAnswer(questionAnswerDTO);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/retailer/{dsrMsisdn}/search/{retailerId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> searchRetailer(@PathVariable String dsrMsisdn, @PathVariable String retailerId) {
		ResponseDTO loginDTO = retailerService.searchRetailerByDsr(dsrMsisdn, retailerId);
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);

	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload/image", method = RequestMethod.POST)
	public ResponseEntity<?> uploadImage(@RequestParam("dsrMSISDN") String msisdn,
			@RequestParam("retailerId") Long retailerId, @RequestParam MultipartFile files,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/upload/image request recieved for retailer={}, dsr={}, file={}", retailerId, msisdn,
					files != null ? files.getOriginalFilename() : null);
			User user = userService.getRetailerById(retailerId);
			if (user != null) {
				UserRelationships userRelationships = relationshipsRepository
						.findByMsisdnAndRelationshipAndRelative(msisdn, Relationship.RETAILER, user);
				if (userRelationships != null) {
					String filename = storageService.uploadImage(files, retailerId, true);
					try {
						AttachmentDetails attachmentDetails = attachmentService.persistInDb(user, files, filename,
								true);
						if (attachmentDetails != null)
							return new ResponseEntity<>(files, HttpStatus.OK);
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
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/retailer/image", method = RequestMethod.GET)
	public ResponseEntity<?> getImages(@RequestParam("dsrMSISDN") String msisdn,
			@RequestParam("retailerId") Long retailerId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/retailer/image request recieved for retailer={}, dsr={}", retailerId, msisdn);
			User user = userService.getRetailerById(retailerId);
			if (user != null) {
				UserRelationships userRelationships = relationshipsRepository
						.findByMsisdnAndRelationshipAndRelative(msisdn, Relationship.RETAILER, user);
				if (userRelationships != null) {
					try {
						List<AttachmentDetailsDTO> attachmentDetails = attachmentService.getRetailerDocuments(user);
						if (attachmentDetails != null)
							return new ResponseEntity<>(attachmentDetails, HttpStatus.OK);
						else {
							return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
						}
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("exception raised while fetching retailer documents for retailer={},error={}",
								retailerId, e.getMessage());
						return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/retailer/image/file", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImages(@RequestParam("dsrMSISDN") String msisdn,
			@RequestParam("retailerId") Long retailerId, @RequestParam("filename") String filename,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorized(msisdn, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/retailer/image/file request recieved for retailer={}, dsr={}, filename={}", retailerId,
					msisdn, filename);
			User user = userService.getRetailerById(retailerId);
			if (user != null) {
				UserRelationships userRelationships = relationshipsRepository
						.findByMsisdnAndRelationshipAndRelative(msisdn, Relationship.RETAILER, user);
				if (userRelationships != null) {
					try {
						if (filename != null && !filename.isEmpty()) {
							return new ResponseEntity<>(storageService.getImage(filename, user.getId()), HttpStatus.OK);
						} else {
							return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
						}
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("exception raised while fetching retailer={} image={},error={}", user.getId(),
								filename, e.getMessage());
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}
}
