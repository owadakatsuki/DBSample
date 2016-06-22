package com.form.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.form.dao.MakeFormService;
import com.form.dao.UserAnswerService;
import com.form.model.ChoicesEntity;
import com.form.model.Content;
import com.form.model.Question;
import com.form.model.QuestionList;
import com.form.model.ResultEntity;
import com.form.model.UserAnswer;

@Controller
public class UserAnswerController {
	 @Autowired
	 UserAnswerService 	service;
	 @Autowired
	 MakeFormService 	makeformservice;

	 // redirectするときに必要
	 public void addViewControllers(ViewControllerRegistry registry) {
	     registry.addViewController("/result/{user_id}/{content_id}").setViewName("form/userAnswerResult");
	 }

	 // ユーザ解答結果
	 @RequestMapping(value="/result/{user_id}/{content_id}", method=RequestMethod.GET)
	 public String List(@PathVariable Integer user_id, @PathVariable Integer content_id, Model model ) {
		 try{
			 QuestionList	question_list  = makeformservice.findFormByContent_id(content_id);

			 //UserAnswer answer = service.findById(id);
			 List<UserAnswer> userAnswerList = service.findByUserIdandContentId(user_id, content_id);
			 //System.out.println(id);



			//小問ごとの結果を入れるListを作成
			 List<ResultEntity> resultList = new ArrayList<ResultEntity>();
			 boolean flg = true;

			 for(Question question : question_list.getQuestions()) {
				 ResultEntity resultEntity = new ResultEntity();

				 for(ChoicesEntity choices : question_list.getChoices()){
					 resultEntity.setQuestion(question.getQuestion());
					 resultEntity.setCommentary(question.getCommentary());

					 if ((choices.getQuestion_id() == question.getQuestion_id()) && choices.getIs_answer()) {
						    resultEntity.setAnswer(choices.getAnswer());
					 }
				 }

				 for(UserAnswer userAnswer : userAnswerList ){
					 //qustionEntityとuserAnswerEntityの小問IDが一致した解答をresultEntityに入れる
					 if(userAnswer.getQuestion_id()==question.getQuestion_id()){
					 resultEntity.setSelect_answer(userAnswer.getSelect_answer());
					 }
				 }





				 //正解の数と解答の数が一致していなかったらfalse
				 if (resultEntity.getAnswer().size() != resultEntity.getSelect_answer().size()) {
				 flg = false;
				 } else {
				 //解答の中に一つでも正解がなければfalse
				 for(String answer : resultEntity.getAnswer()){
				 if(!resultEntity.getSelect_answer().contains(answer)){
				 	flg = false;
				 }
				 }
				 }

				 //flgの結果によって値をresultEntityに入れる
				 //正解と解答の完全一致のみtrue
				 if(flg){
				 resultEntity.setMaruBatsu("〇");
				 }else{
				 resultEntity.setMaruBatsu("×");
				 }

				 //resultEntityをresultListに入れる
				 resultList.add(resultEntity);
			 }
			 model.addAttribute("resultList",resultList);

		 }catch(Exception e){
			 return "error";
		 }
		 return "form/userAnswerResult";
	 }

	 @RequestMapping(value="/form", method=RequestMethod.POST)	// user : menuからもらう必要ある
	 public String GetUserAnswerForm(@Valid Content content, Model model ){
		try{
			 QuestionList	question_list  = makeformservice.findFormByContent_id(content.getContent_id());

			 for(ChoicesEntity choices : question_list.getChoices()) {
				 choices.setIs_answer(false);		// is answer 表示しない
			 }

			 model.addAttribute("question_list", question_list);
			 model.addAttribute("content_id", content.getContent_id());
		}catch(Exception e){
			return "error";
		}
		return "form/userAnswerForm";
	 }

	 //BindingResult : object so you can test for and retrieve validation errors.
	 @RequestMapping(value="/formAnswer", method=RequestMethod.POST)
	 public String PostUserAnswerForm(@Valid Content content, @Valid QuestionList question_list, BindingResult result, Model model){
		 try{
			 UserAnswer useranswer_result = null;
			 if(!result.hasErrors()){
			 		try{
			 				for(ChoicesEntity choices : question_list.getChoices()) {
			 					if(choices.getIs_answer() == true){
			 						useranswer_result = new UserAnswer();
			 						useranswer_result.setUser_id(1);
			 						useranswer_result.setContent_id(choices.getContent_id());
			 						useranswer_result.setQuestion_id(choices.getQuestion_id());
			 						useranswer_result.setAnswer_id(choices.getAnswer_id());
			 						useranswer_result.setSelect_answer(choices.getAnswer());
			 						service.Save(useranswer_result);
			 					}
			 				}
			 		}catch(Exception e){
			 			model.addAttribute("error", e.getMessage());
			 			return "error";
			 		}
			 	}else{
			 		model.addAttribute("validationError", "不正な値が入力されました。");
			 		return GetUserAnswerForm(content, model);
			 	}
			 	return "redirect:/result/" + useranswer_result.getUser_id() + "/" + useranswer_result.getContent_id();
		 }catch(Exception e){
			return "error";
		 }
		 /*
		 // try{
			 	UserAnswer useranswer_result = null;
			 	if(!result.hasErrors()){
			 		try{
		 					useranswer_result = new UserAnswer();
			 				useranswer_result.setUser_id(useranswer.getUser_id());
			 				useranswer_result.setContent_id(useranswer.getContent_id());
			 				useranswer_result.setQuestion_id(useranswer.getQuestion_id());
			 				useranswer_result.setAnswer_id(useranswer.getAnswer_id());
			 				useranswer_result.setSelect_answer(useranswer.getSelect_answer());
			 				//service.Save(useranswer_result);
			 				for (UserAnswer user_Answer : list) {
			 					useranswer_result = new UserAnswer();
				 				useranswer_result.setUser_id(user_Answer.getUser_id());
				 				useranswer_result.setContent_id(user_Answer.getContent_id());
				 				useranswer_result.setQuestion_id(user_Answer.getQuestion_id());
				 				useranswer_result.setAnswer_id(user_Answer.getAnswer_id());
				 				useranswer_result.setSelect_answer(user_Answer.getSelect_answer());
				 				service.Save(useranswer_result);
							}
			 		}catch(Exception e){
			 			model.addAttribute("error", e.getMessage());
			 			return "error";
			 		}
			 	}else{
			 		model.addAttribute("validationError", "不正な値が入力されました。");
			 		return GetUserAnswerForm(content, model);
			 	}
			 	return "redirect:/result/" + useranswer_result.getId();
		}catch(Exception e){
			return "error";
		}*/
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
