package co.yabx.admin.portal.app.kyc.entity.entityListener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;


/**
 * 
 * @author Asad.ali
 *
 */
public class AccountStatusesTrackerListener extends Listener {
	@PreUpdate
	@PrePersist
	public void persist(AccountStatuses accountStatuses) {
		if (checkAccountStatusTrackerEnableDisable()) {
			if (accountStatuses != null) {
				publishAccountStatusesTrackers(accountStatuses);
			}
		}
	}

}
