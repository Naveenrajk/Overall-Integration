package com.rts.ccp.bean;


import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "tbl_poll")
@Getter
@Setter
public class Poll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "poll_id")
	private long pollId;

	@Column(columnDefinition = "longtext")
	@Lob
	private String pollQuestion;
 

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "poll_id")
	private List<PollOption> options;

	@CreationTimestamp
	@Column(name = "time_stamp")
	private Date timeStamp;

	private Date endDate;

	private boolean status;
	
	private boolean commentStatus;
	
	private String visibility;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "poll_id")
	private List<PollReaction> reaction;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "poll_id")
	private List<PollComment> comments;
	

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "region_id")
	private Region region;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "department_id")
	private Department department;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "project_id")
	private Project project;

	public Poll() {
		super();
	}

	public Poll(long pollId, String pollQuestion, List<PollOption> options, Date timeStamp, Date endDate, boolean status,boolean commentStatus,
			List<PollReaction> reaction, User user, Region region, Department department,
			Project project,String visibility, List<PollComment> comments) {
		super();
		this.pollId = pollId;
		this.pollQuestion = pollQuestion;
		this.options = options;
		this.timeStamp = timeStamp;
		this.endDate = endDate;
		this.status = status;
		this.commentStatus = commentStatus;
		this.reaction = reaction;
		this.comments = comments;
		this.user = user;
		this.region = region;
		this.department = department;
		this.project = project;
		this.visibility = visibility;
	}

}
