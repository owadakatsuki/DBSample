package com.form.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.form.model.UserAnswer;

@Service
@Transactional
public class UserAnswerService {
	@Autowired
	UserAnswerRepository user_answer_repository;

	@Transactional(readOnly = true, timeout = 3)
	public UserAnswer findById(final Integer id) {
	    return user_answer_repository.findOne(id);
	}
	
	@Transactional(readOnly = true, timeout = 10)
	public List<UserAnswer> FindAll(){
		return user_answer_repository.findAll();
	}
	
	public List<UserAnswer> findByUserIdandContentId(String user_id, int content_id){
		return user_answer_repository.findByUserIdandContentId(user_id, content_id);
	}
	
	@Transactional(rollbackFor = {Exception.class}, timeout = 3) 
	public UserAnswer Save(final UserAnswer user_answer) {
		return user_answer_repository.save(user_answer);
	}
	
	@Transactional(rollbackFor = {Exception.class}, timeout = 10)
	public List<UserAnswer> SaveAll(final Iterable<UserAnswer> user_answer) {
	    return user_answer_repository.save(user_answer);
	}
	 
	
}
