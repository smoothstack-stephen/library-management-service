package com.smoothstack.lms;

public class Author {
	private String name, id;
	
	public Author(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public void printInfo() {
		System.out.println("==============================================");
		System.out.println("Author Name: " + name);
		System.out.println("Author Id: " + id);
		System.out.println("==============================================");
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
