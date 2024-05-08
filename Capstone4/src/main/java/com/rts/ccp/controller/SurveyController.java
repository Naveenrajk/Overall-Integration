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

import com.rts.ccp.bean.Response;
import com.rts.ccp.bean.Survey;
import com.rts.ccp.dto.MappingDTO;
import com.rts.ccp.dto.SurveyDTO;
import com.rts.ccp.service.SurveyService;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SurveyController {

	@Autowired
	SurveyService srService;

	@PostMapping("/survey")
	public Response performInsert(@RequestBody SurveyDTO survey) {
		return srService.insert(survey);
	}

	@PutMapping("/survey")
	public String performUpdate(@RequestBody Survey survey) {
		srService.update(survey);
		return "Record Updated";
	}

	@DeleteMapping("/survey/{surveyId}")
	public void performDelete(@PathVariable("surveyId") long surveyId) {
		srService.delete(surveyId);
	}

	@GetMapping("/survey/{surveyName}")
	public List<Survey> viewSurveyDetails(@PathVariable("surveyName") String surveyName) {
		return srService.getSurveyDetails(surveyName);

	}
	
	@GetMapping("/getsurveyregion/{userId}")
	public List<SurveyDTO> viewSurvey(@PathVariable("userId") long userId) {
		return srService.getSurvey(userId);

	}
	
	@GetMapping("/getsurveyuser/{userId}")
	public List<SurveyDTO> viewSurveyByUser(@PathVariable("userId") long userId) {
		return srService.getSurveyByUser(userId);

	}
	
	@GetMapping("/getsurvey/{surveyId}")
	public SurveyDTO getSurveyById(@PathVariable("surveyId") long surveyId) {
		return srService.getSurveyById(surveyId);

	}
	
	@GetMapping("/getsurveydepregpro/{userId}")
	public List<MappingDTO> getSurveyRegDepPro(@PathVariable("userId") long userId) {
		return srService.getRegDepPro(userId);
	}

//	@GetMapping("/survey/{userId}")
//	public List<Survey> viewAllSurveyDetails(@PathVariable("userId") long userId) {
//		return srService.getAllSurveyDetails(userId);
//	}
	
	@GetMapping("/surveyv/{userId}")
	public List<Survey> viewAllSurvey(@PathVariable("userId") long userId) {
		return srService.getAllSurveyDetails(userId);

	}
	
	@GetMapping("/changeStatus")
	public boolean chageStatus() {
		return srService.compareDate();
	}
	
	@GetMapping("/getsurveyregionuser/{userId}")
	public List<SurveyDTO> viewSurveyRegionUser(@PathVariable("userId") long userId) {
		return srService.getSurveyRegionUser(userId);

	}

}
