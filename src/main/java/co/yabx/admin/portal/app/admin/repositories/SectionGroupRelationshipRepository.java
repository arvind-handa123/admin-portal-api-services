package co.yabx.admin.portal.app.admin.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.admin.entities.SectionGroupRelationship;


@Repository("sectionGroupRelationshipRepository")
public interface SectionGroupRelationshipRepository extends CrudRepository<SectionGroupRelationship, Long> {

	List<SectionGroupRelationship> findBySectionId(Long sectionId);

}
