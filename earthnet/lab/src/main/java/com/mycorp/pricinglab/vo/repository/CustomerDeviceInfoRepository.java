package com.vzwcorp.pricinglab.vo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vzwcorp.pricinglab.vo.CustomerDeviceInfo;

public interface CustomerDeviceInfoRepository extends JpaRepository<CustomerDeviceInfo,Long> {
	
	public List<CustomerDeviceInfo> findByLoggedInMdn(String mdn);

}
