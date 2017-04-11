package model;
public class Magazine extends Book{
	//getLateFees method
	@Override
	public double getLateFees(int days){
		double fees = (days * 0.75);
		return fees;
	}
	
	//ID Constructor
	public Magazine(String name, String authors, String publisher, int year, int id){
		super(name, authors, publisher, year, id);
	}
	//Copy constructor
	public Magazine(Book book) {
		super(book);
	}
	//Constructor with all the attributes
	public Magazine(String name, String authors, String publisher, int year){
		super(name, authors, publisher, year);
	}
	
	//Overwritten Equals method
	@Override
	public boolean equals(Object obj){
        if (obj instanceof Magazine) {
            return super.equals(obj); 
        }
        return false;
	}
	//Overwritten toString method
	@Override
	public String toString() {
		return "Magazine [name=" + rName() + ", id=" + rId() + ", authors=" + rAuthors() + ", publisher=" + rPublisher() + ", year=" + rYear() + "]";
	}
	//Overwritten Clone method
	@Override
	public Item Clone(){
		return new Magazine(this);
	}
}
