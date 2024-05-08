package com.rts.ccp.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.Department;
import com.rts.ccp.bean.Pages;
import com.rts.ccp.bean.Project;
import com.rts.ccp.bean.Region;
import com.rts.ccp.bean.Response;
import com.rts.ccp.bean.ResponseDetails;
import com.rts.ccp.bean.Survey;
import com.rts.ccp.bean.User;
import com.rts.ccp.dto.MappingDTO;
import com.rts.ccp.dto.SurveyDTO;
import com.rts.ccp.repository.DepartmentRepo;
import com.rts.ccp.repository.PagesRepo;
import com.rts.ccp.repository.ProjectRepo;
import com.rts.ccp.repository.RegionRepo;
import com.rts.ccp.repository.ResponseDetailsRepo;
import com.rts.ccp.repository.SurveyRepo;
import com.rts.ccp.repository.UserRepo;

@Service
public class SurveyService {

	@Autowired
	SurveyRepo srRepo;

	@Autowired
	RegionRepo rgRepo;

	@Autowired
	DepartmentRepo dtRepo;

	@Autowired
	ProjectRepo ptRepo;
	
	@Autowired
	UserRepo urRepo;

	@Autowired
	Survey survey;
	
	@Autowired
	User user;
	
	@Autowired
	Pages pages;
	
	@Autowired
	PagesRepo pagesRepo;

	@Autowired
	Region region;

	@Autowired
	Department department;
	
	@Autowired
	ResponseDetailsRepo resDetRepo;

	@Autowired
	Project project;

	public synchronized Response insert(SurveyDTO surveydto) {

		survey.setSurveyId(srRepo.count() + 1);
		survey.setSurveyName(surveydto.getSurveyName());
		survey.setEndDate(surveydto.getEndDate());
		User user=urRepo.findById(surveydto.getUser()).get();
		
		if(surveydto.getValue().equalsIgnoreCase("Region")) {
			region = rgRepo.findById(surveydto.getId()).get();
			survey.setRegion(region);
			survey.setDepartment(null);
			survey.setProject(null);
		}
		else if(surveydto.getValue().equalsIgnoreCase("Department")) {
			survey.setRegion(null);
			department = dtRepo.findById(surveydto.getId()).get();
			survey.setDepartment(department);
			survey.setPage(null);
		}
		else {
			survey.setRegion(null);
			survey.setDepartment(null);
			project = ptRepo.findById(surveydto.getId()).get();
			survey.setProject(project);
		}
        survey.setUser(user);
		survey.setStatus("Open");
		survey.setDeleteStatus("Not Deleted");
		srRepo.save(survey);
		
		Iterator<Survey> it=srRepo.findAll().iterator();
		ArrayList<Survey> surveyList=new ArrayList<Survey>();
		while(it.hasNext()) {
			surveyList.add(it.next());
		}
		int b=surveyList.size();
		Survey sur=surveyList.get(b-1);
		Long value=sur.getSurveyId();
		Response response=new Response();
		response.setMessage(value.toString());
		return response;
	}
	
	

	public boolean update(Survey survey) {
		srRepo.save(survey);
		return true;
	}

	public boolean delete(long surveyId) {
//		srRepo.deleteById(surveyId);
		Survey survey=srRepo.findById(surveyId).get();
		survey.setDeleteStatus("Deleted");
		srRepo.save(survey);
		return true;
	}

	public List<Survey> getSurveyDetails(String surveyName) {
		Iterator<Survey> it1 = srRepo.findBySurveyName(surveyName).iterator();
		ArrayList<Survey> surveyList = new ArrayList<>();
		while (it1.hasNext()) {
			surveyList.add(it1.next());
		}
		return surveyList;
	}
	
