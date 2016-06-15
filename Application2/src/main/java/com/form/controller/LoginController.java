package com.form.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Servlet implementation class LoginSV
 */
@Controller
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	@RequestMapping("/login")
	public String login(Model model, @RequestParam("userid") String userID, @RequestParam("pass") String password){

		if (userID.length() == 0 && password.length() == 0) {
			model.addAttribute("errMsg", "未入力");
			return "login";
		}
		if(!userID.equals(password)) {
			model.addAttribute("errMsg", "Login Error!");
			return "login";
		}

		if(userID.equals("admin")) {

			model.addAttribute("userType", "admin");
		}  else {

			model.addAttribute("userType", "user");
		}
		return "menu";
	}
}
