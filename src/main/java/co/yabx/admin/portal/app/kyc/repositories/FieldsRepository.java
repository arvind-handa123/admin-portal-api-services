package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.Fields;

@Repository("kycFieldsRepository")
public interface FieldsRepository extends CrudRepository<Fields, Long> {

	List<Fields> findByFieldId(String fieldId);

}
