/**
 * 
 */
package com.vzwcorp.pricinglab.service;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.vzwcorp.pricinglab.constants.OneMessageType;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.ChoiceInstance;
import com.vzwcorp.pricinglab.vo.OfferInstance;
import com.vzwcorp.pricinglab.vo.OneMessageDetails;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.onemessagerequest.Customer;
import com.vzwcorp.pricinglab.vo.onemessagerequest.DataMapItemType;
import com.vzwcorp.pricinglab.vo.onemessagerequest.DataMapType;
import com.vzwcorp.pricinglab.vo.onemessagerequest.OneMessageRequest;
import com.vzwcorp.pricinglab.vo.onemessagerequest.TransactionRecordType;
import com.vzwcorp.pricinglab.vo.repository.OfferInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;

/**
 * @author kovelde
 *
 */

@RestController
@Transactional("blTransactionManager")
public class OneMessageService {

	@Autowired
	GridService gridService;
	
	@Autowired
	PricingLabUtility pricingLabUtility;

	public Map<String, List<String>> oneMessageMap = new HashMap<String, List<String>>();

	Logger logger = LogManager.getLogger(OneMessageService.class);

	public static String APPLICATION_ID = "UB";

	public static String GRID_GIVEN_NAME_ELEMENT = "givenName";
	public static String GRID_EMAIL_ID_ELEMENT = "emailAddr";

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

	public static String ONE_MESSAGE_JAXB_PACKAGE = "com.vzwcorp.pricinglab.vo.onemessagerequest";

	Marshaller marshaller = null;

	Unmarshaller unMarshaller = null;

	@Value("${onemessage.url}")
	private String oneMessageURL;

	@Value("${onemessage.survey.url}")
	private String surveyURL;

	@Value("${http.connection.timeout}")
	Integer connectionTimeOut;

	@Value("${http.connection.request.timeout}")
	Integer connectionRequestTimeOut;

	@Value("${http.connection.socket.timeout}")
	Integer connectionSocketTimeOut;
	
	@Value("${onemessage.default.name}")
	private String defaultName;
	
	@Value("${onemessage.default.email}")
	private String defaultEmail;

	@Autowired
	PlabCustRepository plabCustRepository;

	@Autowired
	OfferInstanceRepository offerInstanceRepository;

