package com.form.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.form.model.Question;

public interface QuestionRepository extends JpaRepository<Question, String>{


	  @Query("select q from Question q where content_id = ?1")
	  List<Question> findByContent_id(int id);
}
