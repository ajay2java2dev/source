package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.vo.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateSpeedTierOffer {
	public static void main(String[] args) {
		createSpeedTierOffer(PlabConstants.DEFAULT_CREATED_BY_CPI);
	}

	public static void createSpeedTierOffer(String userName) {
		try {
			
			// Night Surfer
			Offer offer= new Offer();
			offer.setName("Speed Tier");
			offer.setSimplePages(new ArrayList<SimplePage>());
			offer.setMdnOptionEnabled(true);
			
			addStartEndDate(offer);
			addTCs(offer);
			addOfferDetail(offer);
			addOfferOverview(offer) ;
			
			addSurvey(offer);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer= mapper.writerWithView(Views.MobileFirstView.class);
			String jsonProduct= writer.writeValueAsString(offer);
			
			System.out.println(jsonProduct);

			Map<String, String> params = new HashMap<String, String>();
			params.put("OfferObject", jsonProduct);
			// Invoke VISP web service to create the service
			String offerId= RestClient.postRequest(CreateOffers.HostUrl+"/offer", params);
			
			Service nightSurferService1= CreateProduct.createSpeedTierService("fast", 20000000, 50,userName,offerId);
			
			CreateProduct.saveService(nightSurferService1);


			params = new HashMap<String, String>();
			params.put("offerId", offerId);
			params.put("serviceId", nightSurferService1.getServiceId().toString());			
			offerId= RestClient.postRequest(CreateOffers.HostUrl+"/offer/service", params);					

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void addSurvey(Offer offer) {
		List<Survey> surveys= new ArrayList<Survey>();
	
		Survey entrySurvey= new Survey();
		entrySurvey.setOffer(offer);
		entrySurvey.setSurveyType("Enrollement");
		entrySurvey.setSurveyname("Survey 1");
		entrySurvey.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
		entrySurvey.setDateCreated(new Date());

		List<SurveyQuestion> questions= new ArrayList<SurveyQuestion>();
		questions.add(Surveys.createSurveyQuestion("How many hours you spend surfing at night",1,"10 hoursk","1 hour","more than 24 hour"));
				
		questions.add(Surveys.createSurveyQuestion("If you were to go any where..where would go!",1,"park","beach","river","mountain"));
		entrySurvey.setQuestions(questions);
		surveys.add(entrySurvey);

		Survey exitSurvey= new Survey();
		exitSurvey.setOffer(offer);
		exitSurvey.setSurveyType("Exit");
		exitSurvey.setSurveyname("Did you like the pilot!");
		exitSurvey.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
		exitSurvey.setDateCreated(new Date());

		questions= new ArrayList<SurveyQuestion>();
		questions.add(Surveys.createSurveyQuestion("How much would you pay for 1 G of data",1,"100$","200$","1$"));
		
		questions.add(Surveys.createSurveyQuestion("If you were to go any where..where would go!",1,"park","beach","river","mountain"));
		exitSurvey.setQuestions(questions);
		surveys.add(exitSurvey);
		
		offer.setSurveys(surveys);
		
	}

	private static void addOfferDetail(Offer offer) {
		SimplePage offerDetail= new SimplePage();
		offerDetail.setType("OfferDetail");
		offerDetail.setHeading("Unlimited at your own speed");
		offerDetail.setHeadingImgUrl("http://Speed Tier detail URL");
		offerDetail.setBodyImgUrl("Here are the details");
		offerDetail.setBody("Basically, with this property you can set which sides of your view can be extended to cover the whole screen. Imagine that you push a UIViewController into a UINavigationController, when the view of that view controller is laid out. it will start where the navigation bar ends, but this property will set which side of the view (top, left, bottom, right) can be extended to fill the whole screen.");
		offerDetail.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
		offerDetail.setDateCreated(new Date());
		offer.getSimplePages().add(offerDetail);
	}

	private static void addOfferOverview(Offer offer) {
		SimplePage offerDetail= new SimplePage();
		offerDetail.setType("OfferOverview");
		offerDetail.setHeading("Unlimited at your own speed");
		offerDetail.setHeadingImgUrl("http://Speed Tier detail URL");
		offerDetail.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
		offerDetail.setDateCreated(new Date());
		offer.getSimplePages().add(offerDetail);
	}
	
	private static void addStartEndDate(Offer offer) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("08/11/2016");
		offer.setStartDate(date);
		//offer.setEndDate(PricingLabUtility.getDefaultEndTimeStamp());
		offer.setMdnOptionEnabled(true);
		offer.setDateCreated(new Date());
		offer.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
		offer.setTotalInvitedCust(0);
		offer.setTotalActiveCust(0);
		offer.setMdnOptionEnabled(true);
		offer.setMdnSelectOptional(true);
		offer.setState("deployed");
		date = formatter.parse("08/11/2017");
		offer.setEndDate(date);
	}

	private static void addTCs(Offer offer) {
		SimplePage termsAndConditions= new SimplePage();
		termsAndConditions.setType("Terms&Consditions");
		termsAndConditions.setHeading("Terms and Conditions");
		termsAndConditions.setHeadingImgUrl("http://Night Surfer Terms &Conditions URL");
		termsAndConditions.setBody("<font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"\n" +
				"</font><p style=\"margin: 0in 0in 10pt;\"><font face=\"Calibri\"><font size=\"3\"><font color=\"#000000\">Your participation in Beaker Pilot program and use of the “Select\n" +
				"Your Speed” Price Plan is subject to terms and conditions listed below.<span style=\"mso-spacerun: yes;\">  </span></font></font><font color=\"#000000\" size=\"3\">Please read and review before indicating your\n" +
				"acceptance of the terms and conditions by clicking “Accept”.</font></font></p><font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"\n" +
				"</font><p style=\"margin: 0in 0in 10pt;\"><font color=\"#000000\" face=\"Calibri\" size=\"3\">The Beaker program requires you to manage your price plan\n" +
				"and “Select Your Speed” Price Plan via Beaker Mobile App only. You will not\n" +
				"have “My Verizon” online access during trial period. However, customer support\n" +
				"over phone is also available to you. Verizon reserves the right to end the\n" +
				"offer at any point. </font></p><font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"\n" +
				"</font><p style=\"margin: 0in 0in 10pt;\"><font face=\"Calibri\"><font size=\"3\"><font color=\"#000000\">You are required to successfully complete the Beaker Program\n" +
				"signup process and purchase “Select Your Speed” Price Plan. All the lines in\n" +
				"your account should participate in the trail. Smart phones only <span style=\"mso-spacerun: yes;\"> </span></font></font><font color=\"#000000\" size=\"3\">can patriciate in the trial. </font></font></p><font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"\n" +
				"</font><p style=\"margin: 0in 0in 10pt;\"><font color=\"#000000\" face=\"Calibri\" size=\"3\">You are required to toggle your device airplane mode on and\n" +
				"off in order to complete the sign up process. Failure to comply with this action\n" +
				"will result in inaccurate data usage calculation and billing. </font></p><font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"\n" +
				"</font><p style=\"margin: 0in 0in 10pt;\"><font color=\"#000000\" face=\"Calibri\" size=\"3\">The Next Bill Summary (NBS) displayed to you during sign up\n" +
				"process is only an estimation based on your plan or Price Plan changes that you\n" +
				"are trying to make. It may vary based on further changes to your plan or Price\n" +
				"Plan and taxes, surcharges and fees associated with it. </font></p><font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"\n" +
				"</font><p style=\"margin: 0in 0in 10pt;\"><font color=\"#000000\" face=\"Calibri\" size=\"3\">The trial period typically runs for three billing cycles,\n" +
				"you are expected to stay on the pilot program for full course. You can exit the\n" +
				"pilot at your will. However, premature exit may disqualify you from getting any\n" +
				"gifts/promos/incentives for your participation. Your will be presented with\n" +
				"entry and exit survey. Your participation in surveys may be mandatory to\n" +
				"receive gifts/promos/incentives. Verizon’s decision in this regard will be\n" +
				"final. </font></p><font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"\n" +
				"</font><p style=\"margin: 0in 0in 10pt;\"><font color=\"#000000\"><font face=\"Calibri\" size=\"3\">Upon completion of the pilot period for “Select Your Speed” Price\n" +
				"Plan, you will go back to you previous price plan. You will be able to retrieve\n" +
				"and view billing information for the time period you were in pilot. It will be\n" +
				"present to you in PDF format only. Billing information for the rest of the\n" +
				"period will be available online</font><span style='line-height: 115%; font-family: \"Arial\",\"sans-serif\"; font-size: 10pt;'>.</span></font><font color=\"#000000\" face=\"Times New Roman\" size=\"3\">\n" +
				"</font></p><p style=\"margin: 0in 0in 10pt;\"><font color=\"#000000\" face=\"Times New Roman\" size=\"3\"><br></font></p>");

		termsAndConditions.setHeading("<p align=\"center\"><font size=\"4\"><font size=\"5\"><font size=\"2\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"6\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"6\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"6\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"6\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"6\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"3\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"6\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"6\"><font color=\"#ce040b\" face=\"NHaasGroteskDSStd-75Bd\" size=\"5\">And here’s the fine print</font></font></font></font></font></font></font></font></font></font></font></font></p>");
		termsAndConditions.setType("Terms&Conditions");
		termsAndConditions.setFooter("<font face=\"Calibri\" size=\"2\">END OF TERMS AND CONDITIONS</font>");

		termsAndConditions.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
		termsAndConditions.setDateCreated(new Date());

		offer.getSimplePages().add(termsAndConditions);
		return ;
	}

}

