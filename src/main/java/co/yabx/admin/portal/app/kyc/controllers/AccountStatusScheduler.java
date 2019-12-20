package co.yabx.admin.portal.app.kyc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
public class AccountStatusScheduler {
	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AccountStatusService accountStatusService;

	/**
	 * 
	 * @param magicKey
	 * @return
	 * 
	 * 
	 * 
	 *         Responsible for updating account status to active or blocked based on
	 *         kyc details and aml/cft status
	 */
	@RequestMapping(value = "/update/status", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestParam("magicKey") String magicKey) {
		if (appConfigService.getProperty("SECRET_KEY_FOR_UPDATING_ACCOUNT_STATUSES", "yabx@kyc")
				.equalsIgnoreCase(magicKey)) {
			return new ResponseEntity<>(accountStatusService.updateAllAccountStatus(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	/**
	 * 
	 * @param magicKey
	 * @return
	 * 
	 * 
	 *         API is responsible for re activating all those accounts which are
	 *         suspended within some time threshold and other conditions.
	 */
	@RequestMapping(value = "/status/reactivate", method = RequestMethod.POST)
	public ResponseEntity<?> reactivate(@RequestParam("magicKey") String magicKey) {
		if (appConfigService.getProperty("SECRET_KEY_FOR_UPDATING_ACCOUNT_STATUSES", "yabx@kyc")
				.equalsIgnoreCase(magicKey)) {
			return new ResponseEntity<>(accountStatusService.reActivate(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
