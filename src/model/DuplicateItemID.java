package model;

public class DuplicateItemID extends Exception{
	public DuplicateItemID(){
		super("Duplicate Item ID");
	}
	public DuplicateItemID(String message){
		super(message);
	}
}
