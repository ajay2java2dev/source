package com.vzwcorp.pricinglab.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;

import com.vzwcorp.pricinglab.helper.ProductHelper;
import com.vzwcorp.pricinglab.profile.rbm.vo.HazelcastRbmListener;
import com.vzwcorp.pricinglab.rest.controller.VispController;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgroups.JChannel;
import org.jgroups.blocks.ReplicatedHashMap;
import org.jgroups.blocks.atomic.CounterService;
import org.jgroups.blocks.locking.LockService;
import org.jgroups.jmx.JmxConfigurator;
import org.jgroups.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MessageListener;
import com.vzwcorp.pricinglab.vo.RefGridParam;
import com.vzwcorp.pricinglab.vo.RefGridPlabConvParam;

@Service
public class DistributedService {

	static Logger logger = LogManager.getLogger(DistributedService.class);

	private static Properties props = null;
	private static JChannel channel = null;

	private HazelcastInstance hazelcastInstance;
	
	private static LockService lock_service;
	private static CounterService counterService = null;
	public static final long PRECISION = 100000000000L;

	public static final String PROP_CLUSTER_NAME = "clusterName";
	public static final String PROP_TCP_PATH = "TCPPATH";

	@Value("${hazelcast.config.file:hazelcast.xml}")
	String hazelcastConfig;

	boolean isInitialized= false;
	
	@Autowired
	RefGridParam  refGridListener;
	
	@Autowired
	RefGridPlabConvParam refGridConvListener;

	@Autowired
	HazelcastRbmListener rbmListener;

	@Autowired
	ProductHelper helper;


	@PostConstruct
	public void init() {
/*		String propsFileName = "C:\\Users\\zoheiam\\workspace_UB\\JITRLoader\\etc\\DistributedService.properties";
		if (propsFileName == null) {
			System.out.println("Can not find the property file.");
			return;
		}
		props = new Properties();
		FileInputStream in = null;
		try {
			System.out.println("Property Path is: " + propsFileName);
			in = new FileInputStream(propsFileName);
			props.load(in);
		} catch (FileNotFoundException e1) {
			System.out.println("Error reading properties from '" + propsFileName + "'" + e1.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading properties from '" + propsFileName + "'" + e.getMessage());
		} finally {
			try {
				if(in!=null){
				in.close();
				}
			} catch (IOException e) {
				System.out.println("Error in closing file '" + propsFileName + "'" + e.getMessage());
			}
		}

		// create channel
		channel = createChannel();
		// insert or update counters to DB
*/
		// Initialize Hazel Cast 
		try {
			hazelcastInstance = Hazelcast.newHazelcastInstance(new FileSystemXmlConfig(hazelcastConfig));
		} catch (Exception exp) {
			logger.info(exp.getMessage() + ". Using default config instead.");
			hazelcastInstance = Hazelcast.newHazelcastInstance(new ClasspathXmlConfig(hazelcastConfig));
		}
		
		/*
		 * if(null != counterService){ //UpdateCounterThread updateCounter = new
		 * UpdateCounterThread(counterService,
		 * props.getProperty("UPDATE_COUNTERS_SLEEPTIME"));
		 * 
		 * UpdateCounterThread updateCounter = new
		 * UpdateCounterThread(counterService);
		 * updateCounter.setName("UpdateCounterThread");
		 * if(!updateCounter.isAlive()){ updateCounter.start(); } }
		 */
		
	 	// REFGRID , REF_RG and RBM EXTERNAL ACTION QUEUE
		String dc = helper.findMyDataCenterSuffix();
		this.getHazelcastInstance().getQueue("REF_GRID_REQUEST_"+dc).addItemListener(refGridListener, true);
		this.getHazelcastInstance().getQueue("REF_RG_CONV_"+dc).addItemListener(refGridConvListener,true);
		this.getHazelcastInstance().getQueue("RBM_EXTERNAL_ACTION_QUEUE_"+dc).addItemListener(rbmListener, true);

		logger.info("Created queues : {}, {}, {}","REF_GRID_REQUEST_"+dc,"REF_RG_CONV_"+dc,"RBM_EXTERNAL_ACTION_QUEUE_"+dc);
	}

	@Bean
	public  HazelcastInstance getHazelcastInstance() {
		return hazelcastInstance;
	}

	public static JChannel createChannel() {

		// single channel is created
		if (channel != null)
			return channel;
		else {

			String propsPath = props.getProperty(PROP_TCP_PATH);
			int i = 1;
			if (i > 0)
				propsPath = propsPath.replaceAll("tcp.xml", "tcp" + i + ".xml");
			// replace with system props
			propsPath = StrSubstitutor.replaceSystemProperties(propsPath);
			logger.debug("tcp path is :" + propsPath);

			final String clusterName = props.getProperty(PROP_CLUSTER_NAME);
			logger.debug("clusterName is :" + clusterName);

			if (propsPath != null && clusterName != null) {
				try {
					channel = new JChannel(propsPath);
					channel.connect(clusterName);

					// Register the channel
					MBeanServer server = Util.getMBeanServer();
					if (server != null) {
						try {
							JmxConfigurator.registerChannel(channel, Util.getMBeanServer(), "MCS_CHANNEL_LOCK",
									channel.getClusterName(), true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						Util.registerChannel((JChannel) channel, "jgroups");
					}

					// Initialize the JGroup Lock Service and Counter Service by
					// setting channel
					lock_service = new LockService(channel);
					// counterService = new CounterService(channel);

				} catch (Exception e) {
					logger.error("Exception is " + e.getMessage());
				}
			}
			return channel;
		}
	}

	public void acquireLock(String lockName) {

		if (lockName != null || lockName.trim().equals("")) {

			long lt1 = System.currentTimeMillis();
			Lock lock = lock_service.getLock(lockName);
			lock.lock();
			long lt2 = System.currentTimeMillis();
			logger.debug("Lock (" + lockName + ") acquired, total time is " + (lt2 - lt1));

		} else {
			return; // throw new Exception("Invalid Lock Name.");
		}
	}

	public void releaseLock(String lockName) {

		if (lockName != null || lockName.trim().equals("")) {

			long lt1 = System.currentTimeMillis();
			Lock lock = lock_service.getLock(lockName);
			if (lock != null) {
				lock.unlock();
				long lt2 = System.currentTimeMillis();
				logger.debug("Lock (" + lockName + ") released, total time is " + (lt2 - lt1));
			}
		} else {
			return; // throw new Exception("Invalid Lock Name.");
		}
	}
	

	
}
