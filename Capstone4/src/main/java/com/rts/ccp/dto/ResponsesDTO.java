package com.rts.ccp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ResponsesDTO {

	private long OptionId;
	
	private long responsesId;
	
	private List<Long> Option;  
	
	private String optionType;
	
	private long responseDetailId;
	
	private String responseAnswer;
	
	private String responseQuestion;

	public ResponsesDTO() {
		super();
	}
	
	public ResponsesDTO(long optionId, long responsesId, List<Long> option, String optionType, long responseDetailId,
			String responseAnswer, String responseQuestion) {
		super();
		OptionId = optionId;
		this.responsesId = responsesId;
		Option = option;
		this.optionType = optionType;
		this.responseDetailId = responseDetailId;
		this.responseAnswer = responseAnswer;
		this.responseQuestion = responseQuestion;
	}

}
