package model;
public class Laptop extends Device {
	//getLateFees method
	@Override
	public double getLateFees(int days){
		double fees = (days * 5);
		return fees;
	}
	
	public double getRentFees(){
		double fees = (0.2*super.rRentalCost());
		return fees;
	}
	
	//ID Constructor
	public Laptop(String name, double rentalCost, int id) throws WrongRentalCost{
		super(name, rentalCost, id);
	}
	//Copy constructor
	public Laptop(Device device) {
		super(device);
	}
	//Constructor with all the attributes
	public Laptop(String name, double rentalCost) throws WrongRentalCost{
		super(name, rentalCost);
	}
	
	//Overwritten Equals method
	@Override
	public boolean equals(Object obj){
        if (obj instanceof Laptop) {
            return super.equals(obj); 
        }
        return false;
	}
	//Overwritten toString method
	@Override
	public String toString() {
		return "Laptop [name=" + rName() + ", id=" + rId() + ", rentalCost=" + rRentalCost() + "]";
	}
	//Overwritten Clone method
	@Override
	public Item Clone(){
		return new Laptop(this);
	}
}
