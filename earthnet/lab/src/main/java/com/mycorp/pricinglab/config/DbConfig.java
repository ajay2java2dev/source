package com.vzwcorp.pricinglab.config;

import com.vzwcorp.pricinglab.loader.config.RacDataSource;
import com.vzwcorp.pricinglab.properties.DbConnectionSettings;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "blEntityManagerFactory", transactionManagerRef = "blTransactionManager", basePackages = {
		"com.vzwcorp.pricinglab.vo.repository" })
@EnableConfigurationProperties(DbConnectionSettings.class)
public class DbConfig {
	@Autowired
	private DbConnectionSettings db;

	@Value("${db.hibernate.dialect}")
	private String hibernateDialect;
	@Value("${db.hibernate.hbm2ddl.auto}")
	private String hibernateDdlAuto;
	@Value("${db.hibernate.show_sql}")
	private boolean hibernateShowSql;
	@Value("${db.hibernate.default_schema}")
	private String hibernateSchema;
	@Value("${db.hibernate.naming.strategy}")
	private String namingStrategy;	
	@Value("${db.rac.node.number:1}")
	int numberOfNodes;

	//Cache Settings for Hibernate (Ver : 4.4.2)
	@Value("${db.hibernate.cache.region.factory_class:com.hazelcast.hibernate.HazelcastCacheRegionFactory}")
	private String hibernateCacheRegionFactoryClass;
	@Value("${db.hibernate.cache.use_second_level_cache:false}")
	private boolean hibernateSecondLevelCache;
	@Value("${db.hibernate.cache.use_query_cache:false}")
	private boolean hibernateUseQueryCache;
	@Value("${db.hibernate.cache.use_minimal_puts:false}")
	private boolean hibernateMinimalPuts;
	@Value("${db.hibernate.cache.hazelcast.use_lite_member:false}")
	private boolean hibernateUseLiteMember;

	static Logger logger = LogManager.getLogger(DbConfig.class);
	private static final String PRICINGLAB_ENTITY_FOLDER = "com.vzwcorp.pricinglab.vo";

	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "false", matchIfMissing = true)
	@Bean(name = "blDataSource")
	@Autowired
	public DataSource dataSource(Environment environment) {
		logger.info("Application starting up - loading DB repository");

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		for (int index = 0; index < numberOfNodes; index++) {
			String nodeName = environment.getProperty("db.rac.node." + index);
			
			if (StringUtils.isNotBlank(nodeName)) {
				HikariConfig dataSourceConfig = new HikariConfig();

				dataSourceConfig.setDriverClassName(db.getDriver());
				dataSourceConfig.setJdbcUrl(db.getUrl().replaceAll("SERVICE_NAME=\\w+", "SERVICE_NAME=" + nodeName));
				dataSourceConfig.setUsername(db.getUsername());
				dataSourceConfig.setPassword(db.getPassword());
				dataSourceConfig.setAutoCommit(true);
				targetDataSources.put(index, new HikariDataSource(dataSourceConfig));
			}
		}
		
		return  createRacDataSource(targetDataSources);
	}
	
	
	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "true", matchIfMissing = false)
	@Bean(name = "blDataSource")
	@Autowired
	public DataSource jndiDataSource(Environment environment) {
		logger.info("Application starting up - loadding plab DB RAC DataSource");
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		for (int index = 0; index < numberOfNodes; index++) {
			targetDataSources.put(index,
					jndiDataSourceLookup.getDataSource(environment.getProperty("jndi.db.datasource." + index)));
		}

		return createRacDataSource(targetDataSources);
	}


	@Bean(name = "blEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory(@Qualifier("blDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(PRICINGLAB_ENTITY_FOLDER);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		jpaProperties.put("namingStrategy",namingStrategy);
		jpaProperties.put("hibernate.default_schema", hibernateSchema);
		//cache settings
		jpaProperties.put("hibernate.cache.region.factory_class",hibernateCacheRegionFactoryClass);
		jpaProperties.put("hibernate.cache.use_second_level_cache",hibernateSecondLevelCache);
		jpaProperties.put("hibernate.cache.use_query_cache",hibernateUseQueryCache);
		jpaProperties.put("hibernate.cache.use_minimal_puts",hibernateMinimalPuts);
		jpaProperties.put("hibernate.cache.hazelcast.use_lite_members",hibernateUseLiteMember);

		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		entityManagerFactoryBean.afterPropertiesSet();

		return entityManagerFactoryBean.getObject();
	}

	@Bean(name = "blTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("blEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
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
