package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.vo.Service;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClient {

	public static int numRequests = 0;

	public static void main(String[] args) {

		// createProduct();
		/*
		 * deleteRequest("http://localhost:8080//service",
		 * "productid=8ac5539053169de60153169e7dbc0000");
		 * deleteRequest("http://localhost:8080/action",
		 * "actionid=8ac5539053169de60153169e7dbc0000");
		 * addAction("sendAlert","Alert");
		 * addAction("setClassOfService","Network");
		 * addAction("sendCharge","Billing"); addAction("sendAlert","Alert");
		 * 
		 * addAction("sendAlert","Alert");
		 */

		Map<String, String> sParam = new HashMap<String, String>();
		sParam.put("mdn", "9087050964");
		sParam.put("usage", "200");
		int i = 0;
		long t0 = System.currentTimeMillis();
		boolean done = false;
		List<WorkerThread> workers = new ArrayList<WorkerThread>();

		// while ( !done){
		for (int y = 0; y < 200; y++) {
			i++;
			WorkerThread worker = new WorkerThread("http://localhost:8080/usage", sParam);
			workers.add(worker);
			worker.setPriority(Thread.MAX_PRIORITY);
			worker.start();

		}

		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int j = 0;
		for (WorkerThread worker : workers) {
			worker.done = true;
			try {
				worker.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			j = j + worker.numReq;
		}

		System.out.println(j + "  " + j + "  " + numRequests);

	}
	// 8ac5539053169de60153169e7dbc0000

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
				System.err.println("Method failed: " + response.getStatusLine());
			}

			// Read the response body.
			String responseBody = response.getEntity().toString();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			System.out.println(new String(responseBody));

		} catch (ClientProtocolException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
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
			System.out.println(jsonString);

			// Execute the method.
			HttpResponse response = client.execute(method);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + response.getStatusLine());
			}

			// Read the response body.
			String responseBody = response.getEntity().toString();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			System.out.println(new String(responseBody));

		} catch (ClientProtocolException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
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
			method = new HttpPost(builder.build());
			for (java.util.Map.Entry<String, String> entry : sParam.entrySet()) {
				if (url.contains("application") || url.contains("qos")) {
					builder.addParameter(entry.getKey(), entry.getValue());
				} else {
					StringEntity entity = new StringEntity(entry.getValue());
					method.setEntity(entity);
				}
			}
			method.addHeader("accept", "application/json");
			// Execute the method.
			HttpResponse response = client.execute(method);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + response.getStatusLine());
			}
			else{
				String responseBody = response.getEntity().toString();
				System.out.println(new String(responseBody));
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				//return responseBody;
				return responseString;
				
			}

		} catch (ClientProtocolException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
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
				System.err.println("Method failed: " + response.getStatusLine());
			}
			// Read the response body.
			else{
				String responseBody = response.getEntity().toString();
				System.out.println(new String(responseBody));
				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				//return responseBody;
				return responseString;
			}
		} catch (ClientProtocolException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			if (method != null)
				method.releaseConnection();
		}
		return null;
	}	
}
