package co.yabx.admin.portal.app.kyc.service;

import java.util.List;

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.dto.ProductDocumentsDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;

/**
 * 
 * @author Asad.ali
 *
 */
public interface KYCService {

	List<PagesDTO> findAllRetailers();

	List<PagesDTO> fetchRetailersByKycStatus(KycStatus kycStatus);

	AccountStatuses updateKycStatus(String msisdn, String username, KycStatus approved);

	List<ProductDocumentsDTO> getDisclaimerDocuments(String msisdn, String username);

}
