package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Adaptor;
import model.Book;
import model.Customer;
import model.Customer.Type;
import model.Device;
import model.DuplicateCustomerID;
import model.DuplicateItemID;
import model.DuplicateTransactionID;
import model.Item;
import model.Laptop;
import model.Library;
import model.Magazine;
import model.Rental;
import model.Textbook;
import model.WrongRentalCost;


public class mainController extends Main{
	@FXML
	ComboBox bookCombo;
	@FXML
	TextField bookID;
	@FXML
	TextField bookName;
	@FXML
	TextField bookAuthor;
	@FXML
	TextField bookPublisher;
	@FXML
	TextField bookYear;
	@FXML
	Button addBook;
	@FXML
	ComboBox deviceCombo;
	@FXML
	TextField deviceID;
	@FXML
	TextField deviceName;
	@FXML
	TextField deviceRental;
	@FXML
	Button deviceAdd;
	@FXML
	Label bookStatus;
	@FXML
	Label deviceStatus;
	@FXML
	ComboBox searchField;
	@FXML
	TextField searchValue;
	@FXML
	Button searchButton;
	@FXML
	TextArea searchText;
	@FXML
	TextField custID;
	@FXML
	TextField custName;
	@FXML
	TextField custDepartment;
	@FXML
	ComboBox custType;
	@FXML
	Button custAdd;
	@FXML
	Label custStatus;
	@FXML
	ComboBox itemList;
	@FXML
	ComboBox custList;
	@FXML
	TextField transactionRental;
	@FXML
	TextField transactionDue;
	@FXML
	TextField transactionReturn;
	@FXML
	TextField transactionID;
	@FXML
	Button transactionAdd;
	@FXML
	Label transactionStatus;
	@FXML
	ComboBox tSearchField;
	@FXML
	TextField tSearchValue;
	@FXML
	Button tSearchButton;
	@FXML
	TextArea tSearchText;
	@FXML
	Button getLate;
	@FXML
	TextArea lateArea;
	@FXML
	Button exitButton;
	
	
	private Main Main;
	HashMap rentals = new HashMap();
	Library library = new Library(rentals);
	
	public void initialize() throws NumberFormatException, FileNotFoundException, WrongRentalCost, ParseException {
	    bookCombo.getItems().removeAll(bookCombo.getItems());
	    bookCombo.getItems().addAll("Book", "Magazine", "Textbook");
	    bookCombo.getSelectionModel().select("Type");
	    deviceCombo.getItems().removeAll(deviceCombo.getItems());
	    deviceCombo.getItems().addAll("Device", "Adaptor", "Laptop");
	    deviceCombo.getSelectionModel().select("Type");
	    searchField.getItems().removeAll(searchField.getItems());
	    searchField.getItems().addAll("ID", "Name");
	    searchField.getSelectionModel().select("Field");
	    tSearchField.getItems().removeAll(searchField.getItems());
	    tSearchField.getItems().addAll("ID", "Item ID", "Customer ID");
	    tSearchField.getSelectionModel().select("Field");
	    custType.getItems().removeAll(custType.getItems());
	    custType.getItems().addAll("Student", "Employee");
	    custType.getSelectionModel().select("Type");
	    itemList.getSelectionModel().select("Available Items");
	    custList.getSelectionModel().select("Available Customers");
	    //Initialize values from file
	    library.setItems(library.readItems("items.csv"));
	    library.setCustomer(library.readCustomers("customers.csv"));
	    library.setRentals(library.readTransactions("trans.csv"));
	}
	@FXML
	private void addBook(ActionEvent event) throws DuplicateItemID {
		if(bookCombo.getValue().equals("Book")){
			Book book = new Book(bookName.getText(),bookAuthor.getText(),bookPublisher.getText(),Integer.parseInt(bookYear.getText()), Integer.parseInt(bookID.getText()));
			library.AddItem(book);
			bookStatus.setText("Added: " + book.toString());
			itemList.getItems().add(book);
		}
		if(bookCombo.getValue().equals("Magazine")){
			Magazine magazine = new Magazine(bookName.getText(),bookAuthor.getText(),bookPublisher.getText(),Integer.parseInt(bookYear.getText()), Integer.parseInt(bookID.getText()));
			library.AddItem(magazine);
			bookStatus.setText("Added: " + magazine.toString());
			itemList.getItems().add(magazine);
		}
		if(bookCombo.getValue().equals("Textbook")){
			Textbook textbook = new Textbook(bookName.getText(),bookAuthor.getText(),bookPublisher.getText(),Integer.parseInt(bookYear.getText()), Integer.parseInt(bookID.getText()));
			library.AddItem(textbook);
			bookStatus.setText("Added: " + textbook.toString());
			itemList.getItems().add(textbook);
		}	
	}
	@FXML
	private void deviceAdd(ActionEvent event) throws NumberFormatException, WrongRentalCost, DuplicateItemID {
		if(deviceCombo.getValue().equals("Device")){
			Device device = new Device(deviceName.getText(),Double.parseDouble(deviceRental.getText()),Integer.parseInt(deviceID.getText()));
			library.AddItem(device);
			deviceStatus.setText("Added: " + device.toString());
			itemList.getItems().add(device);
		}
		if(deviceCombo.getValue().equals("Adaptor")){
			Adaptor adaptor = new Adaptor(deviceName.getText(),Double.parseDouble(deviceRental.getText()),Integer.parseInt(deviceID.getText()));
			library.AddItem(adaptor);
			deviceStatus.setText("Added: " + adaptor.toString());
			itemList.getItems().add(adaptor);
		}
		if(deviceCombo.getValue().equals("Laptop")){
			Laptop laptop = new Laptop(deviceName.getText(),Double.parseDouble(deviceRental.getText()),Integer.parseInt(deviceID.getText()));
			library.AddItem(laptop);
			deviceStatus.setText("Added: " + laptop.toString());
			itemList.getItems().add(laptop);
		}
		
	}
	@FXML
	private void custAdd(ActionEvent event) throws DuplicateCustomerID{
		Customer customer = null;
		if(custType.getValue().equals("Student")){
			customer = new Customer(Type.Students, Integer.parseInt(custID.getText()), custName.getText(), custDepartment.getText());
		}
		if(custType.getValue().equals("Employee")){
			customer = new Customer(Type.Employees, Integer.parseInt(custID.getText()), custName.getText(), custDepartment.getText());
		}
		library.AddCustomer(customer);
		custStatus.setText("Added: " + customer.toString());
		custList.getItems().add(customer);
	}
	
