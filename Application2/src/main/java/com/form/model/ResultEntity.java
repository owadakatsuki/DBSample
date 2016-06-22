package com.form.model;

import java.util.List;

import javax.persistence.Column;

public class ResultEntity {

	@Column
	private  String question;
	@Column
	private String MaruBatsu;
	@Column
	private List<String> select_answer;
	@Column
	private List<String> answer;
	@Column
	private String commentary;

	public String getQuestion() {
		return question;
	}
	public String getMaruBatsu() {
		return MaruBatsu;
	}
	public List<String> getSelect_answer(){
		return select_answer;
	}
	public List<String> getAnswer() {
		return answer;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setMaruBatsu(String maruBatsu) {
		MaruBatsu = maruBatsu;
	}
	public void setSelect_answer(String select_answer){
		this.select_answer.add(select_answer);
	}
	public void setAnswer(String answer) {
		this.answer.add(answer);
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
}
