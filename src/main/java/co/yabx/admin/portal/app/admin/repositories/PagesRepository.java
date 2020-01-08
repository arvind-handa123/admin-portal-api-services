package co.yabx.admin.portal.app.admin.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.admin.entities.Pages;
import co.yabx.admin.portal.app.admin.entities.ProductConfigurations;
import co.yabx.admin.portal.app.enums.PageType;

@Repository("pagesRepository")
public interface PagesRepository extends CrudRepository<Pages, Long> {

	List<Pages> findByPageName(String user_type);

	List<Pages> findByPageType(PageType type);

	List<Pages> findByProductConfig(ProductConfigurations productConfigurations);

}
