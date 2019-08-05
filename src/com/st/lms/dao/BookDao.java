package com.st.lms.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import com.st.lms.model.Book;

public class BookDao {

	private String fileRelativePath = System.getProperty("user.dir") + "\\src\\books.csv";
	private Set<Book> books = new TreeSet<>();

	public Set<Book> readBooks() throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(fileRelativePath));
		String line;
		
		while((line = buffer.readLine()) != null) {
			String[] values = line.split(",");
			books.add(new Book(values[0], values[1]));
		}
		
		buffer.close();
		return books;
	}
	
	public void addBook(String name, String id, String authId, String pubId) {
		books.add(new Book(name, id, authId, pubId));
	}
	
	public void updateBook(String currentName, String newName) {
		books.stream()
		.filter(book -> book.getName().equalsIgnoreCase(currentName))
		.forEach(book -> book.setName(newName));
	}
	
	public void retrieveBook(String searchName) {
		books.stream()
		.filter(book -> book.getName().equalsIgnoreCase(searchName))
		.forEach(book -> book.printInfo());
	}
	
	public void removeBook(String searchName) {
		books.stream()
		.filter(book -> book.getName().equalsIgnoreCase(searchName))
		.forEach(book -> books.remove(book));
	}
	
}
