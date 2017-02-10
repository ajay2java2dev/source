package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.SubCustBlCycle;
import com.vzwcorp.pricinglab.profile.vo.SubCustBlCyclePK;

import java.util.List;

public interface SubCustBlCycleRepository extends CrudRepository<SubCustBlCycle, SubCustBlCyclePK> {
	
	//@Query("SELECT t FROM SubCustBlCycle t where t.custIdNo = :custIdNo and sysdate between t.effDt and t.endDt" )	
	//public SubCustBlCycle  findActiveBlCycleForCustomer(@Param("custIdNo") Long custIdNo );

	//@Query("SELECT t FROM SubCustBlCycle t where t.blCycNo = :blCycleNo and sysdate between t.effDt and t.endDt" )
	//public List<SubCustBlCycle> findActiveBlCycleForBillingCycleNo(@Param("blCycleNo") String blCycleNo );
	

	//@Query("SELECT t.blCycNo FROM SubCustBlCycle t where t.custIdNo = :custIdNo AND t.blTypCd = 'C' AND TO_CHAR(t.endDt, 'YYYY-MM-DD') = '9999-12-31' AND t.deleteInd = 'N'" )
	//public String findBlCycNoByCustIdNo(@Param("custIdNo") Long custIdNo );
}
