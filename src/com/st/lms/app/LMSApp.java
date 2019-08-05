package com.st.lms.app;
import java.io.IOException;

import com.st.lms.dao.AuthorDao;
import com.st.lms.dao.BookDao;
import com.st.lms.dao.PublisherDao;
import java.util.*;

public class LMSApp {
	
	private static Scanner scan = new Scanner(System.in);
	AuthorDao authDao;
	BookDao bookDao;
	PublisherDao pubDao;
	
	public static void main(String[] args) throws IOException {
		LMSApp app = new LMSApp();
		//app.start();
		app.showTitleMenu();
	}

	public void start() throws IOException {
		authDao = new AuthorDao();
		bookDao = new BookDao();
		pubDao = new PublisherDao();
		
		showTitleMenu();
		
		exitApp();
	}
	
	public void showTitleMenu() {
		System.out.println("============== Library Management Service, by SSSJ ==============\n");
		System.out.println("Select from the following options (type the option number):\n");
		System.out.println("[1] Add new...");
		System.out.println("[2] Update existing...");
		System.out.println("[3] Retrieve existing...");
		System.out.println("[4] Remove existing...");
		System.out.println("[5] Exit service\n");
		
		int operationChoice = Integer.parseInt(scan.nextLine());
		
		if (operationChoice == 5) exitApp();
		
		System.out.println();
		System.out.println("[1] Author");
		System.out.println("[2] Book");
		System.out.println("[3] Publisher");
		System.out.println("[4] Exit service\n");
		
		int objectChoice = Integer.parseInt(scan.nextLine());
		
		if (objectChoice == 4) exitApp();
		
		performTask(operationChoice, objectChoice);
		
	}
	
	public void performTask(int taskType, int objectType) {
		String name, id, authId, pubId, address;
		
		switch (objectType) {
		case 1: // Author
			System.out.println("Enter the author's id:");
			id = scan.nextLine();
			switch (taskType) {
				case 1: // Add
					System.out.println("Enter the author's name:");
					name = scan.nextLine();
					authDao.addAuthor(name, id);
					break;
				case 2: // Update
					System.out.println("Enter the author's new name:");	
					name = scan.nextLine();
					authDao.updateAuthor(id, name);
					break;
				case 3: // Retrieve
					authDao.retrieveAuthor(id);
					break;
				case 4: // Remove
					authDao.removeAuthor(id);
					break;
				default:
					break;
			}
			break;
		case 2: // Book
			System.out.println("Enter the book's id:");
			id = scan.nextLine();
			switch (taskType) {
				case 1: // Add
					System.out.println("Enter the book's name:");
					name = scan.nextLine();
					System.out.println("Enter the author id:");
					authId = scan.nextLine();
					System.out.println("Enter the publisher id:");
					pubId = scan.nextLine();
					bookDao.addBook(name, id, authId, pubId);
					break;
				case 2: // Update
					System.out.println("Enter the new book name:");
					name = scan.nextLine();
					System.out.println("Enter the new author id:");
					authId = scan.nextLine();
					System.out.println("Enter the new publisher id:");
					pubId = scan.nextLine();
					bookDao.updateBook(id, name, authId, pubId);
					break;
				case 3: // Retrieve
					bookDao.retrieveBook(id);
					break;
				case 4: // Remove
					bookDao.removeBook(id);
					break;
				default:
					break;
			}
			break;
		case 3: // Publisher
			System.out.println("Enter the publisher's id:");
			id = scan.nextLine();
			switch (taskType) {
				case 1: // Add
					System.out.println("Enter the publisher's name:");
					name = scan.nextLine();
					System.out.println("Enter the publisher's address:");
					address = scan.nextLine();
					pubDao.addPublisher(name, id, address);
					break;
				case 2: // Update
					System.out.println("Enter the new publisher name:");
					name = scan.nextLine();
					System.out.println("Enter the new publisher address:");
					address = scan.nextLine();
					pubDao.updatePublisher(id, name, address);
					break;
				case 3: // Retrieve
					pubDao.retrievePublisher(id);
					break;
				case 4: // Remove
					pubDao.removePublisher(id);
					break;
				default:
					break;
			}
			break;
		default:
			break;
		}
	}
	
	public void exitApp() {
		System.out.println("=================================================================\n");
		System.out.println("Thank you for using our Library Management Service!");
		System.out.println("We hope you have a great day.");
		System.exit(0);		
	}
}
