package co.yabx.admin.portal.app.kyc.controllers;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;

/**
 * 
 * @author Asad.ali
 *
 */
@Controller
@RequestMapping(value = "/version/v1")
public class AppLaunchEventController {

	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	@Autowired
	private UserRepository userRepository;

	private static Logger LOGGER = LoggerFactory.getLogger(AppLaunchEventController.class);

	@RequestMapping(value = "/appLaunch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> feedback(@RequestParam("msisdn") String msisdn,
			@RequestParam(value = "locale", required = false) String language, HttpServletRequest req) {

		User user = userRepository.findBymsisdn(msisdn);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		Locale deviceLocale = new Locale("en");
		if (language != null) {
			deviceLocale = new Locale(language);
		}
		user.setLocale(deviceLocale.getLanguage());
		LOGGER.info("found user locale {}", language);

		userRepository.save(user);
		LOGGER.info("update user locale country", deviceLocale.getCountry());

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
