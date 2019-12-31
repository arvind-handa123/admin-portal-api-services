package co.yabx.admin.portal.app.kyc.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	String uploadImage(MultipartFile file, Long userId, boolean createFileName) throws Exception;

	byte[] getImage(String filename, Long userId);

	byte[] getDisclaimerDocuments(String filename);

}
