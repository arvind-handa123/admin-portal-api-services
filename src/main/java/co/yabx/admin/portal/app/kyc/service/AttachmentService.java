package co.yabx.admin.portal.app.kyc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.yabx.admin.portal.app.kyc.dto.AttachmentDetailsDTO;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.User;

/**
 * 
 * @author Asad.ali
 *
 */
public interface AttachmentService {

	AttachmentDetails persistInDb(User user, MultipartFile file, String filename) throws Exception;

	AttachmentDetails persistDsrProfilePicInDb(User user, MultipartFile files, String saveFileName);

	String fetchDsrProfilePic(User user);

	List<AttachmentDetailsDTO> getRetailerDocuments(User user);

}
