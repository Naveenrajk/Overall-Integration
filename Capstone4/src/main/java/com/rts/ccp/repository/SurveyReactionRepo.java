package com.rts.ccp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rts.ccp.bean.SurveyReaction;

public interface SurveyReactionRepo extends CrudRepository<SurveyReaction, Long> {

	@Query(value = "select * FROM tbl_survey_reaction where survey_id=1", nativeQuery = true)
	public List<SurveyReaction> getReaction();
	
	@Query(value = "select COUNT(*)  FROM tbl_survey_reaction WHERE survey_id=?", nativeQuery = true)
	   public long getReactionCount(long surveyId);
	
	@Modifying
	@Query(value= "delete from tbl_survey_reaction order by reaction_id desc limit 1;", nativeQuery = true)
	public int deletebylastId();
	
	@Query(value = "select *  FROM tbl_survey_reaction WHERE survey_id=?", nativeQuery = true)
	public List<SurveyReaction> getBySurveyId(long surveyId);
	
	@Query(value = "select reaction_id  FROM tbl_survey_reaction WHERE survey_id=? and user_id=?", nativeQuery = true)
	public long getReactionByUserSurvey(long surveyId,long userId);
	

}
