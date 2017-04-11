/************************************************************************
 * Author: Sean Lee
 * 15sl18
 * #10199548
 * CMPE 212
 * Hesham Farahat
 * 29/3/2017
************************************************************************/

package model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import model.Customer.Type;

public class Assn5_15sl18 {

	public static void main(String[] args) throws ParseException, NumberFormatException, WrongRentalCost, IOException, DuplicateItemID, DuplicateCustomerID {
		HashMap rentals = new HashMap();
		Library stauffer = new Library(rentals);
		
		/*
		 * Assignment 3
		 * Create new object for each type, with appropriate parameters
		 * As an example, each device has a rental cost of 10
		 */
		Adaptor adaptor = null;
		Device earphones = null;
		Laptop laptop = null;
		try {
			earphones = new Device("earphones", 10);
			adaptor = new Adaptor("adaptor", 10);
			laptop = new Laptop("laptop", 10);
		} catch (WrongRentalCost e) {
			System.out.println(e);
		}
		Book book = new Book("Test Book", "Sean", "Queen's", 2017);
		Magazine magazine = new Magazine("Test Magazine", "Tupac", "Ryerson", 2016);
		Textbook textbook = new Textbook("Test Textbook", "Lee", "Waterloo", 2015);
		
		/**************************************************************************
		 * Assignment 5 begin here
		 * All files are read and printed in the same directory as project folder
		 */
		
		//Read Items from file, removed commented area at top of files
		System.out.println("Reading items from items.in");
		HashMap<Integer, Item> fileItems = stauffer.readItems("items.in");
		for (Integer key: fileItems.keySet()){
			System.out.println(fileItems.get(key).toString());
		}
		//Write items to file
		fileItems.put(1000, earphones);
		System.out.println("Adding earphones, and rewriting to items.csv");
		stauffer.writeItems(fileItems);
		//Read Customers from file, correction to file since types are Employees and Students, not employee and student
		System.out.println("Reading customers from customers.in");
		HashMap<Integer, Customer> fileCustomers = stauffer.readCustomers("customers.in");
		for (Integer key: fileCustomers.keySet()){
			System.out.println(fileCustomers.get(key).toString());
		}
		//Write customers to file
		Customer sean = new Customer(Type.Students, 10199658, "Sean Lee", "ECE");
		fileCustomers.put(10199658, sean);
		System.out.println("Adding myself and rewriting to customers.csv");
		stauffer.writeCustomers(fileCustomers);
		//Read Transactions from file, fixed inconsistencies with CSV
		//Add customers and items with appropriate transaction ids to pull from
		Device computer = new Device("Computer", 10, 45);
		Device mouse = new Device("Mouse", 10, 11);
		Device keyboard = new Device("Keyboard", 10, 22);
		stauffer.AddItem(computer);
		stauffer.AddItem(mouse);
		stauffer.AddItem(keyboard);
		Customer sean2 = new Customer(Type.Students, 78, "Sean Lee", "ECE");
		Customer sean4 = new Customer(Type.Students, 79, "Sean Lee", "ECE");
		stauffer.AddCustomer(sean2);
		stauffer.AddCustomer(sean4);
		System.out.println("Adding Customers and Items with respective transaction ID's and Reading transactions from trans.in");
		HashMap<Integer, Rental> fileRentals = stauffer.readTransactions("trans.in");
		for (Integer key: fileRentals.keySet()){
			System.out.println(fileRentals.get(key).toString());
		}
		//Write transactions to file
		System.out.println("Rewriting transactions to trans.csv");
		stauffer.writeTransactions(fileRentals);
		//Search for transactions between date
		System.out.println("Adding read transactions to library");
		stauffer.setRentals(fileRentals);
		System.out.println("Searching for transactions between 28/3/2017 and 30/3/2017");
		SimpleDateFormat simpleForm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date1 = simpleForm.parse("28/03/2017 00:00:00");
		Date date2 = simpleForm.parse("30/03/2017 00:00:00");
		HashMap<Integer, Rental> rentalSearch = stauffer.dateTransaction(date1, date2);
		for (Integer key: rentalSearch.keySet()){
			System.out.println(rentalSearch.get(key).toString());
		}
		//Search for transactions by field
		System.out.println("Searching for transactions by transaction ID: 123");
		rentalSearch = stauffer.searchTransaction("TransactionID 123");
		for (Integer key: rentalSearch.keySet()){
			System.out.println(rentalSearch.get(key).toString());
		}
		//Search for items by field
		stauffer.setItems(fileItems);
		System.out.println("Searching for item by name: earphones");
		HashMap<Integer, Item>itemSearch = stauffer.searchItem("NAME earphones");
		for (Integer key: itemSearch.keySet()){
			System.out.println(itemSearch.get(key).toString());
		}

		/*************************************************************************************
		 * Mostly Assignment 4 material starts below this, Assignment 3 tests have been commented out
		//Adding some test customers
		Customer martin = new Customer(Type.Students, 10191985, "Martin Lee", "Electrical");
		Customer darian = new Customer(Type.Students, 10181505, "Darian Lio", "Riot Games");
		
		//Adding dates
		SimpleDateFormat simpleForm = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
		Date date1 = simpleForm.parse("2017-03-30-12:00");
		Date date2 = simpleForm.parse("2017-03-31-12:00");
		Date date3 = simpleForm.parse("2017-04-01-12:00"); //Will be used as current date for on time transaction
		Date date4 = simpleForm.parse("2017-04-02-12:00"); //Will be used as current day for late transaction
		
		 * Exception Testing (No exceptions for file reading since those were removed from assignment 4)

		System.out.println("Exception Testing");
		//Create two new items with conflicting IDS
		Textbook textbook2 = new Textbook("Test Textbook", "Lee", "Waterloo", 2015, 100);
		Textbook textbook3 = new Textbook("Test Textbook", "Lee", "Waterloo", 2017, 100);
		try{
			stauffer.AddItem(textbook2);
			stauffer.AddItem(textbook3);
		} catch (DuplicateItemID e) {
			System.out.println(e);
		}

		//Create two new customers with conflicting IDS
		Customer sean = new Customer(Type.Students, 10, "Sean Lee", "Computer");
		Customer andus = new Customer(Type.Students, 10, "ANdus Yu", "Computer");
		try{
			stauffer.AddCustomer(sean);
			stauffer.AddCustomer(andus);
		} catch (DuplicateCustomerID e) {
			System.out.println(e);
		}
		
		//Create device with negative rental cost
		try{
		Device gameboy = new Device("Gameboy", -10);
		} catch(WrongRentalCost e) {
			System.out.println(e);
		}
		
		//Create rentals with conflicting ids
		Rental rental3 = new Rental(textbook, sean, date1, date2, 20);
		Rental rental4 = new Rental(textbook, sean, date1, date2, 20);
		try{
			stauffer.AddTransaction(rental3);
			stauffer.AddTransaction(rental4);;
		} catch(DuplicateTransactionID e){
			System.out.println(e);
		}
		
		//Try to return item before rental date
		Rental rental5 = new Rental(textbook, sean, date3, date4, 30);
		try{
			rental5.itemReturned(date1);
		} catch (DateReturnedBeforeDateRented e){
			System.out.println(e);
		}
		
		/*************************************************************************************
		 * Method Testing

		System.out.println("\n" + "Method Testing");
		//Add both transactions under Darian and Martin
		Rental rental1 = new Rental(adaptor, martin, date1, date2);
		Rental rental2 = new Rental(adaptor, darian, date1, date3);
		System.out.println("New Transaction: " + rental1.rItem().rName() + " rented by " + rental1.rCustomer().rName() + " on " + simpleForm.format(rental1.rRentalDate()) + " due by " + simpleForm.format(rental1.rEstDate()));
		System.out.println("New Transaction: " + rental2.rItem().rName() + " rented by " + rental2.rCustomer().rName() + " on " + simpleForm.format(rental2.rRentalDate()) + " due by " + simpleForm.format(rental2.rEstDate()));
		try{
			stauffer.AddTransaction(rental1);
			stauffer.AddTransaction(rental2);
		} catch(DuplicateTransactionID e){
			System.out.println(e);
		}

		//Return items
		try{
			rental1.itemReturned(date4); //Item returned late for martin, due on March 31, returned on April 2, 2 day difference
			rental2.itemReturned(date1); //Item returned on time for darian
		} catch (DateReturnedBeforeDateRented e){
			System.out.println(e);
		}
		System.out.println("Martin returned on " + simpleForm.format(rental1.rReturnedDate()));
		System.out.println("Darian returned on " + simpleForm.format(rental2.rReturnedDate()));
		
		//Check who is late
		System.out.println("Martin late: " + rental1.isLate(date4)); //Did martin return late
		System.out.println("Darian late: " + rental2.isLate(date1)); //Did darian return late
		
		
		//2 days late, and adaptor cost is 2.5$/day -> cost is 5$, rental cost is 10*0.15 = 1.5, with 25% off since martin is a student = 1.125
		System.out.println("Late Fees Martin: $" + ((Rental) stauffer.rRentals().get(0)).getLateFees());
		System.out.println("Rental Fees Martin: $" + ((Rental) stauffer.rRentals().get(0)).getRentCosts());
		System.out.println("Total Fees Martin: $" + ((Rental) stauffer.rRentals().get(0)).getTotalToBePaid());
		
		/*************************************************************************************
		 * Assignment 3 methods 
		 */
		/*
		System.out.println("Total Late Fees: $" + stauffer.GetTotalLateFees());
		System.out.println("Total Rent Costs: $" + stauffer.GetTotalRentCosts() + "\n");
		//toString to show each attribute for each item
		System.out.println("toString for each object" + "\n");
		
		System.out.println(stauffer.toString());
		System.out.println(((Rental) stauffer.rRentals().get(0)).rItem().toString());
		System.out.println(((Rental) stauffer.rRentals().get(1)).rItem().toString());
		System.out.println(((Rental) stauffer.rRentals().get(2)).rItem().toString());
		System.out.println(((Rental) stauffer.rRentals().get(3)).rItem().toString());
		System.out.println(((Rental) stauffer.rRentals().get(4)).rItem().toString());
		System.out.println(((Rental) stauffer.rRentals().get(5)).rItem().toString());*/
	}
}
