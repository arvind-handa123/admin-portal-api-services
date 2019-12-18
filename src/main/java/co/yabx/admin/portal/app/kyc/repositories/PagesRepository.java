package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.PageType;
import co.yabx.admin.portal.app.kyc.entities.Pages;

@Repository("kycPagesRepository")
public interface PagesRepository extends CrudRepository<Pages, Long> {

	List<Pages> findByPageName(String user_type);

	List<Pages> findByPageType(PageType type);

}
