package com.vzwcorp.pricinglab.service;

import com.hazelcast.core.IMap;
import com.vzwcorp.pricinglab.profile.vo.PlabUsage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by menonka on 11/13/2016.
 */
@Service
public class HazelcastPrintService {

    static Logger logger = LogManager.getLogger(HazelcastPrintService.class);

    @Autowired
    DistributedService distributedService;

    //@Scheduled(fixedDelayString = "${usagetimer.print.delay:1000}")
    @Scheduled(fixedDelayString = "${usagetimer.print.delay:900000}")
    public void plabUsagePrintTimer () {
        IMap<String, PlabUsage> plabUsageMap = distributedService.getHazelcastInstance().getMap("PLAB_USAGE_MAP");
        if (plabUsageMap!=null && !plabUsageMap.isEmpty()) {
            logger.debug("############### PLAB-USAGE TIMER ENTRY DETAILS START ############### ");
            for (Map.Entry<String,PlabUsage> entry : plabUsageMap.entrySet()) {
                logger.debug("Customer : {}, Usage : {}", entry.getKey(), entry.getValue().getUsageRec());
            }
            logger.debug("############### PLAB-USAGE TIMER ENTRY DETAILS END ############### ");
            logger.debug("Map Size : " + plabUsageMap.size());
        } else {
            logger.debug("Plab Usage table is empty.");
        }

    }

}
