package com.st.lms.dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
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
			books.add(new Book(values[0].trim(), values[1].trim(), values[2].trim(), values[3].trim()));
		}
		
		buffer.close();
		return books;
	}
	
	public void addBook(String name, String id, String authId, String pubId) {
		books.add(new Book(name, id, authId, pubId));
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
		books.stream()
		.filter(book -> book.getId().equalsIgnoreCase(queryId))
		.forEach(book -> books.remove(book));
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
}
