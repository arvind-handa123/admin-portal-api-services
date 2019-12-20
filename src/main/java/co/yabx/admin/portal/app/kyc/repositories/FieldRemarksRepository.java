package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.FieldRemarks;

@Repository("fieldRemarksRepository")
public interface FieldRemarksRepository extends CrudRepository<FieldRemarks, Long> {

	List<FieldRemarks> findByUserIdAndFieldId(Long userId, String fieldId);

	List<FieldRemarks> findByUserId(Long id);

}
