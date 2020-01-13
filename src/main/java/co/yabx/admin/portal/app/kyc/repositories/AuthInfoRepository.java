package co.yabx.admin.portal.app.kyc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Query(value = "update AuthInfo a set a.password=?3 , a.yabxToken=null where a.username = ?1 and a.password = ?2 ")
	void updatePassword(String username, String currentPassword, String newPassword);

}
