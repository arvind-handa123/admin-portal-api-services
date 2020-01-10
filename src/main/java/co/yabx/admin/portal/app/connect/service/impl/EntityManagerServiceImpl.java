package co.yabx.admin.portal.app.connect.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.admin.repositories.PagesRepository;
import co.yabx.admin.portal.app.admin.repositories.ProductConfigurationRepository;
import co.yabx.admin.portal.app.cache.RedisRepository;
import co.yabx.admin.portal.app.connect.service.EntityManagerService;
import co.yabx.admin.portal.app.kyc.dto.FieldsDTO;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;

@Service
public class EntityManagerServiceImpl implements EntityManagerService {

	@Autowired
	private ProductConfigurationRepository productConfigurationRepository;

	@PersistenceContext
	EntityManager connectEntityManagerFactory;

	@Autowired
	private RedisRepository redisRepository;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private PagesRepository pagesRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerServiceImpl.class);

	@Override
	public List<FieldsDTO[]> getResult(String filename) {
		String queryString = "SELECT cus.created_at as created_at,cus.msisdn as msisdn,cus.instruments_count as instruments_count,cus.customer_at as customer_at,cus.updated_at as updated_at FROM customers cus where cus.partner_id = 38 order by cus.id desc";
		Iterator<?> queryItr = executeQuery(queryString);
		List<FieldsDTO[]> fieldsDTOs = new ArrayList<FieldsDTO[]>();
		while (queryItr.hasNext()) {
			Object[] tuple = (Object[]) queryItr.next();
			FieldsDTO[] fieldsDTOList = new FieldsDTO[tuple.length];
			FieldsDTO fieldsDTO = new FieldsDTO();
			fieldsDTO.setFieldName("Created");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 0 ? tuple[0] : null);
			fieldsDTOList[0] = fieldsDTO;
			fieldsDTO = new FieldsDTO();
			fieldsDTO.setFieldName("MSISDN");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 1 ? tuple[1] : null);
			fieldsDTOList[1] = fieldsDTO;
			fieldsDTO = new FieldsDTO();
			fieldsDTO.setFieldName("Products");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 2 ? tuple[2] : null);
			fieldsDTOList[2] = fieldsDTO;
			fieldsDTO = new FieldsDTO();
			fieldsDTO.setFieldName("Last Customer Action");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 3 ? tuple[3] : null);
			fieldsDTOList[3] = fieldsDTO;
			fieldsDTO = new FieldsDTO();
			fieldsDTO.setFieldName("Updated");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 4 ? tuple[4] : null);
			fieldsDTOList[4] = fieldsDTO;
			fieldsDTOs.add(fieldsDTOList);

		}
		return fieldsDTOs;
	}

	@Override
	public List<FieldsDTO[]> getFieldsDtoList(String filename) {
		String queryString = "SELECT tm.msisdn as msisdn,   tm.message as tm_message,     tm.status as tm_status,     tm.vendor_last_message as vendor_last_message,     ifnull((tm.vendor_last_updated_at),(tm.dispatched_at)) as last_feedback     FROM text_messages tm     where tm.partner_id = 38          order by tm.id desc";
		Iterator<?> queryItr = executeQuery(queryString);
		List<FieldsDTO[]> fieldsDTOs = new ArrayList<FieldsDTO[]>();
		while (queryItr.hasNext()) {
			Object[] tuple = (Object[]) queryItr.next();
			FieldsDTO[] fieldsDTOList = new FieldsDTO[tuple.length];
			for (int i = 0; i < tuple.length; i++) {
				FieldsDTO fieldsDTO = new FieldsDTO();
				if (i == 0)
					fieldsDTO.setFieldName("MSISDN");
				if (i == 1)
					fieldsDTO.setFieldName("Message");
				if (i == 2)
					fieldsDTO.setFieldName("Status");
				if (i == 3)
					fieldsDTO.setFieldName("Vendor Message	");
				if (i == 4)
					fieldsDTO.setFieldName("Last Feedback");
				fieldsDTO.setSavedData(tuple != null && tuple.length > i ? tuple[i] : null);
				fieldsDTOList[i] = fieldsDTO;
			}
			fieldsDTOs.add(fieldsDTOList);
		}
		return fieldsDTOs;
	}

	private Iterator<?> executeQuery(String queryString) {
		Query query = connectEntityManagerFactory.createNativeQuery(queryString);
		return query.getResultList().iterator();
	}

}
