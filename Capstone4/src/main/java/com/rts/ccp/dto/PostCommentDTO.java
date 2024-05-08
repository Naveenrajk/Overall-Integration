package com.rts.ccp.dto;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentDTO {
	
	private long postId;

	private Date createdAt;

	private String body;

	private long userId;

	private long parentId;

	public PostCommentDTO() {
		super();
	}

	public PostCommentDTO(long postId, Date createdAt, String body, long userId, long parentId) {
		super();
		this.postId = postId;
		this.createdAt = createdAt;
		this.body = body;
		this.userId = userId;
		this.parentId = parentId;
	}

}