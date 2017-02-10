package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity(name="CUSTOMER_DEVICE")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerDeviceInfo {
	
	@Id
	@SequenceGenerator(name = "CUSTOMER_DEVICE_GEN", sequenceName = "CUSTOMER_DEVICE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_DEVICE_GEN")
	@Column(name = "CUSTOMER_DEVICE_ID")
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private Long customerDeviceInfoID;

	@Column(name="MDN",nullable = false,length = 10)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String loggedInMdn;
	

	@Column(name="APP_VERSION",length = 25)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String appVersion;
	
	@Column(name="DEVICE_OS_VERSION",length = 25)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String osVersion;
	
	@Column(name="DEVICE_OS_NAME",length = 25)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String osName;
	
	@Column(name="DEVICE_MODEL",length = 25)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String model;
	
	@Column(name="FEEDBACK_TOPIC",length = 100)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String action;
	
	@Column(name="DEVICE_SESSION_ID",nullable = false,length = 50)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String sessionId;
	
	@Column(name="DEVICE_NAME",length = 25)
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	private String deviceName;

	@Column(name = "CREATE_DTM",nullable = false)
	@JsonView(Views.InternalView.class)
	private Date dateCreated = new Date();

	@Column(name = "CREATE_USR",nullable = false,length = 25)
	@JsonView(Views.InternalView.class)
	private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="custom_var")
	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	@JsonBackReference
	private Feedback feedback;

	public String getLoggedInMdn() {
		return loggedInMdn;
	}

	public void setLoggedInMdn(String loggedInMdn) {
		this.loggedInMdn = loggedInMdn;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Long getCustomerDeviceInfoID() {
		return customerDeviceInfoID;
	}

	public void setCustomerDeviceInfoID(Long customerDeviceInfoID) {
		customerDeviceInfoID = customerDeviceInfoID;
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
