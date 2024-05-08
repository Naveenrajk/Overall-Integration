package com.rts.ccp.dto;

import java.util.List;

import com.rts.ccp.bean.Questions;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PagesDTO {
	
	private long pageId;

	private long pageNo;

	private String pageTitle;

	private List<Questions> question;
	
	private long surveyId;

	public PagesDTO() {
		super();
	}

	public PagesDTO(long pageId, long pageNo, String pageTitle, List<Questions> question, long surveyId) {
		super();
		this.pageId = pageId;
		this.pageNo = pageNo;
		this.pageTitle = pageTitle;
		this.question = question;
		this.surveyId = surveyId;
	}
}