	@FXML
	private void searchButton(ActionEvent event) throws ParseException {
		HashMap<Integer, Item>itemSearch = null;
		if(searchField.getValue().equals("ID")){itemSearch = library.searchItem("ID " + searchValue.getText());}
		if(searchField.getValue().equals("Name")){itemSearch = library.searchItem("NAME " + searchValue.getText());}
		for (Integer key: itemSearch.keySet()){
			searchText.appendText("Found: " + itemSearch.get(key).toString() + "\n");
		}
	}
	@FXML
	private void transactionAdd(ActionEvent event) throws DuplicateTransactionID, ParseException {
		SimpleDateFormat simpleForm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Rental rental = new Rental((Item)itemList.getValue(), (Customer)custList.getValue(), simpleForm.parse(transactionRental.getText()), simpleForm.parse(transactionDue.getText()), simpleForm.parse(transactionReturn.getText()), Integer.parseInt(transactionID.getText()));
		library.AddTransaction(rental);
		transactionStatus.setText("Added: " + rental.toString());
	}
	@FXML
	private void tSearchButton(ActionEvent event) throws ParseException {
		HashMap<Integer, Rental>rentalSearch = null;
		if(tSearchField.getValue().equals("ID")){rentalSearch = library.searchTransaction("TransactionID " + tSearchValue.getText());}
		if(tSearchField.getValue().equals("Item ID")){rentalSearch = library.searchTransaction("ItemID " + tSearchValue.getText());}
		if(tSearchField.getValue().equals("Customer ID")){rentalSearch = library.searchTransaction("CustomerID " + tSearchValue.getText());}
		for (Integer key: rentalSearch.keySet()){
			tSearchText.appendText("Found: " + rentalSearch.get(key).toString() + "\n");
		}
	}
	@FXML
	private void getLate(ActionEvent event) {
		for (Integer key: library.rRentals().keySet()){
			lateArea.appendText(library.rRentals().get(key).rCustomer().rName() + "'s Total Late Fee: $" + library.rRentals().get(key).getTotalToBePaid() + "\n");
		}
	}
	@FXML
	private void exitButton(ActionEvent event) throws IOException {
		library.writeCustomers(library.rCustomers());
		library.writeItems(library.rItems());
		library.writeTransactions(library.rRentals());
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
	public void setMain(Main main) {
        this.Main = main;
		
	}
}
