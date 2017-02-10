package com.vzwcorp.pricinglab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.vo.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

	public static int numRequests = 0;

	static Logger logger = LogManager.getLogger(RestClient.class);

	protected static void addAction(String name, String category) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", name);
		param.put("category", category);
		postRequest("http://localhost:8080/action", param);
	}

	protected static void deleteRequest(String url, String queryString) {
		HttpDelete method = null;
		try {
			HttpClient client = HttpClients.createDefault();

			// Create a method instance.
			method = new HttpDelete(url + "?" + queryString);
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
			logger.debug(new String(responseBody));

		} catch (ClientProtocolException e) {
			logger.error("Fatal protocol violation: ",e.getMessage(),e);
		} catch (IOException e) {
			logger.error("Fatal IO error: ",e.getMessage(),e);
		} catch (Exception e) {
			logger.error("Fatal transport error: ",e.getMessage(),e);
		} finally {
			// Release the connection.
			// method.releaseConnection();
		}
	}

	protected static void createProduct() {
		HttpPost method = null;
		try {
			HttpClient client = HttpClients.createDefault();

			// Create a method instance.
			URIBuilder builder = new URIBuilder("http://localhost:8080//service").addParameter("name", "test2")
					.addParameter("allowence", "40000000");
			method = new HttpPost(builder.build());
			method.addHeader("accept", "application/json");
			ObjectMapper mapper = new ObjectMapper();
			Service service = new Service();
			service.setAllowance(40000000);
			service.setName("test2");
			String jsonString = mapper.writeValueAsString(service);
			logger.debug(jsonString);

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
			logger.debug(new String(responseBody));

		} catch (ClientProtocolException e) {
			logger.error("Fatal protocol violation: ",e.getMessage(),e);
		} catch (IOException e) {
			logger.error("Fatal IO error: ",e.getMessage(),e);
		} catch (Exception e) {
			logger.error("Fatal transport error: ",e.getMessage(),e);
		} finally {
			// Release the connection.
			// method.releaseConnection();
		}
	}

	public static String postRequest(String url, Map<String, String> sParam) {
		HttpPost method = null;
		try {
			HttpClient client = HttpClients.createDefault();
			// Create a method instance.
			URIBuilder builder = new URIBuilder(url);
			for (java.util.Map.Entry<String, String> entry : sParam.entrySet()) {
				builder.addParameter(entry.getKey(), entry.getValue());
			}
			method = new HttpPost(builder.build());

			method.addHeader("accept", "application/json");
			HttpEntity entity = new ByteArrayEntity(builder.getQueryParams().toString().getBytes("UTF-8"));
	        method.setEntity(entity);
			// Execute the method.
			HttpResponse response = client.execute(method);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error("Method failed: " + response.getStatusLine());
			}
			else{
				String responseBody = response.getEntity().toString();
				logger.debug(new String(responseBody));
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				//return responseBody;
				return responseString;

			}

		} catch (ClientProtocolException e) {
			logger.error("Fatal protocol violation: ",e.getMessage(),e);
		} catch (IOException e) {
			logger.error("Fatal IO error: ",e.getMessage(),e);
		} catch (Exception e) {
			logger.error("Fatal transport error: ",e.getMessage(),e);
		} finally {
			// Release the connection.
			if (method != null)
				method.releaseConnection();
		}
		return null;
	}

	public static String deleteRequest(String url, Map<String, String> sParam) {
		HttpDelete method = null;
		try {
			HttpClient client = HttpClients.createDefault();
			// Create a method instance.
			URIBuilder builder = new URIBuilder(url);
			for (java.util.Map.Entry<String, String> entry : sParam.entrySet()) {
				builder.addParameter(entry.getKey(), entry.getValue());
			}
			method = new HttpDelete(builder.build());

			method.addHeader("accept", "application/json");
			HttpEntity entity = new ByteArrayEntity(builder.getQueryParams().toString().getBytes("UTF-8"));

			// Execute the method.
			HttpResponse response = client.execute(method);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error("Method failed: " + response.getStatusLine());
			}
			else{
				String responseBody = response.getEntity().toString();
				logger.debug(new String(responseBody));
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				//return responseBody;
				return responseString;

			}

		} catch (ClientProtocolException e) {
			logger.error("Fatal protocol violation: ",e.getMessage(),e);
		} catch (IOException e) {
			logger.error("Fatal IO error: ",e.getMessage(),e);
		} catch (Exception e) {
			logger.error("Fatal transport error: ",e.getMessage(),e);
		} finally {
			// Release the connection.
			if (method != null)
				method.releaseConnection();
		}
		return null;
	}

	public static String postRequest(String url, String body) {
		HttpPost method = null;
		logger.debug("POST Request. URL : {}, Body {}",url,body);
		try {
			if (url!=null && !url.isEmpty()) {

				String responseString = "";
				CloseableHttpClient httpClient = null;

				if (url.contains("https") || url.contains("HTTPS") || url.contains("Https")) {

					//FIXME : Configuration needs to moved to property file.
					RequestConfig requestConfig = RequestConfig.custom()
							.setConnectionRequestTimeout(10000)
							.setConnectTimeout(10000)
							.setSocketTimeout(10000)
							.build();

					SSLContextBuilder builder = new SSLContextBuilder().useProtocol("TLSv1.2");
					builder.loadTrustMaterial(null,new TrustSelfSignedStrategy());
					SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());

					httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setSSLHostnameVerifier(new NoopHostnameVerifier()).setDefaultRequestConfig(requestConfig).build();

					HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
					requestFactory.setHttpClient(httpClient);
					requestFactory.setConnectTimeout(10000);
					requestFactory.setConnectionRequestTimeout(10000);
					requestFactory.setReadTimeout(10000);

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
					httpHeaders.add(HttpHeaders.ACCEPT,"application/json");

					org.springframework.http.HttpEntity entity = new org.springframework.http.HttpEntity(body,httpHeaders);
					ResponseEntity<String> responseEntity = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST,entity,String.class);

					if (responseEntity!=null) {
						if (responseEntity.getStatusCode() != org.springframework.http.HttpStatus.OK) {
							logger.debug("Method failed, Response : {}", responseEntity.getStatusCode());
						} else {
							String responseBody = responseEntity.getBody();
							logger.debug("Response : {}", responseBody);
							return responseBody;
						}
					} else {
						logger.debug("Response Entity is null.");
					}
				} else {
					HttpClient client = HttpClients.createDefault();
					// Create a method instance.
					URIBuilder builder = new URIBuilder(url);
					method = new HttpPost(builder.build());

					method.addHeader("accept", "application/json");
					method.addHeader("Content-Type", "application/json");
					HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
					method.setEntity(entity);
					// Execute the method.
					HttpResponse response = client.execute(method);
					if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
						logger.error("Method failed: " + response.getStatusLine());
					} else {
						String responseBody = response.getEntity().toString();
						logger.debug(new String(responseBody));
						responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
						//return responseBody;
						return responseString;
					}
				}
			} else {
				logger.info("");
			}
		} catch (ClientProtocolException e) {
			logger.error("Fatal protocol violation: ",e.getMessage(),e);
		} catch (IOException e) {
			logger.error("Fatal IO violation: ",e.getMessage(),e);
		} catch (Exception e) {
			logger.error("Fatal transport error: ",e.getMessage(),e);
		} finally {
			// Release the connection.
			if (method != null)
				method.releaseConnection();
		}
		return null;
	}
	
	public static String getRequest(String url) {
		HttpGet method = null;
		try {
			HttpClient client = HttpClients.createDefault();

			// Create a method instance.
			URIBuilder builder = new URIBuilder(url);
			method = new HttpGet(builder.build());
			method.addHeader("accept", "application/json");

			// Execute the method.
			HttpResponse response = client.execute(method);			

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error("Method failed: " + response.getStatusLine());
			}
			// Read the response body.
			else{
				String responseBody = response.getEntity().toString();
				logger.debug(new String(responseBody));
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				//return responseBody;
				return responseString;
			}
		} catch (ClientProtocolException e) {
			logger.error("Fatal protocol violation: ",e.getMessage(),e);
		} catch (IOException e) {
			logger.error("Fatal IO violation: ",e.getMessage(),e);
		} catch (Exception e) {
			logger.error("Fatal transport error: ",e.getMessage(),e);
		} finally {
			// Release the connection.
			if (method != null)
				method.releaseConnection();
		}
		return null;
	}	
}
