package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.SubAcctSplan;
import com.vzwcorp.pricinglab.profile.vo.SubAcctSplanPK;

public interface SubAcctSplanRepository extends CrudRepository<SubAcctSplan, SubAcctSplanPK> {
	@Query("SELECT t FROM SubAcctSplan t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo")
	public List<SubAcctSplan> findByCustomerAndAccount(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo);
	
	@Query("SELECT t FROM SubAcctSplan t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND TO_TIMESTAMP(:sfoEffectDate,'YYYY-MM-DD.HH24.MI.SS.FF') <  TO_TIMESTAMP(:endDate,'YYYY-MM-DD.HH24.MI.SS.FF')")
	public SubAcctSplan findSplanDetails(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo,@Param("sfoEffectDate") String sfoEffectDate, @Param("endDate") String endDate);
	
}

