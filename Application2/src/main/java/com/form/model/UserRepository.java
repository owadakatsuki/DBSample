package com.form.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface UserRepository  extends Repository<User, String>{
	Page<User> findAll(Pageable pageable);
}
