package co.yabx.admin.portal.app.kyc.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.kyc.dto.RemarksDTO;
import co.yabx.admin.portal.app.kyc.entities.AuthInfo;
import co.yabx.admin.portal.app.kyc.entities.DSRUser;
import co.yabx.admin.portal.app.kyc.entities.FieldRemarks;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.repositories.AuthInfoRepository;
import co.yabx.admin.portal.app.kyc.repositories.FieldRemarksRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.AdminService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;
import co.yabx.admin.portal.app.kyc.service.DSRService;
import co.yabx.admin.portal.app.kyc.service.FieldRemarkService;
import co.yabx.admin.portal.app.security.SecurityUtils;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class FieldRemarkServiceImpl implements FieldRemarkService {

	@Autowired
	private AuthInfoService authInfoService;

	@Autowired
	private FieldRemarksRepository fieldRemarksRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRelationshipsRepository userRelationshipsRepository;

	@Autowired
	private DSRService dsrService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FieldRemarkServiceImpl.class);

	@Override
	public boolean updateRemark(Long user_id, String remarkBy, List<RemarksDTO> remarksDTOList) {
		Optional<User> user = userRepository.findById(user_id);
		if (user.isPresent()) {
			try {
				List<FieldRemarks> fieldRemarksList = fieldRemarksRepository.findByUserId(user_id);
				for (RemarksDTO remarksDTO : remarksDTOList) {
					FieldRemarks fieldRemarks = null;
					if (remarksDTO.getSide() != null && !remarksDTO.getSide().isEmpty()) {
						Optional<FieldRemarks> fieldRemarksOptional = fieldRemarksList.stream()
								.filter(f -> f.getFieldId().equalsIgnoreCase(remarksDTO.getFieldId())
										&& remarksDTO.getSide().equalsIgnoreCase(f.getSide()))
								.findFirst();
						fieldRemarks = fieldRemarksOptional.isPresent() ? fieldRemarksOptional.get() : null;
					} else {
						Optional<FieldRemarks> fieldRemarksOptional = fieldRemarksList.stream()
								.filter(f -> f.getFieldId().equalsIgnoreCase(remarksDTO.getFieldId())).findFirst();
						if (fieldRemarksOptional.isPresent()) {
							fieldRemarks = fieldRemarksOptional.get();
						}
					}
					if (fieldRemarks == null) {
						fieldRemarks = new FieldRemarks();
						fieldRemarks.setUserId(user_id);
						fieldRemarks.setFieldId(remarksDTO.getFieldId());
					}
					fieldRemarks.setRemark(remarksDTO.getRemark());
					fieldRemarks.setRemarkBy(remarkBy);
					fieldRemarks.setSide(remarksDTO.getSide());
					fieldRemarksRepository.save(fieldRemarks);
				}

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Exception raised while pushing remark for user={},error={}", user_id, e.getMessage());
			}
			return true;
		}
		return false;
	}

}
