package com.rts.ccp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rts.ccp.bean.SurveyOptions;


public interface SurveyOptionRepo extends CrudRepository<SurveyOptions, Long> {

	@Query(value = "select * FROM tbl_survey_option where question_id=?", nativeQuery = true)

	public List<SurveyOptions> findByQuestionId(long questionId);

	@Query(value = "select question_id FROM tbl_survey_option where option_id=?", nativeQuery = true)
	public long findByOptionId(long optionId);
}
