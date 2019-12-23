package co.yabx.admin.portal.app.service;

import java.util.List;

import co.yabx.admin.portal.app.dto.LoginDto;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.ResponseDTO;

public interface AdminPortalService {

	List<PagesDTO> fetchProductDetails(Long productId);

	ResponseDTO login(LoginDto loginDto);

}
