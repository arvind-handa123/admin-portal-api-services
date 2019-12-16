package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.entities.User;

@Repository("userRepository")
public interface UserRepository extends BaseUserRepository<User>, CrudRepository<User, Long> {

	User findBymsisdn(String msisdn);

	User findByAuthInfo(AuthInfo authInfo);

	User findBymsisdnAndUserType(String dsrMSISDN, String userType);

}
