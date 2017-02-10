package com.vzwcorp.pricinglab.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hazelcast.core.IQueue;
import com.hazelcast.monitor.LocalQueueStats;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hazelcast.core.IMap;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefGridPlabCustRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.PlabUsageRepository;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCust;
import com.vzwcorp.pricinglab.profile.vo.PlabUsage;
import com.vzwcorp.pricinglab.vo.RefGridParam;

@Service
public class TimerService  {
	
	static Logger logger = LogManager.getLogger(TimerService.class);

	@Autowired
	DistributedService distributedService;

	@Autowired
	PlabUsageRepository plabUsageRepo;

	@Autowired
	RefGridPlabCustRepository refGridPlabCustRepository;

	@Autowired
	ProductManager productManager;

	@Scheduled(fixedDelayString = "${usage.timer.delay:1000}")
	public void timer() {
		IMap<String, PlabUsage> plabUsageMap = distributedService.getHazelcastInstance().getMap("PLAB_USAGE_MAP");

		List<PlabUsage> plabUsageList = plabUsageRepo.findAll();

		for (PlabUsage plabUsage : plabUsageList) {

			PlabUsage mapUsageInstance = plabUsageMap.get(plabUsage.getPrimaryKey());

			if (mapUsageInstance != null) {
				if (plabUsage.getUbsrTS() != null && plabUsage.getUbsrTS().after(mapUsageInstance.getUbsrTS())) {
					plabUsageMap.put(plabUsage.getPrimaryKey(), plabUsage);
				}
			} else {
				plabUsageMap.put(plabUsage.getPrimaryKey(), plabUsage);
			}

		}

	}
	
	

	@Scheduled(fixedDelayString = "${refgrid.timer.delay:60000}")
	public void refGridTimer() {
		IMap<String, RefGridPlabCust> refGridHazelcastMap = distributedService.getHazelcastInstance().getMap("REF_GRID_MAP");
		List<RefGridPlabCust> refGridPlabCustList = refGridPlabCustRepository.findAll();
		Map<String, RefGridPlabCust> refGridDbMap = new HashMap<String,RefGridPlabCust >();

		for (RefGridPlabCust dbRefGrid: refGridPlabCustList){
			//This piece of code here is to handle timestamps like these : 10000-01-01 02:59:59.000000
			if (dbRefGrid!=null && dbRefGrid.getPlEndDate()!=null
					&& dbRefGrid.getPlEndDate().after(PricingLabUtility.getDefaultEndTimeStamp()) ){
				dbRefGrid.setPlEndDate(PricingLabUtility.getDefaultEndTimeStamp());
			}

			String refGridKey= dbRefGrid.getCustIdNo()+dbRefGrid.getAcctNo()+dbRefGrid.getMdn();
			refGridDbMap.put(refGridKey, dbRefGrid);
		}
		
		// Scan all DB items.. do they exist in Map, if not add to Map
		// if they do, if the modified date in the DB more recent then update map
		for (Entry<String, RefGridPlabCust> dbRefGridEntrySet: refGridDbMap.entrySet()){
			RefGridPlabCust dbRefGrid=dbRefGridEntrySet.getValue();
			String refGridKey= dbRefGrid.getCustIdNo()+dbRefGrid.getAcctNo()+dbRefGrid.getMdn();
			RefGridPlabCust mapRefGrid= refGridHazelcastMap.get(refGridKey);
			if (mapRefGrid == null){
				refGridHazelcastMap.put(refGridKey, dbRefGrid);
			} else if (dbRefGrid.getModifiedDate()!=null && mapRefGrid.getModifiedDate()!=null
					&& dbRefGrid.getModifiedDate().after(mapRefGrid.getModifiedDate()) )
				refGridHazelcastMap.put(refGridKey, dbRefGrid);
		}
		
		// Scan all Map items.. do they exist in DB, if not then add to DB
		// if they do, if the modified date in the map more recent then update DB
		
		for (Entry<String, RefGridPlabCust> mapEntrySet: refGridHazelcastMap.entrySet()){
			RefGridPlabCust mapRefGrid=mapEntrySet.getValue();
			String refGridKey= mapRefGrid.getCustIdNo()+mapRefGrid.getAcctNo()+mapRefGrid.getMdn();
			RefGridPlabCust dbRefGrid= refGridDbMap.get(refGridKey);
			
			if (dbRefGrid == null){
				productManager.DBcreateOrUpdateRefGrid(mapRefGrid );
			} else if (mapRefGrid.getModifiedDate()!=null && dbRefGrid.getModifiedDate()!=null
					&& mapRefGrid.getModifiedDate().after(dbRefGrid.getModifiedDate()) )
				productManager.DBcreateOrUpdateRefGrid(mapRefGrid );
		}
		
	}	

	/*
	@Scheduled (fixedDelayString = "${rbmaction.timer.delay:900000}")
	public void rbmTimer () {
		IQueue<String> rbmQueue = distributedService.getHazelcastInstance().getQueue("RBM_EXTERNAL_ACTION_QUEUE");
		if (!rbmQueue.isEmpty()) {
			try {
				int quesizeBefore = rbmQueue.size();
				String custAcctString = rbmQueue.take();
				if (custAcctString!=null && !custAcctString.isEmpty()) {
					logger.debug("Retrieved object : {} on queue {} ",custAcctString, rbmQueue.getName());
					productManager.addCustomerToAccActionSummaryFromQueue(custAcctString);
					logger.debug("Queue size Before {} and After : {} ",quesizeBefore,rbmQueue.size());
				}
			} catch (Exception ex) {
				logger.error("Exception in rbmTimer",ex.getMessage(),ex);
			}
		} else {
			if (logger.isTraceEnabled()) {
				logger.debug("Queue {} is empty",rbmQueue.getName());
			}
		}
	}
	*/
}
