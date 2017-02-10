package com.vzwcorp.pricinglab.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Id;

import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefRgPlabConvRepository;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCust;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefRgPlabConv;
import com.vzwcorp.pricinglab.rest.controller.ServiceController;
import com.vzwcorp.pricinglab.service.DistributedService;

@Component
public class RefGridPlabConvParam implements Serializable, ItemListener<Object> {
	
	@Autowired
	DistributedService distributedService;
	
	@Autowired
	RefRgPlabConvRepository refRgPlabConvRepository;
	
	@Autowired
	ServiceController serviceController;
	
	static Logger logger = LogManager.getLogger(RefGridPlabConvParam.class);
	
	public RefGridPlabConvParam(String apnName, Long apnRtGrpNoMin, Long apnRtGrpNoMax, String dataCatCd) {
		this.apnName= apnName;
		this.apnRtGrpNoMin= apnRtGrpNoMin;
		this.apnRtGrpNoMax=apnRtGrpNoMax;
		this.dataCatCd= dataCatCd;
	}
	public RefGridPlabConvParam(Long apnRtGrpNoMinMax) {	
		this.apnRtGrpNoMax=apnRtGrpNoMinMax;
	}
	

	public RefGridPlabConvParam() {
		// TODO Auto-generated constructor stub
	}

	
    private String apnName;

    private Long apnRtGrpNoMin;

    private Long apnRtGrpNoMax;

	private String dataCatCd;	
    
    public String getApnName() {
		return apnName;
	}

	public void setApnName(String apnName) {
		this.apnName = apnName;
	}

	public Long getApnRtGrpNoMin() {
		return apnRtGrpNoMin;
	}

	public void setApnRtGrpNoMin(Long apnRtGrpNoMin) {
		this.apnRtGrpNoMin = apnRtGrpNoMin;
	}

	public Long getApnRtGrpNoMax() {
		return apnRtGrpNoMax;
	}

	public void setApnRtGrpNoMax(Long apnRtGrpNoMax) {
		this.apnRtGrpNoMax = apnRtGrpNoMax;
	}

	public String getDataCatCd() {
		return dataCatCd;
	}

	public void setDataCatCd(String dataCatCd) {
		this.dataCatCd = dataCatCd;
	}


	@Override
	public void itemAdded (ItemEvent<Object> itemEvent) {
		RefGridPlabConvParam refGridPlabConvParam = (RefGridPlabConvParam) itemEvent.getItem();

		distributedService.getHazelcastInstance().getLock("REF_RG_CONV").lock(10, TimeUnit.SECONDS );
		try {
			logger.debug("Message received = " + refGridPlabConvParam.getApnRtGrpNoMax());

			RefRgPlabConv refRgPlabConv = refRgPlabConvRepository.findOne(refGridPlabConvParam.getApnRtGrpNoMax());

			if (refRgPlabConv != null) {
				logger.debug("No update to refRgPlabConv for mdn :  "+refGridPlabConvParam.getApnRtGrpNoMax()+" since refRgPlabConv already exists");
			} else {
				serviceController.createRefrgPlabConv(refGridPlabConvParam.getApnRtGrpNoMax());
				logger.debug("Refrgplabconv updated with "+refGridPlabConvParam.getApnRtGrpNoMax());
			}
		} catch (Exception ex) {
			logger.error("Error in refrdconv onMessage() :",ex.getMessage(),ex);
		} finally {
            distributedService.getHazelcastInstance().getLock("REF_RG_CONV").unlock();
        }
	}

	@Override
	public void itemRemoved (ItemEvent<Object> itemEvent) {
		throw new UnsupportedOperationException("Item Remove operation in RefGridParam Listener is currently not supported");
	}
	
	/*public void onMessage(Message<Object> message) {
		RefGridPlabConvParam refGridPlabConvParam = (RefGridPlabConvParam) message.getMessageObject();
		distributedService.getHazelcastInstance().getLock("REF_RG_CONV").lock(10, TimeUnit.SECONDS );
		try {
			logger.debug("Message received = " + refGridPlabConvParam.getApnRtGrpNoMax());

			RefRgPlabConv refRgPlabConv = refRgPlabConvRepository.findOne(refGridPlabConvParam.getApnRtGrpNoMax());

			if (refRgPlabConv != null) {
				logger.debug("No update to refRgPlabConv for mdn :  "+refGridPlabConvParam.getApnRtGrpNoMax()+" since refRgPlabConv already exists");
			} else {
				serviceController.createRefrgPlabConv(refGridPlabConvParam.getApnRtGrpNoMax());
				logger.debug("Refrgplabconv updated with "+refGridPlabConvParam.getApnRtGrpNoMax());
			}
		} catch (Exception e) {
			logger.error("Error in refrdconv onMessage() :",e.getMessage(),e);
		}

		distributedService.getHazelcastInstance().getLock("REF_RG_CONV").unlock();
	}*/


}
