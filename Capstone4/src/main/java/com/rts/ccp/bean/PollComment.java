package com.rts.ccp.bean;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_poll_comment")
@Getter
@Setter
public class PollComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length=1000)
	private String body;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	private long parentId;

	
	@ManyToOne
	@JoinColumn(name = "poll_id")
	private Poll poll;

	@jakarta.persistence.Column(nullable = false, updatable = false)
	@jakarta.persistence.Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	public PollComment() {
		super();
	}

	public PollComment(long id, String body, User user, long parentId, Poll poll, Date createdAt) {
		super();
		this.id = id;
		this.body = body;
		this.user = user;
		this.parentId = parentId;
		this.poll = poll;
		this.createdAt = createdAt;
	}

}