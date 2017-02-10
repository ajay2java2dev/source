package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.loader.util.LoaderQueries;
import com.vzwcorp.pricinglab.profile.rbm.vo.Custprodaddonratedetails;
import com.vzwcorp.pricinglab.profile.rbm.vo.CustprodaddonratedetailsPK;

public interface CustprodaddonratedetailsRepository extends CrudRepository<Custprodaddonratedetails, CustprodaddonratedetailsPK> {
	
	//w641601
	@Query(LoaderQueries.GET_CUSTPRODADDONRATEDETAILS_DETAIL)
	Custprodaddonratedetails findCustprodaddonratedetailsRow(@Param("customerRef")  String customerRef, @Param("productSeq") Long productSeq , @Param("domainId") Long domainId, 
			@Param("eventSource") String eventSource , @Param("startDtm") Timestamp startDtm  );
}
