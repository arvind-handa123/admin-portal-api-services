package co.yabx.admin.portal.app.kyc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.AuthInfo;

/**
 * 
 * @author Asad.ali
 *
 */
@Repository("authInfoRepository")
public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {

	AuthInfo findByMsisdn(String msisdn);

	AuthInfo findByYabxToken(String token);

	@Query(value = "SELECT u FROM AuthInfo u where u.username = ?1 and u.password = ?2 ")
	Optional<AuthInfo> login(String username, String password);

	Optional<AuthInfo> findByUsername(String userName);

}
