package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;


import com.vzwcorp.pricinglab.profile.vo.MtdtJitrRoutingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.MtdtJitrRouting;

import java.util.List;


public interface MtdtJitrRoutingRepository extends JpaRepository<MtdtJitrRouting, MtdtJitrRoutingPK> {
	
	@Query("SELECT t FROM MtdtJitrRouting t WHERE t.custIdNo = :custIdNo ")
	public MtdtJitrRouting findByCustId(@Param("custIdNo") Long custIdNo);

	@Query("SELECT t FROM MtdtJitrRouting t WHERE t.custIdNo = :custIdNo and sysdate <= t.jitrRoutingEndDt")
	public List<MtdtJitrRouting> findActiveMtdtByCustId(@Param("custIdNo") Long custIdNo);

}
