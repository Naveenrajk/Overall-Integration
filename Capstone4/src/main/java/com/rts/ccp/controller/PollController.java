package com.rts.ccp.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rts.ccp.bean.Department;
import com.rts.ccp.bean.PollOption;
import com.rts.ccp.bean.Poll;
import com.rts.ccp.bean.Project;
import com.rts.ccp.bean.Region;
import com.rts.ccp.bean.User;
import com.rts.ccp.dto.MappingDTO;
import com.rts.ccp.dto.PollOptionDTO;
import com.rts.ccp.dto.PollDTO;
import com.rts.ccp.repository.DepartmentRepo;
import com.rts.ccp.repository.PollRepo;
import com.rts.ccp.repository.ProjectRepo;
import com.rts.ccp.repository.RegionRepo;
import com.rts.ccp.repository.UserRepo;
import com.rts.ccp.service.PollService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class PollController {

	@Autowired
	PollService pollService;

	@Autowired
	PollRepo pollRepo;
	
	@Autowired(required = false)
	PollDTO pollDto;
	
	@Autowired
	RegionRepo rgRepo;

	@Autowired
	DepartmentRepo dtRepo;

	@Autowired
	ProjectRepo ptRepo;
	
	@Autowired
	UserRepo userRepo;

	
	
//	@PostMapping("/poll")
//	public void performPollInsert(@RequestBody Poll poll) {
//		pollService.insertPoll(poll);
//
//	}
	
	@PostMapping("/poll")
	public void uploadpdf(
			@RequestParam("pollQuestion") String pollQuestion,
			@RequestParam("endDate") String endDate,@RequestParam("status") boolean status,
			@RequestParam("visibility") String visibility,@RequestParam("id") String id,@RequestParam("values") String values,
			@RequestParam(value = "file", required = false) List<MultipartFile> file, 
			@RequestParam("value") List<String> value,@RequestParam("userId") long userId)
			throws IOException, ParseException {
		Poll poll = new Poll();
		
		poll.setPollQuestion(pollQuestion);

		System.out.println(endDate);

		
		String dateString = endDate;
		Date sqlDate = Date.valueOf(dateString);
		System.out.println(sqlDate);
		
		poll.setEndDate(sqlDate);
		poll.setStatus(status);
		poll.setVisibility(visibility);

		if (values.equalsIgnoreCase("Region")) {
			Region region = rgRepo.findById(Long.parseLong(id)).get();
			poll.setRegion(region);
			poll.setDepartment(null);
			poll.setProject(null);

		} else if (values.equalsIgnoreCase("Department")) {
			poll.setRegion(null);
			Department department = dtRepo.findById(Long.parseLong(id)).get();
			poll.setDepartment(department);
			poll.setProject(null);
		} else if (values.equalsIgnoreCase("Project")) {
			poll.setRegion(null);
			poll.setDepartment(null);
			Project project = ptRepo.findById(Long.parseLong(id)).get();
			poll.setProject(project);
		}
		 
		User user = userRepo.findById(userId).orElse(null);
		poll.setUser(user);

		PollOption option = new PollOption();
 
		for (int i = 0; i <= value.size() - 1; i++) {
			if (file == null || file.isEmpty()) {
				option = pollService.storeOptionOnly(value.get(i), poll);
			} else {
				option = pollService.storeFile(file.get(i), value.get(i), poll);
			}
 
		}

		pollRepo.save(poll);
	}
	

	@PostMapping("/pollResponse")
	public PollDTO getResponse(@RequestBody PollDTO poll) throws Exception {
		return pollService.getResponses(poll);

	}

	@PutMapping("/updateDraft")
	public void performPollUpdate(@RequestBody PollDTO poll) {
		
	  pollService.updatePoll(poll);
 
		
	}
	
	@PutMapping("/updatePoll")
	public void performPollQuestionUpdate(@RequestBody PollDTO poll) {
		pollService.updatePollQuestion(poll);
	}

	@DeleteMapping("/poll/{pollId}")
	public void performPollDelete(@PathVariable("pollId") long pollId) {
		pollService.deletePoll(pollId);
	}

	@GetMapping("/poll")
	public List<Poll> viewAllPollDetails() {
		return pollService.getAllPollDetails();
	}


	@GetMapping("/poll/{pollId}")
	public List<Poll> getPollById(@PathVariable("pollId") long pollId) {
		return pollService.pollDetails(pollId);
	}
	
	@GetMapping("/pollpublished")
	  public ResponseEntity<List<PollDTO>> getPublishedPoll() {
	    List<PollDTO> publishedPoll = pollService.getPublishedPoll();
	    return ResponseEntity.ok(publishedPoll);
	  }

	  @GetMapping("/polldraft")
	  public ResponseEntity<List<Poll>> getDraftPoll() {
	    List<Poll> draftPoll = pollService.getDraftPoll();
	    return ResponseEntity.ok(draftPoll);
	  }
	  
	  @GetMapping("/changeStatus")
		public boolean chageStatus() throws ParseException {
			return pollService.compareDate();
		}

	  @GetMapping("/draft/{pollId}")
		public PollDTO getPollDetailsById(@PathVariable("pollId") long pollId) {
			return pollService.getPollDetails(pollId);
		}
	  
	  @GetMapping("/draftOption/{pollId}")
		public List<PollOptionDTO> getPollOptionsById(@PathVariable("pollId") long pollId) {
			return pollService.getPollOptions(pollId);
	  }
	  
	  @GetMapping("/pollQuestion")
	  public List<Poll> getPollQuestion(@RequestParam String pollQuestion)
	  {
		  return pollService.getQuestionDetails(pollQuestion);
	  }
	  
		@GetMapping("/poll/count")
	    public long getNumberOfPolls() {
	        return pollService.getNumberOfPolls();
	}
		
		@GetMapping("/pollAllResponse/{userId}")
		public List<PollDTO> getPollAllResponses(@PathVariable("userId") long userId) {
			return pollService.getPollAllResponse(userId);
	  }
		
		@GetMapping("/getpolldepregpro/{userId}")
		public List<MappingDTO> getPollRegDepPro(@PathVariable("userId") long userId) {
			return pollService.getRegDepPro(userId);
		}
	  
}