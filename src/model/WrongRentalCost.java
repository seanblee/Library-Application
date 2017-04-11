package model;

public class WrongRentalCost extends Exception{
	public WrongRentalCost(){
		super("Wrong Rental Cost");
	}
	public WrongRentalCost(String message){
		super(message);
	}
}
