package com.app.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
//@JsonIgnoreProperties(value = {"password"})
@JsonFilter("myDataFilter") // This is the way of dynaimc filter
public class Data {
	
	private String userName;
	//@JsonIgnore // if we do this , this will ignore password in all endpoint. This is kind a static filtering
	private String password;
	private String email;
	
	

}
