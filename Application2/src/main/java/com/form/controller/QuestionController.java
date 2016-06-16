package com.form.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.form.model.Question;
import com.form.model.QuestionList;

@Controller
public class QuestionController {
	@Autowired
	QuestionService questionService;
	  
    @RequestMapping("/question")
    public String helo(Model model) {
    	
    	List<Question> list = questionService.findAll();
    	
    	QuestionList question_list = new QuestionList();
    	
    	List<Question> tmp = new ArrayList<Question>();
    	tmp.add(new Question("sample1", true));
    	tmp.add(new Question("sample2", false));
    	question_list.setQuestions(tmp);
    	
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
    public String create(@ModelAttribute("questions") Question question2, Model model) {  	/*
        public String create(@ModelAttribute("questionList") ArrayList<Question> question_list, Model model) {  */	
    	/*
    	System.out.println("size = " + question_list.size());
    	for(Question question : question_list) {*/
    	System.out.println(question2.toString());
      		questionService.save(question2);
    	/*
    	}*/
    	//repository.flush();
    	Iterable<Question> list = questionService.findAll();
    	model.addAttribute("datas",list);
    	return "question_list";
    }
    
    
    @RequestMapping(value="/long_create", params="add_question" , method=RequestMethod.POST)
    public String add(@ModelAttribute("question_list") QuestionList question_list, Model model) {
    	List<Question> list = question_list.getQuestions();
    	Question question = new Question();
    	list.add(question);
    	question_list.setQuestions(list);
    	model.addAttribute("questionList", question_list);
    	
    	return "make_form";

    }
}
