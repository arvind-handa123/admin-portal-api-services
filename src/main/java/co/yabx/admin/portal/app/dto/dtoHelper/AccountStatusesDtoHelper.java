package co.yabx.admin.portal.app.dto.dtoHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.yabx.admin.portal.app.enums.AccountStatus;
import co.yabx.admin.portal.app.kyc.dto.AccountStatusDTO;
import co.yabx.admin.portal.app.kyc.dto.AccountStatusTrackerDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.AccountStatusesTrackers;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.util.SpringUtil;

public class AccountStatusesDtoHelper implements Serializable {

	public static AccountStatusDTO prepareDto(AccountStatuses accountStatuses,
			List<AccountStatusesTrackers> accountStatusesTrackers) {
		if (accountStatuses != null) {
			AccountStatusDTO accountStatusDTO = new AccountStatusDTO();
			accountStatusDTO.setMsisdn(accountStatuses.getMsisdn());
			accountStatusDTO.setCreatedBy(accountStatuses.getCreatedBy());
			accountStatusDTO.setUpdatedBy(accountStatuses.getUpdatedBy());
			accountStatusDTO.setCreatedAt(accountStatuses.getCreatedAt());
			accountStatusDTO.setUpdatedAt(accountStatuses.getUpdatedAt());
			AccountStatus accountStatus = accountStatuses.getAccountStatus();
			if (AccountStatus.SUSPENDED.equals(accountStatus)) {
				accountStatusDTO.setSuspensionThresholdTime(
						addHoursToJavaUtilDate(accountStatuses.getUpdatedAt(), SpringUtil.bean(AppConfigService.class)
								.getIntProperty("THRESHOLD_TIME_TO_REACTIVATE_SUSPENDED_ACCOUNT", 24)));
			}
			accountStatusDTO.setAccountStatus(accountStatus);
			accountStatusDTO.setAmlCftStatus(accountStatuses.getAmlCftStatus());
			accountStatusDTO.setKycAvailable(accountStatuses.isKycAvailable());
			accountStatusDTO.setKycVerified(accountStatuses.getKycVerified());
			accountStatusDTO.setTransactionStatus(accountStatuses.getTransactionStatus());
			accountStatusDTO.setUpdateReason(accountStatuses.getUpdateReason());
			if (accountStatusesTrackers != null && !accountStatusesTrackers.isEmpty())
				accountStatusDTO.setAccountStatusTrackerDTO(prepareAccountStatusTrackerDto(accountStatusesTrackers));
			return accountStatusDTO;
		}
		return null;
	}

	private static List<AccountStatusTrackerDTO> prepareAccountStatusTrackerDto(
			List<AccountStatusesTrackers> accountStatusesTrackers) {
		if (accountStatusesTrackers != null) {
			List<AccountStatusTrackerDTO> accountStatusTrackerDTOList = new ArrayList<AccountStatusTrackerDTO>();
			for (AccountStatusesTrackers accountStatusesTracker : accountStatusesTrackers) {
				AccountStatusTrackerDTO accountStatusTrackerDTO = new AccountStatusTrackerDTO();
				accountStatusTrackerDTO.setChangedAt(accountStatusesTracker.getChangedAt());
				accountStatusTrackerDTO.setCreatedBy(accountStatusesTracker.getCreatedBy());
				accountStatusTrackerDTO.setFromStatus(accountStatusesTracker.getFrom());
				accountStatusTrackerDTO.setMsisdn(accountStatusesTracker.getMsisdn());
				accountStatusTrackerDTO.setReason(accountStatusesTracker.getReason());
				accountStatusTrackerDTO.setToStatus(accountStatusesTracker.getTo());
				accountStatusTrackerDTO.setUpdatedBy(accountStatusesTracker.getUpdatedBy());
				accountStatusTrackerDTOList.add(accountStatusTrackerDTO);

			}
			return accountStatusTrackerDTOList;

		}
		return null;
	}

	public static Date addHoursToJavaUtilDate(Date date, Integer hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}
}
