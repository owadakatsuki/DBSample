package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.model.UserAnswer;
import com.form.model.UserAnswerRepository;

@Controller
public class UserAnswerController {
	 @Autowired
	 UserAnswerRepository repository;

	 @RequestMapping("/userAnswerResult")
	 public String Result( Model model ) {
	     Iterable<UserAnswer> list_user_answer = repository.findAll();
	     model.addAttribute("datas", list_user_answer);
	     return "form/userAnswerResult";
	 }

}
