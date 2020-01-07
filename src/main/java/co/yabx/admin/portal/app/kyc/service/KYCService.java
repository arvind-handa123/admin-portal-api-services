package co.yabx.admin.portal.app.kyc.service;

import java.util.List;

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.UserDisclaimerDocumentsDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.User;

/**
 * 
 * @author Asad.ali
 *
 */
public interface KYCService {

	List<PagesDTO> findAllRetailers();

	List<PagesDTO> fetchRetailersByKycStatus(KycStatus kycStatus);

	AccountStatuses updateKycStatus(String msisdn, String username, KycStatus approved);

	UserDisclaimerDocumentsDTO getDisclaimerDocuments(String msisdn);

	UserDisclaimerDocumentsDTO getDisclaimerDocuments(User user);

}
