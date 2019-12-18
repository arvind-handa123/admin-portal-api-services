package co.yabx.admin.portal.app.service;

import java.util.List;

import co.yabx.admin.portal.app.kyc.dto.PagesDTO;

public interface AdminPortalService {

	List<PagesDTO> fetchProductDetails(Long productId);

}
