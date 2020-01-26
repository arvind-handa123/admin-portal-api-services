package co.yabx.admin.portal.app.kyc.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;
import co.yabx.admin.portal.app.kyc.repositories.AuthInfoRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.AdminService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.DSRService;
import co.yabx.admin.portal.app.security.SecurityUtils;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AuthInfoService authInfoService;

	@Autowired
	private AuthInfoRepository authInfoRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRelationshipsRepository userRelationshipsRepository;

	@Autowired
	private DSRService dsrService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public Map<String, String> getAuthToken(String msisdn) {
		AuthInfo authInfo = authInfoRepository.findByMsisdn(msisdn);
		if (authInfo == null || authInfo.getYabxToken() == null) {
			return null;
		}
		String yabxToken = authInfo.getYabxToken();
		Map<String, String> jsonResponse = new HashMap<String, String>();
		try {
			jsonResponse.put("YABX_ACCESS_TOKEN", SecurityUtils.encript(yabxToken));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			LOGGER.info("exception while encripting token for msisdn={}", msisdn);
			e.printStackTrace();
		}
		return jsonResponse;
	}

	@Override
	public Map<String, String> prepareTokenAndKey(DSRUser dsrUser, String userName) {
		String yabxToken = null;
		Map<String, String> jsonResponse = new HashMap<String, String>();
		try {
			String uuid = UUID.randomUUID().toString();
			/*
			 * final String salt = UtilHelper
			 * .getNumericString(appConfigService.getIntProperty("AES_ENCRYPTION_LENGTH",
			 * 16));
			 */
			yabxToken = SecurityUtils.encript(uuid);
			if (yabxToken != null) {
				AuthInfo authInfo = persistTokenAndKey(uuid, userName, dsrUser != null ? dsrUser.getMsisdn() : null);
				if (authInfo != null && dsrUser != null) {
					dsrService.updateAuthInfo(dsrUser, authInfo);
				}
				jsonResponse.put("YABX_ACCESS_TOKEN", yabxToken);

			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
			LOGGER.error("Something went wrong while encripting appLozicToken for yabxId={},msisdn={} error={}",
					userName, dsrUser.getMsisdn(), e.getMessage());
		}
		return jsonResponse;
	}

	private AuthInfo persistTokenAndKey(String uuid, String userName, String msisdn) {
		AuthInfo authInfo = null;
		if (msisdn != null) {
			authInfo = authInfoRepository.findByMsisdn(msisdn);
			if (authInfo == null) {
				authInfo = new AuthInfo();
			}
			authInfoService.persistYabxTokenAndSecretKey(authInfo, uuid, userName, msisdn);
		} else {
			Optional<AuthInfo> userOptional = authInfoRepository.findByUsername(userName);
			if (userOptional.isPresent()) {
				authInfo = userOptional.get();
				authInfoService.persistYabxTokenAndSecretKey(authInfo, uuid, userName, msisdn);
			}

		}
		return authInfo;

	}

	@Override
	public void resetYabxToken(AuthInfo authInfo) {
		if (authInfo != null) {
			authInfo.setYabxToken(null);
			authInfo.setCredentialsNonExpired(false);
			authInfo.setCredentialsNonExpired(false);
			authInfo.setAccountNonLocked(false);
			authInfo.setAccountNonExpired(false);
			authInfo.setEnabled(false);
			authInfoRepository.save(authInfo);
		}
	}

	@Override
	public Map<String, String> registerRM(String username, String msisdn, String email, String name, String password) {
		AuthInfo authInfo = authInfoRepository.findByMsisdnOrUsernameOrEmail(msisdn, username, email);
		Map<String, String> jsonResponse = new HashMap<String, String>();
		if (authInfo == null) {
			authInfo = new AuthInfo();
			authInfo.setUsername(username);
			authInfo.setEmail(email);
			authInfo.setPassword(password);
			authInfo.setMsisdn(msisdn);
			authInfo = authInfoRepository.save(authInfo);
			jsonResponse.put("STATUS", "SUCCESS");
			return jsonResponse;
		}
		jsonResponse.put("STATUS", "USER ALREADY EXIST");
		return jsonResponse;

	}

}
