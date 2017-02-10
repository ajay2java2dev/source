package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.loader.util.LoaderQueries;
import com.vzwcorp.pricinglab.profile.rbm.vo.Custeventsource;
import com.vzwcorp.pricinglab.profile.rbm.vo.CusteventsourcePK;

public interface CusteventsourceRepository extends CrudRepository<Custeventsource, CusteventsourcePK> {
	
	// w641601
	@Query(LoaderQueries.GET_CUSTEVENTSOURCE_RBM)
	public List<Custeventsource> findCusteventsourceChangeMdn(@Param("customerRef") String customerRef, @Param("productSeq") Long productSeq,@Param("eventSource") String eventSource );
}
