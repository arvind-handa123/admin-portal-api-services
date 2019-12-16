package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.OtpType;
import co.yabx.admin.portal.app.kyc.entities.OTP;

@Repository("otpRepository")
public interface OtpRepository extends CrudRepository<OTP, Long> {

	OTP findByUserAndOtp(Long user, String otp);

	@Query("select o from OTP o where o.user=?1 and o.otpType=?2")
	List<OTP> findByUserOtpType(Long user, OtpType otpType);


}
