package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.form.dao.MakeFormService;
import com.form.model.ChoicesEntity;
import com.form.model.Question;
import com.form.model.QuestionList;
import com.form.model.SendId;

@Controller
public class QuestionController {
	@Autowired
	MakeFormService MakeFormService;
	

	// menu -> 問題作成 への遷移
	@RequestMapping("/formcreate")
	    public String helo(@ModelAttribute("contentId") int content_id, Model model) {
        System.out.println("[START] 問題作成画面を表示します。");

		QuestionList question_list = MakeFormService.findFormByContent_id(content_id);
		
    	model.addAttribute("question_list", question_list);
    	
    	model.addAttribute("content_id", content_id);

    	return "make_form";
    }

	
	// フォームの新規作成
	@RequestMapping(value = "createNewForm")
	public void createNewForm(Model model) {
		MakeFormService.createForm();
	}
	

	// 新規問題追加
	@RequestMapping(value = "createNewQuestion",consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Question createNewQuestion(@RequestBody SendId id) {
		Question question = new Question();
		question.setContent_id(id.getContent_id());
		System.out.println("id:" + id.getContent_id());
    	MakeFormService.questionSave(question);
    	
    	
		return question;
	}

	// 新規選択肢追加
	@RequestMapping(value = "createNewChoice", consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ChoicesEntity createNewChoice(@RequestBody SendId id) {
		ChoicesEntity  choice= new ChoicesEntity();
		choice.setContent_id(id.getContent_id());
		choice.setQuestion_id(id.getQuestion_id());
    	MakeFormService.choiceSave(choice);
		
		return choice;
	}

	// 選択肢削除
	@RequestMapping(value = "deleteChoice", consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public void deleteChoice(@RequestBody SendId id) {
    	MakeFormService.choiceDelete(id.getChoice_id());
		
		return;
	}
	
	
	// 問題作成・更新
    @RequestMapping(value="/create", params="submit", method=RequestMethod.POST)
    public String create(@ModelAttribute("question_list") QuestionList question_list, Model model) {
    	for(Question q : question_list.getQuestions()) {
    		System.out.println(q.toString());
    	}
    	MakeFormService.save(question_list);

    	Iterable<Question> list = MakeFormService.findAll();
    	model.addAttribute("datas",list);
    	return "redirect:/menu";
    }
    
    
    
    
    // debug
    @RequestMapping("/question_list")
    public String question_list(Model model) {
    	Iterable<Question> list = MakeFormService.findAll();
    	model.addAttribute("datas",list);
    	return "question_list";
    }
    
    
}
