package com.vzwcorp.pricinglab.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import com.vzwcorp.pricinglab.helper.ProductHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefGridPlabCustRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.PlabUsageRepository;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCust;
import com.vzwcorp.pricinglab.rest.controller.ServiceController;
import com.vzwcorp.pricinglab.service.DistributedService;
import com.vzwcorp.pricinglab.service.ProductManager;

@Component
public class RefGridParam implements Serializable, ItemListener<Object> {

	@Autowired
	DistributedService distributedService;

	@Autowired
	RefGridPlabCustRepository refGridPlabCustRepository;
	
	@Autowired
	ProductManager productManager;

	@Autowired
	ProductHelper helper;
	
	static Logger logger = LogManager.getLogger(RefGridParam.class);
	
	public RefGridParam(Long customerId, Long acctNum, String mdn, Long indicator, Timestamp mtneffDate,Date billCycleEndDt) {
		this.customerId= customerId;
		this.acctNum= acctNum;
		this.mdn=mdn;
		this.indicator= indicator;
		this.mtneffDate= mtneffDate;
		this.billCycleEndDt= billCycleEndDt;
	}

	public RefGridParam() {
		// TODO Auto-generated constructor stub
	}

	private Long customerId;
	private Long acctNum;
	private String mdn;
	private Long indicator;
	private Timestamp mtneffDate;
	private Date billCycleEndDt;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(Long acctNum) {
		this.acctNum = acctNum;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public Long getIndicator() {
		return indicator;
	}

	public void setIndicator(Long indicator) {
		this.indicator = indicator;
	}

	public Timestamp getMtneffDate() {
		return mtneffDate;
	}

	public void setMtneffDate(Timestamp mtneffDate) {
		this.mtneffDate = mtneffDate;
	}

	public Date getBillCycleEndDt() {
		return billCycleEndDt;
	}

	public void setBillCycleEndDt(Date billCycleEndDt) {
		this.billCycleEndDt = billCycleEndDt;
	}

	@Override
	public void itemAdded (ItemEvent<Object> itemEvent) {

		distributedService.getHazelcastInstance().getLock("REF_GRID_REQUEST_LOCK").lock(10, TimeUnit.SECONDS );
		try {
			String dc = helper.findMyDataCenterSuffix();
			Object object = distributedService.getHazelcastInstance().getQueue("REF_GRID_REQUEST_"+dc).poll();
			if(object!=null) {
				RefGridPlabCust refGridParam = (RefGridPlabCust) object;
				while (refGridParam != null) {
					logger.debug("RefGridParam : Message received = " + refGridParam.getMdn());

					RefGridPlabCust refGridPlabCust = refGridPlabCustRepository.findByCustIdNoAndAcctNoAndMdn(refGridParam.getCustIdNo(), refGridParam.getAcctNo(), refGridParam.getMdn());

					if (refGridPlabCust != null && (refGridPlabCust.getModifiedDate()!=null && refGridParam.getModifiedDate()!=null
                            && refGridPlabCust.getModifiedDate().equals(refGridParam.getModifiedDate()))) {
						logger.debug("No update to refgrid for mdn :  " + refGridParam.getMdn() + " since the indicator is already synchronized");
					} else {
						if (refGridPlabCust != null && (refGridPlabCust.getModifiedDate()!=null && refGridParam.getModifiedDate()!=null
                                && refGridPlabCust.getModifiedDate().before(refGridParam.getModifiedDate()))) {
							productManager.DBcreateOrUpdateRefGrid(refGridParam);
							logger.debug("Sucessfully updated indicator for mdn : " + refGridParam.getMdn());
						}
					}
					Object objectNew = distributedService.getHazelcastInstance().getQueue("REF_GRID_REQUEST_"+dc).poll();
					if(objectNew!=null) {
						refGridParam = (RefGridPlabCust) objectNew;
					} else {
                        refGridParam = null;
                    }
				}
			}
		} catch (Exception ex) {
            logger.error("Exception in refGridParam Listener -> Item Added ()",ex.getMessage(),ex);
		} finally {
            distributedService.getHazelcastInstance().getLock("REF_GRID_REQUEST_LOCK").unlock();
        }
	}

	@Override
	public void itemRemoved (ItemEvent<Object> itemEvent) {
		if (itemEvent!=null) {
			Object item = itemEvent.getItem();
			if (item!=null) {
				if (item instanceof RefGridPlabCust) {
					RefGridPlabCust ref = (RefGridPlabCust)item;
					if (ref!=null) {
						logger.info("Item Removed from queue. RefGrid Customer ID : {} , account : {}, mdn : {} ", ref.getCustIdNo(),ref.getAcctNo(),ref.getMdn());
					}
				} else {
					logger.info("Item Removed from queue : " + item.toString());
				}
			}
		}
		//throw new UnsupportedOperationException("Item Remove operation in RefGridParam Listener is currently not supported");
	}

	/*@Override
	public void onMessage(Message<Object> message) {
		RefGridPlabCust refGridParam = (RefGridPlabCust) message.getMessageObject();
		distributedService.getHazelcastInstance().getLock("REF_GRID_REQUEST_LOCK").lock(10, TimeUnit.SECONDS );
		try {
			logger.debug("Message received = " + refGridParam.getMdn());

			RefGridPlabCust refGridPlabCust = refGridPlabCustRepository.findByCustIdNoAndAcctNoAndMdn(refGridParam.getCustIdNo(), refGridParam.getAcctNo(), refGridParam.getMdn());

			if (refGridPlabCust != null && ( refGridPlabCust.getModifiedDate().equals(refGridParam.getModifiedDate()))) {
				logger.debug("No update to refgrid for mdn :  "+refGridParam.getMdn()+" since the indicator is already synchronized");
			} else {
				if (refGridPlabCust != null && ( refGridPlabCust.getModifiedDate().before(refGridParam.getModifiedDate()))) {
					productManager.DBcreateOrUpdateRefGrid(refGridParam );
					logger.debug("Sucessfully updated indicator for mdn : "+refGridParam.getMdn());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		distributedService.getHazelcastInstance().getLock("REF_GRID_REQUEST_LOCK").unlock();
	}*/
}
