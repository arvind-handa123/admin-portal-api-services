package co.yabx.admin.portal.app.admin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.admin.entities.ProductConfigurations;

@Repository("productConfigurationRepository")
public interface ProductConfigurationRepository extends CrudRepository<ProductConfigurations, Long> {

	// public void saveOrUpdate(AppConfigurations config);

	/* public AppConfigurations findByProperty(String propertyName); */

}
