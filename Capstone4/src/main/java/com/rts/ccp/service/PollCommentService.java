package com.rts.ccp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.PollComment;
import com.rts.ccp.dto.PollCommentDTO;
import com.rts.ccp.bean.Poll;
import com.rts.ccp.bean.User;
import com.rts.ccp.repository.PollCommentRepo;
import com.rts.ccp.repository.PollRepo;
import com.rts.ccp.repository.UserRepo;

@Service
public class PollCommentService {

	@Autowired
	PollCommentRepo commentRepo;

	@Autowired
	PollRepo pollRepo;

	@Autowired
	UserRepo userRepo;

//	public boolean insertComment(Comment comment) {
//		commentRepo.save(comment);
//		return true;
//	}

	public boolean insertComment(PollCommentDTO cmt) {

		Poll poll = pollRepo.findById(cmt.getPollId()).get();
		User user = userRepo.findById(cmt.getUserId()).get();

		PollComment comment = new PollComment();
		comment.setBody(cmt.getBody());
		comment.setCreatedAt(cmt.getCreatedAt());
		comment.setParentId(cmt.getParentId());
		comment.setPoll(poll);
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

	public boolean updateComment(long id) {
		commentRepo.updateById(id);
		return true;
	}

	public boolean deleteComment(Long id) {
		commentRepo.deleteById(id);
		return true;
	}

	public List<PollComment> getAllCommentDetails() {
		Iterator<PollComment> it = commentRepo.findAll().iterator();
		ArrayList<PollComment> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<PollComment> getComment(long pollId) {
		Iterator<PollComment> it = commentRepo.findByPollId(pollId).iterator();
		ArrayList<PollComment> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

}
