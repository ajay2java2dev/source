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


/**
 * @author : Pricing-Lab
 * To store the history of one-to-one mapping between the Choice's and OfferInstance.
 * An Entry here would mean that the particular choice was selected for the specific offer Instance at some point in time.
 */
@Entity(name="CHOICE_INSTANCE_HISTORY")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChoiceInstanceHistory implements Comparable<ChoiceInstanceHistory>{

	@Id
	@SequenceGenerator(name = "CHOICE_INSTANCE_HISTORY_GEN", sequenceName = "CHOICE_INSTANCE_HISTORY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHOICE_INSTANCE_HISTORY_GEN")
	@JsonView({Views.ManagedView.class,Views.PlabAdminView.class})   
	@Column(name="CHOICE_INSTANCE_HISTORY_ID")
	private int choiceInstanceHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({Views.ManagedView.class,Views.PlabAdminView.class})
    @JoinColumn(name = "CHOICE_INSTANCE_ID")
    private ChoiceInstance choiceInstance;

	@JsonView({Views.MobileFirstView.class, Views.ManagedView.class,Views.PlabAdminView.class})
	@Column
	private Boolean selected;

	@Column(name = "CREATE_DTM",nullable = false)
	@JsonView({Views.InternalView.class, Views.PlabAdminView.class})
	private Date dateCreated = new Date();

	@Column(name = "CREATE_USR",nullable = false,length = 25)
	@JsonView(Views.InternalView.class)
	private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

	@Column(name = "END_DTM",nullable = false)
	@JsonView(Views.InternalView.class)
	private Timestamp endTmstamp = PricingLabUtility.getDefaultEndTimeStamp();

	public int getChoiceInstanceHistoryId() {
		return choiceInstanceHistoryId;
	}

	public void setChoiceInstanceHistoryId(int choiceInstanceHistoryId) {
		this.choiceInstanceHistoryId = choiceInstanceHistoryId;
	}

	public ChoiceInstance getChoiceInstance() {
		return choiceInstance;
	}

	public void setChoiceInstance(ChoiceInstance choiceInstance) {
		this.choiceInstance = choiceInstance;
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

	public Timestamp getEndTmstamp() {
		return endTmstamp;
	}

	public void setEndTmstamp(Timestamp endTmstamp) {
		this.endTmstamp = endTmstamp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Override
	public int compareTo(ChoiceInstanceHistory o) {
		return getDateCreated().compareTo(o.getDateCreated());
	}
}
