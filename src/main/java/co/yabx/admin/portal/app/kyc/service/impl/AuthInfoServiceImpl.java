package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.repositories.AuthInfoRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.security.SecurityUtils;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class AuthInfoServiceImpl implements AuthInfoService {

	@Autowired
	private AuthInfoRepository authInfoRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppConfigService appConfigService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInfoServiceImpl.class);

	@Override
	public String login(String username, String password) {
		Optional<AuthInfo> AuthInfoOptional = authInfoRepository.login(username, password);
		if (AuthInfoOptional.isPresent()) {
			return AuthInfoOptional.get().getYabxToken();
		}
		return StringUtils.EMPTY;
	}

	@Override
	public Optional findByToken(String token) {
		String decryptedToken = SecurityUtils.decript(token);
		AuthInfo authInfo = authInfoRepository.findByYabxToken(decryptedToken);
		if (authInfo != null) {
			// User user = userRepository.findByAuthInfo(authInfo);
			return Optional.of(authInfo);
		}
		return Optional.empty();

	}

	@Override
	public AuthInfo findByYabxToken(String token) {
		String decryptedToken = SecurityUtils.decript(token);
		return authInfoRepository.findByYabxToken(decryptedToken);

	}

	@Override
	public boolean isAuthorized(String dsrMSISDN, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		String token = httpServletRequest.getHeader("YABX_ACCESS_TOKEN");
		AuthInfo authInfo = findByYabxToken(token);
		return authInfo != null && authInfo.getMsisdn().equals(dsrMSISDN);
	}

	@Override
	public AuthInfo persistYabxTokenAndSecretKey(AuthInfo authInfo, String uuid, String userName, String msisdn) {
		if (authInfo != null) {
			authInfo.setMsisdn(msisdn);
			authInfo.setUsername(userName);
			authInfo.setYabxToken(uuid);
			authInfo.setCredentialsNonExpired(true);
			authInfo.setAccountNonLocked(true);
			authInfo.setAccountNonExpired(true);
			authInfo.setEnabled(true);
			authInfo = authInfoRepository.save(authInfo);
			return authInfo;
		}
		return authInfo;
	}

}
