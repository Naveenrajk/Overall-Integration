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

import com.rts.ccp.bean.SurveyOptions;
import com.rts.ccp.dto.SurveyOptionDTO;
import com.rts.ccp.service.OptionService;
import com.rts.ccp.service.OptionsService;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SurveyOptionController {

	@Autowired
	OptionsService srService;

	@PostMapping("/option")
	public String performInsert(@RequestBody SurveyOptions options) {
		srService.insert(options);
		return "Record Inserted";

	}

	@PutMapping("/option")
	public String performUpdate(@RequestBody SurveyOptionDTO options) {
		srService.update(options);
		return "Record Updated";
	}

	@DeleteMapping("/option/{optionId}")
	public void performDelete(@PathVariable("optionId") long optionId) {
		srService.delete(optionId);
	}

	@GetMapping("/getOption/{surveyId}")
	public List<SurveyOptionDTO> viewAllOptions(@PathVariable("surveyId") long surveyId) {
    return srService.getAllOptions(surveyId);
	}
	
	@GetMapping("/getOptionQuestion/{questionId}")
	public List<SurveyOptionDTO> viewOptions(@PathVariable("questionId") long questionId) {
    return srService.getAllOptions(questionId);
	}
	
	@GetMapping("/getOptionPage/{pageId}")
	public List<SurveyOptionDTO> viewOptionsPage(@PathVariable("pageId") long pageId) {
    return srService.getOptionsPage(pageId);
	}

	@GetMapping("/option")
	public List<SurveyOptions> viewAllOptionsDetails() {
		return srService.getAllOptionsDetails();
	}

}
