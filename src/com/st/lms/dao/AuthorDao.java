package com.st.lms.dao;
import com.st.lms.model.Author;
import java.util.*;
import java.util.stream.*;
import java.io.*;

public class AuthorDao {
	
	private String fileRelativePath = System.getProperty("user.dir") + "\\src\\authors.csv";
	private Set<Author> authors = new TreeSet<>();
	
	public AuthorDao() throws IOException {
		readAuthors();
	}

	public Set<Author> readAuthors() throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(fileRelativePath));
		String line;
		
		while((line = buffer.readLine()) != null) {
			String[] values = line.split(";");
			String name = values[0].trim();
			String id = values[1].trim();
			
			authors.add(new Author(name, id));
		}
		
		buffer.close();
		return authors;
	}
	
	public void addAuthor(String name, String id) {
		sanitize(name, id);
		if (!isDuplicateId(id)) authors.add(new Author(name, id));
		else System.out.println("Author Id already exists, cannot add author with duplicate Id.");
	}
	
	public void updateAuthor(String queryId, String newName) {
		authors.stream()
		.filter(author -> author.getId().equalsIgnoreCase(queryId))
		.forEach(author -> author.setName(newName));
	}
	
	public void retrieveAuthor(String queryId) {
		authors.stream()
		.filter(author -> author.getId().equalsIgnoreCase(queryId))
		.forEach(author -> author.printInfo());
	}
	
	public void removeAuthor(String queryId) {
		authors = authors.stream()
				.filter(author -> !author.getId().equalsIgnoreCase(queryId))
				.collect(Collectors.toSet());
	}
	
	public void saveToCSV() throws IOException {
		BufferedWriter buffer = new BufferedWriter(new FileWriter(fileRelativePath));
		
		authors.stream()
		.forEach(author -> {
			String line = String.join("; ", author.getName(), author.getId());
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
	
	// Checks if there already exists an Author with some Id value
	public boolean isDuplicateId(String idToCheck) {
		return authors.stream().anyMatch(author -> author.getId().equalsIgnoreCase(idToCheck));
	}
	
}
