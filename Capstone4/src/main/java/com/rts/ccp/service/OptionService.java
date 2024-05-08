package com.rts.ccp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.ccp.bean.PollOption;
import com.rts.ccp.dto.PollOptionDTO;
import com.rts.ccp.dto.PollDTO;
import com.rts.ccp.bean.Poll;
import com.rts.ccp.repository.PollOptionRepo;
import com.rts.ccp.repository.PollRepo;

@Service
public class OptionService {

	@Autowired
	PollOptionRepo optionRepo;

	@Autowired
	PollRepo pollrepo;
	
	

	public boolean updateOption(PollOption option) {
		optionRepo.save(option);
		return true;
	}

	public boolean deleteOption(long optionId) {
		optionRepo.deleteById(optionId);
		return true;
	}

	public List<PollOption> getAllOptionDetails() {
		Iterator<PollOption> it = optionRepo.findAll().iterator();
		ArrayList<PollOption> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<PollOptionDTO> optionsDetails() {
		Iterator<PollOption> itOpt=optionRepo.findAll().iterator();
        List<PollOptionDTO> optionDtoList=new ArrayList<>();
		while(itOpt.hasNext()) {
			PollOptionDTO optionDto=new PollOptionDTO();
			PollOption option=itOpt.next();
		if( option.getPicture()== null) {
			optionDto.setNullPicture(true);
			optionDto.setValue(option.getValue());
			optionDto.setOptionId(option.getOptionId());
			Poll poll = option.getPollId();
			optionDto.setPollId(poll.getPollId());
			
		}else {
			optionDto.setNullPicture(false);
			optionDto.setPicture(option.getPicture());
			optionDto.setPictureName(option.getPictureName());
			optionDto.setPictureType(option.getPictureType());
			optionDto.setValue(option.getValue());
			optionDto.setOptionId(option.getOptionId());
			Poll poll = option.getPollId();
			optionDto.setPollId(poll.getPollId());
		}
		optionDtoList.add(optionDto);
		}		return optionDtoList;
	}
	
	public List<PollOption> optionValues(long pollId){
		
			Iterator<PollOption> it = optionRepo.findOptionValues(pollId).iterator();
			ArrayList<PollOption> list = new ArrayList<>();
			while (it.hasNext()) {
				list.add(it.next());
			}
			return list;
		}
	public List<PollOptionDTO> optionValueDetails(long pollId) {
			Iterator<PollOption> it = optionRepo.findOptionValues(pollId).iterator();
			ArrayList<PollOptionDTO> lists = new ArrayList<>();
			while (it.hasNext()) {
//				lists.add(it.next());
				PollOption opt=it.next();
				Poll poll=opt.getPollId();
				System.out.println(poll);
				PollOptionDTO optdto =  new PollOptionDTO();
				optdto.setOptionId(opt.getOptionId());
				optdto.setPollId(poll.getPollId());
				optdto.setValue(opt.getValue());
				lists.add(optdto);
			}
			return lists;
		}
	}
