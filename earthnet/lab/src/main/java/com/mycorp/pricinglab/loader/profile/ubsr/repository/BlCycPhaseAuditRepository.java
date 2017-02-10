package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.profile.vo.BlCycPhaseAudit;
import com.vzwcorp.pricinglab.profile.vo.BlCycPhaseAuditPK;


public interface BlCycPhaseAuditRepository extends CrudRepository<BlCycPhaseAudit, BlCycPhaseAuditPK> {
	
	@Query("SELECT t FROM BlCycPhaseAudit t WHERE t.cycMthYr = :cycMthYr AND t.blCycNo = :blCycNo AND t.visionSource = :visionSource  AND t.phaseTyp = 'BQ' AND t.rptColNameCd = 'CUS' ")
	public List<BlCycPhaseAudit> findByblCycNoAndcycMthYrAndvisionSource(@Param("cycMthYr") String  cycMthYr, @Param("blCycNo") String  blCycNo , @Param("visionSource") String  visionSource );

}
