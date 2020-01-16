package co.yabx.admin.portal.app.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import co.yabx.admin.portal.app.admin.entities.ProductConfigurations;
import co.yabx.admin.portal.app.dto.LoginDto;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;

public interface AdminPortalService {

	List<PagesDTO> fetchProductDetails(Long productId) throws ClientProtocolException, URISyntaxException, IOException;

	ResponseDTO login(LoginDto loginDto);

	Iterable<ProductConfigurations> fetchProducts();

	ResponseDTO logout(String username);

	ResponseDTO changePassword(String username, LoginDto loginDto);

}
