package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.LiabilityType;
import co.yabx.admin.portal.app.kyc.entities.LiabilitiesDetails;
import co.yabx.admin.portal.app.kyc.entities.User;

@Repository("liabilitiesDetailsRepository")
public interface LiabilitiesDetailsRepository extends CrudRepository<LiabilitiesDetails, Long> {

	LiabilitiesDetails findByUserAndLiabilityType(User retailerOrDsrUser, LiabilityType liabilityType);

}
