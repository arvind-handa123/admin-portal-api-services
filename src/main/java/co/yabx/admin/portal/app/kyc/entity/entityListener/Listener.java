package co.yabx.admin.portal.app.kyc.entity.entityListener;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.AccountStatusesTrackers;
import co.yabx.admin.portal.app.kyc.service.AccountStatusTrackerService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.impl.AppConfigServiceImpl;
import co.yabx.admin.portal.app.util.SpringUtil;

/**
 * 
 * @author Asad.ali
 *
 */
public abstract class Listener {
	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);

	protected boolean checkEnableDisable() {
		AppConfigService dbConfigService = SpringUtil.bean(AppConfigServiceImpl.class);
		return dbConfigService.getBooleanProperty("ENTITY_LISTENER_ENABLE", false);
	}

	protected boolean checkAccountStatusTrackerEnableDisable() {
		AppConfigService dbConfigService = SpringUtil.bean(AppConfigServiceImpl.class);
		return dbConfigService.getBooleanProperty("ENTITY_ACCOUNT_STATUS_TRACKER_LISTENER_ENABLE", true);
	}

	protected void publishKycId(String id) {
		// TODO Auto-generated method stub

	}

	protected void publishAccountStatusesTrackers(AccountStatuses accountStatuses) {
		executor.schedule(getRunnable(accountStatuses), 100, TimeUnit.MICROSECONDS);
	}

	private Runnable getRunnable(AccountStatuses accountStatuses) {
		return () -> {
			try {
				AccountStatusTrackerService accountStatusTrackerService = SpringUtil
						.bean(AccountStatusTrackerService.class);
				List<AccountStatusesTrackers> accountStatusesTrackers = accountStatusTrackerService
						.findByMsisdn(accountStatuses.getMsisdn());
				if (accountStatusesTrackers != null && !accountStatusesTrackers.isEmpty()) {
					accountStatusesTrackers.sort((o1, o2) -> o1.getChangedAt().compareTo(o2.getChangedAt()));
					accountStatusTrackerService.updateAccountTracker(accountStatuses,
							accountStatusesTrackers.get(accountStatusesTrackers.size() - 1).getTo());
				} else {
					accountStatusTrackerService.persistAccountStatusTracker(accountStatuses);
				}

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while updating account status tracker for msisdn={}, error={}",
						accountStatuses.getMsisdn(), e.getMessage());
			}
		};
	}

}
