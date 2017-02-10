package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "SELECTION_PAGE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="discriminator",
		discriminatorType=DiscriminatorType.STRING
		)
@DiscriminatorValue(value="P")
public abstract class SelectionPage {

	@Id
	@Column(name = "SELECTION_PAGE_ID")
	@SequenceGenerator(name = "SELECTION_PAGE_GEN", sequenceName = "SELECTION_PAGE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SELECTION_PAGE_GEN")
	private Long selectionPageId;

	@Column(name = "PAGE_TITLE",length = 200)
	String title;

	@Column(name = "PAGE_SUB_TITLE",length = 50)
	@JsonView(Views.MobileFirstView.class)
	String subTitle;

	@Column(name = "PAGE_SELECTION_TYPE",length = 20)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String selectionType;

	@Column(name = "MAX_SELECTED_OPTIONS")
	@JsonView({Views.InternalView.class,Views.MobileFirstView.class})
	private int maxSelectedOptions=1;

	@Column(name = "MAX_VALUES")
	@JsonView(Views.MobileFirstView.class)
	private int maxValues;
	
	@Column(name = "MIN_VALUES")
	@JsonView(Views.MobileFirstView.class)
	private int minValues;

	@Column(name = "CREATE_DTM",nullable = false)
	@JsonView(Views.InternalView.class)
	private Date dateCreated = new Date();

	@Column(name = "CREATE_USR",nullable = false,length = 25)
	@JsonView(Views.InternalView.class)
	private String createdBy = PlabConstants.DEFAULT_CREATED_BY_CPI;

	@Column(name = "END_DTM",nullable = false)
	@JsonView(Views.InternalView.class)
	private Timestamp endTmstamp = PricingLabUtility.getDefaultEndTimeStamp();

	//Getters and Setters
	public int getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(int maxValues) {
		this.maxValues = maxValues;
	}

	public int getMinValues() {
		return minValues;
	}

	public void setMinValues(int minValues) {
		this.minValues = minValues;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getSelectionPageId() {
		return selectionPageId;
	}

	public void setSelectionPageId(Long selectionPageId) {
		this.selectionPageId = selectionPageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSelectionType() {
		return selectionType;
	}

	public void setSelectionType(String selectionType) {
		this.selectionType = selectionType;
	}

	public int getMaxSelectedOptions() {
		return maxSelectedOptions;
	}

	public void setMaxSelectedOptions(int maxSelectedOptions) {
		this.maxSelectedOptions = maxSelectedOptions;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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
