package com.vzwcorp.pricinglab.profile.rbm.vo;

import com.hazelcast.core.*;
import com.vzwcorp.pricinglab.helper.ProductHelper;
import com.vzwcorp.pricinglab.service.DistributedService;
import com.vzwcorp.pricinglab.service.ProductManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by menonka on 11/11/2016.
 */
@Component
public class HazelcastRbmListener implements Serializable, ItemListener<Object> {

    static Logger logger = LogManager.getLogger(HazelcastRbmListener.class);

    @Autowired
    ProductManager manager;

    @Autowired
    DistributedService distributedService;

    @Autowired
    ProductHelper helper;

    @Override
    public void itemAdded(ItemEvent<Object> itemEvent) {

        long startTime = System.currentTimeMillis();
        ILock rbmLock = distributedService.getHazelcastInstance().getLock("RBM_LOCK");


        try {
            logger.debug("HazlecastRbmListener on : {} ", InetAddress.getLocalHost().getHostName());

            rbmLock.lock(10, TimeUnit.SECONDS);

            String dc = helper.findMyDataCenterSuffix();

            Object object = distributedService.getHazelcastInstance().getQueue("RBM_EXTERNAL_ACTION_QUEUE_"+dc).poll();

            while (object != null) {
                String customerAcctStr = (String) object;

                logger.info("AccExternalAction call for customer-account : {}", customerAcctStr);

                manager.addCustomerToAccActionSummaryFromQueue(customerAcctStr);

                object  = distributedService.getHazelcastInstance().getQueue("RBM_EXTERNAL_ACTION_QUEUE_"+dc).poll();
                //rbmLock.unlock();
            }
        } catch (Exception ex) {
            logger.error("Error in HazlecastRbmListener", ex.getMessage(), ex);
            if (rbmLock != null && rbmLock.isLocked()) {
                rbmLock.unlock();
            }
        } finally {
            if (rbmLock != null && rbmLock.isLocked()) {
                rbmLock.unlock();
                logger.debug("Releasing {} at {}. Lock count {} and remaining lease time {}", rbmLock.getName(),
                        System.currentTimeMillis() - startTime, rbmLock.getLockCount(), rbmLock.getRemainingLeaseTime());
            }
        }
    }

    @Override
    public void itemRemoved (ItemEvent<Object> itemEvent) {
        if (itemEvent!=null) {
            Object item = itemEvent.getItem();
            if (item!=null) {
                if (item instanceof String) {
                    logger.info("Item Removed from queue : " + item);
                } else {
                    logger.info("Item Removed from queue : " + item.toString());
                }
            }
        }
        //throw new UnsupportedOperationException("Item Remove operation in HazelcastRbmListener is currently not supported");
    }

   /*
    @Override
    public void onMessage(Message<Object> message) {
        if (message != null)
    }{
        ILock rbmLock = null;
        long startTime = System.currentTimeMillis();

        try {
            logger.debug("HazlecastRbmListener on : ", InetAddress.getLocalHost().getHostName());

            rbmLock = distributedService.getHazelcastInstance().getLock("RBM_LOCK");
            rbmLock.lock(10, TimeUnit.SECONDS);

            Object messageObject = message.getMessageObject();
            if (messageObject instanceof String) {
                String customerAcctStr = (String) messageObject;

                logger.info("AccExternalAction call for customer-account : {}", message);

                manager.addCustomerToAccActionSummaryFromTopic(customerAcctStr);

                rbmLock.unlock();
            }
        } catch (Exception ex) {
            logger.error("Error in HazlecastRbmListener", ex.getMessage(), ex);
            if (rbmLock != null && rbmLock.isLocked()) {
                rbmLock.unlock();
            }
        } finally {
            if (rbmLock != null && rbmLock.isLocked()) {
                rbmLock.unlock();
                logger.debug("Releasing {} at {}. Lock count {} and remaining lease time {}", rbmLock.getName(),
                        System.currentTimeMillis() - startTime, rbmLock.getLockCount(), rbmLock.getRemainingLeaseTime());
            }
        }
    }
    */

}
