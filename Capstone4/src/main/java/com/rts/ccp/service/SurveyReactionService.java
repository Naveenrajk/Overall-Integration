package com.rts.ccp.service;

import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.SurveyReaction;
import com.rts.ccp.bean.Region;
import com.rts.ccp.bean.Survey;
import com.rts.ccp.bean.User;
import com.rts.ccp.dto.SurveyReactionDTO;
import com.rts.ccp.repository.SurveyReactionRepo;
import com.rts.ccp.repository.SurveyRepo;
import com.rts.ccp.repository.UserRepo;

@Service
public class SurveyReactionService {

	@Autowired
	SurveyReactionRepo rnRepo;
	
	@Autowired
	SurveyRepo srRepo;
	
	@Autowired
	UserRepo urRepo;

	public synchronized boolean insert(SurveyReaction reaction) {
		rnRepo.save(reaction);
		return true;
	}

	public boolean update(SurveyReaction reaction) {
		rnRepo.save(reaction);
		return true;
	}

//	public boolean delete(long reactionId) {
//		rnRepo.deleteById(reactionId);
//		return true;
//	}
	
	public boolean delete(SurveyReactionDTO reaction) {
		long reactionId=rnRepo.getReactionByUserSurvey(reaction.getSurveyId(), reaction.getUserId());
		rnRepo.deleteById(reactionId);
		return true;
	}
	
	
	public long getReactionCount(long surveyId){
        return rnRepo.getReactionCount(surveyId);
    }
	
	public List<SurveyReactionDTO> getReactionCountByRegion(long userId){
		
		User user=urRepo.findById(userId).get();
//		List<Region> reg=user.getRegion();
//		Iterator<Survey> it=srRepo.findByRegionId(reg.getRegionId()).iterator();
		ArrayList<SurveyReactionDTO> list=new ArrayList<SurveyReactionDTO>();
//		while(it.hasNext()) {
//			Survey survey=it.next();
//			SurveyReactionDTO resp=new SurveyReactionDTO();
////			resp.setSurveyId(survey.getSurveyId());
//			resp.setSurveyId(survey.getSurveyId());
////			resp.setUserId(usr.getUserId());
////			if(userId==)
//			Iterator<Reaction> react=rnRepo.getBySurveyId(survey.getSurveyId()).iterator();
//			while(react.hasNext()) {
//				Reaction reaction=react.next();
////				ReactionDTO resp=new ReactionDTO();
////				resp.setReaction(false);
//				User usr=reaction.getUserId();
//				if(userId==usr.getUserId()) {
//					resp.setReaction(true);
//				}else {
//					resp.setReaction(false);
//				}
//			}
//			resp.setReactionCount(rnRepo.getReactionCount(survey.getSurveyId()));
//	        list.add(resp);
//		}
        return list;
    }
	
	public boolean deleteReactionById() {
		rnRepo.deletebylastId();
		return true;
	}
	
	public boolean insertReaction(SurveyReactionDTO reaction) {
		Survey survey = srRepo.findById(reaction.getSurveyId()).get();
		User user = urRepo.findById(reaction.getUserId()).get();
		
		SurveyReaction reactions= new SurveyReaction();
		reactions.setSurveyId(survey);
		reactions.setUserId(user);
		
		rnRepo.save(reactions);
		return true;
	}

	public List<SurveyReaction> getAllReactionDetails() {
		Iterator<SurveyReaction> it = rnRepo.findAll().iterator();
		ArrayList<SurveyReaction> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<SurveyReaction> DepartmentDetails() {
		Iterator<SurveyReaction> it = rnRepo.getReaction().iterator();
		ArrayList<SurveyReaction> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

}
