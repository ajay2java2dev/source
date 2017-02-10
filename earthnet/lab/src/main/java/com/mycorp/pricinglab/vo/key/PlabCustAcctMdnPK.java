package com.vzwcorp.pricinglab.vo.key;

import com.vzwcorp.pricinglab.vo.Offer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class PlabCustAcctMdnPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false,name = "CUST_ID_NO")
	protected Long custIdNo;

	@Column(nullable = false,name = "ACCT_NO")
	protected Long acctNo;

	@Column(nullable = false,name = "MDN")
	protected String mdn;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumns({@JoinColumn(name = "OFFER_ID", referencedColumnName = "OFFER_ID")})
//	@JsonBackReference
//	private Offer offer;

	public PlabCustAcctMdnPK() {
	}

	public PlabCustAcctMdnPK(Long custIdNo, Long acctNo, String mdn, Timestamp plStartDate, Timestamp plEndDate, Offer offer) {
		this.custIdNo = custIdNo;
		this.acctNo = acctNo;
		this.mdn = mdn;
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

//	public Offer getOffer() {
//		return offer;
//	}
//
//	public void setOffer(Offer offer) {
//		this.offer = offer;
//	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlabCustAcctMdnPK)) {
			return false;
		}
		PlabCustAcctMdnPK castOther = (PlabCustAcctMdnPK)other;
		return this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.mdn.equals(castOther.mdn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + this.mdn.hashCode();
		return hash;
	}
}