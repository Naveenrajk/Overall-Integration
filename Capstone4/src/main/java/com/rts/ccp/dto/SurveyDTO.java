package com.rts.ccp.dto;

import java.sql.Date;


import java.util.List;

import com.rts.ccp.bean.SurveyComment;
import com.rts.ccp.bean.Pages;
import com.rts.ccp.bean.SurveyReaction;
import com.rts.ccp.bean.ResponseDetails;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class SurveyDTO {
    
	private long surveyId;
	
	private String surveyName;

	private Date endDate;

	private String status;

	private List<Pages> page;

	private List<ResponseDetails> responseDetails;

	private List<SurveyReaction> reaction;

	private List<SurveyComment> comment;

	private long user;
	
	private String userName;

	private long region;

	private long department;

	private long project;
	
	private boolean response;
	
	private long id;
	 
	private String value;

	public SurveyDTO() {
		super();
	}

	
	public SurveyDTO(long surveyId, String surveyName, Date endDate, String status, List<Pages> page,
			List<ResponseDetails> responseDetails, List<SurveyReaction> reaction, List<SurveyComment> comment, long user,
			String userName, long region, long department, long project, boolean response, long id, String value) {
		super();
		this.surveyId = surveyId;
		this.surveyName = surveyName;
		this.endDate = endDate;
		this.status = status;
		this.page = page;
		this.responseDetails = responseDetails;
		this.reaction = reaction;
		this.comment = comment;
		this.user = user;
		this.userName = userName;
		this.region = region;
		this.department = department;
		this.project = project;
		this.response = response;
		this.id = id;
		this.value = value;
	}
	
	

}
