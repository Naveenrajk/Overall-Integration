package com.rts.ccp.service;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.SurveyOptions;
import com.rts.ccp.bean.Pages;
import com.rts.ccp.bean.Questions;
import com.rts.ccp.dto.SurveyOptionDTO;
import com.rts.ccp.repository.SurveyOptionRepo;
import com.rts.ccp.repository.PagesRepo;
import com.rts.ccp.repository.QuestionsRepo;

@Service
public class OptionsService {

	@Autowired
	SurveyOptionRepo opRepo;

	@Autowired

	PagesRepo pgRepo;

	@Autowired

	QuestionsRepo qnRepo;

	public boolean insert(SurveyOptions options) {
		opRepo.save(options);
		return true;
	}

	public boolean update(SurveyOptionDTO optionsDTO) {
		SurveyOptions options=opRepo.findById(optionsDTO.getOptionId()).get();
		options.setOptions(optionsDTO.getOptions());
		opRepo.save(options);
		return true;
	}

	public boolean delete(long optionsId) {
		opRepo.deleteById(optionsId);
		return true;
	}

	public List<SurveyOptions> getAllOptionsDetails() {
		Iterator<SurveyOptions> it = opRepo.findAll().iterator();
		ArrayList<SurveyOptions> list = new ArrayList<>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<SurveyOptionDTO> getAllOptions(long surveyId) {
		Iterator<Pages> pIt = pgRepo.findBySurveyId(surveyId).iterator();
		ArrayList<SurveyOptionDTO> opList = new ArrayList<>();
		while (pIt.hasNext()) {
//			list.add(it.next());
			Pages pages = pIt.next();
			Iterator<Questions> qIt = qnRepo.findByPageId(pages.getPageId()).iterator();
			while (qIt.hasNext()) {
				Questions question = qIt.next();
				Iterator<SurveyOptions> opIt = opRepo.findByQuestionId(question.getQuestionId()).iterator();
				while (opIt.hasNext()) {
					SurveyOptions options=opIt.next();
					SurveyOptionDTO optionsDTO=new SurveyOptionDTO();
					optionsDTO.setOptionId(options.getOptionId());
					optionsDTO.setOptions(options.getOptions());
					optionsDTO.setQuestionId(question.getQuestionId());
					opList.add(optionsDTO);
				}
			}
		}
		return opList;
	}
	
	public List<SurveyOptionDTO> getOptionsPage(long pageId) {
//		Iterator<Pages> pIt = pgRepo.findBySurveyId(surveyId).iterator();
		ArrayList<SurveyOptionDTO> opList = new ArrayList<>();
//		while (pIt.hasNext()) {
////			list.add(it.next());
//			Pages pages = pIt.next();
			Iterator<Questions> qIt = qnRepo.findByPageId(pageId).iterator();
			while (qIt.hasNext()) {
				Questions question = qIt.next();
				Iterator<SurveyOptions> opIt = opRepo.findByQuestionId(question.getQuestionId()).iterator();
				while (opIt.hasNext()) {
					SurveyOptions options=opIt.next();
					SurveyOptionDTO optionsDTO=new SurveyOptionDTO();
					optionsDTO.setOptionId(options.getOptionId());
					optionsDTO.setOptions(options.getOptions());
					optionsDTO.setQuestionId(question.getQuestionId());
					opList.add(optionsDTO);
				}
			}
//		}
		return opList;
	}
	
	public List<SurveyOptionDTO> getOptions(long questionId) {
				Questions question = qnRepo.findById(questionId).get();
				ArrayList<SurveyOptionDTO> opList = new ArrayList<>();
				Iterator<SurveyOptions> opIt = opRepo.findByQuestionId(question.getQuestionId()).iterator();
				while (opIt.hasNext()) {
					SurveyOptions options=opIt.next();
					SurveyOptionDTO optionsDTO=new SurveyOptionDTO();
					optionsDTO.setOptionId(options.getOptionId());
					optionsDTO.setOptions(options.getOptions());
					optionsDTO.setQuestionId(question.getQuestionId());
					opList.add(optionsDTO);
				}
		return opList;
	}
}
