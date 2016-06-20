package com.form.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.form.model.ChoicesEntity;

public interface ChoicesRepository extends JpaRepository<ChoicesEntity, String>{



	  @Query("select choice from ChoicesEntity choice where content_id = ?1")
	  List<ChoicesEntity> findByContent_id(int id);
}
