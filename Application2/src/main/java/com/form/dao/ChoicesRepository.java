package com.form.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.form.model.ChoicesEntity;

public interface ChoicesRepository extends JpaRepository<ChoicesEntity, Integer>{



	  @Query("select choice from ChoicesEntity choice where content_id = ?1")
	  List<ChoicesEntity> findByContent_id(int id);

	  @Query("select choice from ChoicesEntity choice where question_id = ?1")
	  List<ChoicesEntity> findByQuestion_id(int id);

      @Modifying
	  @Query("delete from ChoicesEntity where answer_id = ?1")
	  void deleteChoice(int id);

      @Modifying
	  @Query("delete from ChoicesEntity where content_id = ?1")
	  void deleteConChoice(int id);
}
