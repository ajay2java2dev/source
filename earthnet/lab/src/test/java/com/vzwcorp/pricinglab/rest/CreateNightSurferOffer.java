package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.vo.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateNightSurferOffer {
	public static void main(String[] args) {
		createNightSurferOffer("amr");
	}

	static void createNightSurferOffer(String userName) {
		try {
			
			// Night Surfer
			Offer offer= new Offer();
			offer.setName("Night Surfer");
			offer.setSimplePages(new ArrayList<SimplePage>());
			offer.setMdnOptionEnabled(true);
			addTCs(offer);
			
			addStartEndDate(offer);
			
			addOfferDetail(offer);
			
			addSurvey(offer);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer= mapper.writerWithView(Views.MobileFirstView.class);
			String jsonProduct= writer.writeValueAsString(offer);
			
			System.out.println(jsonProduct);

			Map<String, String> params = new HashMap<String, String>();
			params.put("OfferObject", jsonProduct);
			// Invoke VISP web service to create the service
			String offerId= RestClient.postRequest(CreateOffers.HostUrl+"//offer", params);
			
			Service nightSurferService= CreateProduct.createNightSurferService(userName);
			CreateProduct.saveService(nightSurferService);


			params = new HashMap<String, String>();
			params.put("offerId", offerId);
			params.put("serviceId", nightSurferService.getServiceId().toString());			
			RestClient.postRequest(CreateOffers.HostUrl+"//offer/service", params);					

			
			System.out.println(offerId);
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
		List<SurveyQuestion> questions= new ArrayList<SurveyQuestion>();
		questions.add(Surveys.createSurveyQuestion("How many hours you spend surfing at night",1,"10 hoursk","1 hour","more than 24 hour"));		
		questions.add(Surveys.createSurveyQuestion("If you were to go any where..where would go!",1,"park","beach","river","mountain"));

		entrySurvey.setQuestions(questions);
		surveys.add(entrySurvey);
		
		Survey exitSurvey= new Survey();
		exitSurvey.setOffer(offer);
		exitSurvey.setSurveyType("Exit");
		exitSurvey.setSurveyname("Did you like the pilot!");
		questions= new ArrayList<SurveyQuestion>();

		questions.add(Surveys.createSurveyQuestion("If you were to go any where..where would go!",2,"park","beach","river","mountain"));
		exitSurvey.setQuestions(questions);
		surveys.add(exitSurvey);
		
		offer.setSurveys(surveys);
		
	}

	

	private static void addOfferDetail(Offer offer) {
		SimplePage offerDetail= new SimplePage();
		offerDetail.setType("OfferDetail");
		offerDetail.setHeading("Unlimited surfing at night");
		offerDetail.setHeadingImgUrl("http://Night surfer detail URL");
		offerDetail.setBodyImgUrl("Here are the details");
		offerDetail.setBody("Basically, with this property you can set which sides of your view can be extended to cover the whole screen. Imagine that you push a UIViewController into a UINavigationController, when the view of that view controller is laid out. it will start where the navigation bar ends, but this property will set which side of the view (top, left, bottom, right) can be extended to fill the whole screen.");
		offer.getSimplePages().add(offerDetail);
	}

	private static void addStartEndDate(Offer offer) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("08/11/2016");
		offer.setStartDate(date);
		
		date = formatter.parse("08/11/2017");
		offer.setEndDate(date);
	}

	private static void addTCs(Offer offer) {
		SimplePage termsAndConditions= new SimplePage();
		termsAndConditions.setType("Terms&Consditions");
		termsAndConditions.setHeading("Terms and Conditions");
		termsAndConditions.setHeadingImgUrl("http://Night Surfer Terms &Conditions URL");
		offer.getSimplePages().add(termsAndConditions);
		return ;
	}

	
	
}
