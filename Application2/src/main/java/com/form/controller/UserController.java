package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.model.User;
import com.form.model.UserRepository;

@Controller
@EnableAutoConfiguration
public class UserController {




	@Autowired
	UserRepository userRepository;

	@RequestMapping("/dbuser")
	public String user() {
		System.out.println("[START] データベースに接続してデータを取得します。");
		Page<User> users = userRepository.findAll(new PageRequest(0,10));

		for(User user: users) {
			System.out.println(user.getUser_id() + "=" + user.getUsername() + "," + user.getPassword() + "," + user.getRole());
		}
		System.out.println("[END] データベースに接続してデータを取得します。");
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(UserController.class,args);
	}

}
