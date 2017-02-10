package com.vzwcorp.pricinglab.vo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vzwcorp.pricinglab.vo.ChoiceInstance;
import com.vzwcorp.pricinglab.vo.ChoiceInstanceHistory;


public interface ChoiceInstanceHistoryRepository extends JpaRepository<ChoiceInstanceHistory, Long> {
	
	public List<ChoiceInstanceHistory> findByChoiceInstanceAndSelected (ChoiceInstance choiceInstance,Boolean selected);
	
	public List<ChoiceInstanceHistory> findByChoiceInstanceAndSelectedAndDateCreatedBetween (ChoiceInstance choiceInstance,Boolean selected,Date fromDate,Date toDate);
	
	public List<ChoiceInstanceHistory> findByChoiceInstanceAndSelectedAndDateCreatedBefore (ChoiceInstance choiceInstance,Boolean selected,Date fromDate);
	
	public List<ChoiceInstanceHistory> findByChoiceInstanceAndDateCreatedBetween (ChoiceInstance choiceInstance,Date fromDate,Date toDate);
	
	public List<ChoiceInstanceHistory> findByChoiceInstanceAndDateCreatedBefore (ChoiceInstance choiceInstance,Date fromDate);

}
