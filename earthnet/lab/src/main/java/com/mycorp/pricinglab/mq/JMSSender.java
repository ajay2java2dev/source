/**
 * 
 */
package com.vzwcorp.pricinglab.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @author kovelde
 *
 */

@ConditionalOnProperty(name = "jms.enabled", havingValue = "true", matchIfMissing = false)
@Component
public class JMSSender {

	static Logger logger = LogManager.getLogger(JMSSender.class);

	@Autowired
	@Qualifier("agsJmsTemplate")
	public JmsTemplate agsJmsTemplate;

	public void sendAgsDTLMessage(final String message, final String transactionId) {

		try {

			logger.debug("Sending AGS DTL Message with transactionId : {} , message : {} ", transactionId, message);

			agsJmsTemplate.send(new MessageCreator() {

				@Override
				public Message createMessage(Session session) throws JMSException {
					Message msg = session.createTextMessage(message);
					msg.setJMSCorrelationID(transactionId);
					return msg;
				}
			});
			logger.debug("Successfully sent message to AGS Queue for transactionId : {} ", transactionId);
		} catch (Exception exp) {
			logger.error("Exception while sending AGS DTL Message : ", exp);
		}
	}

}
