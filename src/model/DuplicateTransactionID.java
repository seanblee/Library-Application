package model;

public class DuplicateTransactionID extends Exception {
	public DuplicateTransactionID(){
		super("Duplicate Transaction ID");
	}
	public DuplicateTransactionID(String message){
		super(message);
	}
}
