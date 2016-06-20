package com.form.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChoiceMapper implements RowMapper<ChoicesEntity>  {
	public ChoicesEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
	      ChoicesEntity choice = new ChoicesEntity();
	      choice.setContent_id(rs.getInt("content_id"));
	      choice.setQuestion_id(rs.getInt("question_id"));
	      choice.setAnswer_id(rs.getInt("answer_id"));
	      choice.setAnswer(rs.getString("answer"));
	      choice.setIs_answer(rs.getBoolean("is_answer"));
	      return choice;
	   }
}
