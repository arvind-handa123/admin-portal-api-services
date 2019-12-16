package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.Customers;
import co.yabx.admin.portal.app.kyc.entities.User;


@Repository("customersRepository")
public interface CustomersRepository extends BaseUserRepository<Customers>, CrudRepository<Customers, Long> {

	User findBymsisdn(String msisdn);

}
