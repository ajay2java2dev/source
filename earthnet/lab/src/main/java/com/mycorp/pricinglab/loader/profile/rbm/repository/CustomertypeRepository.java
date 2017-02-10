package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import org.springframework.data.repository.CrudRepository;

import com.vzwcorp.pricinglab.profile.rbm.vo.Customertype;
import com.vzwcorp.pricinglab.profile.rbm.vo.CustomertypePK;

public interface CustomertypeRepository extends CrudRepository<Customertype, CustomertypePK> {
}
