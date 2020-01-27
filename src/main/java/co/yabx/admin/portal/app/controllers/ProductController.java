package co.yabx.admin.portal.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import co.yabx.admin.portal.app.admin.entities.ProductConfigurations;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;
import co.yabx.admin.portal.app.kyc.service.AdminService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;
import co.yabx.admin.portal.app.service.AdminPortalService;
import co.yabx.admin.portal.app.service.AdminStorageService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1")
public class ProductController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private AdminPortalService adminPortalService;

	@Autowired
	private AdminStorageService adminStorageService;

	@Autowired
	private AuthInfoService authInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<?> pages(@RequestParam(value = "username", required = true) String username,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			try {
				Iterable<ProductConfigurations> products = adminPortalService.fetchProducts();
				if (products != null) {
					return new ResponseEntity<>(products, HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while feching product list for username={},error={}", username,
						e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Invalid authentication", HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/products/logo/file", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getRetailersDoc(@RequestParam("username") String username,
			@RequestParam("filename") String filename, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			LOGGER.info("/product/logo/file request recieved for username={}, filename={}", username, filename);
			try {
				if (filename != null && !filename.isEmpty()) {
					return new ResponseEntity<>(adminStorageService.getLogoImage(filename), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("exception raised while fetching image={},error={}", filename, e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/products/pages", method = RequestMethod.GET)
	public ResponseEntity<?> pages(@RequestParam("productId") Long productId,
			@RequestParam(value = "username", required = true) String username, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (authInfoService.isAuthorizedByUsername(username, httpServletRequest, httpServletResponse)) {
			ResponseDTO statusDto = new ResponseDTO();
			try {
				List<PagesDTO> pages = adminPortalService.fetchProductDetails(productId);
				if (pages != null && !pages.isEmpty()) {
					statusDto.setMenu(pages);
					statusDto.setStatusCode("200");
					statusDto.setMessage("SUCCESS");
					return new ResponseEntity<>(statusDto, HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while redaing pages for product={},error={}", productId, e.getMessage());
				statusDto.setStatusCode("501");
				statusDto.setMessage(e.getMessage());
				return new ResponseEntity<>(statusDto, HttpStatus.OK);
			}
			return new ResponseEntity<>(statusDto, HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid authentication", HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/register/rm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> registerRM(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "msisdn", required = true) String msisdn,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "secret_key", required = true) String secret_key) {
		if (secret_key.equals(appConfigService.getProperty("CREATE_RM_API_PASSWORD", "magic@yabx"))) {

			return new ResponseEntity<>(adminService.registerRM(username, msisdn, email, name, password),
					HttpStatus.OK);

		}
		return new ResponseEntity<>("Invalid secret key", HttpStatus.UNAUTHORIZED);

	}

}
