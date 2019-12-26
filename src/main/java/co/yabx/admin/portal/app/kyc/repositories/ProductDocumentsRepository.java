package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.ProductName;
import co.yabx.admin.portal.app.kyc.entities.ProductDocuments;

@Repository("productDocumentsRepository")
public interface ProductDocumentsRepository extends CrudRepository<ProductDocuments, Long> {

	List<ProductDocuments> findByProductName(ProductName kyc);

}
