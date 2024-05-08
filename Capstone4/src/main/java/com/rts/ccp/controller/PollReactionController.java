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

import com.rts.ccp.bean.PollReaction;
import com.rts.ccp.dto.PollReactionDTO;
import com.rts.ccp.service.PollReactionService;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PollReactionController {

	@Autowired
	PollReactionService reactionService;

	@PostMapping("/reactionInsert")
	public void performReactionInsert(@RequestBody PollReactionDTO reaction) {
		reactionService.insertReaction(reaction);
	}

//	@PostMapping("/reactionInsert")
//	public String performReactionInsert(@RequestBody Reaction reaction) {
//		reactionService.insertReaction(reaction);
//		return "Reaction Inserted!";
//	}

	@PutMapping("/reactionUpdate")
	public String performReactionUpdate(@RequestBody PollReaction reaction) {
		reactionService.updateReaction(reaction);
		return "Reaction Updated!";
	}

//	@DeleteMapping("/reactionDelete/{reactionId}")
//	public String performReactionDelete(@PathVariable("reactionId") long reactionId) {
//		reactionService.deleteReaction(reactionId);
//		return "Reaction Deleted!";
//	}

	@Transactional
	@PostMapping("/reactionDelete")
   	public void performDelete(@RequestBody PollReactionDTO reaction) {
		reactionService.delete(reaction);
   	}

	@GetMapping("/viewAllReaction")
	public List<PollReaction> viewAllReactionDetails() {
		return reactionService.getAllReactionDetails();
	}

	@GetMapping("/getReactionCount/{userId}")
	public List<PollReactionDTO> performReactionCount(@PathVariable("userId") long userId) {
		return reactionService.getReactionCountByRegion(userId);
	}

}
