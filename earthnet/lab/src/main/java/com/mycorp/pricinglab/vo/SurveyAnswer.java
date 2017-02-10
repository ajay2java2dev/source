package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "SURVEY_ANSWER")
public class SurveyAnswer {

    @Id
    @Column(name = "SURVEY_ANSWER_ID")
    @SequenceGenerator(name = "SURVEY_ANSWER_GEN", sequenceName = "SURVEY_ANSWER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SURVEY_ANSWER_GEN")
    private Long surveyAnswerId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "VERIZON_ENTITY_ID")
    @JsonView({Views.InternalView.class})
    public VerizonEntity verizonEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "SURVEY_QUESTION_ID")
    @JsonView({Views.InternalView.class,Views.MobileFirstView.class})
    @JsonBackReference
    public SurveyQuestion surveyQuestion;

    @ElementCollection
    @CollectionTable(name = "CUST_SURVEY_RESPONSES", joinColumns = @JoinColumn(name = "SURVEY_ANSWER_ID"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"SURVEY_ANSWER_ID","ANSWERS"}))
    private Set<CustSurveyAnswers> custSurveyAnswers = new HashSet<CustSurveyAnswers>(); //List<boolean>  json should match options in Survey

    @Column(name = "CREATE_DTM")
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy= PlabConstants.DEFAULT_CREATED_BY_USER;

    @Column(name = "END_DTM",nullable = true)
    @JsonView(Views.InternalView.class)
    private Timestamp endTmstamp = PricingLabUtility.getDefaultEndTimeStamp();

    //Getters and Setters
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getSurveyAnswerId() {
        return surveyAnswerId;
    }

    public void setSurveyAnswerId(Long surveyAnswerId) {
        this.surveyAnswerId = surveyAnswerId;
    }

    public VerizonEntity getVerizonEntity() {
        return verizonEntity;
    }

    public void setVerizonEntity(VerizonEntity verizonEntity) {
        this.verizonEntity = verizonEntity;
    }

    public Set<CustSurveyAnswers> getCustSurveyAnswers() {
        return custSurveyAnswers;
    }

    public void setCustSurveyAnswers(Set<CustSurveyAnswers> custSurveyAnswers) {
        this.custSurveyAnswers = custSurveyAnswers;
    }

    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getEndTmstamp() {
        return endTmstamp;
    }

    public void setEndTmstamp(Timestamp endTmstamp) {
        this.endTmstamp = endTmstamp;
    }


}
