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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.form.dao.ContentRepository;
import com.form.dao.UserRepository;
import com.form.model.User;
import com.form.model.UserInfo;


@Controller
@SessionAttributes("user_info")
@EnableAutoConfiguration
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    ContentRepository contentRepository;
	@Autowired
	private UserInfo user_info;

/*	@RequestMapping("/user")
	public String user() {
		System.out.println("管理者画面きました。");
		return "user";
	}
*/

	@RequestMapping(value="/userlist")
	public String userlist(Model model) {
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
	public String userdelete(@RequestParam ("user_id") String user_id, Locale locale, Model model) {
		System.out.println("Stringでuseridは受け取れた。");

		//	レコードを削除
		userRepository.delete(user_id);
		System.out.println("削除できた");

		List<User> userlist = userRepository.findAll();
		//リストをセット
		model.addAttribute("userlist" , userlist);
		return "userlist";
	}

	@RequestMapping(value="/usernew",method=RequestMethod.GET)
	public String usernew(Model model) {
		System.out.println("新規登録画面きました。");
		//UserEntity用意
		model.addAttribute("newuser", new User());
		System.out.println(user_info.getUser_id() + user_info.getRole());
		boolean isAdmin;
		if (user_info.getRole().equals("user")) {
			isAdmin = false;
		} else {
		isAdmin = user_info.getRole().equals("admin") ;
		}
		model.addAttribute("isAdmin", isAdmin);
		//model.addAttribute("isAdmin", user_info.getRole() == null ? false : user_info.getRole().equals("admin") );

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

		String rtnVal = "usernewOK";
		//受け取ったUserIDで重複があるか判定
		User userid = userRepository.findOne(user.getUser_id());
		if(userid == null) {
			//ゲストかどうかの判定
			if(user_info.getUser_id() == null) {
				user.setRole("user");
				user_info.setRole(user.getRole());
				user_info.setUser_id(user.getUser_id());

				rtnVal = "redirect:/menu";
			}
			//重複ないなら登録
			userRepository.save(user);
		//確認画面のためdataをセット
		model.addAttribute("user", user);
		model.addAttribute("id", user.getUser_id());
		model.addAttribute("name", user.getUsername());
		model.addAttribute("pass", user.getPassword());
		model.addAttribute("role", user.getRole());
		
		System.out.println(user.getRole());
		System.out.println(user_info.getRole());
		System.out.println("登録しました");
		//確認画面へ
		return rtnVal;

		} else {
			//エラーメッセージをセット
			String ermsg = "IDが既に使われています。";
			model.addAttribute("ermsg",ermsg);
			model.addAttribute("isAdmin", user_info.getRole().equals("admin") );


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