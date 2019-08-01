package com.smoothstack.lms;

public class Book {
	private String name, id, authId, pubId;

	public Book(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public Book(String name, String id, String authId, String pubId) {
		this.name = name;
		this.id = id;
		this.authId = authId;
		this.pubId = pubId;
	}
	
	public void printInfo() {
		System.out.println("==============================================");
		System.out.println("Book Name: " + name);
		System.out.println("Book Id: " + id);
		System.out.println("Book Author Id: " + authId);
		System.out.println("Book Publisher Id: "+ pubId);
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

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getPubId() {
		return pubId;
	}

	public void setPubId(String pubId) {
		this.pubId = pubId;
	}
}
