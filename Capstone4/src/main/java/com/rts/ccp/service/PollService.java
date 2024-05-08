package com.rts.ccp.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.ccp.bean.Department;
import com.rts.ccp.bean.PollOption;
import com.rts.ccp.bean.OptionResponse;
import com.rts.ccp.bean.Poll;
import com.rts.ccp.dto.MappingDTO;
import com.rts.ccp.dto.PollOptionDTO;
import com.rts.ccp.dto.PollDTO;
import com.rts.ccp.bean.Project;
import com.rts.ccp.bean.Region;
import com.rts.ccp.bean.User;
import com.rts.ccp.repository.DepartmentRepo;
import com.rts.ccp.repository.PollOptionRepo;
import com.rts.ccp.repository.OptionResponseRepo;
import com.rts.ccp.repository.PollRepo;
import com.rts.ccp.repository.ProjectRepo;
import com.rts.ccp.repository.RegionRepo;
import com.rts.ccp.repository.UserRepo;

@Service
public class PollService {

	@Autowired
	RegionRepo rgRepo;

	@Autowired
	DepartmentRepo dtRepo;

	@Autowired
	ProjectRepo ptRepo;

	@Autowired
	Region region;

	@Autowired
	Department department;

	@Autowired
	Project project;

	@Autowired
	PollRepo pollRepo;

	@Autowired
	User user;

	@Autowired
	UserRepo userRepo;

	@Autowired
	PollOptionRepo optionRepo;

	@Autowired
	OptionResponseRepo optionResponseRepo;

//	public boolean insertPoll(Poll poll) {
//		pollRepo.save(poll);
//		return true;
//	}

	
	
	
	public PollOption storeFile(MultipartFile file,String value,Poll pollid) throws IOException {		
		PollOption dbFile = new PollOption();
		dbFile.setPicture(file.getBytes());
		dbFile.setValue(value);
		dbFile.setPollId(pollid);
		dbFile.setPictureName(file.getOriginalFilename());
		dbFile.setPictureType(file.getContentType());
	    return optionRepo.save(dbFile);
 
	}
	
	public PollOption storeOptionOnly(String value,Poll pollid) throws IOException {		
		PollOption dbFile = new PollOption();
		dbFile.setValue(value);
		dbFile.setPollId(pollid);
	    return optionRepo.save(dbFile);
	}
	
	public void updatePoll(PollDTO poll) {
		Poll newPoll = pollRepo.findById(poll.getPollId()).get();

		newPoll.setPollId(poll.getPollId());
		newPoll.setStatus(poll.isStatus());
		newPoll.setPollQuestion(poll.getPollQuestion());

		Iterator<PollOption> optionIt = poll.getOptions().iterator();
		List<PollOption> optList = new ArrayList();
		while (optionIt.hasNext()) {
			PollOption option = optionIt.next();
			PollOption opt = new PollOption();
			opt = optionRepo.findById(option.getOptionId()).get();
			opt.setValue(option.getValue());
			optList.add(opt);
		}
		newPoll.setOptions(optList);
		pollRepo.save(newPoll);

	}

	public void updatePollQuestion(PollDTO poll) {
		Poll updatePoll = pollRepo.findById(poll.getPollId()).get();
		updatePoll.setPollQuestion(poll.getPollQuestion());
//		updatePoll.setOptions(poll.getOptions());
		Iterator<PollOption> optionIt = poll.getOptions().iterator();
		List<PollOption> optList = new ArrayList();
		while (optionIt.hasNext()) {
			PollOption option = optionIt.next();
			PollOption opt = new PollOption();
			opt = optionRepo.findById(option.getOptionId()).get();
			opt.setValue(option.getValue());
			optList.add(opt);
		}
		updatePoll.setOptions(optList);
		pollRepo.save(updatePoll);
	}

	/*
	 * public boolean insertPoll(PollDTO pollDto) {
	 * 
	 * Poll poll = new Poll(); poll.setPollQuestion(pollDto.getPollQuestion());
	 * poll.setOptions(pollDto.getOptions());
	 * poll.setTimeStamp(pollDto.getTimeStamp());
	 * poll.setEndDate(pollDto.getEndDate());
	 * 
	 * poll.setStatus(pollDto.isStatus());
	 * poll.setCommentStatus(pollDto.isCommentStatus()); poll.setVisibility("Open");
	 * 
	 * region = rgRepo.findById(pollDto.getRegion()).get(); poll.setRegion(region);
	 * 
	 * department = dtRepo.findById(pollDto.getDepartment()).get();
	 * poll.setDepartment(department);
	 * 
	 * project = ptRepo.findById(pollDto.getProject()).get();
	 * poll.setProject(project);
	 * 
	 * user = userRepo.findById(pollDto.getUser()).get(); poll.setUser(user);
	 * 
	 * pollRepo.save(poll); return true; }
	 */

