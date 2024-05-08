package com.rts.ccp.dto;

import java.util.Date;
import java.util.List;

import com.rts.ccp.bean.PostComment;
import com.rts.ccp.bean.PostReaction;
import com.rts.ccp.bean.Share;
import com.rts.ccp.bean.Tags;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class PostDTO {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;

	private String postContent;

	private String postImage;

	private Date dateTime;

	private boolean status;
	
	private boolean commentStatus;

	private List<PostComment> comments;

	private List<PostReaction> reactions;

	private List<Share> share;

	private List<Tags> tags;

	private long user;
	
	private String userFirstName;

	private long region;
	
	private String regionName;

	private long department;
	
	private String departmentName;

	private long project;
	
	private String projectName;
	
	private long id;
	
	private String value; 

	public PostDTO() {
		super();

	}

	public PostDTO(long postId, String postContent, String postImage, Date dateTime, boolean status,
			boolean commentStatus, List<PostComment> comments, List<PostReaction> reactions, List<Share> share,
			List<Tags> tags, long user, String userFirstName, long region, String regionName, long department,
			String departmentName, long project, String projectName, long id, String value) {
		super();
		this.postId = postId;
		this.postContent = postContent;
		this.postImage = postImage;
		this.dateTime = dateTime;
		this.status = status;
		this.commentStatus = commentStatus;
		this.comments = comments;
		this.reactions = reactions;
		this.share = share;
		this.tags = tags;
		this.user = user;
		this.userFirstName = userFirstName;
		this.region = region;
		this.regionName = regionName;
		this.department = department;
		this.departmentName = departmentName;
		this.project = project;
		this.projectName = projectName;
		this.id = id;
		this.value = value;
	}

	
	
}
