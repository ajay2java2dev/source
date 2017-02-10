package com.vzwcorp.pricinglab.constants;

/**
 * Created by menonka on 6/9/2016.
 */
public enum OfferStatus {
    SUBMITTED("submitted"),
    DEPLOYED("deployed"),
    DRAFT("draft");

    private String offerState;

    OfferStatus (String serviceStatus) {
        this.offerState = serviceStatus;
    }

    public String getOfferState() {
        return offerState;
    }

    public void setOfferState(String offerState) {
        this.offerState = offerState;
    }
}
