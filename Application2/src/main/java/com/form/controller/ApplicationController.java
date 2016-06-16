package com.form.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.model.ChoicesEntity;
import com.form.model.ChoicesRepository;

@Controller
public class ApplicationController {

	@Autowired
	 ChoicesRepository choicesrepository;

	@RequestMapping("/")
	public String startApp(Model model){
		System.out.println("startApp来ました。");
        Page<ChoicesEntity> configs = choicesrepository.findAll(new PageRequest(0, 10));


        for (ChoicesEntity config : configs) {
            System.out.println(config.getContent_id() + " = " + config.getQuestion_id()+"です。"+config.getAnswer());
        }

		return "login";
	}

	@RequestMapping("/hello")
	public String hello(Model model){
		System.out.println("hello来ました");
		model.addAttribute("message", "sample");

		return "hello";
	}


}
