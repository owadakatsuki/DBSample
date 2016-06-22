package com.form.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.form.model.ChoicesEntity;
import com.form.model.Content;
import com.form.model.ContentRepository;
import com.form.model.Question;
import com.form.model.QuestionList;

@Service
@Transactional
public class MakeFormService {  
	@Autowired
	  ContentRepository contentRepository;
	@Autowired
	  QuestionRepository questionRepository; 
	@Autowired
	  ChoicesRepository choicesRepository;

	  public List<Question> findAll() {
	    return questionRepository.findAll();
	  }
	  public void createForm() {
		  Content content = new Content();
		  content.setContent_title("無題のタイトル");
		  contentRepository.save(content);
		  
		  
	  }

	  public int questionSave(Question question) {
		  questionRepository.saveAndFlush(question);
		  
	    return question.getQuestion_id();
	  }
	  
	  public int choiceSave(ChoicesEntity choice) {
		  choicesRepository.saveAndFlush(choice);
		  
	    return choice.getQuestion_id();
	  }
	  
	  public void save(QuestionList question_list) {
		  List<Question> questions = question_list.getQuestions();
		  List<ChoicesEntity> choices = question_list.getChoices();
		  
		  contentRepository.save(question_list.getContent());
		  
		  for(Question q : questions) {
			  	if(q.getQuestion_id() != 0) {
			  		questionSave(q);
			  	}
		  }
		  
		  for(ChoicesEntity c : choices) {
			  	if(c.getAnswer_id() != 0) {
			  		choiceSave(c);
			  	}
		  }
		  return;
	  }

	  public void delete(Long id) {
	   // questionRepository.delete(id);
	  }

	  public void deleteQuestion(int id) {
		  List<ChoicesEntity>choices = choicesRepository.findByQuestion_id(id);
		  for(ChoicesEntity c: choices) {
			  deleteChoice(c.getAnswer_id());
		  }
		  
		  questionRepository.deleteQuestion(id);
	  }
	  
	  public void deleteChoice(int id) {
		  choicesRepository.deleteChoice(id);
	  }
	   
	  public QuestionList findFormByContent_id(int id) {
		  	QuestionList question_list = new QuestionList();
		  	question_list.setContent(contentRepository.find(id));
		  	question_list.setQuestions(questionRepository.findByContent_id(id));
		  	question_list.setChoices(choicesRepository.findByContent_id(id));
		  	if(question_list.getQuestions().size() == 0) {
		  		Question question = new Question();
		  		question.setContent_id(id);
		  		question.setQuestion("無題の質問");
		  		questionSave(question);
		  		question_list.getQuestions().add(question);
		  		
		  		ChoicesEntity choice = new ChoicesEntity();
		  		choice.setQuestion_id(question.getQuestion_id());
		  		choice.setContent_id(id);
		  		choice.setAnswer("選択肢");
		  		choiceSave(choice);
		  		question_list.getChoices().add(choice);
		  	}
		  	return question_list;
	    }

}
