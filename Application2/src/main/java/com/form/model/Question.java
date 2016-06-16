package com.form.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_tb")
public class Question {
	
	@Id/*
	@GeneratedValue*/
	private int content_id;
	private String question;
	private boolean required_flag;
	private String commentary;
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int question_id;
	
	public Question() {
		super();
	}
	
	public Question(String question, boolean required_flag) {
		super();
		this.question = question;
		this.required_flag = required_flag;
	}
	
	
	
	public Question(int content_id, int question_id, String question, boolean required_flag, String commentary) {
		super();
		this.content_id = content_id;
		this.question_id = question_id;
		this.question = question;
		this.required_flag = required_flag;
		this.commentary = commentary;
	}

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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public boolean isRequired_flag() {
		return required_flag;
	}
	public void setRequired_flag(boolean required_flag) {
		this.required_flag = required_flag;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	
	@Override
	public String toString() {
		return "Question [content_id=" + content_id + ", question_id=" + question_id + ", question=" + question
				+ ", required_flag=" + required_flag + ", commentary=" + commentary + "]";
	}
	
	
}
