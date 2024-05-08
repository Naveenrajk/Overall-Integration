package com.rts.ccp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rts.ccp.bean.Questions;
import com.rts.ccp.bean.Survey;

public interface SurveyRepo extends JpaRepository<Survey, Long> {

	public List<Survey> findBySurveyName(String surveyName);

	public List<Questions> findBySurveyId(long surveyId);
	
	@Query(value = "select * FROM tbl_survey where region_id=? order by survey_id desc", nativeQuery = true)
	public List<Survey> findByRegionId(long regionId);
	
	@Query(value = "select * FROM tbl_survey where user_id=? order by survey_id desc", nativeQuery = true)
	public List<Survey> findByUserId(long userId);
	
	@Query(value = "select * FROM tbl_survey where region_id=? and user_id!=?", nativeQuery = true)
	public List<Survey> findByRegionUser(long userId,long regionId);
	

}
