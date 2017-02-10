/**
 * 
 */
package com.vzwcorp.pricinglab.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author kovelde
 *
 */

@SuppressWarnings("serial")
public class AdminOfferInstance implements Serializable {

	private Long offerInstanceId;

	private Long offerId;

	private String offerName;

	private Long custIdNo;

	private Long acctNo;

	private String mdn;

	private Date dateCreated;

	private Timestamp endTmstamp;

	private boolean expired;

	public AdminOfferInstance() {
		super();
	}

	public AdminOfferInstance(Long offerInstanceId, Long offerId, String offerName, Long custIdNo, Long acctNo,
			String mdn, Date dateCreated, Timestamp endTmstamp) {
		super();
		this.offerInstanceId = offerInstanceId;
		this.offerId = offerId;
		this.offerName = offerName;
		this.custIdNo = custIdNo;
		this.acctNo = acctNo;
		this.mdn = mdn;
		this.dateCreated = dateCreated;
		this.endTmstamp = endTmstamp;
		setExpired(this.endTmstamp.before(new Date()));
	}

	public Long getOfferInstanceId() {
		return offerInstanceId;
	}

	public void setOfferInstanceId(Long offerInstanceId) {
		this.offerInstanceId = offerInstanceId;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
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

	public boolean getExpired() {
		return this.expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

}
