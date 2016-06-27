package com.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.form.model.UserInfo;

@Controller
//@SessionAttributes("user_info")
public class LogoutController {
	//private UserInfo user_info;
	
	@RequestMapping("/logout")
	public String startApp(Model model, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        //model.addAttribute("user_info", user_info);
		return "redirect:/";
	}

}
