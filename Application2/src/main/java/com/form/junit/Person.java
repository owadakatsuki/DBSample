package com.form.junit;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Person {
	@Column(name = "perMinWalk")
	private 	Integer 	perMinWalk;
	
	@Column(name = "perMinRun")
	private 	Integer 	perMinRun;
	
	@Column(name = "name")
	private 	String 		name;
	
	public Person(Integer perMinWalk, Integer perMinRun, String name){
		this.perMinWalk 	= 	perMinWalk;
		this.perMinRun 		= 	perMinRun;
		this.name 			= 	name;
	}

	public Integer getPerMinWalk() {
		return perMinWalk;
	}

	public void setPerMinWalk(Integer perMinWalk) {
		this.perMinWalk = perMinWalk;
	}

	public Integer getPerMinRun() {
		return perMinRun;
	}

	public void setPerMinRun(Integer perMinRun) {
		this.perMinRun = perMinRun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// なぜIntegerじゃだめなのか。。。
	public long walk(long min) {
		return perMinWalk * min;
	}
	
	public long run(long min) {
		throw new ArithmeticException();
	}
}
