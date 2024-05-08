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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_survey_option")
@Component
@Getter
@Setter
public class SurveyOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_Id")
	private long optionId;

	@Column(name = "options")
	private String options;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="question_id")
	private Questions question;
	

	public SurveyOptions() {
		super();
	}

	

	public SurveyOptions(long optionId, String options, Questions question) {
		super();
		this.optionId = optionId;
		this.options = options;
		this.question = question;
	}
}
