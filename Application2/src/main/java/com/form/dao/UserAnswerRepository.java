package com.form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.form.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {

}
