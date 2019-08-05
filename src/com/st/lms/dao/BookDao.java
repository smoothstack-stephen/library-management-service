package com.st.lms.dao;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import com.st.lms.model.Book;

public class BookDao {

	private String fileRelativePath = System.getProperty("user.dir") + "\\src\\books.csv";
	private Set<Book> books = new TreeSet<>();

	public BookDao() throws IOException {
		readBooks();
	}
	
	public Set<Book> readBooks() throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(fileRelativePath));
		String line;
		
		while((line = buffer.readLine()) != null) {
			String[] values = line.split(";");
			String name = values[0].trim();
			String id = values[1].trim();
			String authId = values[2].trim();
			String pubId = values[3].trim();
			
			books.add(new Book(name, id, authId, pubId));
		}
		
		buffer.close();
		return books;
	}
	
	public void addBook(String name, String id, String authId, String pubId) {
		sanitize(name, id, authId, pubId);
		if (!isDuplicateId(id)) books.add(new Book(name, id, authId, pubId));
		else System.out.println("Book Id already exists, cannot add book with duplicate Id.");
	}
	
	public void updateBook(String queryId, String newName, String newAuthId, String newPubId) {
		books.stream()
		.filter(book -> book.getId().equalsIgnoreCase(queryId))
		.forEach(book -> {
			book.setName(newName);
			book.setAuthId(newAuthId);
			book.setPubId(newPubId);
		});
	}
	
	public void retrieveBook(String queryId) {
		books.stream()
		.filter(book -> book.getId().equalsIgnoreCase(queryId))
		.forEach(book -> book.printInfo());
	}
	
	public void removeBook(String queryId) {
		books = books.stream()
				.filter(book -> !book.getId().equalsIgnoreCase(queryId))
				.collect(Collectors.toSet());
	}
	
	public void saveToCSV() throws IOException {
		BufferedWriter buffer = new BufferedWriter(new FileWriter(fileRelativePath));
		
		books.stream()
		.forEach(book -> {
			String line = String.join("; ", book.getName(), book.getId(), book.getAuthId(), book.getPubId());
			try {
				buffer.write(line + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		buffer.close();
	}
	
	// Removes special characters (just ; at the moment)
	public void sanitize(String ...args) {
		Set<String> strings = new TreeSet<>(Arrays.asList(args));
		strings.stream()
		.forEach(string -> string.replaceAll(";", ""));
	}
	
	// Checks if there already exists a Book with some Id value
	public boolean isDuplicateId(String idToCheck) {
		return books.stream().anyMatch(book -> book.getId().equalsIgnoreCase(idToCheck));
	}
	
}
