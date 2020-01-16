package co.yabx.admin.portal.app.kyc.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.databind.JsonNode;

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

	JsonNode fetchRetailersByKycStatus(KycStatus kycStatus)
			throws URISyntaxException, ClientProtocolException, IOException;

	AccountStatuses updateKycStatus(String msisdn, String username, KycStatus approved);

	UserDisclaimerDocumentsDTO getDisclaimerDocuments(String msisdn);

	UserDisclaimerDocumentsDTO getDisclaimerDocuments(User user);

}
