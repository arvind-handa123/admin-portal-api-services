package co.yabx.admin.portal.app.kyc.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.StorageService;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private AppConfigService appConfigService;

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

	@Override
	public String uploadImage(MultipartFile file, Long retailerId) throws Exception {
		String fileName = file.getOriginalFilename().replaceAll(" ", "_");
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String newFileName = System.currentTimeMillis() + "." + extension;
		File convFile = new File(fileName);
		String path = appConfigService.getProperty("DOCUMENT_STORAGE_BASE_PATH", "/tmp/") + retailerId + "/"
				+ newFileName;
		convFile.createNewFile();
		try (FileOutputStream fos = new FileOutputStream(convFile)) {
			fos.write(file.getBytes());
		}
		BufferedImage image = null;
		image = ImageIO.read(convFile);
		LOGGER.info("Image getting stored at location={} for retailer={}", path, retailerId);
		File destination = new File(
				appConfigService.getProperty("DOCUMENT_STORAGE_BASE_PATH", "/tmp/") + retailerId + "/");
		if (!destination.exists())
			destination.mkdirs();
		ImageIO.write(image, extension, new File(path));
		return newFileName;
	}

	@Override
	public byte[] getImage(String filename, Long retailerId) throws Exception {
		String path = appConfigService.getProperty("DOCUMENT_STORAGE_BASE_PATH", "/tmp/") + retailerId + "/" + filename;
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);
		String extension = FilenameUtils.getExtension(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, extension, baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}

	@Override
	public byte[] getDisclaimerDocuments(String filename) throws Exception {

		String uri = appConfigService.getProperty("DISCLAIMER_DOCUMENT_STORAGE_BASE_PATH", "/var/lib/kyc/disclaimer_documents")
				+ "/" + filename;
		Path path = Paths.get(uri);
		return Files.readAllBytes(path);
	}

}
