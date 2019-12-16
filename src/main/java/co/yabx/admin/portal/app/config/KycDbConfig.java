package co.yabx.admin.portal.app.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "kycEntityManagerFactory", transactionManagerRef = "kycTransactionManager", basePackages = {
		"co.yabx.admin.portal.app.kyc.repositories" })
public class KycDbConfig {

	@Bean(name = "kycDataSource")
	@ConfigurationProperties(prefix = "kyc.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "kycEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("kycDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("co.yabx.admin.portal.app.kyc.entities").persistenceUnit("User")
				.build();
	}

	@Bean(name = "kycTransactionManager")
	public PlatformTransactionManager barTransactionManager(
			@Qualifier("kycEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
		return new JpaTransactionManager(barEntityManagerFactory);
	}
}