package com.rts.ccp.dto;
import java.sql.Date;
import java.util.List;

import com.rts.ccp.bean.PollComment;
import com.rts.ccp.bean.PollOption;
import com.rts.ccp.bean.PollReaction;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PollDTO {

	private long pollId;

	private String pollQuestion;

	private List<PollOption> options;

	private Date timeStamp;

	private Date endDate;

	private boolean status;
	private boolean commentStatus;

	private List<PollReaction> reaction;

	private List<PollComment> comments;

	private long user;

	private long region;

	private long department;

	private long project;

	private boolean response;

	private long id;

	private String value;

	private String visibility;

	private String userFirstName;

	private String regionName;

	private String departmentName;

	private String projectName;
	
	private List<PollOptionDTO> optionList;

	public PollDTO() {
		super();
	}

	

	public PollDTO(long pollId, String pollQuestion, List<PollOption> options, Date timeStamp, Date endDate, boolean status,
			boolean commentStatus, List<PollReaction> reaction, List<PollComment> comments, long user, long region,
			long department, long project, boolean response, long id, String value, String visibility,
			String userFirstName, String regionName, String departmentName, String projectName,
			List<PollOptionDTO> optionList) {
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
		this.response = response;
		this.id = id;
		this.value = value;
		this.visibility = visibility;
		this.userFirstName = userFirstName;
		this.regionName = regionName;
		this.departmentName = departmentName;
		this.projectName = projectName;
		this.optionList = optionList;
	}



	

}