package com.smoothstack.lms;
import java.util.*;

public class Service {
	
	private Author[] authList;
	private Publisher[] pubList;
	private Book[] bookList;
	private static String[] menuHeaders = {"Add new Author, Book, or Publisher",
										   "Update existing Author, Book, or Publisher",
										   "Retrieve existing Author, Book, or Publisher",
										   "Remove existing Author, Book, or Publisher",
										   "Exit service"};
	private Scanner scan;
	
	public void showMenuHeaders() {
		for(int i=0; i<menuHeaders.length; i++) {
			System.out.printf(">> [%d] %s\n", i+1, menuHeaders[i]);
		}
		System.out.println();
	}

	public void showMoreOptions(String operationType) {
		System.out.println("============================================================\n");
		System.out.println(operationType + " which of the following options? (type the option number):\n");
		System.out.println(">> [1] Author\n>> [2] Book\n>> [3] Publisher\n>> [4] Exit service\n");
		System.out.println("============================================================");
		
		int objType = scan.nextInt();
		System.out.println("Selected Option " + objType);
		
		switch(objType) {
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
		
		scan.close();
	}

	public void performOperation(String operationType, String objType) {
		
	}
	
	// Prints the title menu that displays options to Add, Update, Retrieve, or Remove
	public void showTitleMenu() {
		scan = new Scanner(System.in);
		System.out.println("============== Library Management Service v1.0 Beta ==============\n");
		System.out.println("Select from the following options (type the option number):\n");
		showMenuHeaders();
		System.out.println("==================================================================");

		int optionNumber = scan.nextInt();
		
		if (optionNumber > 0 && optionNumber <= menuHeaders.length) {
			String operationType = menuHeaders[optionNumber-1];
			String operationKeyword = operationType.substring(0, operationType.indexOf(" "));
			System.out.printf("Selected Option %d: %s\n", optionNumber, operationType);
			
			// Assuming exit option is always the last in the array
			if (operationKeyword.toLowerCase().equals("exit")) {
				System.out.println("Thank you for using Library Management Service, see you again soon!");
			} else {
				// Add, Update, Retrieve, or Remove (just one word for convenience)
				showMoreOptions(operationKeyword); 
			}
		} else {
			System.out.println("Selected an invalid menu option, please try again.");
			showTitleMenu();
		}
		
		scan.close();
	}
	
	public static void main(String[] args) {
		Service LibraryManagementService = new Service();
		LibraryManagementService.showTitleMenu();
	}

}
