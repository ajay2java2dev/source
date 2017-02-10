package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.rbm.vo.Accountattributes;
import com.vzwcorp.pricinglab.profile.rbm.vo.AccountattributesPK;

public interface AccountattributesRepository extends CrudRepository<Accountattributes, AccountattributesPK> {
	
	
	
	
	@Query("SELECT t FROM Accountattributes t where t.accountNum = :accountNum ")
	public Accountattributes findByCustIdAndAcctNum(@Param("accountNum") String accountNum);
}
