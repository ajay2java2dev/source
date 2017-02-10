package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import org.springframework.data.repository.CrudRepository;

import com.vzwcorp.pricinglab.profile.rbm.vo.Customer;
import com.vzwcorp.pricinglab.profile.rbm.vo.CustomerPK;

public interface CustomerRepository extends CrudRepository<Customer, CustomerPK> {
}
