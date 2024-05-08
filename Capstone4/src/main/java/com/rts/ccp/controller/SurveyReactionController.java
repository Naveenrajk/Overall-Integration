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

import com.rts.ccp.bean.SurveyReaction;
import com.rts.ccp.dto.SurveyReactionDTO;
import com.rts.ccp.service.SurveyReactionService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SurveyReactionController {

	@Autowired
	SurveyReactionService rnService;

	@PostMapping("/reaction")
	public String performInsert(@RequestBody SurveyReaction reaction) {
		rnService.insert(reaction);
		return "Record Inserted";

	}
	
	@PostMapping("/reactionInsert")
	public void performReactionInsert(@RequestBody SurveyReactionDTO reaction) {
		rnService.insertReaction(reaction);
	}

	@PutMapping("/reaction")
	public String performUpdate(@RequestBody SurveyReaction reaction) {
		rnService.update(reaction);
		return "Record Updated";
	}

//	@DeleteMapping("/reaction/{reactionId}")
//	public void performDelete(@PathVariable("reactionId") long reactionId) {
//		rnService.delete(reactionId);
//	}
	
//    @DeleteMapping("/reaction")
//	public void performDelete(@RequestBody ReactionDTO reaction) {
//		rnService.delete(reaction);
//	}
    
    @PostMapping("/reactionDelete")
   	public void performDelete(@RequestBody SurveyReactionDTO reaction) {
   		rnService.delete(reaction);
   	}

	@GetMapping("/reaction")
	public List<SurveyReaction> viewAllReactionDetails() {
		return rnService.getAllReactionDetails();
	}
	
	@GetMapping("/getReactionCount/{surveyId}")
	public long performReactionCount (@PathVariable("surveyId") long surveyId) {
		return rnService.getReactionCount(surveyId);
		//return reactionService.getAllReactionDetails();
	}
	
	@GetMapping("/getReactionCountByRegion/{userId}")
	public List<SurveyReactionDTO> performReactionCountByRegion (@PathVariable("userId") long userId) {
		return rnService.getReactionCountByRegion(userId);
		//return reactionService.getAllReactionDetails();
	}
	
	@Transactional
	@DeleteMapping("/reactionDelete")
	public String performReactionDeleteById() {
		rnService.deleteReactionById();
		return "Reaction Deleted!";
	}

	@GetMapping("/reactiondetail")
	public List<SurveyReaction> viewDetails() {
		return rnService.DepartmentDetails();
	}

}
