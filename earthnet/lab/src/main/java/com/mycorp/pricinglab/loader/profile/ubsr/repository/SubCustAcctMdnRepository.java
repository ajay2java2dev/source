package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import com.vzwcorp.pricinglab.loader.util.LoaderQueries;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdn;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdnPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface SubCustAcctMdnRepository extends JpaRepository<SubCustAcctMdn, SubCustAcctMdnPK> {

	@Query("SELECT t FROM SubCustAcctMdn t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo")
	public List<SubCustAcctMdn> findByCustomerAndAccount(@Param("custIdNo") Long custIdNo,
			@Param("acctNo") Long acctNo);

	@Query(LoaderQueries.GET_CUST_ACCT_MDN)
	public List<SubCustAcctMdn> getCustAcctDetails(@Param("mdn") String mdn);

	@Query("SELECT t FROM SubCustAcctMdn t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND sysdate <= t.endDt AND t.custMtnStatCd in ('A','AC','AR','AT')")
	public List<SubCustAcctMdn> findByCustomerAndAccountAndEnddate(@Param("custIdNo") Long custIdNo,
			@Param("acctNo") Long acctNo);

	@Query("SELECT t FROM SubCustAcctMdn t where t.mdn = :mdn AND sysdate <= t.endDt AND t.custMtnStatCd in ('A','AC','AR','AT')")
	public List<SubCustAcctMdn> findByActiveMdn(@Param("mdn") String mdn);
}