package com.library.bean;

/**
 * Abstract class of Person. Person can be a member or librarian
 * @author Somanath
 *
 */
public abstract class Person {

	protected Long id;
	protected String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
