package com.vzwcorp.pricinglab.rest;

import java.util.ArrayList;
import java.util.List;

import com.vzwcorp.pricinglab.vo.SurveyQuestion;

public class Surveys {

	
public static SurveyQuestion createSurveyQuestion(String name, int maxSelected, String ... options) {
		
		SurveyQuestion  question= new SurveyQuestion();
		question.setSelectionType("Radio");
		question.setName(name);
		List<String> optionsList =new ArrayList<String>();
		for ( int i=0; i<options.length; i++)
			optionsList.add(options[i]);
		//question.setOptions(optionsList);
		question.setMaxSelectedOptions(maxSelected);
		return question;
	}
}
