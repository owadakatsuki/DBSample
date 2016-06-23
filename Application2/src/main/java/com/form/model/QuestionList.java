package com.form.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {
	private Content content;
	private List<Question> questions;
	private List<ChoicesEntity> choices;

	public QuestionList() {
		super();
		this.questions = new ArrayList<Question>(); 
		this.choices = new ArrayList<ChoicesEntity>(); 
	}


	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<ChoicesEntity> getChoices() {
		return choices;
	}

	public void setChoices(List<ChoicesEntity> choices) {
		this.choices = choices;
	}

}
