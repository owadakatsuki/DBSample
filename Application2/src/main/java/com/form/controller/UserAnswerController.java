package com.form.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.form.dao.UserAnswerService;
import com.form.model.UserAnswer;

@Controller
public class UserAnswerController {
	 @Autowired
	 UserAnswerService service;
	 
	 public void addViewControllers(ViewControllerRegistry registry) {
	     registry.addViewController("/result").setViewName("form/userAnswerResult");
	     
	     
	     
	 }
	 
	 // User Answer Result List
	 @RequestMapping(value="/result", method=RequestMethod.GET)
	 public String List( Model model ) {
		 try{
			 model.addAttribute("datas", service.FindAll());
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
			 model.addAttribute("checkItems", CHECK_ITEMS);
			 model.addAttribute("radioItems", RADIO_ITEMS); 
		}catch(Exception e){
			return "error";
		}  
		return "form/userAnswerForm";
	 }
	
	 //BindingResult : object so you can test for and retrieve validation errors.
	 @RequestMapping(value="/form", method=RequestMethod.POST)
	 public String PostUserAnswerForm(@Valid UserAnswer useranswer, BindingResult result, Model model){
		 try{
			 	if(!result.hasErrors()){
			 		UserAnswer useranswer_result = new UserAnswer();
			 		try{
			 				useranswer_result.setUser_id(useranswer.getUser_id());
			 				useranswer_result.setContent_id(useranswer.getContent_id());
			 				useranswer_result.setQuestion_id(useranswer.getQuestion_id());
			 				useranswer_result.setAnswer_id(useranswer.getAnswer_id());
			 				useranswer_result.setSelect_answer(useranswer.getSelect_answer());
			 				
			 				service.Save(useranswer_result);
			 		}catch(Exception e){
			 			return "error";
			 		}
			 	}else{
			 		model.addAttribute("validationError", "不正な値が入力されました。");
			 		return GetUserAnswerForm(useranswer, model);
			 		//return "form/userAnswerForm";
			 	}
			 	return "redirect:/result";
		}catch(Exception e){
			return "error";
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
