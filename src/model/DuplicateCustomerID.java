package model;

public class DuplicateCustomerID extends Exception {
	public DuplicateCustomerID(){
		super("Duplicate Customer ID");
	}
	public DuplicateCustomerID(String message){
		super(message);
	}
}
