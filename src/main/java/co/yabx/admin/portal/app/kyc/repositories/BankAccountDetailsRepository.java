package co.yabx.admin.portal.app.kyc.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.User;

@Repository("bankAccountDetailsRepository")
public interface BankAccountDetailsRepository extends CrudRepository<BankAccountDetails, Long> {

	Set<BankAccountDetails> findByUser(User user);

}
