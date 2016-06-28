package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.form.dao.ContentRepository;
import com.form.dao.LoginService;
import com.form.model.User;
import com.form.model.UserInfo;

@Controller
@SessionAttributes("user_info")
public class LoginController {
	@Autowired
	LoginService logService;
	@Autowired
    ContentRepository contentRepository;
	@Autowired
	private UserInfo user_info;

		@RequestMapping("/")
		public String startApp(Model model){
			System.out.println("StartApp来ました");
			return "login";
			//こんにちは
		}

		@RequestMapping(value ="/Login",method=RequestMethod.POST)
		public String login(Model model, @RequestParam("userid") String user_ID, @RequestParam("pass") String password){
			System.out.println("login来ました");

			//user_IDを引数として渡し、login判定をしてもらう。
			/*User user_info = logService.execute(user_ID);*/
			User user_data = logService.execute(user_ID);

			//データがあったかどうか判定。
			if(user_data  == null){
				model.addAttribute("errMsg","※ユーザIDかパスワードが違います。");
				return"login";
			}

			user_info.setUser_id(user_data.getUser_id());
			user_info.setRole(user_data.getRole());
		    String uPassword = user_data.getPassword();

			 //入力されたPasswordが合ってるかどうか判定。
			if(! uPassword.equals(password)) {
				model.addAttribute("errMsg", "※ユーザIDかパスワードが違います。");
				return "login";
			}
			System.out.println("login成功");

			return "redirect:/menu";
	}
}

