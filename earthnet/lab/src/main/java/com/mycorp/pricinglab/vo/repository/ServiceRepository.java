package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.RatingGroup;
import com.vzwcorp.pricinglab.vo.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

	public List<Service> findByOffer(@Param("offer") Offer offer);

	public Service findBySpoID(String spoId);
	
	public Service findByServiceId(@Param("serviceId") Long serviceId);

	public Service findByRatingGroup(@Param("ratingGroup") RatingGroup ratingGroup);

	@Query(value = "Select t from Service t where t.ratingGroup.ratingGroupId=:ratingGroupId")
	public Service findByRatingGroup(@Param("ratingGroupId") Long ratingGroupId);
	
	public List<Service> findByVispServiceIDIsNull();

	@Query(value = "Select t from Service t where t.state like :state")
	public List<Service> findByState(@Param("state") String state);

	@Query(value = "Select t from Service t where t.spoID like :spoID and rownum=1 order by t.dateCreated asc")
	public List<Service> findAllServicesBySpoID(@Param("spoID")String spoID);

	@Query(value = "Select t from Service t where t.spoID like :spoID")
	public List<Service> findAllOfferServicesBySpoID(@Param("spoID")String spoID);
}
