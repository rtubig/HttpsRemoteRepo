package com.codingwithRod;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHandling {
		
		public static void mainMenu() throws IOException {
			
			/* Displays the code details, e.g., Name, Developer, Email
			   Also displays the main menu options. */
			System.out.println("==============================================");
			System.out.println("Welcome to LockedMe.com's Digitization Product");
			System.out.println("==============================================");
			System.out.println("Application Name: File Handling System");
			System.out.println("Developer Name: Roderick Tubig");
			System.out.println("Developer Email Address: rodericktubig@gmail.com");
			System.out.println("================================================");
			System.out.println();
			System.out.println("What is the operation you wish to perform?");
			System.out.println("=========================================");
			System.out.println("1. Display the list of files\n2. Add / Delete / Search a file\n3. Exit program");
			System.out.println("=========================================");
			System.out.println("Enter the number of your choice ( 1/2/3 )");
			
			try {
				// Creates a scanner object to read the user input from the keyboard
				int choice = 0;
				Scanner input = new Scanner(System.in);
				choice = input.nextInt();
				
				/* Qualifies user input if valid and returns a message if not.
				   If user input is invalid, it will keep on displaying the main menu. */
				if (choice <= 0 || choice > 3) {
					System.out.println("Invalid choice. Please try again.");
					System.out.println();
					mainMenu();
				}
				
				// Calls the different branches of the code based on user input.
				switch (choice)
				{
				/* Calls the listofFiles static block to list the contents of the directory
				   object defined by File class. */
				case 1:
					final File folder = new File("d://temp");
					listofFiles(folder);   
					break;
				// Calls the subMenu static block showing list of operations, e.g., add, delete, search file.
				case 2:
					subMenu();
					break;
				// This will exit the program.
				case 3: System.out.println("Exiting Program...");
					System.exit(0);
				}
				// Closing the input Scanner object here
				input.close();
			}
			/* Returns an java.util.InputMismatchException when keyboard characters are other than the numbers
			   in the presented choices were entered and then shows the main menu options again until choice is valid.*/
			catch (Exception e) {
				System.out.println(e);
				System.out.println("Please enter a valid choice ( 1/2/3 )" );
				System.out.println();
			}
			// Goes back to display the options under the main menu
			mainMenu();
		}
		// This shows the sub menu options when choice (2) from the main menu is picked by user.
		static void subMenu() throws IOException {
			
			System.out.println("What is the operation you wish to perform?");
			System.out.println("=========================================");
			System.out.println("1. Add a file\n2. Delete a file\n3. Search a file\n4. Go back to main menu\n5. Exit program");	
			System.out.println("=========================================");
			System.out.println("Enter the number of your choice ( 1/2/3/4/5 )");
			
			try {
				// Creates a scanner object to read the user input from the keyboard
				int choice = 0;
				Scanner input = new Scanner(System.in);
				choice = input.nextInt();
				
				/* Qualifies user input if valid and returns a message if not.
				   If user input is invalid, it will keep on displaying the main menu. */
				if (choice <= 0 || choice > 5) {
					System.out.println("Invalid choice. Please try again.");
					System.out.println();
					subMenu();
				}
				// Calls the different branches of the code based on user input.
				switch(choice)
				{
				case 1: 
				// Calls the addFile static block to add a file as defined by File class under the directory.
					addFile();
					break;
				case 2: 
				// Calls the deleteFile static block to delete a file as defined by File class under the directory.
					deleteFile();
					break;
				case 3: 
				// Calls the searchFile static block to search for a file under the directory as defined by the File class.
					searchFile();				
					break;
				// Calls the mainMenu method to go back to the main menu options.
				case 4: mainMenu();
					break;
				// Exits the program.
				case 5: System.out.println("Exiting Program...");
					System.exit(0);
				}
				// Closing the input Scanner object here
				input.close();
			}
			/* Returns an java.util.InputMismatchException when keyboard characters are other than the numbers
			   in the presented choices were entered and then shows the sub menu options again until choice is valid.*/
			catch (Exception e) {
				System.out.println("The choice you entered is invalid and yielded the:" + e);
				System.out.println("Please enter a valid choice ( 1/2/3/4/5 )" );
				System.out.println();
			}
			// Calls the subMenu static block to display the sub menu options.
			subMenu();
		}
		// Static block for deleting a file
		static void deleteFile() throws IOException{
			String filename;
			boolean bexp1 = false;
			System.out.println("Please type the name of the file you want to delete.");
			System.out.println();
			
			// Creates a scanner object to read the user input from the keyboard.
			Scanner input = new Scanner(System.in);
			filename = input.next();
			
			/* Creates the file object that points to the directory on the computer and creates an array
			   with the files under the directory as its elements. */
			File file1 = new File("d://temp");
			String[] listOfFiles = file1.list();
			
			/* Iterate through the elements of the array to check if the file to be deleted
			   exists in the array, deletes the file from the directory and sends a confirmation of the file deletion */
			for (int i=0; i<listOfFiles.length; i++) {
				if(listOfFiles[i].equals(filename)) {
					bexp1 = true;
					File file2 = new File("d://temp/" + filename);
					try {
					boolean bexp2 = file2.delete();
					System.out.println(filename + " Deleted");
					}
					catch (Exception e){
						System.out.println(e);
					}
				}	
			}
			// Sends a file not found message if the file to be deleted is not found in the array.
			if (bexp1 == false) {
				System.out.println("File not found.");
				System.out.println();
			}
			System.out.println();
			// Calls the subMenu static block to go back to the sub menu options.
			subMenu();
			// Closing the input Scanner object here
			input.close();
		}
		// Static block for listing of files under the directory.
		static void listofFiles(final File folder) throws IOException {
			// Iterates through the list of files under the folder.
			for (final File fileEntry : folder.listFiles()) {
				if(fileEntry.isDirectory()) {
					listofFiles(fileEntry);
				}
				else {
					System.out.println(fileEntry.getAbsolutePath());
				}
			}
			System.out.println();
			// Calls the mainMenu block to show the main menu options.
			mainMenu();
		}
		// Static block for adding of file under the directory.
		static void addFile() throws IOException{
			// Declaring the variable here.
			String filename;
			System.out.println("Please type the file you wish to add.");
			
			// Creates a scanner object to read the user input from the keyboard
			Scanner input = new Scanner(System.in);
			// Assigning the user input to the variable.
			filename = input.next();
			// Creates the file object using the File class.
			File file = new File("d://temp/"+filename);	
			// Creates the file here if it does not exist and sets the boolean value to true.
			boolean isFileCreated = file.createNewFile();
			/* Checks if file is created via the boolean expression. If true, it sends
			   confirmation that the file has been created. If false, it sends a file already exists message.*/
			if(isFileCreated) {
				System.out.println("File has been successfully created!");
			}
			else {
				System.out.println("File already exists");
			}
			// Calls the subMenu block to show the sub menu options.
			subMenu();	
			// Closing the input Scanner object here
			input.close();			
		}
		// Static block for searching for a file under the directory.
		static void searchFile() throws IOException {
			// Declaring the variable here.
			String filename;
			boolean bexp1 = false;
			System.out.println("Please type the name of the file you want to search.");
			System.out.println();
			
			// Creates a scanner object to read the user input from the keyboard
			Scanner input = new Scanner(System.in);
			// Assigning the user input to the variable.
			filename = input.next();
			
			/* Creates the file object that points to the directory on the computer and creates an array
			   with the files under the directory as its elements. */
			File file1 = new File("d://temp");
			String[] listOfFiles = file1.list();
			
			/* Iterate through the elements of the array to check if there's an element in the array for the file,
			   sets the boolean expression to true, and displays a confirmation that the file is found. */
			for (int i=0; i<listOfFiles.length; i++) {
				if(listOfFiles[i].equals(filename)) {
					bexp1 = true;
					System.out.println(filename + " found");	
				}	
			}
			// Sets the boolean expression to false and displays a file not found message if file does not exist.
			if (bexp1 == false) {
				System.out.println("File not found.");
			}
			// Calls the subMenu block to show the sub menu options.
			subMenu();
			// Closing the input Scanner object here
			input.close();
		}
	public static void main(String[] args) throws IOException {
		
		// Calls the mainMenu static block to show the main menu options.
		mainMenu();
	}
}
