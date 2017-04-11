package model;

public class DateReturnedBeforeDateRented extends Exception{
	public DateReturnedBeforeDateRented(){
		super("Date returned before date rented");
	}
	public DateReturnedBeforeDateRented(String message){
		super(message);
	}
}
