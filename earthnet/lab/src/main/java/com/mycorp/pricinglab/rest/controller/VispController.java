package com.vzwcorp.pricinglab.rest.controller;

import com.vzwcorp.pricinglab.service.RestClient;
import com.vzwcorp.pricinglab.vo.repository.OfferRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class VispController {

	@Value("${visp.url}")
	String vispURL;

	@Value("${plab.url}")
	String plabURL;

	@Autowired
	OfferRepository repository;

	@Autowired
	ServiceRepository serviceRepository;
	
	Map <String, String> instances= new HashMap<String,String>();
	Map <String, Long> instancesBytes= new HashMap<String,Long>();

	static Logger logger = LogManager.getLogger(VispController.class);
	
	// Create a VISP service
	@RequestMapping(value = "/visp/service/create", method = RequestMethod.POST)
	public String addService(@RequestParam(value = "serviceObject", defaultValue = "0") String jsonService) {
		logger.debug(" VISP Received: "+ jsonService);
		String vispServiceID = RandomStringUtils.randomAlphanumeric(7);
		String s= "{ \"Service-correlation-id\": \""+ vispServiceID  +"\" }";
		return s;
	}

	@RequestMapping(value = "/visp/service/mdn/{mdn}", method = RequestMethod.POST)
	public String addServiceInstance(@RequestParam(value = "serviceObject", defaultValue = "0") String jsonService,@PathVariable("mdn") String mdn) {
		logger.debug(" VISP Instance Received: "+ jsonService);
		String vispServiceID = RandomStringUtils.randomAlphanumeric(7);
		instances.put(mdn,vispServiceID);
		instancesBytes.put(mdn,(long)0);
		String s= "{ \"Service-correlation-id\": \""+ vispServiceID  +"\" }";
		return s;
	}
	
	@Scheduled(fixedDelayString = "${refreshtimer.delay:5000}")
	public void sendUsage()
	{
		SimpleDateFormat fmt= new SimpleDateFormat("MM/dd/yy hh:mm:ss");  //2016-06-23 16:50:25.662075
		Date curDate= new Date();

		Date lastDate= new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);  // number of days to add

		for ( String mdn: instances.keySet()){

			long prevBytes= instancesBytes.get(mdn);
			long curBytes= prevBytes +(new Random().nextInt(1000000) + 1);
			instancesBytes.put(mdn, curBytes);
				
			String usage= "{\"eventType\": \"PeriodicUpdateByTime\", \"mdn\":\""+mdn+"\", \"billingInfo\": {\"totalBytes\":"+ curBytes +" , \"vispServiceID\": \"1\", \"ratingGroup\": 0, \"firsttimeofUsage\":\""+fmt.format(curDate)+"\", \"roamingIndicator\": 0, \"vispServiceInstanceID\": \""  +  instances.get(mdn) + "\", \"totalPackets\":"+ (curBytes/100)+" , \"lasttimeofUsage\":\"" + fmt.format(c.getTime()) + "\"}}";
			logger.debug(usage);
			RestClient.postRequest(plabURL+"/usage", usage);
		}

	}
	
	public static void main ( String [] argc){
		SimpleDateFormat fmt= new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.yyyyy");  //2016-06-23 16:50:25.662075
		Date curDate= new Date();
			long prevBytes= 0;
			long curBytes= prevBytes +(new Random().nextInt(1000000) + 1);
			String mdn="123";
				
			String usage= "{\"eventType\": \"PeriodicUpdateByTime\", \"mdn\":"+mdn+", \"billingInfo\": {\"totalBytes\":"+ curBytes +" , \"vispServiceID\": \"1\", \"ratingGroup\": 0, \"firsttimeofUsage\": \"  " + fmt.format(curDate)   +" \", \"roamingIndicator\": 0, \"vispServiceInstanceID\": \" "  +  "123134234" + "\", \"totalPackets\":"+ (curBytes/100)+" , \"lasttimeofUsage\": \"2016-06-23 16:50:25.662075\"}}";
			System.out.println(usage);

	}
}
