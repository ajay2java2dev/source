package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.*;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "CHOICE")
public class Choice {

    @Id
    @Column(name = "CHOICE_ID")
    @SequenceGenerator(name = "CHOICE_GEN", sequenceName = "CHOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHOICE_GEN")
    private Long id;

    @Column(name = "CHOICE_TITLE",length = 50)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class,Views.ManagedView.class,Views.PlabAdminView.class})
    private String title;

    @Column(name="CHOICE_SELECTED")
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    private boolean selected;

    @Column(name="CHOICE_COLOR_CODE",length = 10)
    @JsonView({Views.MobileFirstView.class})
    private String colorCode;

    @Column(name="URL")
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    private String url;
	
    @JsonView({Views.InternalView.class})
    @OneToMany(mappedBy = "choice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChoiceInstance> choice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SURVEY_SELECTION_PAGE_ID")
    @JsonView(Views.InternalView.class)
    private SurveyQuestion surveyQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_SELECTION_PAGE_ID")
    @JsonView(Views.InternalView.class)
    private ServiceQuestion serviceQuestion;

    @Transient
    @JsonView(Views.MobileFirstView.class)
    private BigDecimal chargePerDay;

    @Column(name = "DEFAULT_CHOICE",nullable = false)
    @JsonView(Views.InternalView.class)
    @JsonIgnore
    private Boolean defaultChoice = false;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    public ServiceQuestion getServiceQuestion() {
        return serviceQuestion;
    }

    public void setServiceQuestion(ServiceQuestion serviceQuestion) {
        this.serviceQuestion = serviceQuestion;
    }

    public Boolean getDefaultChoice() {
        return defaultChoice;
    }

    public void setDefaultChoice(Boolean defaultChoice) {
        this.defaultChoice = defaultChoice;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public BigDecimal getChargePerDay() {
        return chargePerDay;
    }

    public void setChargePerDay(BigDecimal chargePerDay) {
        this.chargePerDay = chargePerDay;
    }

    public List<ChoiceInstance> getChoice() {
        return choice;
    }

    public void setChoice(List<ChoiceInstance> choice) {
        this.choice = choice;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
