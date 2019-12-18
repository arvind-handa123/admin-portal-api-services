package co.yabx.admin.portal.app.admin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.admin.entities.Groups;


@Repository("groupsRepository")
public interface GroupsRepository extends CrudRepository<Groups, Long> {

}
