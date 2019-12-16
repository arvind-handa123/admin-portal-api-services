package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.AccountStatus;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;

@Repository("accountStatusesRepository")
public interface AccountStatusesRepository extends CrudRepository<AccountStatuses, String> {

	List<AccountStatuses> findByAccountStatus(AccountStatus accountStatus);

	@Query("select a from AccountStatuses a where a.msisdn=?1")
	AccountStatuses findByMsisdn(String msisdn);

}
