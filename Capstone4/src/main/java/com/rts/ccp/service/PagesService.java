	package com.rts.ccp.service;

import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.SurveyOptions;
import com.rts.ccp.bean.Pages;
import com.rts.ccp.bean.Questions;
import com.rts.ccp.bean.Survey;
import com.rts.ccp.dto.PagesDTO;
import com.rts.ccp.repository.PagesRepo;
import com.rts.ccp.repository.QuestionsRepo;
import com.rts.ccp.repository.SurveyRepo;

@Service
public class PagesService {

	@Autowired
	PagesRepo pgRepo;
	
	@Autowired
    SurveyRepo srRepo;
	
	@Autowired
	QuestionsRepo qnRepo;
	
	@Autowired 
	Pages pages;
	
//	@Autowired
//	Questions questions;
	
//	@Autowired
//	Options options;
	
	public synchronized boolean insert(List<PagesDTO> pagesDto) {
		
		Iterator<PagesDTO> itP=pagesDto.iterator();
		while(itP.hasNext()) {
		PagesDTO pagesDTO=itP.next();
		Survey survey=srRepo.findById(pagesDTO.getSurveyId()).get();
		pages.setPageId(pgRepo.count()+1);
		pages.setPageNo(pagesDTO.getPageNo());
		pages.setPageTitle(pagesDTO.getPageTitle());
//		pages.setQuestion(pagesDTO.getQuestion());
		Iterator<Questions> it=pagesDTO.getQuestion().iterator();
		List<Questions> questList=new ArrayList<Questions>();
		while(it.hasNext()) {
			Questions question=new Questions();
			Questions quest=it.next();
			question.setOptionType(quest.getOptionType());
			question.setQuestionNo(quest.getQuestionNo());
			question.setMandatory(quest.isMandatory());
			question.setQuestions(quest.getQuestions());
			question.setOption(quest.getOption());
			questList.add(question);
		}
		pages.setQuestion(questList);
		pages.setSurvey(survey);
		pgRepo.save(pages);
		}
		return true;
   }
	
	public synchronized boolean update(PagesDTO pagesDTO) {
		Pages pages=pgRepo.findById(pagesDTO.getPageId()).get();
		pages.setPageTitle(pagesDTO.getPageTitle());
		pgRepo.save(pages);
		return true;
	}
	
	public boolean delete(long pageId) {
		pgRepo.deleteById(pageId);
		return true;
	}
	
	public List<Pages> getPages(long surveyId){
		Iterator<Pages> i=pgRepo.findBySurveyId(surveyId).iterator();
		ArrayList<Pages> arList=new ArrayList<>();
		while(i.hasNext()) {
			Pages pages=i.next();
			pages.getPageId();
			pages.getPageNo();
			pages.getPageTitle();
			Iterator<Questions> qList=pages.getQuestion().iterator();
			
			while(qList.hasNext()) {
				Questions questions=qList.next();
				questions.getQuestionId();
				questions.getQuestionNo();
				questions.getQuestions();
				questions.isMandatory();
				Iterator<SurveyOptions> opList=questions.getOption().iterator();
				
				while(opList.hasNext()) {
					SurveyOptions options=opList.next();
					options.getOptionId();
					options.getOptions();
				}
				
			}
			arList.add(pages);	
		}
		return arList;
	}
	
	public List<Pages> getAllPagesDetails(){
		Iterator<Pages> it=pgRepo.findAll().iterator();
		ArrayList<Pages> list=new ArrayList<>();
		while(it.hasNext()) {	
			list.add(it.next());
			
		}
		return list;
	}
	
	public List<Pages> getPagesBySurveyId(long surveyId){
		Iterator<Pages> it=pgRepo.findBySurveyId(surveyId).iterator();
		ArrayList<Pages> list=new ArrayList<>();
		while(it.hasNext()) {	
			list.add(it.next());
			
		}
		return list;
	}
	
	public long getQuestionCount(long surveyId) {
		long value=0;
		int val=0;
		List<Integer> countList=new ArrayList<>();
		Iterator<Pages> it=pgRepo.findBySurveyId(surveyId).iterator();
		ArrayList<Pages> list=new ArrayList<>();
		while(it.hasNext()) {	
			Pages pages=it.next();
			value=qnRepo.getCount(pages.getPageId());
			countList.add((int)value);
			
		}
		for(int i=0;i<countList.size();i++) {
			val += countList.get(i);
		}
		return val;
	}
	
	public long getPageCount(long surveyId) {
		long count=pgRepo.getCount(surveyId);
		return count;
	}

}
