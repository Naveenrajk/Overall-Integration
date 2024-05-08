package com.rts.ccp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rts.ccp.bean.PostComment;
import com.rts.ccp.dto.PostCommentDTO;
import com.rts.ccp.service.PostCommentService;

import jakarta.transaction.Transactional;
@RestController
@CrossOrigin("http://localhost:4200/") 
public class PostCommentController {

	@Autowired
	PostCommentService commentService;
	
	@PostMapping("/postcomments")
	public void performCommentsInsert(@RequestBody PostCommentDTO comment) {
		commentService.insertComments(comment);
	}
 
    @Transactional
	@PatchMapping("/postcomments/{id}")
	public void performCommentsUpdate(@PathVariable("id") long id) {
		commentService.updateComments(id);
	}
 
	@DeleteMapping("/postcomments/{id}")
	public void performCommentsDelete(@PathVariable("id") long id) {
		commentService.deleteComments(id);
	}
 
	@GetMapping("/postcommentsFind")
	public List<PostComment> viewAllCommentsDetails() {
		return commentService.getAllCommentsDetails();
	}
}