	public boolean insertPoll(PollDTO pollDto) {
		Poll poll = new Poll();
		poll.setPollQuestion(pollDto.getPollQuestion());
		poll.setOptions(pollDto.getOptions());
		poll.setTimeStamp(pollDto.getTimeStamp());
		poll.setEndDate(pollDto.getEndDate());
		poll.setStatus(pollDto.isStatus());
		poll.setVisibility("Open");

		if (pollDto.getValue().equalsIgnoreCase("Region")) {
			Region region = rgRepo.findById(pollDto.getId()).get();
//			Department dep = new Department();
//			dep.setDepartmentId((long) 1);
			poll.setRegion(region);
			poll.setDepartment(null);
			poll.setProject(null);

		} else if (pollDto.getValue().equalsIgnoreCase("Department")) {
			poll.setRegion(null);
			Department department = dtRepo.findById(pollDto.getId()).get();
			poll.setDepartment(department);
			poll.setProject(null);
		} else if (pollDto.getValue().equalsIgnoreCase("Project")) {
			poll.setRegion(null);
			poll.setDepartment(null);
			Project project = ptRepo.findById(pollDto.getId()).get();
			poll.setProject(project);
		}

		User user = userRepo.findById(pollDto.getUser()).orElse(null);
		poll.setUser(user);

		pollRepo.save(poll);
		return true;
	}

	public boolean deletePoll(long pollId) {
		pollRepo.deleteById(pollId);
		return true;
	}

	public List<MappingDTO> getRegDepPro(long userId) {
		User user = userRepo.findById(userId).get();
		List<MappingDTO> listMapping = new ArrayList();
		MappingDTO mappingRegionDTO = new MappingDTO();
		MappingDTO mappingDepartmentDTO = new MappingDTO();
		Region region = user.getRegion().get(0);
		mappingRegionDTO.setId(region.getRegionId());
		mappingRegionDTO.setName(region.getRegionName());
		mappingRegionDTO.setValue("Region");
		Department department = user.getDepartment().get(0);
		mappingDepartmentDTO.setId(department.getDepartmentId());
		mappingDepartmentDTO.setName(department.getDepartmentName());
		mappingDepartmentDTO.setValue("Department");
		listMapping.add(mappingRegionDTO);
		listMapping.add(mappingDepartmentDTO);
		Iterator<Project> it = user.getProject().iterator();
		while (it.hasNext()) {
			MappingDTO mapDTO = new MappingDTO();
			Project project = it.next();
			mapDTO.setId(project.getProjectId());
			mapDTO.setName(project.getProjectName());
			mapDTO.setValue("Project");
			listMapping.add(mapDTO);
		}

		return listMapping;
	}

