package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorValue("SURVEY")
public class SurveyQuestion extends SelectionPage{

	@JsonView(Views.InternalView.class)
	@ManyToOne
	@JoinColumn(name = "SURVEY_ID")
	public Survey survey;

	@Column(name = "DISCRIMINATOR_DESC",length = 50)
	@JsonView(Views.MobileFirstView.class)
	private String name;

	@JsonView({Views.InternalView.class,Views.MobileFirstView.class})
	@OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<SurveyAnswer> surveyAnswers;

	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	@OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<Choice> choice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Survey getSurvey() {
		return survey;
	}

	public List<Choice> getChoice() {
		return choice;
	}

	public void setChoice(List<Choice> choice) {
		this.choice = choice;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public List<SurveyAnswer> getSurveyAnswers() {
		return surveyAnswers;
	}

	public void setSurveyAnswers(List<SurveyAnswer> surveyAnswers) {
		this.surveyAnswers = surveyAnswers;
	}

}
