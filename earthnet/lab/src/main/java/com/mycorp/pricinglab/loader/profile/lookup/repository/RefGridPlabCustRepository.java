package com.vzwcorp.pricinglab.loader.profile.lookup.repository;

import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCust;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCustPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by menonka on 7/27/2016.
 */
public interface RefGridPlabCustRepository   extends JpaRepository<RefGridPlabCust,RefGridPlabCustPK> {
	
	@Query("select t from RefGridPlabCust t where t.custIdNo=:custIdNo and t.acctNo=:acctNo and t.mdn in :mdn")
	public RefGridPlabCust findByCustIdNoAndAcctNoAndMdn(@Param("custIdNo")Long custIdNo,@Param("acctNo")Long acctNo,@Param("mdn") String mdn);

}
