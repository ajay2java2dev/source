package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.rbm.vo.Account;
import com.vzwcorp.pricinglab.profile.rbm.vo.AccountPK;

public interface AccountRepository extends CrudRepository<Account, AccountPK> {
	
	@Query("SELECT t FROM Account t where t.customerRef = :customerRef ")
	public List<Account> findByCustId(@Param("customerRef") String customerRef);

	@Query("SELECT t FROM Account t where t.accountNum = :accountNum")
	public List<Account> findByAcctNum(@Param("accountNum") String accountNum);
	
	
}
