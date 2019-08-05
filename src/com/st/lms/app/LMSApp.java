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
		app.start();
	}

	public void start() throws IOException {
		authDao = new AuthorDao();
		bookDao = new BookDao();
		pubDao = new PublisherDao();
		
		showTitleMenu();
	}
	
	public void showTitleMenu() {
		System.out.println("============== Library Management Service, by SSSJ ==============\n");
		System.out.println("Select from the following options (type the option number):\n");
		System.out.println("[1] Add new...");
		System.out.println("[2] Update existing...");
		System.out.println("[3] Retrieve existing...");
		System.out.println("[4] Remove existing...");
		System.out.println("[5] Exit service\n");
		
		int userInput = Integer.parseInt(scan.next());
		
		if (userInput == 5) {
			System.out.println("Thank you for using our Library Management Service!");
			System.exit(0);
		}
		else {
			System.out.println("[1] Author");
			System.out.println("[2] Book");
			System.out.println("[3] Publisher");
		}
	}
	
}
