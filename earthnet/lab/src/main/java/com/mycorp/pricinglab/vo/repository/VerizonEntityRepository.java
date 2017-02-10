package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.VerizonEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface VerizonEntityRepository extends JpaRepository<VerizonEntity, Long> {

    public VerizonEntity findByMdn(@Param("mdn") String mdn);

    public VerizonEntity findByVerizonEntityId(@Param("verizonEntityId") Long verizonEntityId);

    public VerizonEntity findByCustIdNoAndAcctNoAndMdn(@Param("custIdNo") Long custIdNo, @Param("account") Long acctNo, @Param("mdn") String mdn);
    
    public List<VerizonEntity> findByCustIdNoAndAcctNo(@Param("custIdNo") Long custIdNo, @Param("account") Long acctNo);
}
