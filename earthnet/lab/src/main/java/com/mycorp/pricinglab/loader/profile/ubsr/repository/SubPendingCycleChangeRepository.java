package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vzwcorp.pricinglab.profile.vo.SubPendingCycleChange;


public interface SubPendingCycleChangeRepository extends CrudRepository<SubPendingCycleChange, Long> {
	
	
	@Query("SELECT t FROM SubPendingCycleChange t  where t.status in ( 'INIT','FAILED','SENT') AND TO_DATE(t.effDt) <= CURRENT_DATE ")
	public List<SubPendingCycleChange> findBillCycleToProcess();
	
	/*@Query("SELECT * FROM SubPendingCycleChange t , MtdtJitrRouting m where t.custIdNo = m.custIdNo t.status in ( 'INIT','FAILED','SENT') AND TO_DATE(t.effDt) <= CURRENT_DATE ")
	public List<Object> findSPCCObject();*/
	
	
}

