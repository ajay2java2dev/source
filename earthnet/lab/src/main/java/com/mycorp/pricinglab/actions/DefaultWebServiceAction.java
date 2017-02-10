package com.vzwcorp.pricinglab.actions;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.vo.Event;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultWebServiceAction implements IActionExecutor {

	static Logger logger = LogManager.getLogger(DefaultWebServiceAction.class);
	@Override
	public Object execute(Event event, RuleInstance ruleInstance, ServiceInstance serviceInstance) {
		HttpPost method = null;
		try {
			HttpClient client = HttpClients.createDefault();

			ObjectMapper mapper = new ObjectMapper();
			String jsonProductInsatnce = mapper.writeValueAsString(serviceInstance);
			logger.info(jsonProductInsatnce);

			URIBuilder builder = new URIBuilder((String) ruleInstance.getAttributes().get("hostURL"))
					.addParameter("eventType", (String) ruleInstance.getAttributes().get("eventType"))
					.addParameter("productInstance", jsonProductInsatnce);

			// Create a method instance.
			method = new HttpPost(builder.build());
			method.addHeader("accept", "application/json");

			// Execute the method.
			HttpResponse response = client.execute(method);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error("Method failed: " + response.getStatusLine());
			}

			// Read the response body.
			String responseBody = response.getEntity().toString();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			logger.info(new String(responseBody));

		} catch (ClientProtocolException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			// method.releaseConnection();
		}

		return serviceInstance;

	}

}