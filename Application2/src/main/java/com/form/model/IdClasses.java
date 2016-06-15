package com.form.model;

import java.io.Serializable;

public class IdClasses implements Serializable {
	private Integer 	user_id;		// ユーザID
	private Integer 	content_id;		// 問題ID
	private Integer 	question_id;	// 小問ID
	private Integer 	answer_id;		// 解答番号
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getContent_id() {
		return content_id;
	}
	public void setContent_id(Integer content_id) {
		this.content_id = content_id;
	}
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	public Integer getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(Integer answer_id) {
		this.answer_id = answer_id;
	}
}
