package com.rts.ccp.dto;

import java.util.List;

import com.rts.ccp.bean.Responses;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDetailsDTO {

	
    private long responseDetailId;
	
	private String userEmailId;
	
	private String startTime;
	
	private String endTime;
	
	private List<Responses> response;
	
	private long userId;
	
	private String userName;
	
	private long surveyId;
	
	private long responseDetailCount;

	public ResponseDetailsDTO() {
		super();
	}


	public ResponseDetailsDTO(long responseDetailId, String userEmailId, String startTime, String endTime,
			List<Responses> response, long userId, String userName, long surveyId, long responseDetailCount) {
		super();
		this.responseDetailId = responseDetailId;
		this.userEmailId = userEmailId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.response = response;
		this.userId = userId;
		this.userName = userName;
		this.surveyId = surveyId;
		this.responseDetailCount = responseDetailCount;
	}

}
