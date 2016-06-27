package com.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
//@SessionAttributes("user_info")
public class LogoutController {
	//private UserInfo user_info;

	@RequestMapping("/logout")
	public String startApp(Model model, SessionStatus sessionStatus){
		System.out.println("ログアウト来ました");
        sessionStatus.setComplete();
        //model.addAttribute("user_info", user_info);
		return "redirect:/";
	}

}
