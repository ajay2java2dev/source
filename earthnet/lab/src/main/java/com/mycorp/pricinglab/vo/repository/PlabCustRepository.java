/**
 * 
 */
package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.key.PlabCustAcctMdnPK;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * @author semsasr
 *
 */
public interface PlabCustRepository extends JpaRepository<PlabCust, PlabCustAcctMdnPK> {
	
	public List<PlabCust> findByOffer(Offer offer);
	
	@Query("select t from PlabCust t where t.offer.offerId=:offerId and t.indicator in :indicator and sysdate <= t.plEndDate")
	public List<PlabCust> findByOfferAndIndicator(@Param("offerId")Long offerId,@Param("indicator")Long[] indicator);
	
	@Query("select t from PlabCust t where t.mdn=:mdn and t.indicator in :indicator and sysdate <= t.plEndDate")
	public List<PlabCust> findByMdnAndIndicator(@Param("mdn") String mdn, @Param("indicator")Long[] indicator);

	@Query("select t from PlabCust t where t.offer.offerId=:offerId and t.mdn=:mdn and sysdate <= t.plEndDate")
	public PlabCust findByOfferAndMdn(@Param("offerId") Long offerId,@Param("mdn") String mdn);

	public List<PlabCust> findByOfferAndCustIdNoAndAcctNo(Offer offer, Long custIdNo, Long acctNo);

	public List<PlabCust> findByCustIdNo(Long custIdNo);
	
	public List<PlabCust> findByMdn(String mdn);

	@Query("select t from PlabCust t where t.offer.offerId=:offerId and t.custIdNo=:custIdNo and t.acctNo=:acctNo and t.indicator in :indicator and sysdate <= t.plEndDate")
	public List<PlabCust> findByOfferAndCustIdNoAndAcctNoAndIndicator(@Param("offerId")Long offerId, @Param("custIdNo")Long custIdNo, @Param("acctNo")Long acctNo,@Param("indicator")Long[] indicator);
	
	@Query("select t from PlabCust t where t.offer.offerId=:offerId and t.mdn=:mdn and t.indicator in :indicator and sysdate <= t.plEndDate")
	public List<PlabCust> findByOfferAndMdnAndIndicator(@Param("offerId")Long offerId,@Param("mdn") String mdn,@Param("indicator")Long[] indicator);

	@Query("select count(t) from PlabCust t where t.offer.offerId = :offerId and sysdate <= t.plEndDate")
	public int countPlabCustByOffer(@Param("offerId")Long offerId);
	
	/*@Query("select t from PlabCust t where t.offer.offerId=:offerId and t.custIdNo=:custIdNo and t.acctNo=:acctNo and t.indicator in :indicator and (TO_CHAR(plEndDate, 'YYYY-MM-DD') = '9999-12-31')")
	public List<PlabCust> findByOfferAndCustIdNoAndAcctNoAndIndicatorAndPlEndDateIsNull(@Param("offerId")Long offerId, @Param("custIdNo")Long custIdNo, @Param("acctNo")Long acctNo,@Param("indicator")Long[] indicator);*/
	
	/*public List<PlabCust> findByOfferAndMdnAndPlEndDateIsNull(Offer offer, String mdn);*/
	
	@Query("select t from PlabCust t where t.custIdNo=:custIdNo and t.indicator in :indicator")
	public List<PlabCust> findByCustIdNoAndIndicator(@Param("custIdNo")Long custIdNo,@Param("indicator")Long[] indicator);

    @Query("select t from PlabCust t where t.offer.offerId=:offerId and t.custIdNo=:custIdNo and t.acctNo=:acctNo and t.indicator in :indicator and t.plEndDate >= :date")
    public List<PlabCust> findByOfferAndCustIdNoAndAcctNoAndIndicatorAndEndDate(@Param("offerId")Long offerId, @Param("custIdNo")Long custIdNo, @Param("acctNo")Long acctNo, @Param("indicator")Long[] indicator, @Param("date")Date date);
    
	@Query("select t from PlabCust t where t.custIdNo=:custIdNo and t.acctNo=:acctNo and t.indicator in :indicator and sysdate <= t.plEndDate")
	public List<PlabCust> findByCustIdNoAndAcctNoAndIndicator(@Param("custIdNo")Long custIdNo,@Param("acctNo")Long acctNo,@Param("indicator")Long[] indicator);
	
	@Query("select t from PlabCust t where t.offer.offerId=:offerId and t.custIdNo=:custIdNo and t.acctNo=:acctNo and sysdate <= t.plEndDate")
	public List<PlabCust> findByOfferAndCustIdNoAndAcctNoLatest(@Param("offerId") Long offerId, @Param("custIdNo")Long custIdNo,  @Param("acctNo")Long acctNo);

	public PlabCust findByCustIdNoAndAcctNoAndMdn(Long custIdNo, Long acctNo, String mdn);
	
	@Query("select t.offer.offerId from PlabCust t where t.custIdNo=:custIdNo and t.acctNo=:acctNo and t.indicator in :indicator and rownum = 1")
	public Long findOfferIdByCustIdNoAndAcctNoAndIndicator(@Param("custIdNo")Long custIdNo,@Param("acctNo")Long acctNo,@Param("indicator")Long[] indicator);
	
	@Query("select t from PlabCust t where t.custIdNo=:custIdNo and t.acctNo=:acctNo and t.indicator in :indicator and sysdate <= t.plEndDate")
	public List<PlabCust> findByCustIdNoAndAcctNoAndIndicatorLatest(@Param("custIdNo")Long custIdNo,  @Param("acctNo")Long acctNo,@Param("indicator")Long[] indicator);
	
	@Query("select t from PlabCust t where t.mdn=:mdn and t.indicator in :indicator")
	public List<PlabCust> findAllByMdnAndIndicator(@Param("mdn") String mdn, @Param("indicator")Long[] indicator);

	//Cacheable Queries.

	@Cacheable("com.vzwcorp.pricinglab.vo.PlabCust")
	@Query("select t from PlabCust t where t.mdn=:mdn")
	List<PlabCust> findByMdnForLogging (@Param("mdn") String mdn);

}
