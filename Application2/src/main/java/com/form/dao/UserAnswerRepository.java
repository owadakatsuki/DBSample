package com.form.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.form.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
	 @Query("select ua from UserAnswer ua where user_id = :user_id and content_id = :content_id")
	 List<UserAnswer> findByUserIdandContentId(@Param("user_id") String user_id, @Param("content_id")int content_id);
}
