package com.rts.ccp.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "tbl_poll_reaction")
@Getter
@Setter
public class PollReaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reactionId;
//	
//	@OneToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "symbol_id")
//	private Symbol symbol;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private User userId;

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="poll_id")
	private Poll pollId;
	
	public PollReaction() {
		super();
	}

	public PollReaction(long reactionId, User userId, Poll pollId) {
		super();
		this.reactionId = reactionId;
//		this.symbol = symbol;
		this.userId = userId;
		this.pollId = pollId;
	}

}
