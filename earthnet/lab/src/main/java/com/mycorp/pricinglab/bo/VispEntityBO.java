package com.vzwcorp.pricinglab.bo;

import com.vzwcorp.pricinglab.vo.*;
import com.vzwcorp.pricinglab.vo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by menonka on 9/6/2016.
 */

@Component
public class VispEntityBO {

    byte operation = 0;
    String mdn,spoId;

    //vo
    Offer offer;
    Service service;
    PlabCust cust;


    //instances
    OfferInstance instance;
    ServiceInstance serviceInstance;
    ChoiceInstance choiceInstance;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OfferInstanceRepository offerInstanceRepository;

    @Autowired
    ServiceInstanceRepository serviceInstanceRepository;

    @Autowired
    VerizonEntityRepository verizonEntityRepository;

    //functions
    public ServiceInstance getLatestServiceInstance () {
        VerizonEntity entity = verizonEntityRepository.findByMdn(getCust().getMdn());
        ServiceInstance serviceInstance = serviceInstanceRepository.findLatestByEntityIdAndSpoId(entity.getVerizonEntityId(),getSpoId());
        return serviceInstance;
    }

    public ServiceInstance getLatestDefaultServiceInstance () {
        VerizonEntity entity = verizonEntityRepository.findByMdn(getCust().getMdn());
        ServiceInstance serviceInstance = serviceInstanceRepository.findLatestDefaultByEntityId(entity.getVerizonEntityId());
        return serviceInstance;
    }

    //no constructor required.

    //getters and setters;
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public PlabCust getCust() {
        return cust;
    }

    public void setCust(PlabCust cust) {
        this.cust = cust;
    }

    public OfferInstance getInstance() {
        return instance;
    }

    public void setInstance(OfferInstance instance) {
        this.instance = instance;
    }

    public ServiceInstance getServiceInstance() {
        return serviceInstance;
    }

    public void setServiceInstance(ServiceInstance serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

    public ChoiceInstance getChoiceInstance() {
        return choiceInstance;
    }

    public void setChoiceInstance(ChoiceInstance choiceInstance) {
        this.choiceInstance = choiceInstance;
    }

    public byte getOperation() {
        return operation;
    }

    public void setOperation(byte operation) {
        this.operation = operation;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public String getSpoId() {
        return spoId;
    }

    public void setSpoId(String spoId) {
        this.spoId = spoId;
    }
}
