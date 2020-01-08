package co.yabx.admin.portal.app.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.service.AdminStorageService;

@Service
public class AdminStorageServiceImpl implements AdminStorageService {

	private String BASE_PATH = "/var/lib/kyc/icons";
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminStorageServiceImpl.class);

	@Override
	public byte[] getLogoImage(String filename) {
		try {
			String path = BASE_PATH + "/" + filename;
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			String extension = FilenameUtils.getExtension(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, extension, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (Exception e) {
			LOGGER.error("exception raised while fetching image={},error={}", filename, e.getMessage());
			return null;
		}
	}
}
