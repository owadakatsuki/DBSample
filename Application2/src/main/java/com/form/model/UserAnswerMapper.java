package com.form.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

// jsonic import ///////////
import net.arnx.jsonic.JSON;
//jsonic import ///////////

public class UserAnswerMapper implements RowMapper<UserAnswer>{
	public UserAnswer mapRow(ResultSet result_set, int row_number) throws SQLException {
		UserAnswer user_answer = new UserAnswer();
		
		user_answer.setUser_id(result_set.getInt("user_id")); 
		user_answer.setContent_id(result_set.getInt("content_id")); 
		user_answer.setQuestion_id(result_set.getInt("question_id")); 
		user_answer.setAnswer_id(result_set.getInt("answer_id"));
		user_answer.setSelect_answer(result_set.getString("select_answer"));
		//user_answer.setTime(result_set.getTimestamp()); // --> 
		
		// json形テスト ///////////////////////////
		String json = JSON.encode(user_answer);
		System.out.println(json);
		// json形テスト ///////////////////////////
		
		return user_answer;
	}
}
