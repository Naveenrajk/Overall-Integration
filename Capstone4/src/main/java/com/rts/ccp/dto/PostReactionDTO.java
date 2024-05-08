package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReactionDTO {
	
	private long postId;
	 
	private long userId;

	public PostReactionDTO() {
		super();
	
	}

	public PostReactionDTO(long postId, long userId) {
		super();
		this.postId = postId;
		this.userId = userId;
	}


	
	

}

