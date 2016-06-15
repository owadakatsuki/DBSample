package com.form.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_tb")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int user_id;

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Column(nullable = false)
	private String username;

	public String getUsername() {
		return username;
	}
	public void setUesrname(String username) {
		this.username = username;
	}

	@Column(nullable = false)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(columnDefinition= "default user")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role){
		this.role = role;
	}
}
