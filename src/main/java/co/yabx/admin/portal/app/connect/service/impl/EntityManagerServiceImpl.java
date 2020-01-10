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
	public List<FieldsDTO> executeQuery(String filename) {
		String queryString = "SELECT cus.created_at as created_at,cus.msisdn as msisdn,cus.instruments_count as instruments_count,cus.customer_at as customer_at,cus.updated_at as updated_at FROM customers cus where cus.partner_id = 38 order by cus.id desc";
		Query query = connectEntityManagerFactory.createNativeQuery(queryString);
		Iterator<?> queryItr = query.getResultList().iterator();
		List<FieldsDTO> fieldsDTOList = new ArrayList<FieldsDTO>();
		while (queryItr.hasNext()) {
			Object[] tuple = (Object[]) queryItr.next();
			FieldsDTO fieldsDTO = new FieldsDTO();
			fieldsDTO.setFieldName("created_at");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 0 ? tuple[0] : null);
			fieldsDTO.setFieldName("msisdn");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 1 ? tuple[1] : null);
			fieldsDTO.setFieldName("instruments_count");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 2 ? tuple[2] : null);
			fieldsDTO.setFieldName("customer_at");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 3 ? tuple[3] : null);
			fieldsDTO.setFieldName("updated_at");
			fieldsDTO.setSavedData(tuple != null && tuple.length > 4 ? tuple[4] : null);
			fieldsDTOList.add(fieldsDTO);

		}
		return fieldsDTOList;
	}

}
