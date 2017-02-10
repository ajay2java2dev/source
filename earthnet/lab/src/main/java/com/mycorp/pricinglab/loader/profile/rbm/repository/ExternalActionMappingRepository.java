package com.vzwcorp.pricinglab.loader.profile.rbm.repository;

import com.vzwcorp.pricinglab.profile.rbm.vo.ExternalActionMapping;
import com.vzwcorp.pricinglab.profile.rbm.vo.ExternalActionMappingPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by menonka on 10/4/2016.
 */
public interface ExternalActionMappingRepository extends CrudRepository<ExternalActionMapping, ExternalActionMappingPK> {

    @Query(name = "Select t from ExternalActionMapping t where t.accountSpecificBoo = :accountSpecificBoo")
    public List<ExternalActionMapping> findByAccountSpecificBoo (@Param("accountSpecificBoo") String accountSpecificBoo);

}
