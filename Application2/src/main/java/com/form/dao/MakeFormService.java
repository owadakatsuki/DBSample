package com.form.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.form.model.ChoicesEntity;
import com.form.model.ChoicesRepository;
import com.form.model.Question;
import com.form.model.QuestionList;

@Service
@Transactional
public class MakeFormService {  
	@Autowired
	  MakeFormRepository questionRepository; 
	  ChoicesRepository choicesRepository;

	  public List<Question> findAll() {
	    return questionRepository.findAll();
	  }

	  public int questionSave(Question question) {
		 // question.setContent_id(2);
		  questionRepository.saveAndFlush(question);
		  System.out.println(question.toString());
		  
	    return question.getQuestion_id();
	  }
	  
	  public int choiceSave(ChoicesEntity choice) {
		 // question.setContent_id(2);
		  choicesRepository.saveAndFlush(choice);
		  
	    return choice.getQuestion_id();
	  }
	  
	  public void save(QuestionList question_list) {
		  List<Question> questions = question_list.getQuestions();
		  List<ChoicesEntity> choices = question_list.getChoices();
		  
		  for(Question q : questions) {
			  questionSave(q);
		  }
		  
		  for(ChoicesEntity c : choices) {
			  choiceSave(c);
		  }
		  return;
	  }
	   
	  public void delete(Long id) {
	   // questionRepository.delete(id);
	  }
	   
	  public Question find(Long id) {
	     //   return questionRepository.findOne(id);
		  return new Question();
	    }

}
