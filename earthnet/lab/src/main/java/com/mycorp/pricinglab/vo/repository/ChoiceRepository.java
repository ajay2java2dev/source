package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by menonka on 6/17/2016.
 */
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    public List<Choice> findByTitle(@Param("title") String title);
}
