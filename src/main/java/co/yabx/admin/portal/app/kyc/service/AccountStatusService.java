package co.yabx.admin.portal.app.kyc.service;

import java.util.List;
import java.util.Map;

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.kyc.dto.AccountStatusDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;

/**
 * 
 * @author Asad.ali
 *
 */
public interface AccountStatusService {

	void updateAccountStatus(List<AccountStatusDTO> accountStatusDTO, boolean force);

	public AccountStatusDTO fetchAccountStatus(String msisdn);

	public AccountStatuses updateAccountStatus(String msisdn, String status, String reason, String updatedBy);

	public Map<String, String> updateAllAccountStatus();

	public Map<String, String> reActivate();

	public AccountStatuses createAccountStatus(String msisdn, String createdBy);

	public AccountStatuses createAccountStatus(String msisdn, String createdBy, boolean isKycAvailable,
			KycStatus kycStatus);

}
