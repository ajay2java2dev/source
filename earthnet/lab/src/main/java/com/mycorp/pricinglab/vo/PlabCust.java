package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.key.PlabCustAcctMdnPK;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PLAB_CUST", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CUST_ID_NO", "ACCT_NO", "MDN", "OFFER_ID" }))
@IdClass(PlabCustAcctMdnPK.class)
public class PlabCust implements Serializable {

	@Id
	@Column(name = "CUST_ID_NO", nullable = false)
	private Long custIdNo;

	@Id
	@Column(name = "ACCT_NO", nullable = false)
	public Long acctNo;

	@Id
	@Column(name = "MDN", nullable = false, length = 10)
	public String mdn;

	@Column(name = "CUST_IND", nullable = false)
	public Long indicator;

	@Column(name = "CREATE_DTM", nullable = false)
	public Timestamp plStartDate = new Timestamp(new Date().getTime());

	@Column(name = "EFF_DTM", nullable = false)
	public Timestamp createDtm = new Timestamp(new Date().getTime());

	@Column(name = "CREATE_USR", length = 25)
	public String createdBy = PlabConstants.DEFAULT_CREATED_BY_CPI;

	@Column(name = "END_DTM", nullable = false)
	public Timestamp plEndDate = PricingLabUtility.getDefaultEndTimeStamp();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OFFER_ID", referencedColumnName = "OFFER_ID", nullable = false)
	@JsonBackReference
	private Offer offer;

	/**
	 * Added following attributes for displaying invited customer details.
	 */

	@Transient
	private Timestamp inviteExpiryDate = new Timestamp(new Date().getTime());

	@Transient
	private String offerAccepted;

	@Transient
	private String pilotEndDate;

	@Transient
	private String pilotStartDate;

	@Transient
	private String featureStatus;

	@Transient
	private Timestamp exitedDate;

	public String getPilotStartDate() {
		return pilotStartDate;
	}

	public void setPilotStartDate(String pilotStartDate) {
		this.pilotStartDate = pilotStartDate;
	}

	public Timestamp getInviteExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(plStartDate);
		cal.add(Calendar.DAY_OF_WEEK, 21);
		inviteExpiryDate.setTime(cal.getTime().getTime());
		return inviteExpiryDate;
	}

	public void setInviteExpiryDate(Timestamp inviteExpiryDate) {
		this.inviteExpiryDate = inviteExpiryDate;
	}

	public String getOfferAccepted() {
		return offerAccepted;
	}

	public void setOfferAccepted(String offerAccepted) {
		this.offerAccepted = offerAccepted;
	}

	public String getPilotEndDate() {
		return pilotEndDate;
	}

	public void setPilotEndDate(String pilotEndDate) {
		this.pilotEndDate = pilotEndDate;
	}

	public String getFeatureStatus() {
		return featureStatus;
	}

	public void setFeatureStatus(String featureStatus) {
		this.featureStatus = featureStatus;
	}

	public Timestamp getExitedDate() {
		return exitedDate;
	}

	public void setExitedDate(Timestamp exitedDate) {
		this.exitedDate = exitedDate;
	}

	public Long getCustIdNo() {
		return custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public Long getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public Long getIndicator() {
		return indicator;
	}

	public void setIndicator(Long indicator) {
		this.indicator = indicator;
	}

	public Timestamp getPlStartDate() {
		return plStartDate;
	}

	public void setPlStartDate(Timestamp plStartDate) {
		this.plStartDate = plStartDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getPlEndDate() {
		return plEndDate;
	}

	public void setPlEndDate(Timestamp plEndDate) {
		this.plEndDate = plEndDate;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Timestamp getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(Timestamp createDtm) {
		this.createDtm = createDtm;
	}
}
