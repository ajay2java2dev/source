package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import org.springframework.data.repository.CrudRepository;

import com.vzwcorp.pricinglab.profile.vo.SubCustomer;

public interface SubCustomerRepository extends CrudRepository<SubCustomer, Long> {

	SubCustomer findByCustIdNo(Long custId);
}
