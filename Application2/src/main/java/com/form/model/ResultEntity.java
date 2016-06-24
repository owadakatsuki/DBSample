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
	private List<Integer> select_answerID;
	@Column
	private List<Integer> answerID;
	@Column
	private String commentary;

	public ResultEntity() {
		select_answerID = new ArrayList<Integer>();
		answerID = new ArrayList<Integer>();
	}

	public String getQuestion() {
		return question;
	}
	public String getMaruBatsu() {
		return maruBatsu;
	}
	public List<Integer> getSelect_answerID(){
		return select_answerID;
	}
	public List<Integer> getAnswerID() {
		return answerID;
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
	public void setSelect_answerID(Integer select_answerID){
		this.select_answerID.add(select_answerID);
	}
	public void setAnswerID(Integer answerID) {
		this.answerID.add(answerID);
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
}
