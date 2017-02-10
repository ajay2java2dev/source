package com.vzwcorp.pricinglab.mq;

import javax.jms.ConnectionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class JmsMq {
	static Logger logger = LogManager.getLogger(JmsMq.class);

	private String host, port, channel, queueManager, mqName, connections;


	public JmsMq(String host, String port, String channel, String queueManager, String mqName,
			String connections) {

		this.host = host;
		this.port = port;
		this.channel = channel;
		this.queueManager = queueManager;
		this.mqName = mqName;
	}

	public ConnectionFactory getConnectionFactory() throws Exception {
		logger.info("Application starting up - loading JMS connection factory for " + host + ":" + port + "/"
				+ queueManager);

		// Create a connection factory
		JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
		JmsConnectionFactory mqConnectionFactory = ff.createConnectionFactory();

		// Set the properties
		mqConnectionFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
		if(port != null && !port.isEmpty()){
			mqConnectionFactory.setIntProperty(WMQConstants.WMQ_PORT, Integer.valueOf(port));
		}
		mqConnectionFactory.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
		mqConnectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		mqConnectionFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, queueManager);

		return new CachingConnectionFactory(mqConnectionFactory);
	}


	public JmsTemplate getJmsTemplate() {
		logger.info("Application starting up - loading JmsTemplate for queue: " + mqName);
		try {
			return new JmsTemplate(getConnectionFactory());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}
}
