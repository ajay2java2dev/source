package com.vzwcorp.pricinglab.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;

import com.ibm.mq.jms.MQQueue;
import com.vzwcorp.pricinglab.mq.JmsMq;
import com.vzwcorp.pricinglab.mq.JndiMq;

@ConditionalOnProperty(name = "jms.enabled", havingValue = "true", matchIfMissing = false)
@Configuration
public class JmsConfig {

	static Logger logger = LogManager.getLogger(JmsConfig.class);

	private final String MQ_AGS_JNDI = "mq.ags.jndi";

	private final String QUEUE_CF = "cf";
	private final String QUEUE_NAME = "qname";
	private final String QUEUE_CONNS = "conns";

	@Value("${jndi.mq.enabled:false}")
	private boolean jndiEnabled;

	@Value("${mq.ags.host:}")
	private String agsHost;

	@Value("${mq.ags.port:0}")
	private String agsPort;

	@Value("${mq.ags.channel:}")
	private String agsChannel;

	@Value("${mq.ags.qmanager:}")
	private String agsQueueManager;

	@Value("${mq.ags.qname:}")
	private String agsMqName;

	@Value("${mq.ags.connections:}")
	private String agsMqConnections;

	@Autowired
	private Environment env;

	@Bean(name = "agsJmsTemplate")
	public JmsTemplate getAgsJmsTemplate() {
		logger.info("Application starting up - loading JmsTemplate for queue: {} ", agsMqName);
		JmsTemplate template = null;
		try {
			if (jndiEnabled) {
				logger.info(" JNDI is enabled for MQ...");
				String prefix = MQ_AGS_JNDI + ".";
				String queueName = env.getProperty(prefix + QUEUE_NAME);
				template = new JndiMq(env.getProperty(prefix + QUEUE_CF), queueName,
						env.getProperty(prefix + QUEUE_CONNS)).getJmsTemplate();
				JndiTemplate jndiTempate = new JndiTemplate();
				MQQueue agsQueue = (MQQueue) jndiTempate.lookup(queueName);
				String jndiQueName = agsQueue.getQueueName();
				logger.info("Configuring MQ with JNDI for jndiName : {} , with queue: {} ", queueName, jndiQueName);
				template.setDefaultDestinationName(jndiQueName);
				return template;
			}
			template = new JmsMq(agsHost, agsPort, agsChannel, agsQueueManager, agsMqName, agsMqConnections)
					.getJmsTemplate();
			template.setDefaultDestinationName(agsMqName);
			return template;

		} catch (Exception e) {
			logger.error("Exception while initializing AgsJmsTemplate : ", e);
		}

		return template;
	}

}
