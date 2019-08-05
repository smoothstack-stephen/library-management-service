package com.st.lms.archive;
import java.util.*;

import com.st.lms.model.Author;
import com.st.lms.model.Book;
import com.st.lms.model.Publisher;

public class BookSearch {
	
	// TODO: rewrite loops and iterators as lambda functions
	
	ArrayList<Author> authList;
	ArrayList<Book> bookList;
	ArrayList<Publisher> pubList;

	public void listBooksByAuthor(String authName, String queryType) {
		String authId = "";
		ArrayList<Book> queryResult = new ArrayList<>();
		
		for (Author auth : authList) {
			if (auth.getName().equalsIgnoreCase(authName))
				authId = auth.getId();
		}
		
		for (Book book : bookList) {
			if (book.getAuthId().equalsIgnoreCase(authId))
				queryResult.add(book);
		}
		
		for (Book book : queryResult) {
			String pubName = "";
			for (Publisher pub : pubList) {
				if (pub.getId().equalsIgnoreCase(book.getPubId()))
					pubName = pub.getName();
			}
			
			System.out.println("Name: " + book.getName());
			System.out.println("Publisher: " + pubName + "\n");
		}
	}
	
	public void listBooksByPublisher(String pubName, String queryType) {
		String pubId = "";
		ArrayList<Book> queryResult = new ArrayList<>();
		
		for (Publisher pub : pubList) {
			if (pub.getName().equalsIgnoreCase(pubName))
				pubId = pub.getId();
		}
		
		for (Book book : bookList) {
			if (book.getPubId().equalsIgnoreCase(pubId))
				queryResult.add(book);
		}
		
		for (Book book : queryResult) {
			String authName = "";
			
			for (Author auth : authList) {
				if (book.getAuthId().equalsIgnoreCase(auth.getId()))
					authName = auth.getName();
			}
			
			System.out.println("Name: " + book.getName());
			System.out.println("Author: " + authName + "\n");
		}
	}
	
}