	@RequestMapping(value = "/onemessage/send/{messagetype}/{custid}/{acctnbr}/{mdn}/{mdnlist}", method = RequestMethod.POST)
	public void sendMessage(@PathVariable("messagetype") String messageType, @PathVariable("custid") Long customerID,
			@PathVariable("acctnbr") Long accountNumber, @PathVariable("mdn") String primaryMdn,
			@PathVariable("mdnlist") String enrolledMdnList) {

		logger.debug("OneMessage service invoked for customerID : " + customerID + " , accountNumber : " + accountNumber
				+ " , primaryMdn : " + primaryMdn + " , for Message Type : " + messageType);

		try {

			PlabCust plabCustomer = plabCustRepository.findByCustIdNoAndAcctNoAndMdn(Long.valueOf(customerID),
					Long.valueOf(accountNumber), primaryMdn);

			if (plabCustomer == null) {
				logger.error("Customer details not found for : customerID : " + customerID + " , accountNumber : "
						+ accountNumber + " , primaryMdn : " + primaryMdn);
				return;
			}

			OneMessageDetails oneMessageDetails = new OneMessageDetails();
			String custIdStr = customerID.toString();
			oneMessageDetails.setCustIdAccount(custIdStr.substring(custIdStr.length() - 4) + "-" + accountNumber);
			oneMessageDetails.setExpiryDate(plabCustomer.getPlEndDate());
			StringBuilder mdnString = new StringBuilder();
			StringTokenizer tokenizer = null;

			if (enrolledMdnList != null && !enrolledMdnList.isEmpty()) {
				tokenizer = new StringTokenizer(enrolledMdnList, ",");
				while (tokenizer.hasMoreTokens()) {
					String mdn = tokenizer.nextToken();
					mdnString.append("," + mdn.substring(mdn.length() - 4));
				}

				oneMessageDetails.setMdnList(mdnString.toString().replaceFirst(",", ""));
			}

			oneMessageDetails.setSurveyURL(surveyURL);

			// TODO: Get From Grid
			oneMessageDetails.setOldPlan("Old Plan");

			if (OneMessageType.SelectYourSpeedWelcome.toString().equals(messageType)) {

				List<OfferInstance> offerInstanceList = offerInstanceRepository.findOfferInstancesByCustomerAccount(
						plabCustomer.getOffer().getOfferId(), customerID, accountNumber);
				Map<String, String> selectedChoiceMap = new HashMap<String, String>();

				for (OfferInstance offerInst : offerInstanceList) {
					List<ChoiceInstance> choiceInstances = offerInst.getChoiceInstances();
					String selectedChoice = null;
					for (ChoiceInstance choiceInstance : choiceInstances) {
						if (choiceInstance.getSelected().booleanValue()) {
							selectedChoice = choiceInstance.getChoice().getTitle();
							break;
						}
					}
					selectedChoiceMap.put(offerInst.getVerizonEntity().getMdn(), selectedChoice);
				}

				StringBuilder speedList = new StringBuilder();

				tokenizer = new StringTokenizer(enrolledMdnList, ",");
				while (tokenizer.hasMoreTokens()) {
					speedList.append("," + selectedChoiceMap.get(tokenizer.nextToken()));
				}
				oneMessageDetails.setSpeed(speedList.toString().replaceFirst(",", ""));
			}

			setCustomerDetailsFromGrid(customerID + "", oneMessageDetails);

			OneMessageRequest oneMsgRequest = new OneMessageRequest();

			oneMsgRequest.setApplicationId(APPLICATION_ID);
			oneMsgRequest.setBusinessProcessId(messageType);
			oneMsgRequest.setExternalSystemReferenceId("PLAB:" + System.currentTimeMillis());

			TransactionRecordType transactionRecordType = new TransactionRecordType();
			Customer customer = new Customer();
			customer.setAccountNumber(accountNumber + "");
			customer.setCustomerId(customerID + "");
			transactionRecordType.setCustomer(customer);
			oneMsgRequest.setTransactionRecord(transactionRecordType);

			List<String> fieldList = oneMessageMap.get(messageType);

			DataMapType dataMap = getDataMap(oneMessageDetails, fieldList);

			oneMsgRequest.setData(dataMap);

			// Send request to One message system

			StringWriter sWriter = new StringWriter();

			marshaller.marshal(oneMsgRequest, sWriter);
			
			String response = pricingLabUtility.postRequest(oneMessageURL, sWriter.toString(), ContentType.APPLICATION_XML);
			
			logger.debug("OneMessage Response : {} ",  response);

			//sendOneMessageRequest(oneMessageURL, sWriter.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("OneMessage service executed successfully for customerID : " + customerID + " , accountNumber : "
				+ accountNumber + " , primaryMdn : " + primaryMdn + " , for Message Type : " + messageType);

	}