	public List<Poll> getAllPollDetails() {
		Iterator<Poll> it = pollRepo.findAll().iterator();
		ArrayList<Poll> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<Poll> pollDetails(long pollId) {
		Iterator<Poll> it = pollRepo.findByPollId(pollId).iterator();
		ArrayList<Poll> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<PollDTO> getPublishedPoll() {

//		List<Poll> allPoll = pollRepo.findAll();

		Iterator<Poll> allPoll = pollRepo.findAll().iterator();
		List<PollDTO> pollDtoList = new ArrayList<>();
		while (allPoll.hasNext()) {
			PollDTO pollDto = new PollDTO();
			Poll poll = allPoll.next();
			Region region = poll.getRegion();
			if (region == null) {
				pollDto.setRegion(0);
			} else {
				pollDto.setRegion(region.getRegionId());
				pollDto.setRegionName(region.getRegionName());
			}

			Department depart = poll.getDepartment();
			if (depart == null) {
				pollDto.setDepartment(0);
			} else {
				pollDto.setDepartment(depart.getDepartmentId());
				pollDto.setDepartmentName(depart.getDepartmentName());
			}
			Project project = poll.getProject();
			if (project == null) {
				pollDto.setProject(0);
			} else {
				pollDto.setProject(project.getProjectId());
				pollDto.setProjectName(project.getProjectName());
			}
			pollDto.setPollId(poll.getPollId());
			pollDto.setPollQuestion(poll.getPollQuestion());
//			pollDto.setOptions(poll.getOptions());
			Iterator<PollOption> itOpt=poll.getOptions().iterator();
            List<PollOptionDTO> optionDtoList=new ArrayList<>();
			while(itOpt.hasNext()) {
				PollOptionDTO optionDto=new PollOptionDTO();
				PollOption option=itOpt.next();
			if( option.getPicture()== null) {
				optionDto.setNullPicture(true);
				optionDto.setValue(option.getValue());
				optionDto.setOptionId(option.getOptionId());
				Poll poll1 = option.getPollId();
				optionDto.setPollId(poll.getPollId());
			}else {
				optionDto.setNullPicture(false);
				optionDto.setPicture(option.getPicture());
				optionDto.setPictureName(option.getPictureName());
				optionDto.setPictureType(option.getPictureType());
				optionDto.setValue(option.getValue());
				optionDto.setOptionId(option.getOptionId());
				Poll poll1 = option.getPollId();
				optionDto.setPollId(poll.getPollId());
			}
			optionDtoList.add(optionDto);
			}
	        pollDto.setOptionList(optionDtoList);	
			pollDto.setTimeStamp(poll.getTimeStamp());
			pollDto.setStatus(poll.isStatus());
			User user = poll.getUser();
			pollDto.setUser(user.getUserId());
			pollDto.setUserFirstName(user.getUserFirstName());
			pollDto.setVisibility(poll.getVisibility());
			pollDto.setEndDate(poll.getEndDate());
			pollDtoList.add(pollDto);

			Collections.reverse(pollDtoList);

		}
		return pollDtoList;
	}

	public List<Poll> getDraftPoll() {
		List<Poll> allPoll = pollRepo.findAll();
		return allPoll.stream().filter(poll -> !poll.isStatus()) // Filter by negation
				.sorted(Comparator.comparing(Poll::getPollId).reversed()).collect(Collectors.toList());
	}

	public boolean compareDate() {
		Iterator<Poll> it = pollRepo.findAll().iterator();
		while (it.hasNext()) {
			Poll pr = it.next();
			LocalDate today = LocalDate.now();
			String date = pr.getEndDate().toString();
			LocalDate givenDate = LocalDate.parse(date);
			System.out.println(pr.getEndDate());
			if (today.isAfter(givenDate)) {
				pr.setPollId(pr.getPollId());
				pr.setVisibility("Closed");
			}
			pollRepo.save(pr);
		}
		return true;
	}

	public PollDTO getPollDetails(long pollId) {

		Poll poll = pollRepo.findById(pollId).get();

		List<PollOption> list = poll.getOptions();

//		System.out.println("Output:" + list.get(0).getValue() + "OptionId:" + list.get(0).getOptionId() + "PollId:"
//				+ list.get(0).getPollId().getPollId());
		PollDTO dto = new PollDTO();

		dto.setPollId(poll.getPollId());
		dto.setPollQuestion(poll.getPollQuestion());
		dto.setOptions(poll.getOptions());

		return dto;
	}

	public List<PollOptionDTO> getPollOptions(long pollId) {

		Iterator<PollOption> itOp = optionRepo.findByPollId(pollId).iterator();
		List<PollOptionDTO> opList = new ArrayList<PollOptionDTO>();
		while (itOp.hasNext()) {
			PollOption option = itOp.next();
			PollOptionDTO optDto = new PollOptionDTO();
			optDto.setOptionId(option.getOptionId());
			Poll poll = option.getPollId();
			optDto.setPollId(poll.getPollId());
			optDto.setValue(option.getValue());
			opList.add(optDto);
		}

		return opList;
	}

	public PollDTO getResponses(PollDTO pollDto) {
		Iterator<OptionResponse> opRes = optionResponseRepo.findAll().iterator();
//		List<PollDTO> pollDtoList=new ArrayList<>();
		PollDTO pollDto1 = new PollDTO();
		while (opRes.hasNext()) {
			OptionResponse optRes = opRes.next();
			if (pollDto.getUser() == optRes.getUserId() && pollDto.getPollId() == optRes.getPollId()) {
				pollDto1.setUser(optRes.getUserId());
				pollDto1.setResponse(true);
				pollDto1.setPollId(optRes.getPollId());
				break;
			}
		}
		return pollDto1;
	}

	public List<PollDTO> getPollAllResponse(long userId) {

		Iterator<OptionResponse> opRes = optionResponseRepo.findAll().iterator();
		List<PollDTO> opList = new ArrayList<PollDTO>();
		while (opRes.hasNext()) {
			OptionResponse optRes = opRes.next();
			if (userId == optRes.getUserId()) {
				PollDTO pollDto = new PollDTO();
				pollDto.setUser(optRes.getUserId());
				pollDto.setResponse(true);
				pollDto.setPollId(optRes.getPollId());
				opList.add(pollDto);
			} else {

			}

		}

		return opList;
	}

	public List<Poll> getQuestionDetails(String pollQuestion) {
		List<Poll> list = pollRepo.findByPollQuestionContaining(pollQuestion);

		System.out.println(list);
		return list;
	}

	public long getNumberOfPolls() {
		return pollRepo.count();
	}
}
