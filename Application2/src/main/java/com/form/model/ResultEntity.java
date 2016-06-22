package com.form.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

public class ResultEntity {

	@Column
	private  String question;
	@Column
	private String maruBatsu;
	@Column
	private List<Integer> select_answer;
	@Column
	private List<Integer> answer;
	@Column
	private String commentary;

	public ResultEntity() {
		select_answer = new ArrayList<Integer>();
		answer = new ArrayList<Integer>();
	}
	
	public String getQuestion() {
		return question;
	}
	public String getMaruBatsu() {
		return maruBatsu;
	}
	public List<Integer> getSelect_answer(){
		return select_answer;
	}
	public List<Integer> getAnswer() {
		return answer;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setMaruBatsu(String maruBatsu) {
		this.maruBatsu = maruBatsu;
	}
	public void setSelect_answer(Integer select_answer){
		this.select_answer.add(select_answer);
	}
	public void setAnswer(Integer answer) {
		this.answer.add(answer);
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
}
