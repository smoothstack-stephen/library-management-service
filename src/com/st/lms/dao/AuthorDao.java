package com.st.lms.dao;
import com.st.lms.model.Author;
import java.util.*;
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
			authors.add(new Author(values[0].trim(), values[1].trim()));
		}
		
		buffer.close();
		return authors;
	}
	
	public void addAuthor(String name, String id) {
		authors.add(new Author(name, id));
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
		authors.stream()
		.filter(author -> author.getId().equalsIgnoreCase(queryId))
		.forEach(author -> authors.remove(author));
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
}
