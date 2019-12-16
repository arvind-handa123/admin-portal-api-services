package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.Nominees;
import co.yabx.admin.portal.app.kyc.entities.User;

@Repository("nomineesRepository")
public interface NomineesRepository extends BaseUserRepository<Nominees>, CrudRepository<Nominees, Long> {

	User findBymsisdn(String msisdn);

}
