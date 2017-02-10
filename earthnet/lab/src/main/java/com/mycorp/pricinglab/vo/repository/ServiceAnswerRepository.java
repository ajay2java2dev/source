package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.ServiceAnswer;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by menonka on 6/19/2016.
 */
public interface ServiceAnswerRepository extends JpaRepository<ServiceAnswer, Long> {

    public Set<ServiceAnswer> findByServiceInstance(@Param(value = "serviceInstance")ServiceInstance serviceInstance);

}
