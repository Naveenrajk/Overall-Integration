package com.rts.ccp.dto;

import java.util.List;


import org.springframework.stereotype.Component;

import com.rts.ccp.bean.SurveyOptions;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class QuestionsDTO {
	
	private long questionId;

	private long questionNo;

	private String questions;

	private String optionType;
	
	private boolean mandatory;

	private long pageId;

	private List<SurveyOptions> option;

	public QuestionsDTO() {
		super();
	}

	public QuestionsDTO(long questionId, long questionNo, String questions, String optionType, boolean mandatory,
			long pageId, List<SurveyOptions> option) {
		super();
		this.questionId = questionId;
		this.questionNo = questionNo;
		this.questions = questions;
		this.optionType = optionType;
		this.mandatory = mandatory;
		this.pageId = pageId;
		this.option = option;
	}

	
}
