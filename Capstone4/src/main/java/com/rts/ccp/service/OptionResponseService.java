package com.rts.ccp.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.PollOption;
import com.rts.ccp.bean.OptionResponse;
 
import jakarta.transaction.Transactional;
 
import com.rts.ccp.repository.OptionResponseRepo;
 
@Service
public class OptionResponseService {
 
	@Autowired
	private OptionResponseRepo optionResponseRepo;
 
	public OptionResponse createVote(int pollId, int optionId, int userId) {
 
		OptionResponse optionResponse = new OptionResponse();
		optionResponse.setOptionId(optionId);
		optionResponse.setPollId(pollId);
 
		optionResponse.setUserId(userId);
		return optionResponseRepo.save(optionResponse);
 
	}
 
	@Transactional
	public boolean updateReponseById( long optionId,long userId, long pollId) {
		optionResponseRepo.updatebyId(optionId,userId, pollId);
		return true;
	}
	
	public List<OptionResponse> getAllOptionResponseDetails() {
		Iterator<OptionResponse> it = optionResponseRepo.findAll().iterator();
		ArrayList<OptionResponse> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	
	public long getVoteCount(long pollId) {
		return optionResponseRepo.getVoteCount(pollId);
		
	}
}