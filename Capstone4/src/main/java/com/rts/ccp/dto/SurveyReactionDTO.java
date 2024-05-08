package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyReactionDTO {
	
	private long surveyId;
	 
	private long userId;
	
	private long reactionCount;
	
	private boolean reaction;

	public SurveyReactionDTO() {
		super();
	}

	public SurveyReactionDTO(long surveyId, long userId, long reactionCount, boolean reaction) {
		super();
		this.surveyId = surveyId;
		this.userId = userId;
		this.reactionCount = reactionCount;
		this.reaction = reaction;
	}

  
	

}
