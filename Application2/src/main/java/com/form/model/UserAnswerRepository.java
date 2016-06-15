package com.form.model;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, String> {
	//public UserAnswer findOne(Integer id);
}
