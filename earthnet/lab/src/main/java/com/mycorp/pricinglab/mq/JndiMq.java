package com.vzwcorp.pricinglab.mq;

import javax.jms.ConnectionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

public class JndiMq {
	static Logger logger = LogManager.getLogger(JndiMq.class);

	private String cfName, mqName, connections;

	public JndiMq(String cfName, String mqName, String connections) {
		this.cfName = cfName;
		this.mqName = mqName;
		this.connections = connections;
	}

	private ConnectionFactory getConnectionFactory() throws Exception {
		logger.info("Application starting up - loading MQ JNDI connection factory " + cfName);

		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setJndiName(cfName);
		jndiObjectFactoryBean.afterPropertiesSet();
		return (ConnectionFactory) jndiObjectFactoryBean.getObject();
	}


	public JmsTemplate getJmsTemplate() {
		logger.info("Application starting up - loading JNDI JmsTemplate for queue: " + mqName);
		try {
			return new JmsTemplate(getConnectionFactory());
		} catch (Exception exp) {
			logger.error(exp.getMessage(), exp);
		}

		return null;
	}

}
