package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.Retailers;
import co.yabx.admin.portal.app.kyc.entities.User;

@Repository("retailersRepository")
public interface RetailersRepository extends BaseUserRepository<Retailers>, CrudRepository<Retailers, Long> {

	User findBymsisdn(String msisdn);

	Retailers findByRetailerId(String retailerId);

}
