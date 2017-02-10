package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * To store the bidirectional one-to-one mapping between the Choice's and OfferInstance.
 * An Entry here would mean that the particular choice was selected for the specific offer Instance.
 *
 * @author : Pricing-Lab
 * @since : June 20,2016
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "CHOICE_INSTANCE")
public class ChoiceInstance {

    @Id
    @Column(name = "CHOICE_INSTANCE_ID")
    @SequenceGenerator(name = "CHOICE_INSTANCE_GEN", sequenceName = "CHOICE_INSTANCE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHOICE_INSTANCE_GEN")
    @JsonView(Views.ManagedView.class)
    private int choiceInstanceId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonView({Views.ManagedView.class,Views.PlabAdminView.class})
    @JoinColumn(name = "CHOICE_ID")
    private Choice choice;

    @ManyToOne
    @JsonView({Views.ManagedView.class,Views.PlabAdminView.class})
    @JoinColumn(name = "OFFER_INSTANCE_ID")
    private OfferInstance offerInstance;

    @OneToMany(mappedBy = "choiceInstance", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonView(Views.ManagedView.class)
    private List<ChoiceInstanceHistory> choiceInstanceHistory;

    @JsonView({Views.MobileFirstView.class, Views.PlabAdminView.class})
    @Column(name = "CHOICE_INSTANCE_SELECTED")
    private Boolean selected;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView({Views.InternalView.class,Views.PlabAdminView.class})
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView({Views.InternalView.class})
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @Column(name = "END_DTM",nullable = false)
    @JsonView({Views.InternalView.class,Views.PlabAdminView.class})
    private Timestamp endTmstamp = PricingLabUtility.getDefaultEndTimeStamp();

    //Getters and Setters

    public int getChoiceInstanceId() {
        return choiceInstanceId;
    }

    public void setChoiceInstanceId(int choiceInstanceId) {
        this.choiceInstanceId = choiceInstanceId;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    public OfferInstance getOfferInstance() {
        return offerInstance;
    }

    public void setOfferInstance(OfferInstance offerInstance) {
        this.offerInstance = offerInstance;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<ChoiceInstanceHistory> getChoiceInstanceHistory() {
		return choiceInstanceHistory;
	}

	public void setChoiceInstanceHistory(List<ChoiceInstanceHistory> choiceInstanceHistory) {
		this.choiceInstanceHistory = choiceInstanceHistory;
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
