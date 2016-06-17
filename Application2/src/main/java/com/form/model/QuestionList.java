package com.form.model;

import java.util.List;

public class QuestionList {
	private List<Question> questions;
	private List<ChoicesEntity> choices;

	public QuestionList() {
		super();
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
