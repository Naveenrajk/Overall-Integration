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
@Table(name = "tbl_response")
@Component
@Getter
@Setter
public class Responses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "response_id")
	private long responseId;

	@Column(name = "response_question")
	private String responseQuestion;

	@Column(name = "response_answer")
	private String responseAnswer;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="responsedetail_id")
	private ResponseDetails responseDetails;

	public Responses() {
		super();
	}

	public Responses(long responseId, String responseQuestion, String responseAnswer, ResponseDetails responseDetails) {
		super();
		this.responseId = responseId;
		this.responseQuestion = responseQuestion;
		this.responseAnswer = responseAnswer;
		this.responseDetails = responseDetails;
	}
}
