package com.form.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.model.Question;
import com.form.model.QuestionRepository;

@Controller
public class QuestionController {

    @Autowired
    QuestionRepository repository;

    @RequestMapping("/question")
    public String helo(Model model) {
    	Iterable<Question> list = repository.findAll();
    	model.addAttribute("datas",list);
    	return "make_form";
    }
    
    @RequestMapping("/add")
    public String add(Model model) {
      	Question question = new Question(2, 1, "sample2-1", true, "comment");
    	repository.saveAndFlush(question);
    	Iterable<Question> list = repository.findAll();
    	model.addAttribute("datas",list);
    	return "make_form";
    }
}
