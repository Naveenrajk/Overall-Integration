package com.rts.ccp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.Poll;
import com.rts.ccp.bean.PollReaction;
import com.rts.ccp.bean.Region;
import com.rts.ccp.dto.PollReactionDTO;
import com.rts.ccp.bean.User;
import com.rts.ccp.repository.PollRepo;
import com.rts.ccp.repository.PollReactionRepo;
import com.rts.ccp.repository.UserRepo;

@Service
public class PollReactionService {

	@Autowired
	PollReactionRepo reactionRepo;

	@Autowired
	PollRepo pollRepo;

	@Autowired
	UserRepo userRepo;

	public boolean insertReaction(PollReactionDTO reaction) {
		Poll poll = pollRepo.findById(reaction.getPollId()).get();
		User user = userRepo.findById(reaction.getUserId()).get();

		PollReaction reactions = new PollReaction();
		reactions.setPollId(poll);
		reactions.setUserId(user);

		reactionRepo.save(reactions);
		return true;
	}

//
//	public boolean insertReaction(Reaction reaction) {
//		reactionRepo.save(reaction);
//		return true;
//	}

	public boolean updateReaction(PollReaction reaction) {
		reactionRepo.save(reaction);
		return true;
	}

//	public boolean deleteReaction(Long reactionId) {
//		reactionRepo.deleteById(reactionId);
//		return true;
//	}

	
	public boolean delete(PollReactionDTO reaction) {
		long reactionId=reactionRepo.getReactionByUserPoll(reaction.getPollId(), reaction.getUserId());
		reactionRepo.deleteById(reactionId);
		return true;
	}

	public List<PollReaction> getAllReactionDetails() {
		Iterator<PollReaction> it = reactionRepo.findAll().iterator();
		ArrayList<PollReaction> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	
	
public List<PollReactionDTO> getReactionCountByRegion(long userId){
		
		User user=userRepo.findById(userId).get();
		Region reg=user.getRegion().get(0);
		Iterator<Poll> it=pollRepo.findByRegionId(reg.getRegionId()).iterator();
		ArrayList<PollReactionDTO> list=new ArrayList<PollReactionDTO>();
		while(it.hasNext()) {
			Poll poll=it.next();
			PollReactionDTO resp=new PollReactionDTO();
//			resp.setSurveyId(survey.getSurveyId());
			resp.setPollId(poll.getPollId());
//			resp.setUserId(usr.getUserId());
//			if(userId==)
			Iterator<PollReaction> react=reactionRepo.getByPollId(poll.getPollId()).iterator();
			while(react.hasNext()) {
				PollReaction reaction=react.next();
				User usr=reaction.getUserId();
				if(userId==usr.getUserId()) {
					resp.setReaction(true);
				}else {
					resp.setReaction(false);
				}
			}
			resp.setReactionCount(reactionRepo.getReactionCount(poll.getPollId()));
	        list.add(resp);
		}
        return list;
    }

}