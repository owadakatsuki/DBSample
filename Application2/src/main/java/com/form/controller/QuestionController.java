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
	

	@RequestMapping(value = "createNewQuestion",consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Question createNewQuestion(@RequestBody SendId id) {
		Question question = new Question();
		question.setContent_id(id.getContent_id());
    	MakeFormService.questionSave(question);
		return question;
	}


	@RequestMapping(value = "createNewChoice", consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ChoicesEntity createNewChoice(@RequestBody SendId id) {
		ChoicesEntity  choice= new ChoicesEntity();
		choice.setContent_id(id.getContent_id());
		choice.setQuestion_id(id.getQuestion_id());
    	MakeFormService.choiceSave(choice);
		
		return choice;
	}
    
	
	
	
	@RequestMapping("/question")
    public String helo(Model model) {
    	
    	// List<Question> list = MakeFormService.findAll();
    	
    	// QuestionList question_list = new QuestionList();

		QuestionList question_list = MakeFormService.findFormByContent_id(0);
		
    	model.addAttribute("question_list", question_list);
    	
    	model.addAttribute("questions",new Question());

    	return "make_form";
    }
    
    @RequestMapping("/question_list")
    public String question_list(Model model) {
    	Iterable<Question> list = MakeFormService.findAll();
    	model.addAttribute("datas",list);
    	return "question_list";
    }
    
    

    @RequestMapping(value="/create", params="submit", method=RequestMethod.POST)
    public String create(@ModelAttribute("question_list") QuestionList question_list, Model model) {

    	//  debag
    	for(Question q : question_list.getQuestions()) {
			System.out.println("-------------------------");
			System.out.println(q.toString());
    		for(ChoicesEntity c : question_list.getChoices()){
    			if(q.getQuestion_id() == c.getQuestion_id()){
    				System.out.println(c.toString());
    			}
    		}
    	}
    	
    	MakeFormService.save(question_list);

    	Iterable<Question> list = MakeFormService.findAll();
    	model.addAttribute("datas",list);
    	return "question_list";
    }
    
    public void debug(QuestionList question_list){

    	//  debag
    	for(Question q : question_list.getQuestions()) {
			System.out.println("-------------------------");
			System.out.println(q.toString());
    		for(ChoicesEntity c : question_list.getChoices()){
    			if(q.getQuestion_id() == c.getQuestion_id()){
    				System.out.println(c.toString());
    			}
    		}
    	}
    	
    	
    }
}
