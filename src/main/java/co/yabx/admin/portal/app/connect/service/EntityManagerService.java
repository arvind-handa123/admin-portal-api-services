package co.yabx.admin.portal.app.connect.service;

import java.util.List;

import co.yabx.admin.portal.app.kyc.dto.FieldsDTO;

public interface EntityManagerService {

	List<FieldsDTO[]> getResult(String filename);

	List<FieldsDTO[]> getFieldsDtoList(String filename);
}
