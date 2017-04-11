package model;

public class ItemFileReadingException extends Exception {
	public ItemFileReadingException(){
		super("Error reading Item File");
	}
	public ItemFileReadingException(String message){
		super(message);
	}
}
