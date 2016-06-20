package com.form.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendId implements Serializable  {

	private static final long serialVersionUID = 1L;

	@JsonProperty("content_id")
	private int content_id;
	
	@JsonProperty("question_id")
	private int question_id;

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}



}