package com.vzwcorp.pricinglab.loader.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "rbmEntityManagerFactory", transactionManagerRef = "rbmTransactionManager", basePackages = { "com.vzwcorp.pricinglab.loader.profile.rbm.repository" })
@EnableConfigurationProperties(RbmConnectionSettings.class)
public class RbmConfig {
	@Autowired
	private RbmConnectionSettings rbm;

	@Value("${rbm.hibernate.dialect}")
	private String hibernateDialect;
	@Value("${rbm.hibernate.hbm2ddl.auto}")
	private boolean hibernateDdlAuto;
	@Value("${rbm.hibernate.show_sql}")
	private boolean hibernateShowSql;
	@Value("${rbm.hibernate.default_schema}")
	private String hibernateSchema;
	@Value("${rbm.rac.node.number:1}")
	int numberOfNodes;

	static Logger logger = LogManager.getLogger(RbmConfig.class);
	private static final String RBM_ENTITY_FOLDER = "com.vzwcorp.pricinglab.profile.rbm.vo";

	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "false", matchIfMissing = true)
	@Bean(name = "rbmDataSource")
	@Autowired
	public DataSource dataSource(Environment environment) {
		logger.info("Application starting up - loadding RBM RAC repository");

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		for (int index = 0; index < numberOfNodes; index++) {
			String nodeName = environment.getProperty("rbm.rac.node." + index);

			if (StringUtils.isNotBlank(nodeName)) {
				HikariConfig dataSourceConfig = new HikariConfig();

				dataSourceConfig.setDriverClassName(rbm.getDriver());
				dataSourceConfig.setJdbcUrl(rbm.getUrl().replaceAll("SERVICE_NAME=\\w+", "SERVICE_NAME=" + nodeName));
				dataSourceConfig.setUsername(rbm.getUsername());
				dataSourceConfig.setPassword(rbm.getPassword());
				targetDataSources.put(index, new HikariDataSource(dataSourceConfig));
			}
		}

		return createRacDataSource(targetDataSources);
	}

	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "true", matchIfMissing = false)
	@Bean(name = "rbmDataSource")
	@Autowired
	public DataSource jndiDataSource(Environment environment) {
		logger.info("Application starting up - loadding RBM RAC DataSource");
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		for (int index = 0; index < numberOfNodes; index++) {
			targetDataSources.put(index,
					jndiDataSourceLookup.getDataSource(environment.getProperty("jndi.rbm.datasource." + index)));
		}

		return createRacDataSource(targetDataSources);
	}

	@Bean(name = "rbmEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory(@Qualifier("rbmDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(RBM_ENTITY_FOLDER);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		jpaProperties.put("hibernate.default_schema", hibernateSchema);

		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		entityManagerFactoryBean.afterPropertiesSet();

		return entityManagerFactoryBean.getObject();
	}

	@Bean(name = "rbmTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("rbmEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private DataSource createRacDataSource(Map<Object, Object> targetDataSources) {
		RacDataSource racDataSource = new RacDataSource();
		racDataSource.setNumberOfNodes(numberOfNodes);
		racDataSource.setDefaultTargetDataSource(targetDataSources.get(0));
		racDataSource.setTargetDataSources(targetDataSources);

		return racDataSource;
	}
}
