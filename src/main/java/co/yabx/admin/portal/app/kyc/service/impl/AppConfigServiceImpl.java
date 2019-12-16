package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.admin.entities.AppConfigurations;
import co.yabx.admin.portal.app.admin.repositories.AppConfigurationRepository;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;

@Service
public class AppConfigServiceImpl implements AppConfigService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfigServiceImpl.class);

	@Autowired
	private AppConfigurationRepository appConfigurationRepository;

	private Map<String, String> propertyMap = new ConcurrentHashMap<>();
	private volatile boolean isLoaded = false;

	@Override
	public String getProperty(String propertyName, String defaultValue) {
		String value = getProperty(propertyName);
		return value == null ? defaultValue : value;
	}

	@Override
	public String getPropertyWithEmptyCheck(String propertyName, String defaultValue) {
		String value = getProperty(propertyName);
		return value == null || value.isEmpty() ? defaultValue : value;
	}

	@Override
	public String getProperty(String propertyName) {
		checkIfLoadOrRefresh();
		return propertyMap.get(propertyName);
	}

	@Override
	public Boolean getBooleanProperty(String propertyName) {
		checkIfLoadOrRefresh();
		String propertyValue = propertyMap.get(propertyName);
		if (propertyValue == null)
			return null;
		boolean value = Boolean.parseBoolean(propertyValue);
		return value;
	}

	@Override
	public Boolean getBooleanProperty(String propertyName, Boolean defaultValue) {
		Boolean value = getBooleanProperty(propertyName);
		return value == null ? defaultValue : value;
	}

	@Override
	public Integer getIntProperty(String propertyName, Integer defaultValue) {
		Integer value = getIntProperty(propertyName);
		return value == null ? defaultValue : value;
	}

	@Override
	public Long getLongProperty(String propertyName) {
		checkIfLoadOrRefresh();
		String propertyValue = propertyMap.get(propertyName);
		if (propertyValue == null)
			return null;
		try {
			long value = Long.parseLong(propertyValue);
			return value;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long getLongProperty(String propertyName, Long defaultValue) {
		Long value = getLongProperty(propertyName);
		return value == null ? defaultValue : value;
	}

	@Override
	public Integer getIntProperty(String propertyName) {
		checkIfLoadOrRefresh();
		String propertyValue = propertyMap.get(propertyName);
		if (propertyValue == null)
			return null;
		try {
			int value = Integer.parseInt(propertyValue);
			return value;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Double getDoubleProperty(String propertyName, Double defaultValue) {
		Double value = getDoubleProperty(propertyName);
		return value == null ? defaultValue : value;
	}

	@Override
	public Double getDoubleProperty(String propertyName) {
		checkIfLoadOrRefresh();
		String propertyValue = propertyMap.get(propertyName);
		if (propertyValue == null)
			return null;
		try {
			double value = Double.parseDouble(propertyValue);
			return value;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Float getFloatProperty(String propertyName, Float defaultValue) {
		Float value = getFloatProperty(propertyName);
		return value == null ? defaultValue : value;
	}

	@Override
	public Float getFloatProperty(String propertyName) {
		checkIfLoadOrRefresh();
		String propertyValue = propertyMap.get(propertyName);
		if (propertyValue == null)
			return null;
		try {
			float value = Float.parseFloat(propertyValue);
			return value;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void checkIfLoadOrRefresh() {
		if (isLoaded) {
			return;
		}

		synchronized (propertyMap) {
			if (!isLoaded) {
				Iterable<AppConfigurations> propertyList = appConfigurationRepository.findAll();
				for (AppConfigurations dbConfig : propertyList) {
					propertyMap.put(dbConfig.getProperty(), dbConfig.getValue());
				}

				LOGGER.info("App config loaded");

				/**
				 * Not adding to logs
				 */

				System.out.println(propertyMap);

				isLoaded = true;
			}
		}
	}

	@Override
	public void refresh() {
		LOGGER.info("Refreshed DbConfig");
		Iterable<AppConfigurations> propertyList = appConfigurationRepository.findAll();
		propertyMap.clear();
		for (AppConfigurations dbConfig : propertyList) {
			propertyMap.put(dbConfig.getProperty(), dbConfig.getValue());

		}
		LOGGER.info("db config refreshed :" + propertyMap);

	}

	/*
	 * @Override public void update(AppConfigurations config) {
	 * appConfigurationRepository.saveOrUpdate(config); }
	 */

	/*
	 * @Override public List<String> getPropertyValues(String propertyName,
	 * List<String> asList) { AppConfigurations config =
	 * appConfigurationRepository.findByProperty(propertyName); if (config == null)
	 * return asList; return Arrays.asList(config.getValue()); }
	 * 
	 * @Override public void updateTag(String tag, String value) { AppConfigurations
	 * config = appConfigurationRepository.findByProperty(tag); if (config != null)
	 * { config.setValue(value); update(config); refresh(); } }
	 */

}
