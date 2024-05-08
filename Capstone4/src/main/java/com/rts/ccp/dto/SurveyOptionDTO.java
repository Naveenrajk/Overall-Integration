package com.rts.ccp.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyOptionDTO {

	private long optionId;

	private String options;
	
	private long questionId;

	public SurveyOptionDTO() {
		super();
	}

	public SurveyOptionDTO(long optionId, String options, long questionId) {
		super();
		this.optionId = optionId;
		this.options = options;
		this.questionId = questionId;
	}
}
