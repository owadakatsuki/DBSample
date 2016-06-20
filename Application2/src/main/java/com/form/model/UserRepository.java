package com.form.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{

	//public void insertUser(String username, String password, String role);

}
