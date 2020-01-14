package co.yabx.admin.portal.app.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import co.yabx.admin.portal.app.admin.entities.Pages;
import co.yabx.admin.portal.app.admin.entities.ProductConfigurations;
import co.yabx.admin.portal.app.admin.repositories.PagesRepository;
import co.yabx.admin.portal.app.admin.repositories.ProductConfigurationRepository;
import co.yabx.admin.portal.app.cache.RedisRepository;
import co.yabx.admin.portal.app.dto.LoginDto;
import co.yabx.admin.portal.app.dto.dtoHelper.DsrDtoHelper;
import co.yabx.admin.portal.app.dto.dtoHelper.PagesDTOHeper;
import co.yabx.admin.portal.app.enums.ProductName;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.repositories.AuthInfoRepository;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
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
	@Autowired
	private RedisRepository redisRepository;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private PagesRepository pagesRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminPortalServiceImpl.class);

	@Override
	public List<PagesDTO> fetchProductDetails(Long productId) {
		Optional<ProductConfigurations> productConfigurations = productConfigurationRepository.findById(productId);
		List<PagesDTO> appPagesDTOList = new ArrayList<PagesDTO>();
		if (productConfigurations.isPresent()) {
			ProductConfigurations configurations = productConfigurations.get();
			List<Pages> appPages = pagesRepository.findByProductConfig(configurations);
			for (Pages pages : appPages) {
				if (ProductName.KYC.equals(configurations.getProductName())) {
					appPagesDTOList.add(PagesDTOHeper.prepareKycPagesDto(pages));
				} else {
					appPagesDTOList.add(PagesDTOHeper.prepareAirtelPagesDto(pages));
				}
			}
			return appPagesDTOList;
		}
		return null;
	}

	@Override
	public ResponseDTO login(LoginDto loginDto) {
		if (loginDto != null) {
			String username = loginDto.getUsername();
			String password = loginDto.getCurrentPassword();
			Optional<AuthInfo> optional = authInfoRepository.findByUsername(username);
			if (optional.isPresent()) {
				if (password.equalsIgnoreCase(optional.get().getPassword())) {
					ResponseDTO responseDTO = DsrDtoHelper.getLoginDTO("", "SUCCESS", "200", null);
					responseDTO.setUsername(username);
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
			yabxToken = SecurityUtils.encript(uuid);
			if (yabxToken != null) {
				authInfo = authInfoService.persistYabxTokenAndSecretKey(authInfo, uuid, username, null);
				if (authInfo.getYabxToken() != null && redisRepository != null) {
					if (appConfigService.getBooleanProperty("IS_CACHING_ENABLED", false))
						redisRepository.update("YABX_KYC_ACCESS_TOKEN", uuid, authInfo);
				}
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

	@Override
	public Iterable<ProductConfigurations> fetchProducts() {
		return productConfigurationRepository.findAll();
	}

	@Override
	public ResponseDTO logout(String username) {
		if (username != null) {
			authInfoService.resetYabxToken(username);
			return DsrDtoHelper.getLoginDTO(username, "SUCCESS", "200", null);
		}
		return DsrDtoHelper.getLoginDTO(username, "DSR Not Found", "404", null);
	}

	@Override
	public ResponseDTO changePassword(String username, LoginDto loginDto) {
		if (neitherNullNorEmpty(username) && loginDto != null && neitherNullNorEmpty(loginDto.getCurrentPassword())
				&& neitherNullNorEmpty(loginDto.getNewPassword())) {
			authInfoRepository.updatePassword(username, loginDto.getCurrentPassword(), loginDto.getNewPassword());
			return DsrDtoHelper.getLoginDTO(username, "SUCCESS", "200", null);
		}
		return DsrDtoHelper.getLoginDTO(username, "currentPassword/newPassword is null or empty", "404", null);

	}

	private boolean neitherNullNorEmpty(String data) {
		return data != null && !data.isEmpty();
	}

}
