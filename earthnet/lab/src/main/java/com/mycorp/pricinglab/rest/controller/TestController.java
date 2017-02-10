/**
 * 
 */
package com.vzwcorp.pricinglab.rest.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;

import com.hazelcast.core.IQueue;
import com.vzwcorp.pricinglab.service.DistributedService;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vzwcorp.pricinglab.config.SpringContext;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.helper.ProductHelper;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustAcctMdnRepository;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdn;
import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.BillingInfo;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.TestCaseResult;
import com.vzwcorp.pricinglab.vo.UsageRecord;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;

/**
 * @author kovelde
 *
 */

@RestController
public class TestController {

	@Autowired
	PricingLabUtility pricingLabUtility;

	@Autowired
	ServiceController serviceController;

	@Autowired
	OfferController offerController;

	@Autowired
	SubCustAcctMdnRepository subCustAcctMdnRepository;

	@Autowired
	ProductHelper productHelper;

	@Autowired
	UsageRecordController usageRecordController;

	ObjectMapper mapper;

	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;

	@Autowired
	ServiceManager serviceManager;

	@Value("${plab.testcase.url:http://jitrsit1.north.vzwcorp.com/pricinglab}")
	String plabUrl;

	@Autowired
	DistributedService distributedService;
	
    @Autowired
    PlabCustRepository plabCustRepository;

	@Value("${rbm.timertask.delay:10000}")
	public long timerTaskDelay;

	static Logger logger = LogManager.getLogger(TestController.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/test/case1/{offerId}/{mdn}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> useCase1(@PathVariable("offerId") Long offerId,
			@PathVariable("mdn") String mdn) throws Exception {

		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();

		String custKey = getCustomerKey(mdn);

		if (custKey == null) {
			responseMap.put("Get SubCustAcct", "Customer Not found in UBSR");
			return new ResponseEntity(responseMap, HttpStatus.NOT_FOUND);
		}

		ResponseEntity response = null;

		String responseStr = null;

		MultipartFile csvFile = new org.springframework.mock.web.MockMultipartFile("customer.csv", custKey.getBytes());

		response = serviceController.saveInvite(offerId, new MultipartFile[] { csvFile });

		if (response.getStatusCode() != HttpStatus.OK) {
			responseMap.put("Save Invite", "Save Invite Failed with response : " + response.getBody());
			return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		responseMap.put("Save Invite", response.getBody());

		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerId + "/" + mdn + "/terms/accept", "",
				ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseMap.put("Accept Terms", "Terms and Conditions failed with response : " + responseStr);
			return new ResponseEntity(responseMap, HttpStatus.MULTI_STATUS);
		}

		responseMap.put("Accept Terms", responseStr);

		String offerInstanceId = getOfferInstanceId(responseStr);

		responseStr = pricingLabUtility.postRequest(plabUrl + "/offers?mdn=" + mdn, "", ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseMap.put("Get Offers", "Get Offers failed with response : " + responseStr);
			return new ResponseEntity(responseMap, HttpStatus.MULTI_STATUS);
		}

		responseMap.put("Get Offers", responseStr);

		String offerMdnOptions = getOfferMDNOptions(responseStr, true);

		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerInstanceId + "/accept?action=preaccept",
				offerMdnOptions, ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseMap.put("Pre Accept", "Pre Accept offer failed with response : " + responseStr);
			return new ResponseEntity(responseMap, HttpStatus.MULTI_STATUS);
		}

		responseMap.put("Pre Accept", responseStr);

		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerInstanceId + "/accept?action=success",
				"", ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseMap.put("Post Accept-Success", "Post Accept offer failed with response : " + responseStr);
			return new ResponseEntity(responseMap, HttpStatus.MULTI_STATUS);
		}

		responseMap.put("Post Accept-Success", responseStr);

