package co.yabx.admin.portal.app.kyc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.enums.DocumentType;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.User;

@Repository("attachmentDetailsRepository")
public interface AttachmentDetailsRepository extends CrudRepository<AttachmentDetails, Long> {

	AttachmentDetails findByUserAndDocumentType(User user, DocumentType documentType);

	List<AttachmentDetails> findByUser(User user);

	AttachmentDetails findByUserAndDocumentTypeAndAttachmentType(User user, DocumentType documentType,
			AttachmentType attachmentType);

}
