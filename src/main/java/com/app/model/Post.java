package com.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Post {
	@Id
	@GeneratedValue
	private int id;
	private String description;
	
	@JsonIgnore // IF we do not ignore user in post, it will run recursively, user will fetch posts and posts will fetch user
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	

}
