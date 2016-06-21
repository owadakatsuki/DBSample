package com.form.controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.form.dao.MakeFormService;
import com.form.dao.UserAnswerService;
import com.form.model.ChoicesEntity;
import com.form.model.Content;
import com.form.model.Question;
import com.form.model.QuestionList;
import com.form.model.User;
import com.form.model.UserAnswer;

@Controller
public class UserAnswerController {
	 @Autowired
	 UserAnswerService 	service;
	 @Autowired
	 MakeFormService 	makeformservice;
	 
	 // redirectするときに必要
	 public void addViewControllers(ViewControllerRegistry registry) {
	     registry.addViewController("/result/{id}").setViewName("form/userAnswerResult");
	 }
	 
	 // ユーザ解答結果
	 @RequestMapping(value="/result/{id}", method=RequestMethod.GET)
	 public String List(@PathVariable Integer id, Model model ) {
		 try{
			 UserAnswer answer = service.findById(id);
			
			 // 値照らし合わせ
			 // 点数計算
			 
			 // 書いた解答
			 
			 // これはテスト
			 model.addAttribute("datas", answer);
		 }catch(Exception e){
			 return "error";
		 }
		 return "form/userAnswerResult";
	 }
	 
	 @RequestMapping(value="/form", method=RequestMethod.POST)	// user : menuからもらう必要ある
	 public String GetUserAnswerForm(@Valid Content content, Model model ){
		try{
			 QuestionList	question_list 		= makeformservice.findFormByContent_id(content.getContent_id());
			 UserAnswer 	init_hidden_value 	= new UserAnswer();
			 
			 init_hidden_value.setUser_id(1);	// テスト
			 init_hidden_value.setContent_id(content.getContent_id());
			 
			 model.addAttribute("question_list", question_list);
			 model.addAttribute("useranswer", init_hidden_value);
			 
			 
			 //model.addAttribute("checkItems", CHECK_ITEMS);
			 //model.addAttribute("radioItems", RADIO_ITEMS); 
		}catch(Exception e){
			return "error";
		}  
		return "form/userAnswerForm";
		//return "redirect:/formAnswer";
	 }
	
	 //BindingResult : object so you can test for and retrieve validation errors.
	 @RequestMapping(value="/formAnswer", method=RequestMethod.POST)
	 public String PostUserAnswerForm(@Valid Content content, @Valid UserAnswer useranswer, BindingResult result, Model model){
		 try{
			 	UserAnswer useranswer_result = null;
			 	if(!result.hasErrors()){
			 		try{
			 			//for (i = 0; i < useranswer.size(); i++) {
			 				useranswer_result = new UserAnswer();
			 				useranswer_result.setUser_id(useranswer.getUser_id());
			 				useranswer_result.setContent_id(useranswer.getContent_id());
			 				useranswer_result.setQuestion_id(useranswer.getQuestion_id());
			 				useranswer_result.setAnswer_id(useranswer.getAnswer_id());
			 				useranswer_result.setSelect_answer(useranswer.getSelect_answer());
			 				
			 				service.Save(useranswer_result);
			 			//}	
			 		}catch(Exception e){
			 			return "error";
			 		}
			 	}else{
			 		model.addAttribute("validationError", "不正な値が入力されました。");
			 		return GetUserAnswerForm(content, model);
			 	}
			 	return "redirect:/result/" + useranswer_result.getId();
		}catch(Exception e){
			return "error";
		}
	 }
	 
	 // check boxの表示に使用するアイテム
	 final static Map<String, Integer> CHECK_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, Integer>() {
		 {
		      put("checkbox_A", 1);
		      put("checkbox_B", 2);
		      put("checkbox_C", 3);
		      put("checkbox_D", 4);
		      put("checkbox_E", 5);
	     }
	 });

	 // radio buttonの表示に使用するアイテム
	 final static Map<String, Integer> RADIO_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, Integer>() {
	     {
	    	 put("radio_A", 1);
		     put("radio_B", 2);
		     put("radio_C", 3);
		     put("radio_D", 4);
		     put("radio_E", 5);
		 }
	  });
}
