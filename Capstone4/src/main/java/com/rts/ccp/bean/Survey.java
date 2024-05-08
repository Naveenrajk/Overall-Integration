package com.rts.ccp.bean;

import java.sql.Date;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_survey")
@Component
@Getter
@Setter
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "survey_id")
	private long surveyId;

	@Column(name = "survey_name")
	private String surveyName;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "status")
	private String status;
	
	@Column(name = "delete_status")
	private String deleteStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "survey_id")
	private List<Pages> page;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "survey_id")
	private List<ResponseDetails> responseDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "survey_id")
	private List<SurveyReaction> reaction;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "survey_id")
//	private List<Comment> comment;

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

	public Survey() {
		super();
	}

	

	public Survey(long surveyId, String surveyName, Date endDate, String status, String deleteStatus, List<Pages> page,
			List<ResponseDetails> responseDetails, List<SurveyReaction> reaction, User user, Region region,
			Department department, Project project) {
		super();
		this.surveyId = surveyId;
		this.surveyName = surveyName;
		this.endDate = endDate;
		this.status = status;
		this.deleteStatus = deleteStatus;
		this.page = page;
		this.responseDetails = responseDetails;
		this.reaction = reaction;
		this.user = user;
		this.region = region;
		this.department = department;
		this.project = project;
	}
}
