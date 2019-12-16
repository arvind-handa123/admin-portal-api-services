package co.yabx.admin.portal.app.kyc.service;

import java.util.List;

import co.yabx.admin.portal.app.enums.PageType;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;
import co.yabx.admin.portal.app.kyc.entities.Retailers;
import co.yabx.admin.portal.app.kyc.entities.User;

public interface UserService {

	void persistYabxTokenAndSecretKey(User user, String yabxToken, String aPI_SECRET_KEY);

	List<PagesDTO> getUserDetails(User dsrUser, PageType pageType);

	DSRUser getDSRByMsisdn(String dsrMsisdn);

	Retailers getRetailerById(Long retailerId);

	User persistOrUpdateUserInfo(PagesDTO appPagesDTO, User dsrUser, User retailer) throws Exception;

}
