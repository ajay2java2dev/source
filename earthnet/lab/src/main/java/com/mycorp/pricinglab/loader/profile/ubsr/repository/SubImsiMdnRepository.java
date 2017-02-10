package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import com.vzwcorp.pricinglab.profile.vo.SubImsiMdn;
import com.vzwcorp.pricinglab.profile.vo.SubImsiMdnPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubImsiMdnRepository extends CrudRepository<SubImsiMdn, SubImsiMdnPK> {

    public List<SubImsiMdn> findByMdn(@Param("mdn")String mdn);

}
