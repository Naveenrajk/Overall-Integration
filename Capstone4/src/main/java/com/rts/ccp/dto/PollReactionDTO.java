package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollReactionDTO {

	private long pollId;
 
	private long userId;
	
	private boolean reaction;
	
	private long reactionCount;
	
	

	

	public PollReactionDTO(long pollId, long userId) {
		super();
		this.pollId = pollId;
		this.userId = userId;
	}
	
	

	
	public PollReactionDTO() {
		super();
	
	}

	
 	
	
}
