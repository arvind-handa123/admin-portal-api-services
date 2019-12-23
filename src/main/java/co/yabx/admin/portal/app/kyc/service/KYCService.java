package co.yabx.admin.portal.app.kyc.service;

import java.util.List;

import co.yabx.admin.portal.app.dto.LoginDto;
import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.entities.User;

/**
 * 
 * @author Asad.ali
 *
 */
public interface KYCService {

	List<PagesDTO> findAllRetailers();

	List<PagesDTO> fetchRetailersByKycStatus(KycStatus kycStatus);

}
