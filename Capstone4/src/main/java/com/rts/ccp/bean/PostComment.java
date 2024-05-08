package com.rts.ccp.bean;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
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
@Table(name="tbl_postComments")
@Getter
@Setter
public class PostComment {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
	 
		private String body;
		

		@ManyToOne(cascade=CascadeType.MERGE)
		@JoinColumn(name="user_id")
		private User user;
		

		@ManyToOne()
		@JoinColumn(name="post_id")
		private Post post;
	 
		private long parentId;
	 
		@jakarta.persistence.Column(nullable = false, updatable = false)
	    @jakarta.persistence.Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
	    @CreatedDate
	    private Date createdAt;

		public PostComment(long id, String body, User user, Post post, long parentId, Date createdAt) {
			super();
			this.id = id;
			this.body = body;
			this.user = user;
			this.post = post;
			this.parentId = parentId;
			this.createdAt = createdAt;
		}

		public PostComment() {

		}
		
		
	}
	
	

