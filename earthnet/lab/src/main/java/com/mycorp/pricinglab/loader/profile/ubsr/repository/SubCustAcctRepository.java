package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.SubCustAcct;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctPK;

public interface SubCustAcctRepository extends CrudRepository<SubCustAcct, SubCustAcctPK> {
		
	@Query("SELECT t FROM SubCustAcct t where t.id.custIdNo = :custIdNo AND t.id.acctNo = :acctNo AND t.deleteInd = 'N'")
	public SubCustAcct findByCustIdNoAndAcctNo(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo);
	
	
	@Query("SELECT t FROM SubCustAcct t where t.id.custIdNo = :custIdNo AND t.blAcctStatCd = 'A' ")
	public List<SubCustAcct> findByCustId(@Param("custIdNo") Long custIdNo);
	
}
