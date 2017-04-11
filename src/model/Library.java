package model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import model.Customer.Type;
import model.Rental.Status;

public class Library {
	//Collections with ID as index's
	private HashMap<Integer, Item> item = new HashMap<Integer, Item>();
	private HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
	private HashMap<Integer, Rental> rental = new HashMap<Integer, Rental>();
	
	//Read from Items method
	public HashMap<Integer, Item> readItems(String fileName) throws NumberFormatException, WrongRentalCost, FileNotFoundException {
		File file = new File(fileName);
		HashMap<Integer, Item> item = new HashMap<Integer, Item>();
		Scanner sc = new Scanner(file);
		while(sc.hasNext()){
			String readItem = sc.nextLine();
			String readItemSplit[] = readItem.split(",\\s+");
			int ID = Integer.parseInt(readItemSplit[1]);
			if(readItemSplit[0].equals("Adaptor")){
				Adaptor adaptor = new Adaptor(readItemSplit[2],Double.parseDouble(readItemSplit[3]),ID);
				item.put(ID, adaptor);
			}
			else if(readItemSplit[0].equals("Book")){
				Book book = new Book(readItemSplit[2],readItemSplit[3], readItemSplit[4], Integer.parseInt(readItemSplit[5]), ID);
				item.put(ID, book);
			}
			else if(readItemSplit[0].equals("Device")){
				Device device = new Device(readItemSplit[2],Double.parseDouble(readItemSplit[3]), ID);
				item.put(ID, device);
			}
			else if(readItemSplit[0].equals("Laptop")){
				Laptop laptop = new Laptop(readItemSplit[2],Double.parseDouble(readItemSplit[3]), ID);
				item.put(ID, laptop);
			}
			else if(readItemSplit[0].equals("Magazine")){
				Magazine magazine = new Magazine(readItemSplit[2],readItemSplit[3], readItemSplit[4], Integer.parseInt(readItemSplit[5]), ID);
				item.put(ID, magazine);
			}
			else if(readItemSplit[0].equals("Textbook")){
				Textbook textbook = new Textbook(readItemSplit[2],readItemSplit[3], readItemSplit[4], Integer.parseInt(readItemSplit[5]), ID);
				item.put(ID, textbook);
			}
			else{
				final Item i = new Item(readItemSplit[2], ID){
					@Override
					public double getLateFees(int days) {return 0;}
					@Override
					public Item Clone() {return null;}
				};
				item.put(ID, i);
			}
		}
		sc.close();
		return item;
	}
	//Read from Customers method
	public HashMap<Integer, Customer> readCustomers(String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
		Scanner sc = new Scanner(file);
		while(sc.hasNext()){
			String readCustomer = sc.nextLine();
			String readCustomerSplit[] = readCustomer.split(",\\s+");
			
			int ID = Integer.parseInt(readCustomerSplit[0]);
			String name = readCustomerSplit[1];
			String 	department = readCustomerSplit[2];
			Customer customer = null;
			
			if(readCustomerSplit[3].equals("Students")){customer = new Customer(Type.Students, ID, name, department);}
			else if(readCustomerSplit[3].equals("Employees")){customer = new Customer(Type.Employees, ID, name, department);}
			customers.put(ID, customer);
		}
		return customers;
	}
	//Read from Transactions method
	//Uses constructor that takes item and customers, so search Item and Customer hashmaps to get item and customer based on ID
	public HashMap<Integer, Rental> readTransactions(String fileName) throws FileNotFoundException, ParseException{
		File file = new File(fileName);
		HashMap<Integer, Rental> rentals = new HashMap<Integer,Rental>();
		Scanner sc = new Scanner(file); 
		while(sc.hasNext()){
			String readRental = sc.nextLine();
			String readRentalSplit[] = readRental.split(",\\s+");
			
			int tID = Integer.parseInt(readRentalSplit[0]);
			Item item = this.item.get(Integer.parseInt(readRentalSplit[1]));
			Customer customer = this.customers.get(Integer.parseInt(readRentalSplit[2]));
			
			SimpleDateFormat simpleForm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date transDate = simpleForm.parse(readRentalSplit[3]);
			Date estDate = simpleForm.parse(readRentalSplit[4]);
			Date retDate;
			if (readRentalSplit[5].equals("00/0/0000 00:00:00")){ retDate = null; }//Constructor sets as closed if a null date is passed in the return field
			else {retDate = simpleForm.parse(readRentalSplit[5]);}
			Rental rental = new Rental(item, customer, transDate, estDate, retDate, tID);
			rentals.put(tID, rental);
		}
		return rentals;
	}
	
