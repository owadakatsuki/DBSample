package com.form.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContentRepository extends JpaRepository<Content, Integer>{

	  @Query("select c from Content c where content_id = ?1")
	  Content find(int id);
}
