package com.vzwcorp.pricinglab.profile.vo;

import java.util.List;

public class CustomerProfile extends SubCustomer implements State {

	private List<AccountProfile> accounts;
	List<SubCustBlCycle> customerBillCycles;

}
