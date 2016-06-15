package com.form.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAnswerService {
	@Autowired
	UserAnswerRepository user_answer_repository;
	  
	@Transactional(rollbackFor = {Exception.class}, timeout = 3) 
	public UserAnswer Save(final UserAnswer user_answer) {
		return user_answer_repository.save(user_answer);
	}
	
	@Transactional(rollbackFor = {Exception.class}, timeout = 10)
	public List<UserAnswer> SaveAll(final Iterable<UserAnswer> user_answer) {
	    return user_answer_repository.save(user_answer);
	}
	 
	@Transactional(readOnly = true, timeout = 10)
	public List<UserAnswer> FindAll(){
		return user_answer_repository.findAll();
	}
}
