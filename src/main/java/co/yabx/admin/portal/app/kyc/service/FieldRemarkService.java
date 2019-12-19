package co.yabx.admin.portal.app.kyc.service;

import java.util.List;

import co.yabx.admin.portal.app.kyc.dto.RemarksDTO;

public interface FieldRemarkService {

	boolean updateRemark(Long user_id, String remarkBy, List<RemarksDTO> remarksDTOList);

}
