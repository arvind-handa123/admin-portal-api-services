package co.yabx.admin.portal.app.kyc.service;

import java.util.List;

import co.yabx.admin.portal.app.enums.AccountStatus;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.AccountStatusesTrackers;

/**
 * 
 * @author Asad.ali
 *
 */
public interface AccountStatusTrackerService {

	AccountStatusesTrackers createAccountTracker(AccountStatuses accountStatuses);

	AccountStatusesTrackers updateAccountTracker(AccountStatuses accountStatuses, AccountStatus oldStatus);

	List<AccountStatusesTrackers> findByMsisdn(String msisdn);

	void pushTracker(AccountStatuses accountStatuses);

	AccountStatusesTrackers persistAccountStatusTracker(AccountStatuses accountStatuses);

}
