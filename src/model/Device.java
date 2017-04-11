package model;
public class Device extends Item {
	//Private attributes
	private double rentalCost;
	
	//getLateFees method
	@Override
	public double getLateFees(int days){
		double fees = (days * 2);
		return fees;
	}
	
	public double getRentFees(){
		double fees = (0.1*rRentalCost());
		return fees;
	}
	
	//ID constructor
	public Device(String name, double rentalCost, int id) throws WrongRentalCost{
		super(name, id);
		if (rentalCost < 0){ throw new WrongRentalCost(); }
		this.rentalCost = rentalCost;
	}
	//Copy constructor
	public Device(Device device){
		super(device);
		this.rentalCost = rentalCost;
	}
	//Constructor with all the attributes
	public Device(String name, double rentalCost) throws WrongRentalCost{
		super(name);
		if (rentalCost < 0){ throw new WrongRentalCost(); }
		this.rentalCost = rentalCost;
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
		Device other = (Device) obj;
		if (Double.doubleToLongBits(rentalCost) != Double.doubleToLongBits(other.rentalCost))
			return false;
		return true;
	}
	//Overwritten toString method
	@Override
	public String toString() {
		return "Device [name=" + rName() + ", id=" + rId() + ", rentalCost=" + rRentalCost() + "]";
	}
	//Overwritten Clone method
	@Override
	public Item Clone(){
		return new Device(this);
	}
	
	//Setters methods with appropriate checking
	public void setRentalCost(double rentalCost){
		if (rentalCost >= 0){ this.rentalCost = rentalCost; }
		else{ System.out.printf("Invalid Rental Cost"); }
	}
	//Getters methods
	public double rRentalCost(){ return rentalCost; }
}
