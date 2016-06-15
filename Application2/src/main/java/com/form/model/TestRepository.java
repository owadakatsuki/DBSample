package com.form.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;


public interface TestRepository extends Repository<Test, String> {

    Page<Test> findAll(Pageable pageable);

}