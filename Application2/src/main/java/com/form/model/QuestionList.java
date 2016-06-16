package com.form.model;

import java.util.List;

public class QuestionList {
	private List<Question> questions;

	public QuestionList() {
		super();
	}

	public QuestionList(List<Question> questions) {
		super();
		this.questions = questions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
