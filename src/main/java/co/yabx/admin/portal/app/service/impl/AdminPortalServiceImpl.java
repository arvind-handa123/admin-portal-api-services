package co.yabx.admin.portal.app.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.admin.entities.Pages;
import co.yabx.admin.portal.app.admin.entities.ProductConfigurations;
import co.yabx.admin.portal.app.admin.repositories.ProductConfigurationRepository;
import co.yabx.admin.portal.app.dto.LoginDto;
import co.yabx.admin.portal.app.dto.dtoHelper.DsrDtoHelper;
import co.yabx.admin.portal.app.dto.dtoHelper.PagesDTOHeper;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.repositories.AuthInfoRepository;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.security.SecurityUtils;
import co.yabx.admin.portal.app.service.AdminPortalService;

@Service
public class AdminPortalServiceImpl implements AdminPortalService {

	@Autowired
	private ProductConfigurationRepository productConfigurationRepository;
	@Autowired
	private AuthInfoRepository authInfoRepository;
	@Autowired
	private AuthInfoService authInfoService;
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminPortalServiceImpl.class);

	@Override
	public List<PagesDTO> fetchProductDetails(Long productId) {
		Optional<ProductConfigurations> productConfigurations = productConfigurationRepository.findById(productId);
		List<PagesDTO> appPagesDTOList = new ArrayList<PagesDTO>();
		if (productConfigurations.isPresent()) {
			Set<Pages> appPages = productConfigurations.get().getPages();
			for (Pages pages : appPages) {
				appPagesDTOList.add(PagesDTOHeper.prepareAppPagesDto(pages));
			}
			return appPagesDTOList;
		}
		return null;
	}

	@Override
	public ResponseDTO login(LoginDto loginDto) {
		if (loginDto != null) {
			String username = loginDto.getUsername();
			String password = loginDto.getPassword();
			Optional<AuthInfo> optional = authInfoRepository.findByUsername(username);
			if (optional.isPresent()) {
				if (password.equalsIgnoreCase(optional.get().getPassword())) {
					ResponseDTO responseDTO = DsrDtoHelper.getLoginDTO("", "SUCCESS", "200", null);
					responseDTO.setAuthInfo(prepareTokenAndKey(optional.get(), username));
					return responseDTO;

				}
			}
		}
		return null;
	}

	public Map<String, String> prepareTokenAndKey(AuthInfo authInfo, String username) {
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
				authInfoService.persistYabxTokenAndSecretKey(authInfo, uuid, username, null);
				jsonResponse.put("YABX_KYC_ACCESS_TOKEN", yabxToken);

			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
			LOGGER.error("Something went wrong while encripting appLozicToken for username={} error={}", username,
					e.getMessage());
		}
		return jsonResponse;
	}

}
