package com.form.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class QuestionMapper implements RowMapper<Question> {
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Question question = new Question();
	      question.setContent_id(rs.getInt("content_id"));
	      question.setQuestion_id(rs.getInt("question_id"));
	      question.setQuestion(rs.getString("question"));
	      question.setRequired_flag(rs.getBoolean("required_flag"));
	      question.setCommentary(rs.getString("commentary"));
	      return question;
	   }
}
