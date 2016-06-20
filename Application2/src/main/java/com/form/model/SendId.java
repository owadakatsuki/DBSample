package com.form.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendId implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}