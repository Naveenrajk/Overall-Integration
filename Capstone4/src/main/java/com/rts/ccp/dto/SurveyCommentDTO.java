package com.rts.ccp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyCommentDTO {
	
	private long id;
		
		private long surveyId;
	 
		private Date createdAt;
	 
		private String body;
	 
		private long userId;
		
		private long parentId;
		
		public SurveyCommentDTO() {
			super();
		}
	 
		public SurveyCommentDTO(long id,long surveyId, Date createdAt, String body,long userId,long parentId) {
			super();
			this.id = id;
			this.surveyId = surveyId;
			this.createdAt = createdAt;
			this.body = body;
			this.userId = userId;
			this.parentId = parentId;
		}
}
