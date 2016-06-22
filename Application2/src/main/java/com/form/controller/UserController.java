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

import com.form.dao.UserRepository;
import com.form.model.User;

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
		System.out.println("リスト取れた");
		//一覧画面へ
		return "userlist";
	}

	@RequestMapping(value="/userdelete",method=RequestMethod.GET)
	public String userdelete(@RequestParam ("userid") String user_id, Locale locale, Model model) {
		System.out.println("削除しに来た");


		//	レコードを削除
		userRepository.deleteUser(user_id);
		System.out.println("削除できた");
		//削除完了画面
		return "userdelete";
	}

	@RequestMapping(value="/usernew",method=RequestMethod.GET)
	public String usernew(Model model) {
		System.out.println("新規登録画面きました。");
		//UserEntity用意
		model.addAttribute("newuser", new User());
		//新規登録画面へ
		return "usernew";
	}

	@RequestMapping(value="/usernewOK",method=RequestMethod.POST)
	public String usernewOk(@ModelAttribute("newuser") User user ,Model model)  {
		System.out.println("新規情報受け取りました。");
		//確認用
		System.out.println(user.getUser_id());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		//受け取ったUserIDで重複があるか確認
		User userid = userRepository.findOne(user.getUser_id());
		if(userid == null) {
			//重複ないなら登録
			userRepository.save(user);
		//確認画面のためdataをセット
		model.addAttribute("user", user);
		model.addAttribute("id", user.getUser_id());
		model.addAttribute("name", user.getUsername());
		model.addAttribute("pass", user.getPassword());
		model.addAttribute("role", user.getRole());
		
		System.out.println("登録しました");
		//確認画面へ
		return "usernewOK";
		
		} else {
			//エラーメッセージをセット
			String ermsg = "IDが既に使われています。";
			model.addAttribute("ermsg",ermsg);
			
			System.out.println("IDが既に使われています。");
			//新規登録画面へ戻る
			return "usernew";
		}
	}
		
	@RequestMapping(value="/userupdate",method=RequestMethod.POST)
	public String userupdate(@ModelAttribute("user") User user ,Model model)  {
		System.out.println("編集したい情報受け取りました。");
		//確認用
		System.out.println(user.getUser_id());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		//変更する受け取った情報をセットする
		model.addAttribute("user", user);

		//編集画面へ
		return "userupdate";
	}
	
	@RequestMapping(value="/userrenew",method=RequestMethod.POST)
	public String userrenew(@ModelAttribute("user") User user ,Model model)  {
		System.out.println("変更した情報受け取りました。");
		//確認用
		System.out.println(user.getUser_id());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		//編集する
		userRepository.save(user);
		//確認画面用にセットする
		model.addAttribute("id", user.getUser_id());
		model.addAttribute("name", user.getUsername());
		model.addAttribute("pass", user.getPassword());
		model.addAttribute("role", user.getRole());
		model.addAttribute("user", user);

		System.out.println("編集情報更新しました。");
		//確認画面へ
		return "usernewOK";
	}
}