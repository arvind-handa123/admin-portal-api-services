package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.IntroducerDetails;


@Repository("introducerDetailsRepository")
public interface IntroducerDetailsRepository extends CrudRepository<IntroducerDetails, Long> {

}
