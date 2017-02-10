package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import com.vzwcorp.pricinglab.profile.rbm.vo.AccExternalAction;
import com.vzwcorp.pricinglab.profile.rbm.vo.AccExternalActionPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by PricingLab on 9/27/2016.
 */
public interface AccExternalActionRepository  extends CrudRepository<AccExternalAction, AccExternalActionPK> {

}
