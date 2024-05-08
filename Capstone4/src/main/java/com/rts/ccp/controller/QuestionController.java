package com.rts.ccp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rts.ccp.bean.Questions;
import com.rts.ccp.dto.QuestionsDTO;
import com.rts.ccp.service.QuestionsService;

@RestController
@CrossOrigin("http://localhost:4200/")
public class QuestionController {

	@Autowired
	QuestionsService srService;

	@PostMapping("/question")
	public String performInsert(@RequestBody Questions question) {
		srService.insert(question);
		return "Record Inserted";

	}

	@PutMapping("/question")
	public String performUpdate(@RequestBody QuestionsDTO question) {
		srService.update(question);
		return "Record Updated";
	}

	@DeleteMapping("/question/{questionId}")
	public void performDelete(@PathVariable("questionId") long questionId) {
		srService.delete(questionId);
	}

	@GetMapping("/getquestion/{surveyId}")
	public  List<QuestionsDTO> viewQuestionsDetails(@PathVariable("surveyId") long surveyId) {
		return srService.getQuestion(surveyId);
	}
	
	@GetMapping("/getquestionpage/{pageId}")
	public List<Questions> viewByPageIdQuestionsDetails(@PathVariable("pageId") long pageId) {
		return srService.getQuestionByPageId(pageId);
	}

	@GetMapping("/question")
	public List<Questions> viewAllQuestionsDetails() {
		return srService.getAllQuestionsDetails();
	}

}