package com.form.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.form.model.User;
import com.form.model.UserRepository;

@Controller
@EnableAutoConfiguration
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/user")
	public String user(Model model) {
		System.out.println("管理者画面きました。");

		return "user";
	}

	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	public String userlist(Locale locale, Model model) {
		System.out.println("ユーザーリスト表示します！");

		//	ユーザーリストの取得
		Iterable<User> userlist = userRepository.findAll();
		//リストをセット
		model.addAttribute("userlist" , userlist);

		return "userlist";
	}


}

