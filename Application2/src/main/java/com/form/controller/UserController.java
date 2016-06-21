package com.form.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.form.model.User;
import com.form.model.UserRepository;

@Controller
@EnableAutoConfiguration
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/user")
	public String user() {
		System.out.println("管理者画面きました。");
		return "user";
	}

	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	public String userlist(Locale locale, Model model) {
		System.out.println("ユーザーリスト表示します！");

		//	ユーザーリストの取得
		List<User> userlist = userRepository.findAll();
		//リストをセット
		model.addAttribute("userlist" , userlist);
		System.out.println("取れた");

		return "userlist";
	}

	@RequestMapping(value="/userdelete",method=RequestMethod.GET)
	public String userdelete(@RequestParam ("userid") int user_id, Locale locale, Model model) {
		System.out.println("ユーザーを1件削除します！");


		//	レコードを削除
		userRepository.delete(user_id);
		System.out.println("1");
		return "userdelete";

	}

	@RequestMapping(value="/usernew",method=RequestMethod.GET)
	public String usernew(Model model) {
		System.out.println("新規登録画面きました。");
		model.addAttribute("newuser", new User());

		return "usernew";
	}

	@RequestMapping(value="/usernewOK",method=RequestMethod.POST)
	public String usernewOk(@ModelAttribute("newuser") User user ,Model model)  {

		System.out.println("新規情報受け取りました。");
		System.out.println(user.getUser_id());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		User userid=  userRepository.findOne(user.getUser_id());
		if(userid == null) {
			userRepository.save(user);
		
		model.addAttribute("id", user.getUser_id());
		model.addAttribute("name", user.getUsername());
		model.addAttribute("pass", user.getPassword());
		model.addAttribute("role", user.getRole());
		model.addAttribute("user", user);

		System.out.println("登録");
		
		} else {
			String ermsg = "IDが既に使われています。";
			model.addAttribute("ermsg",ermsg);
			
			System.out.println("IDが既に使われています。");
			return "usernew";
		}

		return "usernewOK";
	}
	
	/*
	@RequestMapping(value="/userupdate",method=RequestMethod.POST)
	public String userupdate(@ModelAttribute("newuser") User user ,Model model)  {

		System.out.println("変更する情報受け取りました。");

		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());

		//model.addAttribute("name", user.getUsername());
		//model.addAttribute("pass", user.getPassword());
		//model.addAttribute("role", user.getRole());


		model.addAttribute("newuser", user);
		System.out.println("セットしました");

		return "usernew";
	}
*/
	
	@RequestMapping(value="/userupdate",method=RequestMethod.POST)
	public String userupdate(@ModelAttribute("user") User user ,Model model)  {

		System.out.println("変更情報受け取りました。");
		System.out.println(user.getUser_id());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		model.addAttribute("user", user);

		System.out.println("usernew画面に同じ");

		return "userupdate";
	}
	
	@RequestMapping(value="/userrenew",method=RequestMethod.POST)
	public String userrenew(@ModelAttribute("user") User user ,Model model)  {

		System.out.println("新規情報受け取りました。");
		System.out.println(user.getUser_id());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		userRepository.save(user);
		
		model.addAttribute("id", user.getUser_id());
		model.addAttribute("name", user.getUsername());
		model.addAttribute("pass", user.getPassword());
		model.addAttribute("role", user.getRole());
		model.addAttribute("user", user);

		System.out.println("登録");

		return "usernewOK";
	}
}



