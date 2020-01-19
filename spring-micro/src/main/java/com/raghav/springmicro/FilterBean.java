package com.raghav.springmicro;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FilterBean {
	
	private String name;
	
	@JsonIgnore
	private String cast;
	
	private String gender;
	
	
	

	public FilterBean(String name, String cast, String gender) {
		super();
		this.name = name;
		this.cast = cast;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
