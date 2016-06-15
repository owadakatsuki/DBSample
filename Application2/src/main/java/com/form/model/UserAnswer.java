/**
 * 日付 : 2016.06.15
 * 作成者 : キム
 * ユーザ解答に関してのモデル
 */
package com.form.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_answer_tb")
@IdClass(value = IdClasses.class)
public class UserAnswer implements Serializable { //implements Serializable{
	@Id
	@Column
	private Integer 	user_id;		// ユーザID
	
	@Id
	@Column
	private Integer 	content_id;		// 問題ID
	
	@Id
	@Column
	private Integer 	question_id;	// 小問ID
	
	@Id
	@Column
	private Integer 	answer_id;		// 解答番号
	
	@Column
	private String 		select_answer;	// 選択した値
	
	@Column
	private Timestamp 	time;			// 提出した時間
	
	public Integer getUser_id() { return user_id; }
	public void setUser_id( Integer user_id ) { this.user_id = user_id; }
	
	public Integer getContent_id() { return content_id; }
	public void setContent_id( Integer content_id ) { this.content_id = content_id; }
	
	public Integer getQuestion_id() { return question_id; }
	public void setQuestion_id( Integer question_id ) { this.question_id = question_id; }
	
	public Integer getAnswer_id() { return answer_id; }
	public void setAnswer_id( Integer answer_id ) { this.answer_id = answer_id; }
	
	public String getSelect_answer() { return select_answer; }
	public void setSelect_answer( String select_answer ) { this.select_answer = select_answer; }
	
	public Timestamp getTime() { return time; }
	public void setTime( Timestamp time ) { this.time = time; }
	
	@Override
	public String toString() {
		return "UserAnswer [ユーザID=" + user_id + ", コンテントID=" + content_id + ", クエスチョンID=" + question_id
				+ ", 答えID=" + answer_id + ", 選択した答え=" + select_answer + ", 提出した時間=" + time + "]";
	}
}
