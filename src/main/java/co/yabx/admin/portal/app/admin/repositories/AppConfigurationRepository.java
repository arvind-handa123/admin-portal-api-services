package co.yabx.admin.portal.app.admin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.admin.entities.AppConfigurations;

@Repository("appConfigurationRepository")
public interface AppConfigurationRepository extends CrudRepository<AppConfigurations, Long> {

	// public void saveOrUpdate(AppConfigurations config);

	/* public AppConfigurations findByProperty(String propertyName); */

}
