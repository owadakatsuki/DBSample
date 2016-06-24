package com.form.model;


public class ContentAnswer {

	private String user_id;      // ユーザーID
	private int content_id;   // 問題ID
	private String content_title;
	private boolean answer_flg;   // 解答済フラグ

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getContent_title() {
		return content_title;
	}
	public void setContent_title(String content_title) {
		this.content_title = content_title;
	}
	public boolean getAnswer_flg() {
		return answer_flg;
	}
	public void setAnswer_flg(boolean answer_flg) {
		this.answer_flg = answer_flg;
	}

}

