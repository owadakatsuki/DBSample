package com.form.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.form.model.Question;

public interface QuestionRepository extends JpaRepository<Question, String>{
}
