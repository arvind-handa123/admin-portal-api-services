package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.LicenseType;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.LicenseDetails;

@Repository("licenseDetailsRepository")
public interface LicenseDetailsRepository extends CrudRepository<LicenseDetails, Long> {

	LicenseDetails findByBusinessDetailsAndLicenseType(BusinessDetails businessDetails, LicenseType licenseType);

}
