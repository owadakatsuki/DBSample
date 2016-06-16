package com.form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.form.model.Question;
import com.form.model.QuestionRepository;

@Service
@Transactional
public class QuestionService {  
	@Autowired
	  QuestionRepository questionRepository;

	  public List<Question> findAll() {
	    return questionRepository.findAll();
	  }
	   
	  public Question save(Question question) {
		 // question.setContent_id(2);
	    return questionRepository.save(question);
	  }
	   
	  public void delete(Long id) {
	   // questionRepository.delete(id);
	  }
	   
	  public Question find(Long id) {
	     //   return questionRepository.findOne(id);
		  return new Question();
	    }

}
