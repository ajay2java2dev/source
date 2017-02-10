package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;

import javax.persistence.*;
import java.util.Date;


@Table(name="CUSTOMER_FEEDBACK")
@Entity
public class Feedback {
	
	@Id
	@Column(name="CUST_CARD_ID",length = 20,nullable = false)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private Long ccid;
	
	@Column(name="START_TIME_MS",length = 20)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String time1;
	
	@Column(name="END_TIME_MS",length = 20)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String time2;
		
	@Column(name="CUST_REFERER",length = 30)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String referer;
	
	@Column(name="CUST_URL",length = 30)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String url;
	
	@Column(name = "DEVICE_WIDTH",length = 10)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String width;
	
	@Column(name = "DEVICE_HEIGHT",length = 10)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String height;
	
	@Column(name = "CUST_COMMENT_NUM")
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private Long comment_card;
	
	@Column(name = "CUST_RATING")
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private Long overall; 
	
	@Column(name = "CUST_COMMENTS",length = 2000)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String comments;
	
	@Transient
	//@Column(name = "REPLY_COMMENTS",length = 2000)
	//@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String replyComments;

	@Column(name = "CREATE_DTM",nullable = false)
	@JsonView(Views.InternalView.class)
	private Date dateCreated = new Date();

	@Column(name = "CREATE_USR",nullable = false,length = 25)
	@JsonView(Views.InternalView.class)
	private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="CUSTOMER_DEVICE_ID")
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	@JsonManagedReference
	private CustomerDeviceInfo custom_var;
	
	@Transient
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String offerName;

	public Long getCcid() {
		return ccid;
	}

	public void setCcid(Long ccid) {
		this.ccid = ccid;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Long getComment_card() {
		return comment_card;
	}

	public void setComment_card(Long comment_card) {
		this.comment_card = comment_card;
	}

	public Long getOverall() {
		return overall;
	}

	public void setOverall(Long overall) {
		this.overall = overall;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public CustomerDeviceInfo getCustom_var() {
		return custom_var;
	}

	public void setCustom_var(CustomerDeviceInfo custom_var) {
		this.custom_var = custom_var;
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

	public String getReplyComments() {
		return replyComments;
	}

	public void setReplyComments(String replyComments) {
		this.replyComments = replyComments;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	
	
}
