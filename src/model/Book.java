package model;
public class Book extends Item {
	//Private attributes
	private String authors;
	private String publisher;
	private int year;
	
	//getLateFees method
	@Override
	public double getLateFees(int days){
		double fees = (days * 0.5);
		return fees;
	}
	
	//ID constructor
	public Book(String name, String authors, String publisher, int year, int id){
		super(name, id);
		this. authors = authors;
		this.publisher = publisher;
		this.year = year;
	}
	//Copy constructor
	public Book(Book book){
		super(book);
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}
	//Constructor with all the attributes
	public Book(String name, String authors, String publisher, int year){
		super(name);
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}
	
	//Overwritten Equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	//Overwritten toString method
	@Override
	public String toString() {
		return "Book [name=" + rName() + ", id=" + rId() + ", authors=" + rAuthors() + ", publisher=" + rPublisher() + ", year=" + rYear() + "]";
	}
	//Overwritten Clone method
	@Override
	public Item Clone(){
		return new Book(this);
	}
	
	//Setters methods with appropriate checking
	public void setAuthors(String authors){
		if (!authors.isEmpty()){ this.authors = authors; }
		else { System.out.printf("No Authors Entered"); }
	}
	public void setPublisher(String publisher){
		if (!publisher.isEmpty()){ this.publisher = publisher; }
		else { System.out.printf("No Publisher Entered"); }
	}
	public void setYear(int year){
		if  (year >= 0) { this.year = year; }
		else { System.out.printf("Invalid Year"); }
	}
	//Getters methods
	public String rAuthors(){ return authors; }
	public String rPublisher(){ return publisher; }
	public int rYear(){ return year; }
}
