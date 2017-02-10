package com.vzwcorp.pricinglab.constants;

/**
 * Created by menonka on 6/17/2016.
 */
public enum ServiceStatus {
    SUBMITTED("submitted"),
    DEPLOYED("deployed"),
    DRAFT("draft");

    private String serviceState;

    ServiceStatus (String serviceStatus) {
        this.serviceState = serviceStatus;
    }

    public String getServiceStatus() {
        return serviceState;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceState = serviceStatus;
    }
}
