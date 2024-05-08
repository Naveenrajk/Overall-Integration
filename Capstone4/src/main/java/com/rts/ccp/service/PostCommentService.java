package com.rts.ccp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.PostComment;
import com.rts.ccp.bean.Post;
import com.rts.ccp.bean.User;
import com.rts.ccp.dto.PostCommentDTO;
import com.rts.ccp.repository.PostCommentRepo;
import com.rts.ccp.repository.PostRepo;
import com.rts.ccp.repository.UserRepo;




@Service
public class PostCommentService {
	
	@Autowired
	PostCommentRepo commentRepo;
	
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PostRepo postRepo;
	
	
//	@Autowired
//	CommentRepoJpa commentRepoJpa;
//	
//	public boolean insertComment(Comment comment) {
//		commentRepo.save(comment);
//		return true;
//	}
	
	public boolean insertComments(PostCommentDTO cmt) {
		
		Post post = postRepo.findById(cmt.getPostId()).get();
		User user = userRepo.findById(cmt.getUserId()).get();
		
		PostComment comment = new PostComment();
		comment.setBody(cmt.getBody());
		comment.setCreatedAt(cmt.getCreatedAt());
		comment.setParentId(cmt.getParentId());
		comment.setPost(post);
		comment.setUser(user);
		
		commentRepo.save(comment);
		return true;
	}
 
 
//	public boolean insertComment(CommentDTO cmt) {
//		Poll poll = pollRepo.findById(cmt.getPollId()).get();
//		User user = userRepo.findById(cmt.getUserid()).get();
//		
//		Comment comment = new Comment();
//		comment.setPollId(poll);
//		comment.setUser(user);
//		
//		commentRepo.save(comment);
//		return true;
//	}
 
	public boolean updateComments(long id) {
		commentRepo.updateById(id);
		return true;
	}
 
	public boolean deleteComments(Long id) {
		commentRepo.deleteById(id);
		return true;
	}
 
	public List<PostComment> getAllCommentsDetails() {
		Iterator<PostComment> it = commentRepo.findAll().iterator();
		ArrayList<PostComment> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

}

