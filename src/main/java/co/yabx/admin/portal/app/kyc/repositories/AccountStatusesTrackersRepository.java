package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.AccountStatusesTrackers;


@Repository("accountStatusesTrackersRepository")
public interface AccountStatusesTrackersRepository extends JpaRepository<AccountStatusesTrackers, Long> {

	@Query("select a from AccountStatusesTrackers a where a.msisdn=?1")
	List<AccountStatusesTrackers> findByMsisdn(String msisdn);

}
