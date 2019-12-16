package co.yabx.admin.portal.app.kyc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import co.yabx.admin.portal.app.kyc.entities.User;


@NoRepositoryBean
public interface BaseUserRepository<EntityType extends User> extends CrudRepository<EntityType, Long> {

}
