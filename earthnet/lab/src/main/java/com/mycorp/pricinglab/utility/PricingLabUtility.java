package com.vzwcorp.pricinglab.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vzwcorp.pricinglab.constants.OfferStatus;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.service.ServiceManager.HttpDeleteWithBody;
import com.vzwcorp.pricinglab.vo.ResponseInfo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by menonka on 6/21/2016.
 */
@Component
public class PricingLabUtility {

    private static Logger logger = LogManager.getLogger(PricingLabUtility.class);

    @Value("${http.connection.timeout:10000}")
    Integer connectionTimeOut;
    @Value("${http.connection.request.timeout:10000}")
    Integer connectionRequestTimeOut;
    @Value("${http.connection.socket.timeout:10000}")
    Integer connectionSocketTimeOut;
    @Value("${ssl.tls.default:TLSv1.2}")
    String tlsDefault;

    public PricingLabUtility() {
        logger.debug("Pricinglab utility constructor().");
    }

    public Map<String,Object> createResponseInfo(org.springframework.http.HttpStatus status, String userMessage, String type) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(status.value()+"");
        responseInfo.setMessage(status.getReasonPhrase());
        responseInfo.setUserMessage(userMessage);
        responseInfo.setType(type);
        Map<String,Object> respMap = new HashMap<String,Object>();
        respMap.put("ResponseInfo",responseInfo);
        return  respMap;
    }

    /**
     * Find content type as per the argument passed in the give body.
     * @param body
     * @return
     */
    public final ContentType findContentType (final String body) {
        if (body!=null && !body.isEmpty()) {
            if (body.contains("{")) {
                return ContentType.APPLICATION_JSON;
            } else if (body.contains("xml")){
                return ContentType.APPLICATION_XML;
            }
        }
        return null;
    }


    /**
     * POST Request to accept URL with a Body. The post requests expects a content type = json
     * @param url
     * @param body
     * @return
     */
    public final String postRequest(final String url, final String body, ContentType contentType) {
        HttpPost method = null;
        logger.debug("POST Request. URL : {}, Body : {}",url,body);
        String responseString = null;

        try {
            if (url!=null && !url.isEmpty()) {

                CloseableHttpClient httpClient = null;

                if (contentType==null) {
                    contentType = findContentType(body);
                }

                if (url.contains("https") || url.contains("HTTPS") || url.contains("Https")) {

                    URIBuilder builder = new URIBuilder(url);

                    RequestConfig requestConfig = RequestConfig.custom()
                            .setConnectionRequestTimeout(connectionRequestTimeOut)
                            .setConnectTimeout(connectionTimeOut)
                            .setSocketTimeout(connectionSocketTimeOut)
                            .build();

                    SSLContextBuilder sslContextBuilder = new SSLContextBuilder().useProtocol(tlsDefault);
                    sslContextBuilder.loadTrustMaterial(null,new TrustSelfSignedStrategy());
                    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContextBuilder.build());

                    httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setSSLHostnameVerifier(new NoopHostnameVerifier()).setDefaultRequestConfig(requestConfig).build();

                    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                    requestFactory.setHttpClient(httpClient);
                    requestFactory.setConnectTimeout(connectionTimeOut);
                    requestFactory.setConnectionRequestTimeout(connectionRequestTimeOut);
                    requestFactory.setReadTimeout(connectionTimeOut);

                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType.getMimeType());
                    httpHeaders.add(HttpHeaders.ACCEPT,contentType.getMimeType());

                    org.springframework.http.HttpEntity entity = new org.springframework.http.HttpEntity(body!=null?body.getBytes("UTF-8"):(builder.getQueryParams().toString().getBytes("UTF-8")),httpHeaders);
                    ResponseEntity<String> responseEntity = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST,entity,String.class);

                    if (responseEntity!=null) {
                        if (responseEntity.getStatusCode() != org.springframework.http.HttpStatus.OK) {
                            logger.debug("Method failed, Response : {}", responseEntity.getStatusCode());
                            responseString = "Error : " + responseEntity.getBody();
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

                    method.addHeader("accept", contentType.getMimeType());
                    method.addHeader("Content-Type", contentType.getMimeType());
                    HttpEntity entity = new ByteArrayEntity(body!=null?body.getBytes("UTF-8"):builder.getQueryParams().toString().getBytes("UTF-8"));
                    method.setEntity(entity);
                    // Execute the method.
                    HttpResponse response = client.execute(method);
                    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                        logger.error("Method failed: " + response.getStatusLine());
                        responseString = "Error : " + response.getStatusLine();
                    } else {
                        String responseBody = response.getEntity().toString();
                        logger.debug(new String(responseBody));
                        responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                        //return responseBody;
                        return responseString;
                    }
                }
            } else {
                logger.info("URL is empty or null. No post request was made.");
            }
        } catch (ClientProtocolException e) {
            logger.error("Fatal protocol violation: ",e.getMessage(),e);
            responseString="Error : "+e.getMessage();
        } catch (IOException e) {
            logger.error("Fatal IO violation: ",e.getMessage(),e);
            responseString="Error : "+e.getMessage();
        } catch (Exception e) {
            logger.error("Fatal transport error: ",e.getMessage(),e);
            responseString="Error : "+e.getMessage();
        } finally {
            // Release the connection.
            if (method != null)
                method.releaseConnection();
        }
        return responseString;
    }
    
	public String postHttpRequestWithURLParams(String url, Map<String, String> queryParams) {
		logger.debug("postHttpRequestWithURLParams : URL : {} , queryParams : {} ", url, queryParams);

		if (url == null || url.isEmpty() || queryParams == null || queryParams.isEmpty()) {
			logger.info("postHttpRequestWithURLParams : either invalid URL or queryParams. No post request was made.");
			return null;
		}

		HttpPost post = null;
		String responseString = null;
		URIBuilder builder = null;

		try {

			builder = new URIBuilder(url);

			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeOut)
					.setConnectTimeout(connectionTimeOut).setSocketTimeout(connectionSocketTimeOut).build();

			if (url.contains("https") || url.contains("HTTPS") || url.contains("Https")) {

				SSLContextBuilder sslContextBuilder = new SSLContextBuilder().useProtocol(tlsDefault);
				sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContextBuilder.build());

				CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
						.setSSLHostnameVerifier(new NoopHostnameVerifier()).setDefaultRequestConfig(requestConfig)
						.build();

				HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
				requestFactory.setHttpClient(httpClient);
				requestFactory.setConnectTimeout(connectionTimeOut);
				requestFactory.setConnectionRequestTimeout(connectionRequestTimeOut);
				requestFactory.setReadTimeout(connectionTimeOut);

				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.add(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
				
				MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
				
				for(Entry<String, String> entry : queryParams.entrySet() ){
					map.add(entry.getKey(), entry.getValue());
				}

				org.springframework.http.HttpEntity<MultiValueMap<String, String>> entity = new org.springframework.http.HttpEntity<MultiValueMap<String, String>>(
						map, httpHeaders);
				ResponseEntity<String> responseEntity = new RestTemplate(requestFactory).postForEntity(url, entity,
						String.class);

				if (responseEntity != null) {
					if (responseEntity.getStatusCode() != org.springframework.http.HttpStatus.OK) {
						logger.debug("Method failed, Response : {}", responseEntity.getStatusCode());
                        responseString = "Error : " + responseEntity.getBody();
					} else {
						String responseBody = responseEntity.getBody();
						if(responseBody!=null && !responseBody.isEmpty()){
							if(!responseBody.contains("xml version")){							
								logger.debug("Response Code : {} , Response : {}", responseEntity.getStatusCode(),
										responseBody);
							}
						}
						return responseBody;
					}
				} else {
					logger.debug("Response Entity is null.");
				}

			} else {

				HttpClient client = HttpClientBuilder.create().build();

				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

				for (Entry<String, String> entry : queryParams.entrySet()) {
					urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}

				builder = new URIBuilder(url);
				post = new HttpPost(builder.build());
				HttpEntity entity = new UrlEncodedFormEntity(urlParameters);
				post.setEntity(entity);
				post.setConfig(requestConfig);

				logger.debug("URI: " + post.getURI());
				logger.debug("Entity: " + post.getEntity());
				HttpResponse response = client.execute(post);
				logger.debug("Status Code : " + response.getStatusLine().getStatusCode());
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					logger.debug("Method failed: " + response.getStatusLine());
					responseString = "Error : " + response.getStatusLine();
				} else {
					responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseString="Error : "+e.getMessage();
		} finally {
			if (post != null)
				post.releaseConnection();
		}
		if(responseString!=null && !responseString.isEmpty()){
			if(!responseString.contains("xml version")){							
				logger.debug("Response : " + responseString);
			}
		}
		return responseString;
	}
	
	  public final String deleteRequest(final String url, final String body, ContentType contentType) {
	        HttpPost method = null;
	        logger.debug("Delete Request. URL : {}, Body : {}",url,body);
	        String responseString = null;

	        try {
	            if (url!=null && !url.isEmpty()) {

	                CloseableHttpClient httpClient = null;

	                if (contentType==null) {
	                    contentType = findContentType(body);
	                }

	                if (url.contains("https") || url.contains("HTTPS") || url.contains("Https")) {

	                    URIBuilder builder = new URIBuilder(url);

	                    RequestConfig requestConfig = RequestConfig.custom()
	                            .setConnectionRequestTimeout(connectionRequestTimeOut)
	                            .setConnectTimeout(connectionTimeOut)
	                            .setSocketTimeout(connectionSocketTimeOut)
	                            .build();

	                    SSLContextBuilder sslContextBuilder = new SSLContextBuilder().useProtocol(tlsDefault);
	                    sslContextBuilder.loadTrustMaterial(null,new TrustSelfSignedStrategy());
	                    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContextBuilder.build());

	                    httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setSSLHostnameVerifier(new NoopHostnameVerifier()).setDefaultRequestConfig(requestConfig).build();

	                    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	                    requestFactory.setHttpClient(httpClient);
	                    requestFactory.setConnectTimeout(connectionTimeOut);
	                    requestFactory.setConnectionRequestTimeout(connectionRequestTimeOut);
	                    requestFactory.setReadTimeout(connectionTimeOut);

	                    HttpHeaders httpHeaders = new HttpHeaders();
	                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType.getMimeType());
	                    httpHeaders.add(HttpHeaders.ACCEPT,contentType.getMimeType());

	                    org.springframework.http.HttpEntity entity = new org.springframework.http.HttpEntity(body!=null?body.getBytes("UTF-8"):(builder.getQueryParams().toString().getBytes("UTF-8")),httpHeaders);
	                    ResponseEntity<String> responseEntity = new RestTemplate(requestFactory).exchange(url, HttpMethod.DELETE,entity,String.class);

	                    if (responseEntity!=null) {
	                   	 logger.debug("****RESPONSE***************************************");
	                        if (responseEntity.getStatusCode() != org.springframework.http.HttpStatus.OK) {
	                            logger.debug("Method failed, Response : {}", responseEntity.getStatusCode());
	                            responseString = "Error : " + responseEntity.getBody();
	                            logger.debug("----content:---------------------");
               			        logger.debug(responseEntity.getBody());
	                        } else {
	                            String responseBody = responseEntity.getBody();
	                            logger.debug("----content:---------------------");
	                            logger.debug("Response : {}", responseBody);
	                            return responseBody;
	                        }
	                    } else {
	                        logger.debug("Response Entity is null.");
	                    }
	                } else {
	                	try{
	               	     CloseableHttpClient httpclient = HttpClients.createDefault();
	               	     
	               	     
	               	        HttpDeleteWithBody httpDelete = new ServiceManager().new HttpDeleteWithBody(url);
	               	        StringEntity input = new StringEntity(body, ContentType.APPLICATION_JSON);
	               	        httpDelete.getURI();
	               	        httpDelete.setEntity(input);  
	               	        httpDelete.setHeader("Content-Type", "application/json");
	               	       logger.debug("****REQUEST***************************************");
	               	       logger.debug(url);
	               	       logger.debug("Body:"+ EntityUtils.toString( httpDelete.getEntity()));
	               	        Header requestHeaders[] = httpDelete.getAllHeaders();
	               	        for (Header h : requestHeaders) {
	               	        	 logger.debug(h.getName() + ": " + h.getValue());
	               	        }
	               	 
	               	        CloseableHttpResponse response;
	               				response = httpclient.execute(httpDelete);
	               				 logger.debug("****RESPONSE***************************************");
	               				 logger.debug("----status:---------------------");
	               				 logger.debug(response.getStatusLine());
	               				 logger.debug("----header:---------------------");
	               			        Header responseHeaders[] = response.getAllHeaders();
	               			        for (Header h : responseHeaders) {
	               			        	 logger.debug(h.getName() + ": " + h.getValue());
	               			        }
	               			        logger.debug("----content:---------------------");
	               			        logger.debug(EntityUtils.toString(response.getEntity()));
	               			} catch (ClientProtocolException e) {
	               				//e.printStackTrace();
	               				logger.error("Exception in invokeDeleteRequestWithRequestBody() ",e.getMessage(),e);
	               			} catch (IOException e) {
	               				//e.printStackTrace();
	               				logger.error("Exception in invokeDeleteRequestWithRequestBody() ",e.getMessage(),e);
	               			}
	                }
	            } else {
	                logger.info("URL is empty or null. No post request was made.");
	            }
	        } catch (IOException e) {
	            logger.error("Fatal IO violation: ",e.getMessage(),e);
	            responseString="Error : "+e.getMessage();
	        } catch (Exception e) {
	            logger.error("Fatal transport error: ",e.getMessage(),e);
	            responseString="Error : "+e.getMessage();
	        } finally {
	            // Release the connection.
	            if (method != null)
	                method.releaseConnection();
	        }
	        return responseString;
	    }

    /**
     * Set Default properties for a oject mapper.
     * @param mapper
     */
    public static void setObjectMapperDefault (ObjectMapper mapper) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setDateFormat(df);
    }

    /**
     * Get Default END Timestamp = 9999-12-31 23.59.59
     * @return Timestamp
     */
    public static Timestamp getDefaultEndTimeStamp () {
        DateFormat targetFormat = new SimpleDateFormat(PlabConstants.STD_TIMESTAMP_FORMAT);
        Date date = null;
        try {
            date = targetFormat.parse(PlabConstants.END_DATE_TIME_STAMP);
        } catch (ParseException  pe) {
            logger.error("Error parsing default end timestamp : ",pe.getMessage(),pe);
        }
        return new Timestamp(date.getTime());
    }

    /**
     * Get day as per the given date
     * @param toDate
     * @return
     */
    public String getDay (Date toDate) {
        Date date = toDate;
        Calendar cal = Calendar.getInstance();
        if (date!=null) {
            cal.setTime(date);
        }
        return cal.get(Calendar.DAY_OF_MONTH)+"";
    }

    /**
     * Create a Cron expression for the accepted offers using the Bill Cycle no (Day of the job)
     * @param billCycleNo
     * @return
     */
    public static String createBillingDayCronExpression (String billCycleNo) {
        StringBuffer buildCronExpression = new StringBuffer();
        if (billCycleNo!=null && !billCycleNo.isEmpty()) {
            buildCronExpression.append("0 "); //seconds. TODO : Replcae by property config.
            buildCronExpression.append("0 "); //minutes. TODO : Replcae by property config.
            buildCronExpression.append("12 ");//12 pms. TODO : Replcae by property config.
            buildCronExpression.append(billCycleNo).append(" "); //every billCyclno - day of the month.
            buildCronExpression.append("* ");  //TODO : Replcae by property config.
            buildCronExpression.append("?");  //TODO : Replcae by property config.
        }
        return  buildCronExpression.toString();
    }
    
    /**
     * Create a Cron expression for the accepted offers using the Bill Cycle no (Day of the job)
     * @param dayOfMonth
     * @return
     */
    public static String createCronExpression (int dayOfMonth, int month, int hour) {
        StringBuffer buildCronExpression = new StringBuffer();
        if (dayOfMonth > 0) {
            buildCronExpression.append("0 "); //seconds. 
            buildCronExpression.append("0 "); //minutes. 
            buildCronExpression.append(hour).append(" ");//12 am. 
            buildCronExpression.append(dayOfMonth).append(" "); //every billCyclno - day of the month.
            buildCronExpression.append(month).append(" ");  
            buildCronExpression.append("?");  
        }
        return  buildCronExpression.toString();
    }

    public static String getEOCExpression(final Date date, final String timeZone) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        return "at " + cal.get(Calendar.DAY_OF_MONTH) + " " + timeZone;
    }
    
	public static String convert24HrTo12HrFormat(String input, String response12HrsFormat) {
		SimpleDateFormat format24Hrs = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat format12Hrs = new SimpleDateFormat(response12HrsFormat);
		try {
			Date d = format24Hrs.parse(input);
			return format12Hrs.format(d).toLowerCase();
		} catch (ParseException e) {

		}
		return "";
	}
	
	public static String convertGmtToEst(String input) {
		 SimpleDateFormat gmtFormat = new SimpleDateFormat("HH:mm:ss");
		 gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		 SimpleDateFormat estFormat = new SimpleDateFormat("HH:mm:ss");
		 estFormat.setTimeZone(TimeZone.getTimeZone("EST"));
		 try {
			Date date = gmtFormat.parse(input);
			String parsedDate = estFormat.format(date);
			return parsedDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "";
	}

    public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		// System specific - wherever the code is running.
		String timeZone0 = calendar.getTimeZone().getDisplayName(true,TimeZone.SHORT,Locale.getDefault(Locale.Category.DISPLAY));
		String timeZone1 = calendar.getTimeZone().getDisplayName(false,TimeZone.SHORT,Locale.getDefault(Locale.Category.DISPLAY));
		TimeZone timeZone2 =TimeZone.getDefault();

		// will pick the TIMEZONE info from MZDMIN.REF_VISION_INSTANCE_LKUP
		TimeZone timeZone3 =TimeZone.getTimeZone("America/Los_Angeles"); // not considering daylight
		TimeZone timeZone4 =TimeZone.getTimeZone("America/Los_Angeles"); //considering Daylight

		System.out.println("1. " + getEOCExpression(new Date(),timeZone0));
        System.out.println("2. " + getEOCExpression(new Date(),timeZone1));
		System.out.println("3. " + getEOCExpression(new Date(),timeZone2.getDisplayName(false,TimeZone.SHORT,Locale.getDefault(Locale.Category.DISPLAY))));
		System.out.println("4. " + getEOCExpression(new Date(),timeZone3.getDisplayName(false,TimeZone.SHORT,Locale.getDefault(Locale.Category.DISPLAY))));
		System.out.println("5. " + getEOCExpression(new Date(),timeZone3.getDisplayName(true,TimeZone.SHORT,Locale.getDefault(Locale.Category.DISPLAY))));

    }

}
