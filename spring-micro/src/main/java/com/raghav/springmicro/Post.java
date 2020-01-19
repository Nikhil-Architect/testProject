package com.raghav.springmicro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.criteria.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
@Id
@GeneratedValue
private Integer Id;

@Column(name = "description")
private String description;

@ManyToOne(fetch = FetchType.LAZY)
@JsonIgnore
private User user;

public Integer getId() {
	return Id;
}

public void setId(Integer id) {
	Id = id;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}



}
