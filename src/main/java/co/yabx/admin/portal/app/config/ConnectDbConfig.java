package co.yabx.admin.portal.app.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "connectEntityManagerFactory", transactionManagerRef = "connectTransactionManager", basePackages = {
		"co.yabx.admin.portal.app.connect.repositories" })
public class ConnectDbConfig {
	@Autowired
	private Environment env;

	@Primary
	@Bean(name = "connectDataSource")
	@ConfigurationProperties(prefix = "connect.datasource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("connect.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("connect.datasource.jdbc-url"));
		dataSource.setUsername(env.getProperty("connect.datasource.username"));
		dataSource.setPassword(env.getProperty("connect.datasource.password"));
		return dataSource;

	}

	@Primary
	@Bean(name = "connectEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("connectDataSource") DataSource connectDataSource) {
		return builder.dataSource(connectDataSource).packages("co.yabx.admin.portal.app.connect.entities")
				.persistenceUnit("Users").build();
	}

	@Primary
	@Bean(name = "connectTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("connectEntityManagerFactory") EntityManagerFactory connectEntityManagerFactory) {
		return new JpaTransactionManager(connectEntityManagerFactory);
	}
}