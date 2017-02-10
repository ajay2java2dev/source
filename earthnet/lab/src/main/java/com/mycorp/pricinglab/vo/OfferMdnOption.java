package com.vzwcorp.pricinglab.vo;

import java.util.Comparator;
import java.util.List;

public class OfferMdnOption {

	private String mdn;
	private String subTitle;
	private List<ServiceQuestion> offerOptions;
	private List<String> spos;
	
	private String name;

	public OfferMdnOption() {};

	public OfferMdnOption(String mdn) {
		this.mdn = mdn;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ServiceQuestion> getOfferOptions() {
		return offerOptions;
	}

	public void setOfferOptions(List<ServiceQuestion> offerOptions) {
		this.offerOptions = offerOptions;
	}

	public List<String> getSpos() {
		return spos;
	}

	public void setSpos(List<String> spos) {
		this.spos = spos;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}


	public static final Comparator<Choice> CHOICE_COMPARATOR = new Comparator<Choice>() {
		@Override
		public int compare(Choice o1, Choice o2) {
			if (o1.getChargePerDay()!=null && o2.getChargePerDay()!=null) {
				return o1.getChargePerDay().compareTo(o2.getChargePerDay());
			} else {
				return o1.getId().compareTo(o2.getId());
			}
		}
	};


}
