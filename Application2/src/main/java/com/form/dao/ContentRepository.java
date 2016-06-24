package com.form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.form.model.Content;

public interface ContentRepository extends JpaRepository<Content, Integer>{

	  @Query("select c from Content c where content_id = ?1")
	  Content find(int id);

      @Modifying
	  @Query("delete from Content where content_id = ?1")
	  void deleteContent(int id);
}
