package model;

public class CustomerFileReadingException extends Exception{
	public CustomerFileReadingException(){
		super("Error reading Customer File");
	}
	public CustomerFileReadingException(String message){
		super(message);
	}
}
