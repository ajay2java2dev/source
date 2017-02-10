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
@EnableJpaRepositories(entityManagerFactoryRef = "ubsrEntityManagerFactory", transactionManagerRef = "ubsrTransactionManager", basePackages = { "com.vzwcorp.pricinglab.loader.profile.ubsr.repository" })
@EnableConfigurationProperties(UbsrConnectionSettings.class)
public class UbsrConfig {
	@Autowired
	private UbsrConnectionSettings ubsr;

	@Value("${ubsr.hibernate.dialect}")
	private String hibernateDialect;
	@Value("${ubsr.hibernate.hbm2ddl.auto}")
	private boolean hibernateDdlAuto;
	@Value("${ubsr.hibernate.show_sql}")
	private boolean hibernateShowSql;
	@Value("${ubsr.hibernate.default_schema}")
	private String hibernateSchema;
	@Value("${ubsr.rac.node.number:1}")
	int numberOfNodes;

	static Logger logger = LogManager.getLogger(UbsrConfig.class);
	private static final String UBSR_ENTITY_FOLDER = "com.vzwcorp.pricinglab.profile.vo";

	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "false", matchIfMissing = true)
	@Bean(name = "ubsrDataSource")
	@Autowired
	public DataSource dataSource(Environment environment) {
		logger.info("Application starting up - loadding UBSR RAC repository");

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		for (int index = 0; index < numberOfNodes; index++) {
			String nodeName = environment.getProperty("ubsr.rac.node." + index);

			if (StringUtils.isNotBlank(nodeName)) {
				HikariConfig dataSourceConfig = new HikariConfig();

				dataSourceConfig.setJdbcUrl(ubsr.getUrl().replaceAll("SERVICE_NAME=\\w+", "SERVICE_NAME=" + nodeName));
				dataSourceConfig.setDriverClassName(ubsr.getDriver());
				dataSourceConfig.setUsername(ubsr.getUsername());
				dataSourceConfig.setPassword(ubsr.getPassword());

				targetDataSources.put(index, new HikariDataSource(dataSourceConfig));
			}
		}

		return createRacDataSource(targetDataSources);
	}

	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "true", matchIfMissing = false)
	@Bean(name = "ubsrDataSource")
	@Autowired
	public DataSource jndiDataSource(Environment environment) {
		logger.info("Application starting up - loadding UBSR Rac DataSource");
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		for (int index = 0; index < numberOfNodes; index++) {
			targetDataSources.put(index,
					jndiDataSourceLookup.getDataSource(environment.getProperty("jndi.ubsr.datasource." + index)));
		}

		return createRacDataSource(targetDataSources);
	}

	@Bean(name = "ubsrEntityManagerFactory")
	@Autowired
	public EntityManagerFactory entityManagerFactory(@Qualifier("ubsrDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(UBSR_ENTITY_FOLDER);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		jpaProperties.put("hibernate.default_schema", hibernateSchema);

		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		entityManagerFactoryBean.afterPropertiesSet();

		return entityManagerFactoryBean.getObject();
	}

	@Bean(name = "ubsrTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("ubsrEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
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