	//Write Items method
	public void writeItems(HashMap<Integer, Item> item) throws IOException{
		String filename = "src//items.csv";
		File file = new File(filename);
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter("items.csv"));
		} catch (IOException e){
		}
		for (Integer key : item.keySet()) {
				Item i = item.get(key);
				String itemString = "";
				if (i instanceof Device	){
					itemString = (i.getClass().getName() + ", " + i.rId() + ", " + i.rName() + ", " + ((Device) i).rRentalCost()).replaceAll("model.", "");
				}
				if (i instanceof Book){
					itemString = (i.getClass().getName() + ", " + i.rId() + ", " + i.rName() + ", " + ((Book) i).rAuthors() + ", " + ((Book)i).rPublisher() + ", " + ((Book)i).rYear()).replaceAll("model.", "");
				}
				writer.write(itemString + "\n");
		}
		
		writer.close();
	}
	//Write Customers method
	public void writeCustomers(HashMap<Integer, Customer> customer) throws IOException{
		String filename = "src//customers.csv";
		File file = new File(filename);
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter("customers.csv"));
		} catch (IOException e){
		}
		for (Integer key : customer.keySet()) {
			Customer c = customer.get(key);
			try{
				String customerString = (c.rId() + ", " + c.rName() + ", " + c.rDepartment() + ", " + c.rType().name());
				writer.write(customerString + "\n");
			} catch (FileNotFoundException ex){}
		}
		writer.close();
	}
	//Write Transactions method
	public void writeTransactions(HashMap<Integer, Rental> rental) throws IOException{
		String filename = "src//trans.csv";
		File file = new File(filename);
		BufferedWriter writer = null;
		
		try{
			writer = new BufferedWriter(new FileWriter("trans.csv"));
		} catch (IOException e){
		}
		for (Integer key : rental.keySet()) {
			Rental r = rental.get(key);

			try{
				SimpleDateFormat simpleForm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String transactionString;
				if(r.rReturnedDate()!= null){
					transactionString = (r.rTId() + ", " + r.rItem().rId() + ", " + r.rCustomer().rId() + ", " + simpleForm.format(r.rRentalDate()) + ", " + simpleForm.format(r.rEstDate()) + ", " + simpleForm.format(r.rReturnedDate()));
				}
				else{transactionString = (r.rTId() + ", " + r.rItem().rId() + ", " + r.rCustomer().rId() + ", " + simpleForm.format(r.rRentalDate()) + ", " + simpleForm.format(r.rEstDate()) + ", 00/0/0000 00:00:00");
				}
				writer.write(transactionString + "\n");
			} catch (FileNotFoundException ex){}
		}
		writer.close();
	}
	//Return Transactions method
	public HashMap<Integer, Rental> dateTransaction(Date date1, Date date2){
		HashMap<Integer, Rental> rentals = new HashMap<Integer,Rental>();
		for (Integer key: this.rental.keySet()){
			Rental r = this.rental.get(key);
			if (r.rRentalDate().after(date1) && r.rRentalDate().before(date2)){
				rentals.put(r.rTId(), r);
			}
		}
		return rentals;
	}
	
	//Search Transactions by Field, search is in format "Field Value", if it's transaction ID, just get the value
	public HashMap<Integer, Rental> searchTransaction(String search) throws ParseException{
		HashMap<Integer, Rental> rentals = new HashMap<Integer,Rental>();
		String delim = "\\s+";
		String[] searchTerm = search.split(delim);
		SimpleDateFormat simpleForm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (searchTerm[0].equals("TransactionID")){rentals.put(Integer.parseInt(searchTerm[1]), this.rental.get(Integer.parseInt(searchTerm[1])));}
		//Since my rental format takes an item, first look for item with that id, then look for rental with that item
		if (searchTerm[0].equals("ItemID")){
			for (Integer key: this.item.keySet()){
				Item i = this.item.get(key);
				if (i.rId() == Integer.parseInt(searchTerm[1])){
					for(Integer key2: this.rental.keySet()){
						Rental r = this.rental.get(key2);
						if(i.rId() == r.rItem().rId()){rentals.put(r.rTId(),r);}
					}
				}
			}
		}
		if (searchTerm[0].equals("CustomerID")){
			for (Integer key: this.customers.keySet()){
				Customer c = this.customers.get(key);
				if (c.rId() == Integer.parseInt(searchTerm[1])){
					for(Integer key2: this.rental.keySet()){
						Rental r = this.rental.get(key2);
						if(c.rId() == r.rCustomer().rId()){rentals.put(r.rTId(),r);}
					}
				}
			}
		}
		if (searchTerm[0].equals("TransactionDate")){
			for(Integer key: this.rental.keySet()){
				Rental r = this.rental.get(key);
				if(r.rRentalDate() == simpleForm.parse(searchTerm[1] + " " + searchTerm[2])){
					rentals.put(r.rTId(), r);
				}
			}
		}
		if (searchTerm[0].equals("ExpectedReturnDate")){
			for(Integer key: this.rental.keySet()){
				Rental r = this.rental.get(key);
				if(r.rEstDate() == simpleForm.parse(searchTerm[1] + " " + searchTerm[2])){
					rentals.put(r.rTId(), r);
				}
			}
		}
		if (searchTerm[0].equals("ReturnDate")){
			for(Integer key: this.rental.keySet()){
				Rental r = this.rental.get(key);
				if(r.rReturnedDate() == simpleForm.parse(searchTerm[1] + " " + searchTerm[2])){
					rentals.put(r.rTId(), r);
				}
			}
		}
		return rentals;
	}
	
	//Search Item by Field format: searchItem("Field Value")
	public HashMap<Integer, Item> searchItem(String search) throws ParseException{
		HashMap<Integer, Item> items = new HashMap<Integer,Item>();
		String delim = "\\s+";
		String[] searchTerm = search.split(delim);
		if (searchTerm[0].equals("CLASS_NAME")){
			for(Integer key: this.item.keySet()){
				Item i = this.item.get(key);
				if(i.getClass().getName().replaceAll("libary.","").equals(searchTerm[1])){
					items.put(i.rId(), i);
				}
			}
		}
		if (searchTerm[0].equals("ID")){items.put(Integer.parseInt(searchTerm[1]),this.item.get(Integer.parseInt(searchTerm[1])));}
		if (searchTerm[0].equals("NAME")){
			for(Integer key: this.item.keySet()){
				Item i = this.item.get(key);
				if(i.rName().equals(searchTerm[1])){
					items.put(i.rId(), i);
				}
			}
		}
		if (searchTerm[0].equals("RENTAL_COST")){
			for(Integer key: this.item.keySet()){
				Item i = this.item.get(key);
				if(((Device) i).rRentalCost() == Double.parseDouble(searchTerm[1])){
					items.put(i.rId(), i);
				}
			}
		}
		if (searchTerm[0].equals("AUTHOR")){
			for(Integer key: this.item.keySet()){
				Item i = this.item.get(key);
				if(((Book)i).rAuthors().equals(searchTerm[1])){
					items.put(i.rId(), i);
				}
			}
		}
		if (searchTerm[0].equals("PUBLISHERS")){
			for(Integer key: this.item.keySet()){
				Item i = this.item.get(key);
				if(((Book)i).rPublisher().equals(searchTerm[1])){
					items.put(i.rId(), i);
				}
			}
		}
		if (searchTerm[0].equals("YEAR")){
			for(Integer key: this.item.keySet()){
				Item i = this.item.get(key);
				if(((Book)i).rYear() == Integer.parseInt(searchTerm[1])){
					items.put(i.rId(), i);
				}
			}
		}
		return items;
	}
	//Add Item method
	public void AddItem(Item obj) throws DuplicateItemID {
		if (item.containsKey(obj.rId())){ throw new DuplicateItemID(); }//Throw exception if key has a mapping
		item.put(obj.rId(),obj);
	}
	//Add Transaction method
	public void AddTransaction(Rental obj) throws DuplicateTransactionID{
		if (rental.containsKey(obj.rTId())){ throw new DuplicateTransactionID(); }//Throw exception if key has a mapping
		rental.put(obj.rTId(), obj);
	}
	//Add Customer method
	public void AddCustomer(Customer obj) throws DuplicateCustomerID{
		if (customers.containsKey(obj.rId())){ throw new DuplicateCustomerID(); }
		customers.put(obj.rId(), obj);
	}
	
	//GetTotalLateFees method
	public double GetTotalLateFees(){
		double total = 0;
		for (Integer key : rental.keySet()) {
				total = total + (rental.get(key)).getLateFees();
		}
		return total;
	}
	//GetTotalRentalCosts
	public double GetTotalRentCosts(){
		double total = 0;
		for (Integer key: rental.keySet()) {
			if((rental.get(key)).rItem() instanceof Device){
				total = total + (rental.get(key)).getRentCosts();
			}
		}
		return total;
	}
	
	//Copy constructor
	public Library(Library library){
		this.rental = rental;
	}
	//Construtor with all the attributes
	public Library(HashMap rentals){
		this.rental = rentals;
	}
	
	//Equals method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Library other = (Library) obj;
		if (rental == null) {
			if (other.rental != null)
				return false;
		} else if (!rental.equals(other.rental))
			return false;
		return true;
	}
	//toString method
	public String toString() {
		return "Library [rentals=" + rental+ "]";
	}
	//Clone method
	public Library Clone(){
		return new Library(this);
	}

	//Setters methods
	public void setRentals(HashMap rental){ this.rental = rental; } 
	public void setItems(HashMap item){ this.item = item; }
	public void setCustomer(HashMap customers){ this.customers = customers; }
	//Getters methods
	public HashMap<Integer, Rental> rRentals(){ return rental ; }
	public HashMap<Integer, Item> rItems(){ return item; }
	public HashMap<Integer, Customer> rCustomers(){ return customers; }
}
