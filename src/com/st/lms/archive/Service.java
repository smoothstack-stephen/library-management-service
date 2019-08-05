package com.st.lms.archive;
import java.util.*;
import java.util.Map.Entry;

import com.st.lms.model.Author;
import com.st.lms.model.Book;
import com.st.lms.model.Publisher;

public class Service {
	
	private TreeMap<Author, ArrayList<Book>> searchByAuthor;
	private TreeMap<Publisher, ArrayList<Book>> searchByPublisher;
	private ArrayList<Book> bookList;
	private static String[] menuHeaders = {"Add new Author, Book, or Publisher",
										   "Update existing Author, Book, or Publisher",
										   "Retrieve existing Author, Book, or Publisher",
										   "Remove existing Author, Book, or Publisher",
										   "Exit service"};
	private Scanner scan;
	
	// Shows the title menu that displays options to Add, Update, Retrieve, or Remove
	public void showTitleMenu() {
		System.out.println("============== Library Management Service v1.0 Beta ==============\n");
		System.out.println("Select from the following options (type the option number):\n");
		for(int i=0; i<menuHeaders.length; i++) {
			System.out.printf(">> [%d] %s\n", i+1, menuHeaders[i]);
		}
		System.out.println("\n==================================================================");
		scan = new Scanner(System.in);
		int optionNumber = 0;
		
		try {
			optionNumber = scan.nextInt();
			
			if (optionNumber > 0 && optionNumber <= menuHeaders.length) {
				String operationType = menuHeaders[optionNumber-1];
				String operationKeyword = operationType.substring(0, operationType.indexOf(" "));
				
				// Assuming exit option is always the last in the array
				if (operationKeyword.toLowerCase().equals("exit")) {
					System.out.println("Thank you for using Library Management Service, see you again soon!");
				} else {
					// Add, Update, Retrieve, or Remove (just one word for convenience)
					showMoreOptions(operationKeyword); 
				}
			} else {
				System.out.println("Selected an invalid menu option, please try again.\n");
				showTitleMenu();
			}
		} catch (Exception e) {
			System.out.println("Selected an invalid menu option, please try again.\n");
			showTitleMenu();
		}
		
		scan.close();
	}	

	// Shows the options to perform an operation on an Author, Book, or Publisher
	public void showMoreOptions(String operationType) {
		System.out.println("============================================================\n");
		System.out.println(operationType + " which of the following options? (type the option number):\n");
		System.out.println(">> [1] Author\n>> [2] Book\n>> [3] Publisher\n>> [4] Exit service\n");
		System.out.println("============================================================");
		
		int menuOption = Integer.parseInt(scan.nextLine());
		System.out.println();
		
		switch(menuOption) {
			case 1:
				performOperation(operationType, "Author");
				break;
			case 2:
				performOperation(operationType, "Book");
				break;
			case 3:
				performOperation(operationType, "Publisher");
				break;
			case 4:
				System.out.println("Thank you for using Library Management Service, see you again soon!");
				break;
			default:
				System.out.println("Selected an invalid menu option, please try again.");
				showMoreOptions(operationType);
		}
	}

	// Performs the Add, Update, Retrieve, or Remove function (based on user input)
	public void performOperation(String operationType, String objType) {	
		if (operationType.equals("add")) {
			if (objType.equalsIgnoreCase("author")) addAuthor();
			if (objType.equalsIgnoreCase("book")) addBook();
			if (objType.equalsIgnoreCase("publisher")) addPublisher();
		}
		if (operationType.equals("update")) {
			if (objType.equalsIgnoreCase("author")) updateAuthor();
			if (objType.equalsIgnoreCase("book")) updateBook();
			if (objType.equalsIgnoreCase("publisher")) updatePublisher();
		}
		if (operationType.equals("retrieve")) {
			if (objType.equalsIgnoreCase("author")) retrieveAuthor();
			if (objType.equalsIgnoreCase("book")) retrieveBook();
			if (objType.equalsIgnoreCase("publisher")) retrievePublisher();			
		}
		if (operationType.equals("remove")) {
			if (objType.equalsIgnoreCase("author")) removeAuthor();
			if (objType.equalsIgnoreCase("book")) removeBook();
			if (objType.equalsIgnoreCase("publisher")) removePublisher();				
		}
	}
		
	public void addAuthor() {
		String name, authId;
		System.out.println("Enter the name of the author: ");
		name = scan.nextLine();
		System.out.println("Enter the id of the author: ");
		authId = scan.nextLine();
		searchByAuthor.put(new Author(name, authId), new ArrayList<Book>());		
	}
	
	public void addBook() {
		String name, bookId, authId, pubId;
		System.out.println("Enter the name of the book: ");
		name = scan.nextLine();
		System.out.println("Enter the id of the book: ");
		bookId = scan.nextLine();
		System.out.println("Enter the author id of the book: ");
		authId = scan.nextLine();
		System.out.println("Enter the publisher id of the book: ");
		pubId = scan.nextLine();
		bookList.add(new Book(name, bookId, authId, pubId));		
	}
	
	public void addPublisher() {
		String name, pubId, address;
		System.out.println("Enter the name of the publisher: ");
		name = scan.nextLine();
		System.out.println("Enter the id of the publisher: ");
		pubId = scan.nextLine();
		System.out.println("Enter the address of the publisher: ");
		address = scan.nextLine();
		searchByPublisher.put(new Publisher(name, pubId, address), new ArrayList<Book>());		
	}
	
	public void updateAuthor() {
		
	}

	public void updateBook() {
		
	}
	
	public void updatePublisher() {
		
	}
	
	public void retrieveAuthor() {
		String searchName;
		
		System.out.println("Enter the name of the author: ");
		searchName = scan.nextLine();
		
		for (Entry<Author, ArrayList<Book>> entry : searchByAuthor.entrySet()) {
			String authName = entry.getKey().getName();
			String authId = entry.getKey().getId();
			ArrayList<Book> books = entry.getValue();
			
			if (authName.equalsIgnoreCase(searchName)) {
				System.out.println(authName + " (" + authId + ")");
				for (Book book : books) {
					System.out.println("Book Name: " + book.getName());
					System.out.println("Book Publisher Id: " + book.getPubId());
					System.out.println();
				}
			}
		}
	}

	public void retrieveBook() {
		
	}	

	public void retrievePublisher() {
		
	}	
	
	public void removeAuthor() {
		
	}
	
	public void removeBook() {
		
	}
	
	public void removePublisher() {
		
	}
	
	public static void main(String[] args) {
		Service LibraryManagementService = new Service();
		LibraryManagementService.showTitleMenu();
	}

}
