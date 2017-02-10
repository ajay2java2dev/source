package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, String> {

	public Application findByName(@Param("name") String name);
}
