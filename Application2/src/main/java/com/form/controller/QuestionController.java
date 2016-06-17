package com.form.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.form.dao.QuestionService;
import com.form.model.ChoicesEntity;
import com.form.model.Question;
import com.form.model.QuestionList;

@Controller
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	
	@RequestMapping(value = "getTestData", method = RequestMethod.GET)
	@ResponseBody
	public String[] getTestData() {
		System.out.println("getTime");
	    //logger.info("call getTestData");
	    String[] datas = {"test1", "test2", "test3"};
	 
	    return datas;
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
    	choice.setQestion_id(1);
    	choice.setAnswer_id(1);
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

    @RequestMapping(value="/long_create", method=RequestMethod.POST)
    public String long_create(@ModelAttribute("question_list") QuestionList question_list, Model model) {  
    	for(Question q : question_list.getQuestions()) {
    		System.out.println(q.toString());
    	}
       /*
    	Iterable<Question> list = questionService.findAll();*/
    	model.addAttribute("datas",question_list);
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
    	
    	
    	/*
        public String create(@ModelAttribute("questionList") ArrayList<Question> question_list, Model model) {  */	
    	/*
    	System.out.println("size = " + question_list.size());
    	for(Question question : question_list) {
    	
    		System.out.println(question2.toString());
      		questionService.save(question2);
    	}*/
    	//repository.flush();
    	Iterable<Question> list = questionService.findAll();
    	model.addAttribute("datas",list);
    	return "question_list";
    }
    
    
    @RequestMapping(value="/create", params="add_question" , method=RequestMethod.POST)
    public String add(@ModelAttribute("question_list") QuestionList question_list, Model model) {
   /* 	List<Question> list = question_list.getQuestions();
    	System.out.println("list.len = " + list.size());
    	int id = list.get(list.size()-1).getQuestion_id() + 1;
    	System.out.println("id = "+ id);
    	Question question = new Question();
    	question.setQuestion_id(id);
    	list.add(question);
    	question_list.setQuestions(list);*/
    	
    	/*
    	
    	List<ChoicesEntity> choice_ = question_list.getChoices();
    	ChoicesEntity choice = new ChoicesEntity();
    	choice.setQestion_id(id);
    	choice_.add(choice);
    	question_list.setChoices(choice_);*/
    	
    	model.addAttribute("questionList", question_list);
    	
    	return "make_form";

    }
}
