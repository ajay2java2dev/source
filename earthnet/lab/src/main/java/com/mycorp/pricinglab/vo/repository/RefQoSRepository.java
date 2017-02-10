package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.RefQoS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by menonka on 8/7/2016.
 */
public interface RefQoSRepository extends JpaRepository<RefQoS,String> {
    public RefQoS findByName(@Param("name") String name);
}
