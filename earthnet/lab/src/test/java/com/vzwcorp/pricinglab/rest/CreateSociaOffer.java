package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.vo.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateSociaOffer {
	public static void main(String[] args) {
		createSocialPlanOffer("amr");
	}

	static void createSocialPlanOffer(String userName) {
		try {
			
			// Social plan offer
			Offer offer= new Offer();
			offer.setName("Social Apps");
			offer.setSimplePages(new ArrayList<SimplePage>());
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
			String offerId= RestClient.postRequest(CreateOffers.HostUrl+"/offer", params);
			
			Service socialPlanService= CreateProduct.createSocialPlanService(userName);
			CreateProduct.saveService(socialPlanService);
			Service socialPlan2GService= CreateProduct.createSocialPlan2GService(userName);
			CreateProduct.saveService(socialPlan2GService);


			params = new HashMap<String, String>();
			params.put("offerId", offerId);
			params.put("serviceId", socialPlanService.getServiceId().toString());			
			offerId= RestClient.postRequest(CreateOffers.HostUrl+"/offer/service", params);					

			params = new HashMap<String, String>();
			params.put("offerId", offerId);
			params.put("serviceId", socialPlan2GService.getServiceId().toString());
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
		//entrySurvey.setSurveyTitle("surveyEnrolmentTitle1");
		List<SurveyQuestion> questions= new ArrayList<SurveyQuestion>();

		questions.add(Surveys.createSurveyQuestion("How many times did you win the lottery",1,"more than 5 times","3 times","only one time"));
		questions.add(Surveys.createSurveyQuestion("If you were to travel any where..where would go!", 1, "city#1", "city#2", "city#3"));
		entrySurvey.setQuestions(questions);
		surveys.add(entrySurvey);
		
		
		Survey exitSurvey= new Survey();
		exitSurvey.setOffer(offer);
		exitSurvey.setSurveyType("Exit");
		exitSurvey.setSurveyType("Did you like the pilot!");
		//exitSurvey.setSurveyTitle("surveyExitTitle1");
		questions= new ArrayList<SurveyQuestion>();
		questions.add(Surveys.createSurveyQuestion("How many movies you watch a week",1,"more than 10","5-10","1-5"," i don't watch movies!"));
		exitSurvey.setQuestions(questions);
		surveys.add(exitSurvey);
		
		offer.setSurveys(surveys);
		
	}

	private static void addOfferDetail(Offer offer) {
		HashSet<SimplePage> simpleDetailPages = new HashSet<SimplePage>();

		SimplePage offerDetail= new SimplePage();
		offerDetail.setType("OfferDetail");
		offerDetail.setHeading("2G  + Unlimited social apps bundle");
		offerDetail.setHeadingImgUrl("https://mobile-edev.vzw.com/dev03/MobileFirst/headerImg.png");
		offerDetail.setFooterImgUrl("https://mobile-edev.vzw.com/dev03/MobileFirst/footerImg.png");
		offerDetail.setBody("Here are the details");

		SimplePage offerDetail2 = new SimplePage();
		offerDetail2.setType("OfferDetail2");
		offerDetail2.setBody("Basically, with this property you can set which sides of your view can be extended to cover the whole screen. Imagine that you push a UIViewController into a UINavigationController, when the view of that view controller is laid out. it will start where the navigation bar ends, but this property will set which side of the view (top, left, bottom, right) can be extended to fill the whole screen.");
		simpleDetailPages.add(offerDetail2);
		//offerDetail.setSimplePageDetailSub(simpleDetailPages);

		offer.getSimplePages().add(offerDetail);
        offer.getSimplePages().add(offerDetail2);
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
		termsAndConditions.setHeading("And here's the find print");
        termsAndConditions.setSubHeading("Reveiw and accept our Terms and Conditions");
		termsAndConditions.setBody("<HTML><BODY><B>CUSTOMER AGREEMENT</B><BR/><BR/>YOUR USE OF MY VERIZON AND ANY MY VERIZON SERVICES IS SUBJECT TO THE TERMS AND CONDITIONS OF YOUR CUSTOMER AGREEMENT, INCLUDING THE DISPUTE RESOLUTION PROVISIONS.<BR/><BR/><B>PASSWORD/PERSONAL IDENTIFICATION NUMBER (PIN)</B><BR/><BR/>YOU ARE SOLELY RESPONSIBLE FOR MAINTAINING THE CONFIDENTIALITY OF YOUR PASSWORD AND/OR PIN CODE. IF YOUR PASSWORD OR PIN CODE IS LOST, STOLEN OR USED WITHOUT YOUR PERMISSION, CALL US IMMEDIATELY AT 1-800-922-0204.IF YOU DISCLOSE YOUR PASSWORD OR PIN TO A THIRD PARTY BILL PAYMENT VENDOR, VERIZON WIRELESS IS NOT RESPONSIBLE FOR THE ACCURACY AND TIMELINESS OF YOUR BILL PAYMENTS.<BR/>YOU CONSENT TO DELIVERY OF PIN CODES BY TEXT MESSAGE TO YOUR WIRELESS PHONE UNLESS VERIZON WIRELESS DETERMINES THAT YOUR WIRELESS PHONE IS NOT CAPABLE OF RECEIVING TEXT MESSAGES.<BR/><BR/><B>PAPER-FREE BILLING</B><BR/><BR/>YOU MAY ENROLL IN PAPERLESS BILLING THROUGH MY VERIZON, WHICH MEANS YOU WILL NO LONGER RECEIVE A PAPER BILL AND YOU ACCEPT THE PRESENTATION OF YOUR BILL ONLINE THROUGH MY VERIZON OR BY EMAIL.<BR/><BR/><B>TRADEMARKS</B> YOU MAY NOT USE ANY VERIZON WIRELESS TRADEMARKS OR SERVICE MARKS WITHOUT VERIZON WIRELESS'S PRIOR WRITTEN PERMISSION. FOR INFORMATION ABOUT USAGE OR LICENSING, SEE OUR <A HREF=\\\"HTTPS://WWW.VERIZONWIRELESS.COM/B2C/GLOBALTEXT?TEXTNAME=WEBSITE_USE&JSPNAME=FOOTER/WEBUSE.JSP\\\">WEBSITE USE TERMS AND CONDITIONS.</A><BR/><BR/><B>U.S EXPORT LAWS</B><BR/><BR/>YOUR USE OF VERIZON WIRELESS ONLINE SERVICES IS SUBJECT TO U.S. EXPORT CONTROL LAWS AND REGULATIONS. YOU REPRESENT THAT YOU ARE NOT A CITIZEN OF AN EMBARGOED COUNTRY OR PROHIBITED END USER UNDER APPLICABLE U.S. EXPORT AND ANTI-TERRORISM LAWS, REGULATIONS AND LISTS. YOU WILL NOT USE, EXPORT OR ALLOW A THIRD PARTY TO USE OR EXPORT VERIZON WIRELESS ONLINE SERVICES IN ANY MANNER THAT WOULD VIOLATE APPLICABLE LAW, INCLUDING BUT NOT LIMITED TO APPLICABLE EXPORT CONTROL LAWS AND REGULATIONS.\\B<B>PRIVACY</B><BR/><BR/>FOR INFORMATION ON PRIVACY, SEE OUR <A HREF=\\\"HTTP://WWW.VERIZON.COM/ABOUT/PRIVACY/PRIVACY-POLICY-SUMMARY\\\">PRIVACY POLICY.</A><BR/><BR/><B>WEBSITE USE TERMS AND CONDITIONS</B><BR/><BR/>YOUR USE OF MY VERIZON IS SUBJECT TO OUR <A HREF=\\\"HTTPS://WWW.VERIZONWIRELESS.COM/B2C/GLOBALTEXT?TEXTNAME=WEBSITE_USE&JSPNAME=FOOTER/WEBUSE.JSP\\\">WEBSITE USE TERMS AND CONDITIONS.</A><BR/><BR/><B>NO WARRANTIES</B><BR/><BR/>ANY VERIZON WIRELESS MATERIAL ON THIS SERVER IS PROVIDED \\\"AS IS\\\" AND \\\"AS AVAILABLE\\\" AND MAY INCLUDE TECHNICAL INACCURACIES OR TYPOGRAPHY ERROR. VERIZON WIRELESS MAKES NO GUARANTEE THAT COMMUNICATIONS OR TRANSACTIONS CONDUCTED ONLINE WILL BE ABSOLUTELY SECURE OR FREE FROM HARMFUL COMPONENTS. THERE MAY BE TIMES WHEN SERVICE IS UNAVAILABLE. VERIZON WIRELESS PROVIDES NO WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO WARRANTIES OF TITLE, NONINFRINGEMENT OR IMPLIED WARRANTIES OF MERCHANTABILITY OR FITNESS FOR A PARTCULAR PURPOSE. NO ADVICE OR INFORMATION GIVEN BY VERIZON WIRELESS, ITS AFFILIATES OR THEIR RESPECTIVE EMPLOYEES SHALL CREATE ANY WARRANTY HEREUNDER. VERIZON WIRELESS IS NOT RESPONSIBLE FOR ANY DAMAGES INCURRED, CONSEQUENTIAL OR OTHERWISE.</BODY></HTML>\"},\"");
		termsAndConditions.setHeadingImgUrl("http://Social Plan Terms&Conditions URL");
		offer.getSimplePages().add(termsAndConditions);
		return ;
	}

	
	
}
