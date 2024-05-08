package com.rts.ccp.bean;

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
@Table(name = "tbl_question")
@Component
@Getter
@Setter
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private long questionId;

	@Column(name = "question_no")
	private long questionNo;

	@Column(name = "questions")
	private String questions;

	@Column(name = "option_type")
	private String optionType;
	
	@Column(name="mandatory")
	private boolean mandatory;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "page_id")
	private Pages page;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	private List<SurveyOptions> option;

	public Questions() {
		super();
	}

	public Questions(long questionId, long questionNo, String questions, String optionType, boolean mandatory,
			Pages page, List<SurveyOptions> option) {
		super();
		this.questionId = questionId;
		this.questionNo = questionNo;
		this.questions = questions;
		this.optionType = optionType;
		this.mandatory = mandatory;
		this.page = page;
		this.option = option;
	}
	
	

}
