package com.vzwcorp.pricinglab.quartz;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

import com.vzwcorp.pricinglab.loader.config.RacDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.vzwcorp.pricinglab.service.ProductManager;

@Component
@Configuration
public class JobConfig {
	@Value("${job.db.url:}") //default value is set to empty
	String url;
	@Value("${job.db.username:}") //default value is set to empty
	String username;
	@Value("${job.db.password:}")  //default value is set to empty
	String password;
	@Value("${job.db.driverclassname}")
	String driverClassName;
	@Value("${job.cronexpression}")
	String cronexpression;
	@Value("${job.autostart}")
	boolean autoStart;
	@Value("${job.overwritejob}")
	boolean overwritejob;
	@Value("${job.quartz.properties:quartz.properties}")
	String quartzProperties;
	@Value("${job.name}")
	String jobName;
	@Value("${job.trigger.name}")
	String jobTriggerName;
	@Value("${job.scheduler.name}")
	String jobSchedulerName;
	@Value("${job.nodes.number:1}")
	Integer numberOfNodes;

	@Value("${jndi.enabled:false}")
	boolean jndiEnabled;
	@Value("${jndi.plab.quartz.datasource.0}")
	String jndiQuartz;

	@Autowired
	ProductManager productManager;
	static Logger logger = LogManager.getLogger(JobConfig.class);

	private DataSource createRacDataSource(Map<Object, Object> targetDataSources) {
		RacDataSource racDataSource = new RacDataSource();
		racDataSource.setNumberOfNodes(numberOfNodes);
		racDataSource.setDefaultTargetDataSource(targetDataSources.get(0));
		racDataSource.setTargetDataSources(targetDataSources);
		return racDataSource;
	}

/*
	@Bean
	DataSource dataSource() {

		DataSource ds = null;

		if (jndiEnabled) {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup(jndiQuartz);
			} catch (Exception ex) {
				logger.error("Exception in job config : ",ex.getMessage(),ex);
			}
			return ds;
		} else {
			DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
			driverManagerDataSource.setDriverClassName(driverClassName);
			driverManagerDataSource.setUrl(url);
			driverManagerDataSource.setUsername(username);
			driverManagerDataSource.setPassword(password);
			return driverManagerDataSource;
		}
	}
*/

	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "false", matchIfMissing = true)
	@Bean(name = "blQrtzDataSource")
	@Autowired
	public DataSource dataSource(Environment environment) {
		logger.info("Application starting up - loading DB repository");

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		for (int index = 0; index < numberOfNodes; index++) {
			String nodeName = environment.getProperty("job.db.rac.node." + index);

			if (StringUtils.isNotBlank(nodeName)) {
				HikariConfig dataSourceConfig = new HikariConfig();

				dataSourceConfig.setDriverClassName(driverClassName);
				dataSourceConfig.setJdbcUrl(url.replaceAll("SERVICE_NAME=\\w+", "SERVICE_NAME=" + nodeName));
				dataSourceConfig.setUsername(username);
				dataSourceConfig.setPassword(password);
				dataSourceConfig.setAutoCommit(true);
				targetDataSources.put(index, new HikariDataSource(dataSourceConfig));
			}
		}

		return  createRacDataSource(targetDataSources);
	}


	@ConditionalOnProperty(name = "jndi.enabled", havingValue = "true", matchIfMissing = false)
	@Bean(name = "blQrtzDataSource")
	@Autowired
	public DataSource jndiDataSource(Environment environment) {
		logger.info("Application starting up - loadding plab DB RAC DataSource");
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		for (int index = 0; index < numberOfNodes; index++) {
			targetDataSources.put(index,
					jndiDataSourceLookup.getDataSource(environment.getProperty("jndi.plab.quartz.datasource." + index)));
		}

		return createRacDataSource(targetDataSources);
	}

	@Bean(name = "plabQuartzJndiTransactionManager")
	PlatformTransactionManager transactionManager(@Qualifier("blQrtzDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	Scheduler scheduler(ApplicationContext applicationContext,@Qualifier("plabQuartzJndiTransactionManager") PlatformTransactionManager platformTransactionManager
            ,@Qualifier("blQrtzDataSource") DataSource dataSource) throws Exception {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setQuartzProperties(quartzProperties());
		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setTransactionManager(platformTransactionManager);
		schedulerFactoryBean.setSchedulerName(jobSchedulerName);
		schedulerFactoryBean.setOverwriteExistingJobs(overwritejob);
		schedulerFactoryBean.setAutoStartup(autoStart);
		schedulerFactoryBean.afterPropertiesSet();

		Scheduler s = schedulerFactoryBean.getObject();
		s.start();

		return s;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource(quartzProperties));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

}