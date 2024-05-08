package com.rts.ccp.bean;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="tbl_posts")
@Component
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long postId;

	@Column(name = "post_content",columnDefinition = "LONGTEXT") 
	  @Lob
	private String postContent;

	
	@CreationTimestamp
	@Column(name = "date_time")
	private Date dateTime;

	private boolean status;
	
	private boolean commentStatus;


	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private List<PostReaction> reactions;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "post_id")
	private List<Share> share;


	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private List<Tags> tags;

	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "region_id")
	private Region region;


	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "department_id")
	private Department department;

//    @JsonIgnoreProperties("Project")
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "project_id")
	private Project project;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(long postId, String postContent, Date dateTime, boolean status, boolean commentStatus,
			List<PostReaction> reactions, List<Share> share, List<Tags> tags, User user, Region region,
			Department department, Project project) {
		super();
		this.postId = postId;
		this.postContent = postContent;
		this.dateTime = dateTime;
		this.status = status;
		this.commentStatus = commentStatus;
		this.reactions = reactions;
		this.share = share;
		this.tags = tags;
		this.user = user;
		this.region = region;
		this.department = department;
		this.project = project;
	}

	
}
