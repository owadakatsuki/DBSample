package com.form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.form.model.User;



@Repository
public interface UserRepository  extends JpaRepository<User, String>{

	 @Query("delete from User where user_id = ?1")
	  void deleteUser(String id);
	 
	
	 
	

}
