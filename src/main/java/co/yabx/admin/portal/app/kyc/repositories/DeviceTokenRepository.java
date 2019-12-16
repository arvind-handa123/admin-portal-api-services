package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.DeviceInformations;

/**
 * 
 * @author Asad.ali
 *
 */
@Repository("deviceTokenRepository")
public interface DeviceTokenRepository extends JpaRepository<DeviceInformations, String> {

	DeviceInformations findByDeviceId(String deviceId);

	List<DeviceInformations> findByDeviceToken(String deviceToken);
}