	public List<SurveyDTO> getSurvey(long userId) {
		User user=urRepo.findById(userId).get();
//		Region reg=user.getRegion();
//		reg.getRegionId()
		Iterator<Survey> it1 = srRepo.findByRegionId(userId).iterator();
		ArrayList<SurveyDTO> surveyList = new ArrayList<>();
		while (it1.hasNext()) {
			SurveyDTO surDTO=new SurveyDTO();
			Survey sur=it1.next();
			if(sur.getDeleteStatus().equalsIgnoreCase("Not Deleted")) {
				surDTO.setSurveyId(sur.getSurveyId());
				surDTO.setSurveyName(sur.getSurveyName());
				surDTO.setEndDate(sur.getEndDate());
				surDTO.setStatus(sur.getStatus());
				User usr=sur.getUser();
				surDTO.setUser(usr.getUserId());
				surDTO.setUserName(usr.getUserFirstName());
				Region rg=sur.getRegion();
				if(rg==null) {
					surDTO.setRegion(0);
				}else {
					surDTO.setRegion(rg.getRegionId());
				}
				Department dt=sur.getDepartment();
				if(dt==null) {
					surDTO.setDepartment(0);
				}else {
				surDTO.setDepartment(dt.getDepartmentId());
				}
				Project pr=sur.getProject();
				if(pr==null) {
					surDTO.setProject(0);
				} else {
					surDTO.setProject(pr.getProjectId());
				}
				Iterator<ResponseDetails> itResDet=resDetRepo.findByUserId(userId).iterator();
				while(itResDet.hasNext()) {
					ResponseDetails responseDetails=itResDet.next();
					Survey survey=responseDetails.getSurvey();
					if(survey.getSurveyId()==sur.getSurveyId()) {
						surDTO.setResponse(true);
					}
				}
				surveyList.add(surDTO);
		
			}
			
		}
		return surveyList;
	}
	
	public List<SurveyDTO> getSurveyByUser(long userId) {
		Iterator<Survey> it1 = srRepo.findByUserId(userId).iterator();
		ArrayList<SurveyDTO> surveyList = new ArrayList<>();
		while (it1.hasNext()) {
			SurveyDTO surDTO=new SurveyDTO();
			Survey sur=it1.next();
			if(sur.getDeleteStatus().equalsIgnoreCase("Not Deleted")) {
				surDTO.setSurveyId(sur.getSurveyId());
				surDTO.setSurveyName(sur.getSurveyName());
				surDTO.setEndDate(sur.getEndDate());
				surDTO.setStatus(sur.getStatus());
				surDTO.setPage(sur.getPage());
				surDTO.setResponseDetails(sur.getResponseDetails());
				surDTO.setReaction(sur.getReaction());
				User user=sur.getUser();
				surDTO.setUser(user.getUserId());
				surDTO.setUserName(user.getUserFirstName());
				Region region=sur.getRegion();
				if(region==null) {
					surDTO.setRegion(0);
				}
				else {
					surDTO.setRegion(region.getRegionId());
				}
				Iterator<ResponseDetails> itResDet=resDetRepo.findByUserId(userId).iterator();
				while(itResDet.hasNext()) {
					ResponseDetails responseDetails=itResDet.next();
					Survey survey=responseDetails.getSurvey();
					if(survey.getSurveyId()==sur.getSurveyId()) {
						surDTO.setResponse(true);
					}
				}
				surveyList.add(surDTO);
			} 
		}
		return surveyList;
	}
	
