package com.smoothstack.lms;

public class Publisher {
	private String name, id, address;
	
	public Publisher(String name, String id, String address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}
	
	public void printInfo() {
		System.out.println("==============================================");
		System.out.println("Publisher Name: " + name);
		System.out.println("Publisher Id: " + id);
		System.out.println("Publisher Address: " + address);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
