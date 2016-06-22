package com.form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.form.dao.ContentRepository;
import com.form.dao.LoginService;
import com.form.model.Content;
import com.form.model.User;

@Controller
public class LoginController{
	@Autowired
	LoginService logService;
	@Autowired
    ContentRepository contentRepository;

		@RequestMapping("/")
		public String startApp(Model model){
			System.out.println("StartApp来ました");
			return "login";
		}

		@RequestMapping(value ="/Login",method=RequestMethod.POST)
		public String login(Model model, @RequestParam("userid") int user_ID, @RequestParam("pass") String password){
			System.out.println("login来ました");

			//user_IDを引数として渡し、login判定をしてもらう。
			User user_info = logService.execute(user_ID);

			//データがあったかどうか判定。
			if(user_info != null){
			    System.out.println("データ取りました！！name="+user_info.getUsername());
			    model.addAttribute("user_info", user_info);
			} else{
				System.out.println("データありません。");
				model.addAttribute("errMsg","※ユーザーIDが存在しません。");
				return"login";
			}

			 String uPassword = user_info.getPassword();

			 //入力されたPasswordが合ってるかどうか判定。
			if(! uPassword.equals(password)) {
				System.out.println("passwordが違います。");
				model.addAttribute("errMsg", "※passwordが違います。");
				return "login";
			} else{
				System.out.println("login成功");

				// 大問一覧取得
		        List<Content> contentList = contentRepository.findAll();
		        model.addAttribute("contentList", contentList);

				return "menu";
			}
	}
}
