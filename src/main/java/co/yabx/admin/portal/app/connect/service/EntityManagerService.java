package co.yabx.admin.portal.app.connect.service;

import java.util.List;

import co.yabx.admin.portal.app.kyc.dto.FieldsDTO;

public interface EntityManagerService {

	List<FieldsDTO[]> executeQuery(String filename);
}
