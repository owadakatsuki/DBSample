package com.form.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.form.dao.MakeFormService;
import com.form.model.ChoicesEntity;
import com.form.model.Question;
import com.form.model.QuestionList;
import com.form.model.SendId;

@Controller
public class QuestionController {
	@Autowired
	MakeFormService questionService;
	

	@RequestMapping(value = "createNewQuestion",consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Question createNewQuestion(@RequestBody SendId content_id) {
		Question question = new Question();/*
		question.setContent_id(content_id.getId());
    	questionService.questionSave(question);*/
		return question;
	}


	@RequestMapping(value = "createNewChoice", consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ChoicesEntity createNewChoice(@RequestBody SendId content_id) {
		ChoicesEntity  choice= new ChoicesEntity();
		choice.setQuestion_id(6);
		return choice;
	}
    
	
	
	
	@RequestMapping("/question")
    public String helo(Model model) {
    	
    	// List<Question> list = questionService.findAll();
    	
    	QuestionList question_list = new QuestionList();

    	// 質問リスト作成
    	List<Question> tmp = new ArrayList<Question>();
    	Question question = new Question();
    	tmp.add(question);
    	question = new Question();
    	question.setQuestion_id(1);
    	tmp.add(question);
    	question_list.setQuestions(tmp);
    	

    	// 回答リスト作成
    	List<ChoicesEntity> choices = new ArrayList<ChoicesEntity>();
    	ChoicesEntity choice = new ChoicesEntity();
    	choices.add(choice);
    	choice = new ChoicesEntity();
    	choice.setQuestion_id(5);
    	choice.setAnswer_id(5);
    	System.out.println(choice.getQuestion_id());
    	choices.add(choice);
    	question_list.setChoices(choices);
    	
    	
    	
    	model.addAttribute("question_list", question_list);
    	
    	model.addAttribute("questions",new Question());

    	return "make_form";
    }
    
    @RequestMapping("/question_list")
    public String question_list(Model model) {
    	Iterable<Question> list = questionService.findAll();
    	model.addAttribute("datas",list);
    	return "question_list";
    }
    
    

    @RequestMapping(value="/create", params="submit", method=RequestMethod.POST)
    public String create(@ModelAttribute("question_list") QuestionList question_list, Model model) {

    	//  debag
    	for(Question q : question_list.getQuestions()) {
			System.out.println("-------------------------");
			System.out.println(q.toString());
    		for(ChoicesEntity c : question_list.getChoices()){
    			if(q.getQuestion_id() == c.getQuestion_id()){
    				System.out.println(c.toString());
    			}
    		}
    	}
    	
    	questionService.save(question_list);

    	Iterable<Question> list = questionService.findAll();
    	model.addAttribute("datas",list);
    	return "question_list";
    }
    
    
}
