package co.yabx.admin.portal.app.connect.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.connect.entities.Users;

@Repository("connectUserRepository")
public interface UserRepository extends CrudRepository<Users, Long> {

	Users findBymsisdn(String msisdn);

}
