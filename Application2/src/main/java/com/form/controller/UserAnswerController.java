package com.form.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.form.model.UserAnswer;
import com.form.model.UserAnswerService;

@Controller
public class UserAnswerController {
	 @Autowired
	 UserAnswerService user_answer_service;
	 
	 // User Answer Result List
	 @RequestMapping("/userAnswerResult")
	 public String List( Model model ) {
		 try{
			 java.util.List<UserAnswer> result = user_answer_service.FindAll();
			 model.addAttribute("datas", user_answer_service.FindAll());
		 }catch(Exception e){
			 return "error";
		 }
		 return "form/userAnswerResult";
	 }
	 
	 // 引数UserAnswer-> Question Choice..?
	 @RequestMapping(value="/form", method=RequestMethod.GET)
	 public String GetUserAnswerForm( UserAnswer user_answer, Model model ){
		try{
			 model.addAttribute("useranswer", new UserAnswer());
			 //model.addAttribute("checkItems", CHECK_ITEMS);
			 model.addAttribute("radioItems", RADIO_ITEMS); 
		}catch(Exception e){
			return "error";
		}  
		return "form/userAnswerForm";
	 }
		
	 @RequestMapping(value="/userAnswerResult", method=RequestMethod.POST)
	 public String PostUserAnswerForm(@ModelAttribute("useranswer") UserAnswer user_answer, BindingResult result, Model model){
		 try{
			 	if(!result.hasErrors()){
			 		UserAnswer useranswer_result = new UserAnswer();
			 		try{
			 				useranswer_result.setUser_id(user_answer.getUser_id());
			 				useranswer_result.setContent_id(user_answer.getContent_id());
			 				useranswer_result.setQuestion_id(user_answer.getQuestion_id());
			 				useranswer_result.setAnswer_id(user_answer.getAnswer_id());
			 				useranswer_result.setSelect_answer(user_answer.getSelect_answer());
			 				user_answer_service.Save(useranswer_result);
			 		}catch(Exception e){
			 			return "error";
			 		}finally{
			 			List(model);
			 		}
			 		return "form/userAnswerResult";
			 	}else{
			 		model.addAttribute("validationError", "不正な値が入力されました。");
			 		return GetUserAnswerForm(user_answer, model);
			 	}
		}catch(Exception e){
			return "error";
		}finally{
 			List(model);
 		}
	 }
		
	 /**
	  * check boxの表示に使用するアイテム
	  */
	 final static Map<String, String> CHECK_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
		 {
		      put("checkbox_A", "A");
		      put("checkbox_B", "B");
		      put("checkbox_C", "C");
		      put("checkbox_D", "D");
		      put("checkbox_E", "E");
	     }
	 });

	 /**
	  * radio buttonの表示に使用するアイテム
	  */
	 final static Map<String, String> RADIO_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
	     {
	    	 put("radio_A", "A");
		     put("radio_B", "B");
		     put("radio_C", "C");
		     put("radio_D", "D");
		     put("radio_E", "E");
		 }
	  });
}
