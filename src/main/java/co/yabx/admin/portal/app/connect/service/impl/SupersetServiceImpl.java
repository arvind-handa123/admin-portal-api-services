package co.yabx.admin.portal.app.connect.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.admin.repositories.ProductConfigurationRepository;
import co.yabx.admin.portal.app.cache.RedisRepository;
import co.yabx.admin.portal.app.connect.service.SupersetService;
import co.yabx.admin.portal.app.service.AppConfigService;
import co.yabx.admin.portal.app.util.JwtTokenUtil;

@Service
public class SupersetServiceImpl implements SupersetService {

	@Autowired
	private ProductConfigurationRepository productConfigurationRepository;

	@PersistenceContext
	EntityManager connectEntityManagerFactory;

	@Autowired
	private RedisRepository redisRepository;
	@Autowired
	private AppConfigService appConfigServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(SupersetServiceImpl.class);

	@Override
	public String getSupersetURL(String link) {
		String token = JwtTokenUtil.prepareToken(link);
		return appConfigServiceImpl.getProperty("SUPERSET_BASE_URL", "http://superset-dr.yabx.co/iframe?token=")
				+ token;
	}

}
