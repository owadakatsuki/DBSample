package com.form.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.form.model.User;

@Service
@Transactional
public class LoginService {
	@Autowired
	UserRepository uRepo;

	public User execute(String user_id){
		System.out.println("Service.execute来ました");

		//int user_id = uEntity.getUser_id();

		//受け取ったuser_idと一致するIDがデータベースにあるか検索
		User userid = uRepo.findOne(user_id);

		if(userid != null){
			//IDがあったらそのレコードをuser_infoに詰める
			User user_info =uRepo.findOne(user_id) ;
			return user_info;

		}else
			//IDがなかったらnullを返す
			return null;
	}
}
