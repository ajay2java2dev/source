package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.SubAcctTyp;
import com.vzwcorp.pricinglab.profile.vo.SubAcctTypPK;

public interface SubAcctTypRepository extends CrudRepository<SubAcctTyp, SubAcctTypPK> {
	@Query("SELECT t FROM SubAcctTyp t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo")
	public List<SubAcctTyp> findByCustomerAndAccount(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo);
}
