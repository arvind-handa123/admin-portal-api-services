package co.yabx.admin.portal.app.kyc.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.AddressType;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.User;

@Repository("addressDetailsRepository")
public interface AddressDetailsRepository extends CrudRepository<AddressDetails, Long> {

	AddressDetails findByAddressType(AddressType addressType);

	AddressDetails findByUserAndAddressType(User retailerOrDsrUser, AddressType addressType);

	AddressDetails findByBusinessDetailsAndAddressType(BusinessDetails businessDetails, AddressType addressType);

	Set<AddressDetails> findByUser(User user);

}
