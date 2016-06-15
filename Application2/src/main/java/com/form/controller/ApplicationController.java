package com.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

	@RequestMapping("/")
	public String startApp(Model model){
		return "login";
	}

	@RequestMapping("/hello")
	public String hello(Model model){
		model.addAttribute("message", "sample");

		return "hello";
	}


}
