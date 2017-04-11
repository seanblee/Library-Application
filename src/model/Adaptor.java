package model;
public class Adaptor extends Device{
	//getLateFees method
	@Override
	public double getLateFees(int days){
		double fees = (days * 2.5);
		return fees;
	}
	
	public double getRentFees(){
		double fees = (0.15*super.rRentalCost());
		return fees;
	}

	//ID Constructor
	public Adaptor(String name, double rentalCost, int id) throws WrongRentalCost{
		super(name, rentalCost, id);
	}
	//Copy constructor
	public Adaptor(Device device) {
		super(device);
	}
	//Constructor with all the attributes
	public Adaptor(String name, double rentalCost) throws WrongRentalCost{
		super(name, rentalCost);
	}
	
	//Overwritten Equals method
	@Override
	public boolean equals(Object obj){
        if (obj instanceof Adaptor) {
            return super.equals(obj); 
        }
        return false;
	}
	//Overwritten toString method
	@Override
	public String toString() {
		return "Adaptor [name=" + rName() + ", id=" + rId() + ", rentalCost=" + rRentalCost() + "]";
	}
	//Overwritten Clone method
	@Override
	public Item Clone(){
		return new Adaptor(this);
	}
}
