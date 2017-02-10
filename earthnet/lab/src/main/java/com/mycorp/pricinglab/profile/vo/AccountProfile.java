package com.vzwcorp.pricinglab.profile.vo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubAcctSplanRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubAcctTypRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustAcctMdnRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustAcctRepository;

public class AccountProfile {

	Long custIdNo;
	Long acctNo;

	@Autowired
	SubCustAcctRepository custAcctRepository;

	@Autowired
	SubAcctTypRepository custAcctTypeRepository;

	@Autowired
	SubCustAcctMdnRepository custAcctMdnRepository;

	@Autowired
	ApplicationContext context;

	public AccountProfile(Long custIdNo, Long acctNo) {
		this.custIdNo = custIdNo;
		this.acctNo = acctNo;
	}

	// @Transient
	private SubCustAcct custAccount = null;
	// @Transient
	private List<SubAcctTyp> accountTypes = null;
	// @Transient
	private List<SubCustAcctMdn> mdns = null;
	// @Transient
	private List<SubAcctSplan> splans = null;

	public SubCustAcct getCustAccount() {
		// SUB_CUST_ACCT_Repository custAcctRepository=
		// Application.getContext().getBean(SUB_CUST_ACCT_Repository.class);
		SubCustAcctPK key = new SubCustAcctPK(custIdNo, acctNo);
		return custAcctRepository.findOne(key);
	}

	public List<SubAcctTyp> getAccountTypes() {
		// SUB_ACCT_TYP_Repository custAcctTypeRepository=
		// Application.getContext().getBean(SUB_ACCT_TYP_Repository.class);
		return custAcctTypeRepository.findByCustomerAndAccount(custIdNo, acctNo);
	}

	public List<SubCustAcctMdn> getAccountMdns() {
		// SUB_CUST_ACCT_MDN_Repository custAcctMdnRepository=
		// Application.getContext().getBean(SUB_CUST_ACCT_MDN_Repository.class);
		return custAcctMdnRepository.findByCustomerAndAccount(custIdNo, acctNo);
	}

	public List<SubAcctSplan> getAccountSplans() {
		SubAcctSplanRepository custAcctSplanRepository = context.getBean(SubAcctSplanRepository.class);
		return custAcctSplanRepository.findByCustomerAndAccount(custIdNo, acctNo);
	}

}
