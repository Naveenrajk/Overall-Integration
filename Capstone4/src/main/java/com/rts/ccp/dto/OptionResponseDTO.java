package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponseDTO {

	private long optionId;
	private long pollId;

	public OptionResponseDTO() {
		super();
	}

	public OptionResponseDTO(long optionId, long pollId) {
		super();
		this.optionId = optionId;
		this.pollId = pollId;
	}

	
	

}
