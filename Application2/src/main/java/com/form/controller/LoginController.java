package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.form.dao.LoginService;
import com.form.model.User;

@Controller
public class LoginController{
	@Autowired
	LoginService logService;

		@RequestMapping(value ="/Login",method=RequestMethod.POST)
		public String login(Model model, @RequestParam("userid") int user_ID, @RequestParam("pass") String password){
			System.out.println("login来ました");

			//エラー判定
			//user_id,passwordのどちらかが未入力だった場合のエラー処理
//			if (name.length() ==0|| password.length() == 0) {
//				model.addAttribute("errMsg", "未入力");
//				return "login.html";
//			}

//			if(!userID.equals(password)) {
//				model.addAttribute("errMsg", "Login Error!");
//				return "login";
//			}

			//ユーザーに権限があるかないか判定。この処理自体menu.htmlにまかせる？？
//			if(userID.equals("admin")) {
//				model.addAttribute("userType", "admin");
//			}  else {
//				model.addAttribute("userType", "user");
//			}
//			return "menu";

			//引数で渡された値をEntityに格納
			User user = new User();
			user.setUser_id(user_ID);
			user.setPassword(password);

			User user_info = logService.execute(user);

			if(user_info != null){
			    System.out.println("データ取りました！！name="+user_info.getUsername());
			    model.addAttribute("user_info", user_info);
				return"login";	//のちにmenuへ修正
			}else{
				System.out.println("データありません。");
				model.addAttribute("errMsg","ユーザーIDかパスワードが違います。");
				return"login";
			}
		}
}
