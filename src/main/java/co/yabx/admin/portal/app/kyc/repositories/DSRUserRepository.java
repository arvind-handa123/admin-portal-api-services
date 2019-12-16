package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.DSRUser;

@Repository("dsrUserRepository")
public interface DSRUserRepository extends BaseUserRepository<DSRUser>, CrudRepository<DSRUser, Long> {

	DSRUser findByEmail(String mail);

	DSRUser findByMsisdn(String dsrMsisdn);

}