		return new ResponseEntity(responseMap, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/test/case/{num}/{offerId}/{mdn}", method = RequestMethod.POST)
	public String useCase(@PathVariable("num") int num, @PathVariable("offerId") Long offerId,
			@PathVariable("mdn") String mdn) throws Exception {

		List<TestCaseResult> responseList = new ArrayList<TestCaseResult>();

		try {

			switch (num) {
			case 9:

				return case9(offerId, mdn);

			case 10:

				return case10(offerId, mdn);

			case 11:

				return case11(offerId, mdn);
				
			case 12:

				return case12(offerId, mdn);
				
			case 13:

				return case13(offerId, mdn);

			default:
				break;
			}

			String custKey = getCustomerKey(mdn);

			if (custKey == null) {
				responseList.add(new TestCaseResult("Get SubCustAcct", "Customer Not found in UBSR", "Failed"));
				return mapper.writeValueAsString(responseList);
			}

			String responseStr = null;

			MultipartFile csvFile = new org.springframework.mock.web.MockMultipartFile("customer.csv",
					custKey.getBytes());

			ResponseEntity response = serviceController.saveInvite(offerId, new MultipartFile[] { csvFile });

			if (response.getStatusCode() != HttpStatus.OK) {
				responseList.add(new TestCaseResult("Save Invite",
						"Save Invite Failed with response : " + response.getBody(), "Failed"));
				return mapper.writeValueAsString(responseList);
			}

			responseList.add(new TestCaseResult("Save Invite", response.getBody().toString(), "Success"));

			Thread.sleep(2000);

			responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerId + "/" + mdn + "/terms/accept", "",
					ContentType.APPLICATION_JSON);

			if (responseStr.contains("Error")) {
				responseList.add(new TestCaseResult("Accept Terms",
						"Terms and Conditions failed with response : " + responseStr, "Failed"));
				return mapper.writeValueAsString(responseList);
			}

			responseList.add(new TestCaseResult("Accept Terms", responseStr, "Success"));

			String offerInstanceId = getOfferInstanceId(responseStr);

			Thread.sleep(2000);

			responseStr = pricingLabUtility.postRequest(plabUrl + "/offers?mdn=" + mdn, "",
					ContentType.APPLICATION_JSON);

			if (responseStr.contains("Error")) {
				responseList.add(
						new TestCaseResult("Get Offers", "Get Offers failed with response : " + responseStr, "Failed"));
				return mapper.writeValueAsString(responseList);
			}
			responseList.add(new TestCaseResult("Get Offers", responseStr, "Success"));

			String offerMdnOptions = "";

			switch (num) {
			case 1:
				offerMdnOptions = getOfferMDNOptions(responseStr, true);
				return case1(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			case 2:
				offerMdnOptions = getOfferMDNOptions(responseStr, true);
				return case2(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			case 3:
				offerMdnOptions = getOfferMDNOptions(responseStr, true);
				return case3(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			case 4:
				offerMdnOptions = getOfferMDNOptions(responseStr, true);
				return case4(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			case 5:
				offerMdnOptions = getOfferMDNOptions(responseStr, false);
				return case1(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			case 6:
				offerMdnOptions = getOfferMDNOptions(responseStr, false);
				return case2(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			case 7:
				offerMdnOptions = getOfferMDNOptions(responseStr, false);
				return case3(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			case 8:
				offerMdnOptions = getOfferMDNOptions(responseStr, false);
				return case4(responseList, offerId, offerInstanceId, mdn, offerMdnOptions);

			default:
				return mapper.writeValueAsString(responseList);
			}
		} catch (Exception e) {
			responseList.add(new TestCaseResult("Exception", "Exception occured : " + e.getMessage(), "Failed"));
		}
		return mapper.writeValueAsString(responseList);

	}

	/**
	 * Save Invite
	 * 
	 * @param offerId
	 * @param mdn
	 * @return
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private String case9(Long offerId, String mdn) throws JsonProcessingException, Exception {

		List<TestCaseResult> responseList = new ArrayList<TestCaseResult>();

		String custKey = getCustomerKey(mdn);

		if (custKey == null) {
			responseList.add(new TestCaseResult("Get SubCustAcct", "Customer Not found in UBSR", "Failed"));
			return mapper.writeValueAsString(responseList);
		}

		MultipartFile csvFile = new org.springframework.mock.web.MockMultipartFile("customer.csv", custKey.getBytes());

		ResponseEntity response = serviceController.saveInvite(offerId, new MultipartFile[] { csvFile });

		if (response.getStatusCode() != HttpStatus.OK) {
			responseList.add(new TestCaseResult("Save Invite",
					"Save Invite Failed with response : " + response.getBody(), "Failed"));
			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Save Invite", response.getBody().toString(), "Success"));

		return mapper.writeValueAsString(responseList);
	}

	/**
	 * Terminate
	 * 
	 * @param offerId
	 * @param mdn
	 * @return
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Transactional
	private String case10(Long offerId, String mdn) throws JsonProcessingException, Exception {
		List<TestCaseResult> responseList = new ArrayList<TestCaseResult>();
		
        Long[] indicators = {PlabConstants.EARLY_TERMINATE_PENDING_EOC,PlabConstants.EARLY_TERMINATE_VISION_REMOVED};
        List<PlabCust> plabCusts = plabCustRepository.findAllByMdnAndIndicator(mdn, indicators);
        
        boolean removeProvisioningSpoOnly = false;
        
        if(plabCusts != null && !plabCusts.isEmpty()){
        	removeProvisioningSpoOnly = true;
        }
        
		String responseStr = pricingLabUtility.postRequest(
				plabUrl + "/offer/cust/terminate/" + offerId + "/" + mdn + "/"+ removeProvisioningSpoOnly, "", ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Terminate", "Terminate Customer failed with response : " + responseStr,
					"Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Terminate", responseStr, "Success"));

		return mapper.writeValueAsString(responseList);

	}

	/**
	 * Early Terminate
	 * 
	 * @param offerId
	 * @param mdn
	 * @return
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	private String case11(Long offerId, String mdn) throws JsonProcessingException, Exception {

		List<TestCaseResult> responseList = new ArrayList<TestCaseResult>();

		String responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/cust/terminate/" + offerId + "/" + mdn, "",
				ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Early Terminate",
					"Early Terminate Customer failed with response : " + responseStr, "Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Early Terminate", responseStr, "Success"));

		return mapper.writeValueAsString(responseList);

	}
	
	private String case13(Long offerId, String mdn) throws JsonProcessingException, Exception{
		List<TestCaseResult> responseList = new ArrayList<TestCaseResult>();

		String responseStr = pricingLabUtility.postRequest(plabUrl + "/addOrRemoveSpo/" + offerId + "/" + mdn +"/add", "",
				ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Add Spo",
					"Add Spo for mdn failed with response : " + responseStr, "Failed"));
			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Add Spo", responseStr, "Success"));
		return mapper.writeValueAsString(responseList);

	}
	
	private String case12(Long offerId, String mdn) throws JsonProcessingException, Exception{
		List<TestCaseResult> responseList = new ArrayList<TestCaseResult>();

		String responseStr = pricingLabUtility.postRequest(plabUrl + "/addOrRemoveSpo/" + offerId + "/" + mdn +"/remove", "",
				ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Remove Spo",
					"Remove Spo for mdn failed with response : " + responseStr, "Failed"));
			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Remove Spo", responseStr, "Success"));
		return mapper.writeValueAsString(responseList);
		
	}

	private String case1(List<TestCaseResult> responseList, Long offerId, String offerInstanceId, String mdn,
			String offerMdnOptions) throws JsonProcessingException, Exception {

		Thread.sleep(1000);

		String responseStr = pricingLabUtility.postRequest(
				plabUrl + "/offer/" + offerInstanceId + "/accept?action=preaccept", offerMdnOptions,
				ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Pre Accept", "Pre Accept offer failed with response : " + responseStr,
					"Failed"));
			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Pre Accept", responseStr, "Success"));

		Thread.sleep(2000);
		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerInstanceId + "/accept?action=success",
				"", ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Post Accept-Success",
					"Post Accept offer failed with response : " + responseStr, "Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Post Accept-Success", responseStr, "Success"));

		return mapper.writeValueAsString(responseList);
	}

	private String case2(List<TestCaseResult> responseList, Long offerId, String offerInstanceId, String mdn,
			String offerMdnOptions) throws JsonProcessingException, Exception {
		Thread.sleep(1000);
		String responseStr = pricingLabUtility.postRequest(
				plabUrl + "/offer/" + offerInstanceId + "/accept?action=preaccept", offerMdnOptions,
				ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Pre Accept", "Pre Accept offer failed with response : " + responseStr,
					"Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Pre Accept", responseStr, "Success"));

		Thread.sleep(2000);
		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerInstanceId + "/accept?action=success",
				"", ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Post Accept-Success",
					"Post Accept offer failed with response : " + responseStr, "Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Post Accept-Success", responseStr, "Success"));

		Thread.sleep(1000);
		String jsonUsageRecord = getUsageJsonString(mdn, offerInstanceId);

		responseStr = pricingLabUtility.postRequest(plabUrl + "/usage", jsonUsageRecord, ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList
					.add(new TestCaseResult("Add Usage", "Add usage failed with response : " + responseStr, "Failed"));
		} else {
			responseList.add(new TestCaseResult("Add Usage", responseStr, "Success"));
		}

		Thread.sleep(1000);
		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/getdetailusage/" + mdn, "",
				ContentType.APPLICATION_XML);

		if (!responseStr.contains("serviceResponse")) {
			responseList.add(new TestCaseResult("Get Detail Usage",
					"Get Detail usage failed with response : " + responseStr, "Failed"));
		} else {
			responseList.add(new TestCaseResult("Get Detail Usage", responseStr, "Success"));
		}

		Thread.sleep(1000);
		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/cust/terminate/" + offerId + "/" + mdn + "/false",
				"", ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Terminate", "Terminate Customer failed with response : " + responseStr,
					"Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Terminate", responseStr, "Success"));

		return mapper.writeValueAsString(responseList);
	}

	private String case3(List<TestCaseResult> responseList, Long offerId, String offerInstanceId, String mdn,
			String offerMdnOptions) throws JsonProcessingException, Exception {

		Thread.sleep(1000);
		String responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerInstanceId + "/accept/mlmo",
				offerMdnOptions, ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Accept MLMO",
					"Accept offer with MLMO failed with response : " + responseStr, "Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Accept MLMO", responseStr, "Success"));

		return mapper.writeValueAsString(responseList);
	}

	private String case4(List<TestCaseResult> responseList, Long offerId, String offerInstanceId, String mdn,
			String offerMdnOptions) throws JsonProcessingException, Exception {
		Thread.sleep(1000);
		String responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/" + offerInstanceId + "/accept/mlmo",
				offerMdnOptions, ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Accept MLMO",
					"Accept offer with MLMO failed with response : " + responseStr, "Failed"));

			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Accept MLMO", responseStr, "Success"));

		Thread.sleep(1000);
		String jsonUsageRecord = getUsageJsonString(mdn, offerInstanceId);

		responseStr = pricingLabUtility.postRequest(plabUrl + "/usage", jsonUsageRecord, ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList
					.add(new TestCaseResult("Add Usage", "Add usage failed with response : " + responseStr, "Failed"));
		} else {
			responseList.add(new TestCaseResult("Add Usage", responseStr, "Success"));
		}

		Thread.sleep(1000);
		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/getdetailusage/" + mdn, "",
				ContentType.APPLICATION_XML);

		if (!responseStr.contains("serviceResponse")) {
			responseList.add(new TestCaseResult("Get Detail Usage",
					"Get Detail usage failed with response : " + responseStr, "Failed"));
		} else {
			responseList.add(new TestCaseResult("Get Detail Usage", responseStr, "Success"));
		}

		Thread.sleep(1000);
		responseStr = pricingLabUtility.postRequest(plabUrl + "/offer/cust/terminate/" + offerId + "/" + mdn + "/false",
				"", ContentType.APPLICATION_JSON);

		if (responseStr.contains("Error")) {
			responseList.add(new TestCaseResult("Terminate", "Terminate Customer failed with response : " + responseStr,
					"Failed"));
			return mapper.writeValueAsString(responseList);
		}

		responseList.add(new TestCaseResult("Terminate", responseStr, "Success"));

		return mapper.writeValueAsString(responseList);
	}

	@Transactional("blTransactionManager")
	private String getCustomerKey(String mdn) {
		List<SubCustAcctMdn> subCustAcct = subCustAcctMdnRepository.findByActiveMdn(mdn);

		if (subCustAcct == null || subCustAcct.isEmpty()) {
			return null;
		}

		SubCustAcctMdn custAcct = subCustAcct.get(0);

		return custAcct.getCustIdNo() + "," + custAcct.getAcctNo() + "," + custAcct.getMdn();
	}

	private String getOfferInstanceId(String json) {

		String jsonOut = "";

		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json.getBytes());
			jsonOut = rootNode.get("Content").asText();
		} catch (Exception e) {

		}
		return jsonOut;
	}

	private String getOfferMDNOptions(String json, boolean all) {
		String jsonOut = "";

		JsonNode rootNode;
		try {

			boolean isNightSurfer = false;
			rootNode = mapper.readTree(json.getBytes());

			JsonNode contentNode = rootNode.path("Content");

			String offerName = contentNode.get("name").asText();

			if (offerName.toLowerCase().contains("night")) {
				isNightSurfer = true;
			}

			JsonNode offerOptionsNode = contentNode.path("offerMdnOptions");

			if (isNightSurfer) {
				List<JsonNode> optionsNodes = offerOptionsNode.findParents("choice");
				int i = 2;
				if (optionsNodes != null && !optionsNodes.isEmpty()) {
					for (JsonNode option : optionsNodes) {
						JsonNode choiceNode = option.path("choice");
						JsonNode optionNode = choiceNode.get(0);
						if (all) {
							((ObjectNode) optionNode).put("selected", true);
						} else if (i++ % 2 == 0) {
							((ObjectNode) optionNode).put("selected", true);
						}
					}
				}
			}

			jsonOut = offerOptionsNode.toString();

		} catch (Exception e) {

		}

		return jsonOut;
	}

	@Transactional("blTransactionManager")
	private String getUsageJsonString(String mdn, String offerInstanceId) {

		PlatformTransactionManager txManager = (PlatformTransactionManager) SpringContext.getApplicationContext()
				.getBean("blTransactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can only be
		// done programmatically
		def.setName("TestTx" + mdn + offerInstanceId);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = txManager.getTransaction(def);

		List<ServiceInstance> serviceInstances = serviceInstanceRepository
				.findLatestByOfferInstance(Long.valueOf(offerInstanceId));

		if (serviceInstances == null || serviceInstances.isEmpty()) {
			return null;
		}

		ServiceInstance offerServiceInstance = null;

		// Get offer related service instance
		for (ServiceInstance si : serviceInstances) {

			String serviceType = serviceManager.getServiceType(si.getService());
			if (!serviceType.equals("BaseService")) {
				offerServiceInstance = si;
				break;
			}

		}

		ObjectMapper mapper = productHelper.getDefaultObjectMapper();

		String jsonOut = "";

		UsageRecord usage = new UsageRecord();

		usage.setMdn(mdn);
		usage.setEventType("byVolume");
		usage.setChangeTime(new Date());

		BillingInfo billingInfo = new BillingInfo();
		billingInfo.setRoamingIndicator(0l);
		billingInfo.setFirsttimeofUsage(offerServiceInstance.getDateCreated());
		billingInfo.setLasttimeofUsage(new Date());
		billingInfo.setTotalBytes(61500000l);
		billingInfo.setTotalPackets(439803l);
		billingInfo.setUplinkBytes(125000000l);
		billingInfo.setDownlinkBytes(375000000l);
		billingInfo.setVispServiceID(offerServiceInstance.getService().getVispServiceID());
		billingInfo.setVispServiceInstanceID(offerServiceInstance.getVispServiceInstanceId());

		usage.setBillingInfo(billingInfo);

		try {
			DateFormat df = new SimpleDateFormat(PlabConstants.STD_VISP_TIMESTAMP_FORMAT);
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			mapper.setDateFormat(df);
			jsonOut = mapper.writeValueAsString(usage);
		} catch (JsonProcessingException e) {

		}

		// status.flush();

		return jsonOut;
	}

	@RequestMapping(value = "/test/hello", method = RequestMethod.POST)
	public String test() throws JsonProcessingException {

		List<TestCaseResult> responseList = new ArrayList<TestCaseResult>();

		responseList.add(new TestCaseResult("Get SubCustAcct", "Customer found in UBSR", "Success"));
		responseList.add(new TestCaseResult("Save", "save failed", "Failed"));

		return mapper.writeValueAsString(responseList);
	}

	@PostConstruct
	public void initializeMapper() {
		mapper = productHelper.getDefaultObjectMapper();
	}

	// public String postFormMultiPartFile(){
	//
	// HttpPost httpPost = new HttpPost(urlStr);
	//
	// File payload = new File("/Users/CasinoRoyaleBank");
	//
	// HttpEntity entity = MultipartEntityBuilder.create()
	// .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
	// .addBinaryBody("file", payload)
	// .addTextBody("username", "bond")
	// .addTextBody("password", "bond")
	// .build();
	// httpPost.setEntity(entity);
	//
	//
	// return null;
	// }

	@RequestMapping(value = "/test/rbm_queue", method = RequestMethod.POST)
	public String testAndAddToRbmQueues(@RequestParam(value = "custAcctNum") final String custIdAcctNumStr,
										@RequestParam(value = "dcName", required = false, defaultValue = "tdc") final String dc) {
		Timer timer = new Timer(true);
		TimerTask timerTask = null;
		IQueue<String> rbmQueue = distributedService.getHazelcastInstance().getQueue("RBM_EXTERNAL_ACTION_QUEUE_" + dc);

		try {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					try {
						IQueue<String> rbmQueue = distributedService.getHazelcastInstance().getQueue("RBM_EXTERNAL_ACTION_QUEUE_" + dc);
						if (rbmQueue != null && !rbmQueue.contains(custIdAcctNumStr)) {
							logger.debug("Adding to queue : {}", rbmQueue.getName());
							rbmQueue.put(custIdAcctNumStr);
						} else {
							logger.debug("CustomerID-AcctNmm {} already present in queue.", custIdAcctNumStr);
						}
					} catch (Exception ex) {
						logger.error("Exception in HazelcastRbmListener.HazelcastRbmRunner. Message : {}, Stacktrace : {} ", ex.getMessage(), ex);
					}
				}
			};


			timer.schedule(timerTask,timerTaskDelay);

		} catch (Exception exp) {
			logger.error("Unable to add {} back to queue {}. Queue size : {} ", custIdAcctNumStr, rbmQueue.getName(), rbmQueue.size());
			logger.error("Exception : ", exp.getMessage(), exp);

			if (timerTask != null) {
				timerTask.cancel();
			}
			if (timer != null) {
				timer.cancel();
			}
		}

		return "Added to queue : " + rbmQueue.getName();
	}

}