	public List<SurveyDTO> getSurveyRegionUser(long userId) {
		User user=urRepo.getByUserId(userId);
//		Region reg=user.getRegion();
//		reg.getRegionId()
		Iterator<Survey> it1 = srRepo.findByRegionUser(1,userId).iterator();
		ArrayList<SurveyDTO> surveyList = new ArrayList<>();
		while (it1.hasNext()) {
//			surveyList.add(it1.next());
			SurveyDTO surDTO=new SurveyDTO();
			Survey sur=it1.next();
			if(sur.getDeleteStatus().equalsIgnoreCase("Not Deleted")) {
				surDTO.setSurveyId(sur.getSurveyId());
				surDTO.setSurveyName(sur.getSurveyName());
				surDTO.setEndDate(sur.getEndDate());
				surDTO.setStatus(sur.getStatus());
				surDTO.setPage(sur.getPage());
				surDTO.setResponseDetails(sur.getResponseDetails());
				surDTO.setReaction(sur.getReaction());
				User usr=sur.getUser();
				surDTO.setUser(usr.getUserId());
				surDTO.setUserName(usr.getUserFirstName());
				Region region=sur.getRegion();
				surDTO.setRegion(region.getRegionId());
				Iterator<ResponseDetails> itResDet=resDetRepo.findByUserId(userId).iterator();
				while(itResDet.hasNext()) {
					ResponseDetails responseDetails=itResDet.next();
					Survey survey=responseDetails.getSurvey();
					if(survey.getSurveyId()==sur.getSurveyId()) {
						surDTO.setResponse(true);
					}
				}
				surveyList.add(surDTO);
			}
		}
		return surveyList;
	}

//	public List<Survey> getAllSurveyDetails() {
//		Iterator<Survey> it = srRepo.findAll().iterator();
//		ArrayList<Survey> list = new ArrayList<>();
//		while (it.hasNext()) {
//			list.add(it.next());
//		}
//		return list;
//	}
	
	public List<MappingDTO> getRegDepPro(long userId){
		User user=urRepo.findById(userId).get();
		List<MappingDTO> listMapping=new ArrayList();
		MappingDTO mappingRegionDTO=new MappingDTO();
		MappingDTO mappingDepartmentDTO=new MappingDTO();
//		Region region=user.getRegion();
//		mappingRegionDTO.setId(region.getRegionId());
//		mappingRegionDTO.setName(region.getRegionName());
//		mappingRegionDTO.setValue("Region");
//		Department department=user.getDepartment();
//		mappingDepartmentDTO.setId(department.getDepartmentId());
//		mappingDepartmentDTO.setName(department.getDepartmentName());
//		mappingDepartmentDTO.setValue("Department");
//		listMapping.add(mappingRegionDTO);
//		listMapping.add(mappingDepartmentDTO);
		Iterator<Project> it=user.getProject().iterator();
		while(it.hasNext()) {
			MappingDTO mapDTO=new MappingDTO();
			Project project=it.next();
			mapDTO.setId(project.getProjectId());
			mapDTO.setName(project.getProjectName());
			mapDTO.setValue("Project");
			listMapping.add(mapDTO);
		}
		
		return listMapping;
	}
	
	public List<Survey> getAllSurveyDetails(long userId) {
		List<Survey> it = srRepo.findAll();
		User user=urRepo.getByUserId(userId);
//		Region reg=user.getRegion();
//		long val=reg.getRegionId();
		return it.stream()
//				.filter(survey -> survey.getRegion()==reg)
//				.filter(survey -> survey.getStatus()=="Closed")
				.sorted(Comparator.comparing(Survey::getSurveyId).reversed())
				.toList();
//		ArrayList<Survey> list = new ArrayList<>();
//		while (it.hasNext()) {
//			list.add(it.next());
//		}
//		return list;
	}
	
	public SurveyDTO getSurveyById(long surveyId) {
		Survey survey=srRepo.findById(surveyId).get();
		SurveyDTO surveyDto=new SurveyDTO();
		surveyDto.setSurveyName(survey.getSurveyName());
		surveyDto.setStatus(survey.getStatus());
		surveyDto.setEndDate(survey.getEndDate());
		return surveyDto;
	}
	
	public boolean compareDate() {
		Iterator<Survey> it=srRepo.findAll().iterator();
		while(it.hasNext()) {
		Survey sur=it.next();
		LocalDate today=LocalDate.now();
		String date=sur.getEndDate().toString();
		LocalDate givenDate=LocalDate.parse(date);
		 System.out.println(sur.getEndDate());
//		 Survey survey1=new Survey();
		if(today.isAfter(givenDate)) {
			sur.setSurveyId(sur.getSurveyId());
			sur.setStatus("Closed");
		}
		srRepo.save(sur);
		}
		return true;
	}

}
