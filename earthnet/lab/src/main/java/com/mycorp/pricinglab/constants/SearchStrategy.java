package com.vzwcorp.pricinglab.constants;

/**
 * Created by menonka on 11/21/2016.
 */
public enum SearchStrategy {

    FIND_ACTIVE_CUSTOMER_DETAILS_FROM_GRID("grid"),
    FIND_ACTIVE_CUSTOMER_DETAILS_FROM_DB("db");

    private String searchLocation;

    SearchStrategy(String searchLocation) {
        this.searchLocation = searchLocation;
    }

    public String getSearchLocation() {
        return this.searchLocation;
    }

    public void setSearchLocation(String searchLocation) {
        this.searchLocation = searchLocation;
    }
}
