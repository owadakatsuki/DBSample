package com.form.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.model.ResultEntity;
import com.form.model.UserRepository;

@Controller
public class TestController {
	@Autowired
	UserRepository uRepo;

/*	@RequestMapping("/")
	public String test(Model model){
		System.out.println("testメソッドです");
		ResultEntity resultEntity = new ResultEntity();

		resultEntity.setQuestion("ここまでたどり着けましたか？");
		resultEntity.setMaruBatsu("〇");
		resultEntity.setSelect_answer("はい");
		resultEntity.setSelect_answer("こんにちは");
		resultEntity.setAnswer("はい");
		resultEntity.setAnswer("こんにちは");
		resultEntity.setCommentary("解説です。ここまでこれました");

		System.out.println(resultEntity.getQuestion());
		System.out.println(resultEntity.getMaruBatsu());

		List<ResultEntity> resultList = new ArrayList<ResultEntity>();

		resultList.add(resultEntity);
		model.addAttribute("resultList",resultList);
		return"Form/userAnswerResult";


	}*/
}
