package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "SURVEY")
public class Survey {

    @Id
    @Column(name = "SURVEY_ID")
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    @SequenceGenerator(name = "SURVEY_GEN", sequenceName = "SURVEY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SURVEY_GEN")
    private Long surveyId;

    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    @Column(name = "SURVEY_NAME",length = 32)
    private String surveyname;

    @Column(name = "SURVEY_TYPE", length = 32)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    private String surveyType;

    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SurveyQuestion> questions;

    @Transient
    @JsonView({Views.MobileFirstView.class})
    private Integer questionsUnansweredCount;

    @Transient
    @JsonView({Views.MobileFirstView.class})
    private Set<Long> questionsUnanswered;

    @Transient
    @JsonView({Views.MobileFirstView.class})
    private String surveyStatus;

    @JsonView(Views.InternalView.class)
    @ManyToOne
    @JoinColumn(name = "OFFER_ID")
    private Offer offer;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public String getSurveyname() {
        return surveyname;
    }

    public void setSurveyname(String surveyname) {
        this.surveyname = surveyname;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getQuestionsUnansweredCount() {
        int newCount = 0;
        questionsUnanswered  = new HashSet<Long>();

        for (SurveyQuestion question : questions) {
            boolean anyChoicesSelected = false;
            boolean anyAnwersAvailable = false;

            for (Choice choice : question.getChoice()) {
                if (choice.isSelected()) {
                    anyChoicesSelected  = true;
                    break;
                }
            }

            for (SurveyAnswer answer : question.getSurveyAnswers()) {
                if (answer.getCustSurveyAnswers()!=null && !answer.getCustSurveyAnswers().isEmpty()) {
                    anyAnwersAvailable = true;
                    break;
                }
            }

            if (!anyChoicesSelected && !anyAnwersAvailable) {
                newCount++;
                questionsUnanswered.add(question.getSelectionPageId());
            }
        }

        if (questions != null) {
            if (questions.size() == newCount) {
                this.setSurveyStatus(PlabConstants.SURVEY_NEW);
            } else if (newCount != 0 && questions.size() > newCount) {
                this.setSurveyStatus(PlabConstants.SURVEY_INCOMPLETE);
            } else {
                this.setSurveyStatus(PlabConstants.SURVEY_COMPLETE);
            }
        }

        questionsUnansweredCount = newCount;
        return questionsUnansweredCount;
    }

    public void setQuestionsUnansweredCount(Integer questionsUnansweredCount) {
        this.questionsUnansweredCount = questionsUnansweredCount;
    }

    public Set<Long> getQuestionsUnanswered() {
        return questionsUnanswered;
    }

    public void setQuestionsUnanswered(Set<Long> questionsUnanswered) {
        this.questionsUnanswered = questionsUnanswered;
    }

    public String getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(String surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

}
