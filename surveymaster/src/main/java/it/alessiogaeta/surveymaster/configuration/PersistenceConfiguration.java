package it.alessiogaeta.surveymaster.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "it.alessiogaeta.surveymaster.dao")
@EnableTransactionManagement
public class PersistenceConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfiguration.class);

	@Bean(destroyMethod = "close")
	DataSource dataSource() {
		LOGGER.info("Init DataSource");

		final HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName("org.h2.Driver");
		dataSourceConfig.setJdbcUrl("jdbc:h2:mem:appdb;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1");
		dataSourceConfig.setUsername("sa");

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
	        throws FileNotFoundException, URISyntaxException {
		LOGGER.info("Init EntityManagerFactory");

		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("it.alessiogaeta.surveymaster.model");

		final Properties jpaProperties = new Properties();
		jpaProperties.put(PersistenceUnitProperties.TARGET_DATABASE,
		        "org.eclipse.persistence.platform.database.H2Platform");
		jpaProperties.put(PersistenceUnitProperties.SCHEMA_GENERATION_DATABASE_ACTION, "create");
		jpaProperties.put(PersistenceUnitProperties.WEAVING, "false");
		jpaProperties.put(PersistenceUnitProperties.LOGGING_LEVEL, "FINE");

		// Populate DB
		final URI scriptUri = getClass().getResource("/data.sql").toURI();
		jpaProperties.put(PersistenceUnitProperties.SCHEMA_GENERATION_SQL_LOAD_SCRIPT_SOURCE,
		        new FileReader(new File(scriptUri)));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		LOGGER.info("Init TransactionManager");

		final JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

	// H2 console. It shouldn't be there in a real case (maybe just with
	// @Profile("test")). But it could be useful, now...
	@Bean(initMethod = "start", destroyMethod = "stop")
	public org.h2.tools.Server h2WebConsonleServer() throws SQLException {
		return org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "8082");
	}
}
