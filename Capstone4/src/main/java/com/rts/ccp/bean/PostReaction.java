package com.rts.ccp.bean;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_reactions")
@Component
@Getter
@Setter
public class PostReaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reaction_id")
	private long reactionId;
	
	@JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private User user;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="symbol_id")
//	private Symbol symbol;
    
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.MERGE)
   	@JoinColumn(name="post_id")
   	private Post postId;

	public PostReaction() {
		super();

	}

	public PostReaction(long reactionId, User user, Post postId) {
		super();
		this.reactionId = reactionId;
		this.user = user;
		this.postId = postId;
	}
	
}
