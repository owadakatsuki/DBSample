package com.form.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="choices_tb")
public class ChoicesEntity {

		@Id
		private int answer_id;		//解答番号
		private int content_id;		//問
		private int question_id;	//小問
		private String answer;		//解答
		private boolean is_answer;		//正解

		public int getContent_id(){
			return content_id;
		}
		public void setContent_id(int content_id){
			this.content_id = content_id;
		}
		public int getQuestion_id(){
			return question_id;
		}
		public void setQestion_id(int question_id){
			this.question_id = question_id;
		}
		public int getAnswer_id(){
			return answer_id;
		}
		public void setAnswer_id(int answer_id){
			this.answer_id = answer_id;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public boolean getIs_answer(){
			return is_answer;
		}
		public void setIs_answer(boolean is_answer){
			this.is_answer = is_answer;
		}
}
