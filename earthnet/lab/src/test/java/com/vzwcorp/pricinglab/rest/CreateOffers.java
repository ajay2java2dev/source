package com.vzwcorp.pricinglab.rest;

import com.vzwcorp.pricinglab.constants.PlabConstants;

public class CreateOffers {

	//public static String HostUrl = "http://jitrdev3.north.vzwcorp.com/pricinglab/"; // "http://tblercon03advvg.tdc.vzwcorp.com:8090/";
	public static String HostUrl = "http://localhost:8080";

	public static void main(String[] args) {
		String user = PlabConstants.DEFAULT_CREATED_BY_CPI;
		if (args.length > 0)
			user = args[0];
		CreateApplication.createApplications();
		CreateSpeedTierOffer.createSpeedTierOffer(user);
		CreateNightSurferOffer.createNightSurferOffer(user);
		CreateSociaOffer.createSocialPlanOffer(user);

	}

}
