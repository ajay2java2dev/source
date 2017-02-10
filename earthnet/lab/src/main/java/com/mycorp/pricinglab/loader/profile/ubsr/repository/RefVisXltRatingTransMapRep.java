package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.RefVisRatingProdHier;
import com.vzwcorp.pricinglab.profile.vo.RefVisXltRatingTransMap;
import com.vzwcorp.pricinglab.profile.vo.RefVisXltRatingTransMapPK;


public interface RefVisXltRatingTransMapRep extends CrudRepository<RefVisXltRatingTransMap,RefVisXltRatingTransMapPK>{

	@Query("SELECT t FROM RefVisXltRatingTransMap t where t.blIdTyp = :blIdTyp AND t.blIdValue = :blIdValue AND t.rbmIdTyp = :rbmIdTyp")
	RefVisXltRatingTransMap findByBlIdTypAndBlIdValueAndRbmIdTyp(@Param("blIdTyp") String blIdTyp, @Param("blIdValue") long blIdValue,@Param("rbmIdTyp") String rbmIdTyp);
	
	
	@Query("SELECT t FROM RefVisXltRatingTransMap t where t.blIdValue = :blIdValue")
	RefVisXltRatingTransMap findByBlIdValue(@Param("blIdValue") long blIdValue);
	
	
	//@Query("SELECT t FROM RefVisXltRatingTransMap t inner join  t.RefVisRatingProdHier r  where t.blIdValue = :blIdValue AND r.rbmChildValue = 'BT' AND r.rbmParentCd = :rbmIdTyp AND r.rbmParentValue= :rbmIdValue")
	//RefVisXltRatingTransMap  findTariffId(@Param("blIdValue") long blIdValue);
	
	
	/*@Query("SELECT t FROM RefVisXltRatingTransMap t,RefVisRatingProdHier h where t.blIdValue = :blIdValue AND h.rbmParentCd in ('PD','PO') AND h.rbmChildCd = :rbmChildCd ANDh.rbmParentCd = t.rbmIdTyp AND h.rbmParentValue = t.rbmIdValue")
	RefVisXltRatingTransMap  findTariffId(@Param("blIdValue") long blIdValue,@Param("rbmChildCd") String rbmChildCd);*/
	
	@Query("SELECT h.rbmChildValue FROM RefVisXltRatingTransMap t,RefVisRatingProdHier h where t.blIdValue = :blIdValue AND h.rbmParentCd in ('PD','PO') AND h.rbmChildCd = :rbmChildCd AND h.rbmParentCd = t.rbmIdTyp AND h.rbmParentValue = t.rbmIdValue")
	String  findTariffId(@Param("blIdValue") long blIdValue,@Param("rbmChildCd") String rbmChildCd);
	
	@Query("SELECT h FROM RefVisXltRatingTransMap t,RefVisRatingProdHier h where t.blIdValue = :blIdValue AND h.rbmChildCd = :rbmChildCd AND h.rbmParentCd = t.rbmIdTyp AND h.rbmParentValue = t.rbmIdValue")
	List<RefVisRatingProdHier>  findRatingAndBillinTarrif(@Param("blIdValue") long blIdValue,@Param("rbmChildCd") String rbmChildCd);
	
}

