package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.RefVisRatingProdHier;
import com.vzwcorp.pricinglab.profile.vo.RefVisRatingProdHierPK;



public interface RefVisRatingProdHierRep extends CrudRepository<RefVisRatingProdHier,RefVisRatingProdHierPK>{

	@Query("SELECT t FROM RefVisRatingProdHier t where t.rbmParentValue = :rbmParentValue AND t.rbmParentCd = :rbmParentCd")
	RefVisRatingProdHier findByrbmParentValueAndrbmParentCd(@Param("rbmParentValue")  String rbmParentValue, @Param("rbmParentCd") String rbmParentCd);
	
	
	
	@Query("SELECT t.rbmChildValue FROM RefVisRatingProdHier t where t.rbmChildCd = :rbmChildCd  AND t.rbmParentValue = :rbmParentValue AND  t.rbmParentCd in ('PD' , 'CANBP' ) ")
	String findTariffIdForCantenna( @Param("rbmChildCd") String rbmChildCd, @Param("rbmParentValue")  String rbmParentValue);	
	
	
}


