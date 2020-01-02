package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.Fields;

@Repository
public interface FieldsRepository extends CrudRepository<Fields, Long> {

	Fields findByFieldId(String fieldId);

}
