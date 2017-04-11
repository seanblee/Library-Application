package model;
public class Textbook extends Book {
	//getLateFees method
	@Override
	public double getLateFees(int days){
		double fees = days;
		return fees;
	}
	
	//ID Constructor
	public Textbook(String name, String authors, String publisher, int year, int id){
		super(name, authors, publisher, year, id);
	}
	//Copy constructor
	public Textbook(Book book) {
		super(book);
	}
	//Constructor with all the attributes
	public Textbook(String name, String authors, String publisher, int year){
		super(name, authors, publisher, year);
	}
	
	//Overwritten Equals method
	@Override
	public boolean equals(Object obj){
        if (obj instanceof Textbook) {
            return super.equals(obj); 
        }
        return false;
	}
	//Overwritten toString method
	@Override
	public String toString() {
		return "Tetbook [name=" + rName() + ", id=" + rId() + ", authors=" + rAuthors() + ", publisher=" + rPublisher() + ", year=" + rYear() + "]";
	}
	//Overwritten Clone method
	@Override
	public Item Clone(){
		return new Textbook(this);
	}
}
