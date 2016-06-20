/**
 * 日付 : 2016.06.15
 * 作成者 : キム
 * ユーザ解答に関してのモデル
 * 
 * 
 * 6/16
 * 	error : Hibernate : ids for this class must be manually assigned before calling save() 
 *  resolve : id attribute is not set. this MAY be due to the fact that the DB field is not set to auto increment.
 *  		  so, i add three sentences. 
 *  		  @GeneratedValue(strategy = GenerationType.IDENTITY)
 *            @Basic(optional = false)
 *            @Column(name = "id",unique=true, nullable = false)	
 */
package com.form.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_answer_tb")
public class UserAnswer implements Serializable { //implements Serializable{
	private static final long serialVersionUID = 3744346731479843943L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)	// 後でuser_answer_id
	private Integer		id;

	@NotNull
	@Column
	private Integer 	user_id;		// ユーザID
	
	@Column
	private Integer 	content_id;		// 問題ID
	
	@Column
	private Integer 	question_id;	// 小問ID
	
	@Column
	private Integer 	answer_id;		// 解答番号
	
	@Column
	private String 		select_answer;	// 選択した値
	
	@Column
	private Timestamp 	time;			// 提出した時間(input default current local time) 
	
	public Integer getId() { return id; }
	public void setId( Integer id ) { this.id = id; }
	
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
