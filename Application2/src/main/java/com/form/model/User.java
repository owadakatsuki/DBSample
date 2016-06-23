package com.form.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_tb")
public class User {
	@Id
	@Column(name="user_id", nullable = false)	
	private String user_id;

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;	//ユーザーID　主キー
	}

	@Column(name="username", nullable = false)	
	private String username;	//名前

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password",nullable = false)  
	
	private String password; //パスワード

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="role", columnDefinition= "default user")
	private String role; //権限

	public String getRole() {
		return role;
	}

	public void setRole(String role){
		this.role = role;
	}
	
	
/*追加
	
	@Column(name="usernum",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usernum;
	
	public void setUsernum(int usernum){
		this.usernum = usernum;
	}
	public int getUsernum(){
		return usernum;
	}
	*/

}
