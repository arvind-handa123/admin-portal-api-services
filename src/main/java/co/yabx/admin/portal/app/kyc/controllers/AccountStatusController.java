package co.yabx.admin.portal.app.kyc.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import co.yabx.admin.portal.app.kyc.dto.AccountStatusDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.repositories.AccountStatusesRepository;
import co.yabx.admin.portal.app.kyc.service.AccountStatusService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;

/**
 * 
 * @author Asad.ali
 *
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/v1")
public class AccountStatusController {

	@Autowired
	private AccountStatusService accountStatusService;

	@Autowired
	private AccountStatusesRepository accountStatusesRepository;

	@Autowired
	private AppConfigService appConfigService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountStatusController.class);

	/**
	 * 
	 * @param accountStatusDTO
	 * @param force
	 * @return
	 */
	@RequestMapping(value = "/status/account/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateAccountStatuses(@RequestBody List<AccountStatusDTO> accountStatusDTO,
			@RequestParam(name = "byForce", required = false) boolean force) {
		if (appConfigService.getBooleanProperty("IS_TO_DISPLAY_POST_API_LOGS", true))
			LOGGER.info("/status/account/update request received with body={}, and param byForce={}", accountStatusDTO,
					force);
		if (accountStatusDTO != null && !accountStatusDTO.isEmpty()) {
			accountStatusService.updateAccountStatus(accountStatusDTO, force);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	/**
	 * 
	 * @param msisdn
	 * @param status
	 * @param reason
	 * @param updatedBy
	 * @return
	 */
	@RequestMapping(value = "/update/account/status", method = RequestMethod.POST)
	public ResponseEntity<AccountStatuses> updateAccountStatus(@RequestParam("msisdn") String msisdn,
			@RequestParam("status") String status, @RequestParam("reason") String reason,
			@RequestParam("updatedBy") String updatedBy) {
		if (msisdn != null && !msisdn.isEmpty() && !updatedBy.isEmpty() && !reason.isEmpty() && !status.isEmpty()) {
			AccountStatuses accountStatuses = accountStatusService.updateAccountStatus(msisdn, status, reason,
					updatedBy);
			return new ResponseEntity<>(accountStatuses, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	/**
	 * 
	 * @param msisdn
	 * @param channel
	 * @return
	 */
	@RequestMapping(value = "/status/account", method = RequestMethod.GET)
	public ResponseEntity<?> getAccountStatus(@RequestParam("msisdn") String msisdn,
			@RequestParam(name = "channel", required = false) String channel) {
		if (channel != null && !channel.isEmpty() && msisdn != null && !msisdn.isEmpty()) {
			if (channel.equalsIgnoreCase(appConfigService.getProperty("USSD_JOURNEY_CHANNEL", "ussd"))) {
				AccountStatuses accountStatuses = accountStatusesRepository.findByMsisdn(msisdn);
				return new ResponseEntity<>(accountStatuses != null ? accountStatuses.getAccountStatus() : null,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid channel", HttpStatus.NO_CONTENT);
			}
		}
		if (msisdn != null && !msisdn.isEmpty()) {
			AccountStatusDTO accountStatusDTO = accountStatusService.fetchAccountStatus(msisdn);
			if (accountStatusDTO != null) {
				return new ResponseEntity<>(accountStatusDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("user=" + msisdn + " account not found", HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	/**
	 * 
	 * @param msisdn
	 * @param createdBy
	 * @return
	 */
	@RequestMapping(value = "/status/account/create", method = RequestMethod.POST)
	public ResponseEntity<?> craeteAccountStatus(@RequestParam("msisdn") String msisdn,
			@RequestParam("createdBy") String createdBy) {
		if (msisdn != null && !msisdn.isEmpty() && createdBy != null && !createdBy.isEmpty()) {
			AccountStatuses accountStatuses = accountStatusService.createAccountStatus(msisdn, createdBy);
			if (accountStatuses != null) {
				return new ResponseEntity<>(accountStatusService.fetchAccountStatus(msisdn), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
