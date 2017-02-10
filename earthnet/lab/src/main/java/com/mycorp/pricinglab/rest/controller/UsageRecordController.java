package com.vzwcorp.pricinglab.rest.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.PlabUsageRepository;
import com.vzwcorp.pricinglab.service.UsageManager;
import com.vzwcorp.pricinglab.vo.*;
import com.vzwcorp.pricinglab.vo.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RestController
@Transactional("blTransactionManager")
@PropertySource("classpath:application.properties")
public class UsageRecordController {
	
	@Value("${tdr.outfile.location}")
	private String tdrOutFileLocation;
	
	@Autowired
	OfferRepository offerRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	OfferInstanceRepository offerInstanceRepository;

	@Autowired
	VerizonEntityRepository vzwEntityRepository;

	@Autowired
	SelectionPageRepository selectionPageRepository;

	@Autowired
	SimplePageRepository pageRepository;

	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;
	
	static Logger logger = LogManager.getLogger(UsageRecordController.class);

	@Autowired
	UsageRecordRepository usageRecordRepository;

	@Autowired
	BillingInfoRepository billingInfoRepository;
	
	@Autowired
	UsageManager usageManager;

	@Autowired
	PlabUsageRepository plabUsageRepo;
	
	@Autowired
	UsageRecordRepository usageRecRepo;
	
	ObjectMapper mapper = new ObjectMapper();

	// add a usage record
//	 @CrossOrigin (origins = "*")
	@RequestMapping(value = "/usage", method = RequestMethod.POST)
	//public String addUsage(@RequestParam(value = "UsageRecordObject", defaultValue = "0") String jsonUsageRecord)
	public String addUsage(@RequestBody  String jsonUsageRecord)
	
	{
		logger.debug("addUsage (). UsageRecord : {}",jsonUsageRecord);
		/*
		String prefix="\"DataUsageRecord\": ";
		String post="},";
        jsonUsageRecord = jsonUsageRecord.substring(prefix.length()+4);
		int h= jsonUsageRecord.lastIndexOf(post);
		jsonUsageRecord= jsonUsageRecord.substring(0, h+1);
		*/

		//Code commented because it was removing the mdn from JSON.
		/*int h=jsonUsageRecord.indexOf("\"eventType\"");
		int n=jsonUsageRecord.indexOf("}",h);
		n=jsonUsageRecord.indexOf("}",n);
		jsonUsageRecord="{"+jsonUsageRecord.substring(h,n+1);
        jsonUsageRecord = jsonUsageRecord + "}";*/

		//logger.debug("UsageRecordController addUsage  jsonUsageRecord : {}",jsonUsageRecord);

		try {

			UsageRecord usageRecord = null;
            DateFormat df = new SimpleDateFormat(PlabConstants.STD_VISP_TIMESTAMP_FORMAT);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setDateFormat(df);
			usageRecord = mapper.readValue(jsonUsageRecord, UsageRecord.class);

			usageManager.processUsage(usageRecord);

			return "Success";
		} catch (Exception e) {
			logger.error("Error in processing TDR : ",e.getMessage(),e);
		} finally {
			ThreadContext.clearAll();
		}
		return "Error";
	}

	// add a usage record
	// @CrossOrigin (origins = "*")
	@RequestMapping(value = "/offer/usage/{offerInstanceId}", method = RequestMethod.POST)
	public String getUsage(@PathVariable("offerInstanceId") String offerInstanceId) {

		try {
			if (offerInstanceId !=null && !offerInstanceId.trim().isEmpty() && !offerInstanceId.equalsIgnoreCase("undefined")) {
				OfferInstance offerInstance = offerInstanceRepository.findOne(Long.parseLong(offerInstanceId));
				if (offerInstance != null) {
					Offer offer = offerInstance.getOffer();
					VerizonEntity vzEntity = offerInstance.getVerizonEntity();
					ObjectMapper mapper = new ObjectMapper();
					String jsonInString = "";
					for (ServiceInstance serviceInstance : offerInstance.getServiceInstances()) {
						ObjectWriter writer = mapper.writerWithView(Views.DemoView.class);
						try {
							jsonInString += writer.writeValueAsString(serviceInstance);
							break;
						} catch (JsonProcessingException e) {
							logger.error("JsonProcessingException UsageRecordContolller - getUsage() ): ", e.getMessage(), e);
						}
					}
					return jsonInString;
				} else {
					logger.debug("OfferInstance not found");
					return "OfferInstance not found or is Invalid";
				}
			} else {
				logger.debug("OfferInstanceID is null or empty or undefined.");
				return "OfferInstanceID is null or empty or undefined. Unable to get Usage";
			}
		} catch (Exception e) {
			logger.error("Exception UsageRecordContolller - getUsage() ): ", e.getMessage(), e);
		}
		return "Error";
	}
}

