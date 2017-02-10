
package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.rbm.vo.Billsummary;
import com.vzwcorp.pricinglab.profile.rbm.vo.BillsummaryPK;

public interface BillsummaryRepository extends CrudRepository<Billsummary, BillsummaryPK> {
	

	@Query("SELECT t FROM Billsummary t Where t.accountNum = :accountNum AND t.billStatus IN (12,18,19)")
	public List<Billsummary> findByAccountNum(@Param("accountNum") String accountNum);
	
	
}
