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

//管理者ユーザー一覧取得してユーザー画面へ
	@RequestMapping(value="/userlist")
	public String userlist(Model model) {
		System.out.println("ユーザーリスト表示します！");

		//	ユーザーリストの取得
		List<User> userlist = userRepository.findAll();
		//リストをセット
		model.addAttribute("userlist" , userlist);
		System.out.println("リスト取れたyo!");
		//一覧画面へ
		return "userlist";
	}

//ユーザー削除
	@RequestMapping(value="/userdelete",method=RequestMethod.GET)
	public String userdelete(@RequestParam ("user_id") String user_id, Locale locale, Model model) {
		System.out.println("Stringでuseridは受け取れました。");

		//　レコードを削除
		userRepository.delete(user_id);
		System.out.println("削除できました。");
		// 削除後のリスト取得
		List<User> userlist = userRepository.findAll();
		//リストをセット
		model.addAttribute("userlist" , userlist);
		return "userlist";
	}

//ユーザー新規登録	
	@RequestMapping(value="/usernew",method=RequestMethod.GET)
	public String usernew(Model model) {
		System.out.println("新規登録画面を出します。");
		//UserEntity用意
		model.addAttribute("newuser", new User());
		System.out.println(user_info.getUser_id() + user_info.getRole());
		
		model.addAttribute("user_info", user_info);
		//新規登録画面へ
		return "usernew";
	}

//確認画面
	@RequestMapping(value="/usernewOK",method=RequestMethod.POST)
	public String usernewOk(@ModelAttribute("newuser") User user ,Model model)  {
		System.out.println("新規情報受け取りました。");
		
		//管理者とゲストで行き先を分岐する
		String rtnVal = "usernewOK";
		User userid = userRepository.findOne(user.getUser_id());
		
		//受け取ったUserIDで重複があるか判定（nullならば利用可能）
		if(userid == null) {
			//ゲストかどうかの判定（nullならばゲスト）
			if(user_info.getUser_id() == null) {
				user.setRole("user");
				//ログイン情報に権限と取得したIDをセット
				user_info.setRole(user.getRole());
				user_info.setUser_id(user.getUser_id());

				rtnVal = "redirect:/menu";
			}
			//重複なかったので登録
			userRepository.save(user);
			System.out.println("登録しました");
			
		//確認画面へのためdataをセット
		model.addAttribute("user", user);
		model.addAttribute("id", user.getUser_id());
		model.addAttribute("name", user.getUsername());
		model.addAttribute("pass", user.getPassword());
		model.addAttribute("role", user.getRole());
		
		//確認画面へ
		return rtnVal;

		} else {
			//IDが重複していた場合エラーメッセージをセット
			String ermsg = "IDが既に使われています。";
			model.addAttribute("ermsg",ermsg);

			//新規登録画面へ戻る
			return "usernew";
		}
	}

//編集画面
	@RequestMapping(value="/userupdate",method=RequestMethod.POST)
	public String userupdate(@ModelAttribute("user") User user ,Model model)  {
		System.out.println("編集したい情報を受け取りました。");

		//変更する受け取った情報をセットする
		model.addAttribute("user", user);

		//編集画面へ
		return "userupdate";
	}

//編集情報を受け取る	
	@RequestMapping(value="/userrenew",method=RequestMethod.POST)
	public String userrenew(@ModelAttribute("user") User user ,Model model)  {
		System.out.println("変更した情報受け取りました。");

		//編集情報を保存
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