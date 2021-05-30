package com.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

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
//@TableGenerator(name = "sequence", initialValue = 10)
public class User {

	@Id
	//@GeneratedValue(generator="sequence")
	@GeneratedValue
	private int id;
	@Size(min = 2, message = "Name should have at least two letters")
	private String name;
	@Past
	private Date dob;
	//"user is the name of the field in post"
	@OneToMany(mappedBy = "user")
	private List<Post> post;

}