	private String sendOneMessageRequest(String url, String requestXml) {

		logger.debug("One Message Service invoked with URL : " + url);
		logger.debug("Request XML : " + requestXml);
		HttpPost post = null;
		String responseString = null;
		HttpClient client = null;
		URIBuilder builder = null;
		HttpEntity entity = null;
		HttpResponse response = null;

		try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeOut)
					.setConnectTimeout(connectionTimeOut).setSocketTimeout(connectionSocketTimeOut).build();
			client = HttpClientBuilder.create().build();
			builder = new URIBuilder(url);
			post = new HttpPost(builder.build());
			post.addHeader("Content-Type", "application/xml");
			entity = new ByteArrayEntity(requestXml.getBytes("UTF-8"));
			post.setEntity(entity);
			post.setConfig(requestConfig);
			response = client.execute(post);
			logger.debug("Status Code : " + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error("One Message Service request failed with : " + response.getStatusLine());
			} else {
				responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (post != null)
				post.releaseConnection();
		}
		logger.debug("One Message Service Response : " + responseString);
		return responseString;
	}

	private void setCustomerDetailsFromGrid(String customerID, OneMessageDetails oneMessageDetails) {
		try {
			// Invoke Grid Service API
			String custXml = gridService.getCustomer(customerID);
			// Parse response XML and get given Name, email
			Document doc = gridService.getParsedDocument(custXml);

			if (doc.getElementsByTagName(GRID_GIVEN_NAME_ELEMENT) != null
					&& doc.getElementsByTagName(GRID_GIVEN_NAME_ELEMENT).getLength() > 0) {
				oneMessageDetails
						.setCustFirstName(doc.getElementsByTagName(GRID_GIVEN_NAME_ELEMENT).item(0).getTextContent());
			} else {
				oneMessageDetails.setCustFirstName(defaultName);
			}

			if (doc.getElementsByTagName(GRID_EMAIL_ID_ELEMENT) != null
					&& doc.getElementsByTagName(GRID_EMAIL_ID_ELEMENT).getLength() > 0) {
				oneMessageDetails
						.setEmailAddress(doc.getElementsByTagName(GRID_EMAIL_ID_ELEMENT).item(0).getTextContent());
			} else {
				oneMessageDetails.setEmailAddress(defaultEmail);
			}
		} catch (Exception e) {
			logger.error("Exception while parsing XML Content from Grid : " + e);
		}
	}

	private DataMapType getDataMap(OneMessageDetails oneMessageDetails, List<String> fieldList) {
		DataMapType dataMap = new DataMapType();
		for (String element : fieldList) {
			if ("Cust_FirstName".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("Cust_FirstName");
				mapItem.setValue(oneMessageDetails.getCustFirstName());
				dataMap.getItem().add(mapItem);
			} else if ("Last4Acct".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("Last4Acct");
				mapItem.setValue(oneMessageDetails.getCustIdAccount());
				dataMap.getItem().add(mapItem);
			} else if ("MTN_LIST".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("MTN_LIST");
				mapItem.setValue(oneMessageDetails.getMdnList());
				dataMap.getItem().add(mapItem);
			} else if ("Speed".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("Speed");
				mapItem.setValue(oneMessageDetails.getSpeed());
				dataMap.getItem().add(mapItem);
			} else if ("EmailAddress".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("EmailAddress");
				mapItem.setValue(oneMessageDetails.getEmailAddress());
				dataMap.getItem().add(mapItem);
			} else if ("ExpiryDate".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("ExpiryDate");
				mapItem.setValue(DATE_FORMAT.format(oneMessageDetails.getExpiryDate()));
				dataMap.getItem().add(mapItem);
			} else if ("OldPlan".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("OldPlan");
				mapItem.setValue(oneMessageDetails.getOldPlan());
				dataMap.getItem().add(mapItem);
			} else if ("Survey_URL".equals(element)) {
				DataMapItemType mapItem = new DataMapItemType();
				mapItem.setKey("Survey_URL");
				mapItem.setValue(oneMessageDetails.getSurveyURL());
				dataMap.getItem().add(mapItem);
			}
		}
		return dataMap;
	}

	@PostConstruct
	public void initializeOneMessageService() {

		List<String> fieldList = new ArrayList<String>();
		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("MTN_LIST");
		fieldList.add("EmailAddress");
		oneMessageMap.put(OneMessageType.NightSurferWelcome.toString(), fieldList);

		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("MTN_LIST");
		fieldList.add("Speed");
		fieldList.add("EmailAddress");
		oneMessageMap.put(OneMessageType.SelectYourSpeedWelcome.toString(), fieldList);

		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("ExpiryDate");
		fieldList.add("EmailAddress");
		oneMessageMap.put(OneMessageType.NightSurferExp7Days.toString(), fieldList);

		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("ExpiryDate");
		fieldList.add("EmailAddress");
		fieldList.add("OldPlan");
		oneMessageMap.put(OneMessageType.SelectYourSpeedExp7Days.toString(), fieldList);

		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("EmailAddress");
		fieldList.add("Survey_URL");
		oneMessageMap.put(OneMessageType.NightSurferEarlyTerm.toString(), fieldList);

		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("EmailAddress");
		fieldList.add("OldPlan");
		oneMessageMap.put(OneMessageType.SelectYourSpeedEarlyTerm.toString(), fieldList);

		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("EmailAddress");
		fieldList.add("Survey_URL");
		oneMessageMap.put(OneMessageType.NightSurferExpire.toString(), fieldList);

		fieldList = new ArrayList<String>();
		fieldList.add("Cust_FirstName");
		fieldList.add("Last4Acct");
		fieldList.add("EmailAddress");
		fieldList.add("OldPlan");
		oneMessageMap.put(OneMessageType.SelectYourSpeedExpire.toString(), fieldList);

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(ONE_MESSAGE_JAXB_PACKAGE);
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbContext = JAXBContext.newInstance(ONE_MESSAGE_JAXB_PACKAGE);
			unMarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("Exception in initializeOneMessageService : ", e.getMessage(), e);
		}

	}

}
