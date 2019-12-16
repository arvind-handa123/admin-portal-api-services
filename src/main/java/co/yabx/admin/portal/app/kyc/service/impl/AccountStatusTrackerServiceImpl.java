package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.enums.AccountStatus;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.AccountStatusesTrackers;
import co.yabx.admin.portal.app.kyc.repositories.AccountStatusesTrackersRepository;
import co.yabx.admin.portal.app.kyc.service.AccountStatusTrackerService;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;

@Service
public class AccountStatusTrackerServiceImpl implements AccountStatusTrackerService {

	@Autowired
	private AccountStatusesTrackersRepository accountStatusesTrackersRepository;

	@Autowired
	private AppConfigService appConfigService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountStatusTrackerServiceImpl.class);

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);

	@Override
	@Transactional
	public AccountStatusesTrackers createAccountTracker(AccountStatuses accountStatuses) {
		if (accountStatuses != null) {
			try {
				List<AccountStatusesTrackers> accountStatusesTrackersList = accountStatusesTrackersRepository
						.findByMsisdn(accountStatuses.getMsisdn());
				if (accountStatusesTrackersList == null || accountStatusesTrackersList.isEmpty()) {
					persistAccountStatusTracker(accountStatuses);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while updating account status for msisdn={}, error={}",
						accountStatuses.getMsisdn(), e.getMessage());
			}
		}
		return null;

	}

	@Override
	@Transactional
	public AccountStatusesTrackers persistAccountStatusTracker(AccountStatuses accountStatuses) {

		AccountStatusesTrackers accountStatusesTrackers = new AccountStatusesTrackers();
		accountStatusesTrackers.setCreatedBy(accountStatuses.getCreatedBy());
		accountStatusesTrackers.setTo(AccountStatus.NEW);
		accountStatusesTrackers.setMsisdn(accountStatuses.getMsisdn());
		accountStatusesTrackers.setReason(
				appConfigService.getProperty("NEW_KYC_ACCOUNT_TRACKER_REASON", "NEW CREATE REQUEST RECIEVED"));
		accountStatusesTrackers = accountStatusesTrackersRepository.saveAndFlush(accountStatusesTrackers);
		return accountStatusesTrackers;

	}

	@Override
	@Transactional
	public AccountStatusesTrackers updateAccountTracker(AccountStatuses accountStatuses, AccountStatus oldStatus) {
		if (accountStatuses != null) {
			try {
				AccountStatusesTrackers accountStatusesTrackers = new AccountStatusesTrackers();
				accountStatusesTrackers.setUpdatedBy(accountStatuses.getUpdatedBy());
				accountStatusesTrackers.setFrom(oldStatus);
				accountStatusesTrackers.setTo(accountStatuses.getAccountStatus());
				accountStatusesTrackers.setMsisdn(accountStatuses.getMsisdn());
				accountStatusesTrackers.setReason(accountStatuses.getUpdateReason());
				accountStatusesTrackers.setCreatedBy(accountStatuses.getCreatedBy());
				accountStatusesTrackers = accountStatusesTrackersRepository.save(accountStatusesTrackers);
				return accountStatusesTrackers;
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while updating account status for msisdn={}, error={}",
						accountStatuses.getMsisdn(), e.getMessage());
			}
		}
		return null;

	}

	@Override
	@Transactional
	public List<AccountStatusesTrackers> findByMsisdn(String msisdn) {
		return accountStatusesTrackersRepository.findByMsisdn(msisdn);
	}

	@Override
	@Async
	public void pushTracker(AccountStatuses accountStatuses) {
		executor.schedule(getRunnable(accountStatuses), 100, TimeUnit.MICROSECONDS);

	}

	private Runnable getRunnable(AccountStatuses accountStatuses) {
		return () -> {
			try {
				List<AccountStatusesTrackers> accountStatusesTrackers = findByMsisdn(accountStatuses.getMsisdn());
				if (accountStatusesTrackers != null && !accountStatusesTrackers.isEmpty()) {
					accountStatusesTrackers.sort((o1, o2) -> o1.getChangedAt().compareTo(o2.getChangedAt()));
					updateAccountTracker(accountStatuses, accountStatusesTrackers.get(0).getTo());
				} else {
					persistAccountStatusTracker(accountStatuses);
				}

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while updating account status tracker for msisdn={}, error={}",
						accountStatuses.getMsisdn(), e.getMessage());
			}
		};
	}

}
