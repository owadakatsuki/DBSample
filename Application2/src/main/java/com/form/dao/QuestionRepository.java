package com.form.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.form.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{


	  @Query("select q from Question q where content_id = ?1")
	  List<Question> findByContent_id(int id);

      @Modifying
	  @Query("delete from Question where question_id = ?1")
	  void deleteQuestion(int id);

      @Modifying
	  @Query("delete from Question where content_id = ?1")
	  void deleteConQuestion(int id);
}
