package com.vzwcorp.pricinglab.vo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vzwcorp.pricinglab.vo.ServiceInstance;

public interface RbmProductRepository extends JpaRepository<ServiceInstance, String> {

	// String createProduct();
}
