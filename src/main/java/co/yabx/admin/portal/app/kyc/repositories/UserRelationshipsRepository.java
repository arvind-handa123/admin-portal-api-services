package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.kyc.entities.Retailers;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.UserRelationships;

@Repository("userRelationshipsRepository")
public interface UserRelationshipsRepository extends CrudRepository<UserRelationships, Long> {

	List<UserRelationships> findByMsisdn(String msisdn);

	UserRelationships findByRelative(User user);

	UserRelationships findByMsisdnAndRelative(String dsrMsisdn, Retailers retailers);

	List<UserRelationships> findByMsisdnAndRelationship(String msisdn, Relationship relationship);

	UserRelationships findByMsisdnAndRelationshipAndRelative(String dsrMSISDN, Relationship retailer,
			User retailerUser);

	List<UserRelationships> findByRelationship(Relationship retailer);
}
