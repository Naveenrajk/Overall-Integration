package com.rts.ccp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PollCommentDTO {

	private long pollId;

	private Date createdAt;

	private String body;

	private long parentId;

	private long userId;

	public PollCommentDTO() {
		super();
	}

	public PollCommentDTO(long pollId, Date createdAt, String body, long parentId, long userId) {
		super();
		this.pollId = pollId;
		this.createdAt = createdAt;
		this.body = body;
		this.parentId = parentId;
		this.userId = userId;
	}

	
}
