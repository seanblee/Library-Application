package model;

import java.util.Date;
import model.Customer.Type;

public class Rental{
	//Private attributes
	private Item item;
	private Customer customer;
	//Date/Times
	private Date rentalDate;
	private Date estDate;
	private Date returnedDate;
	//Status
	private Status status;
	public enum Status {
		Active,
		Late,
		Closed
	}
	//Incrementing Transactions
	private static int transactionId;
	private int tId = 0;
	
	//isLate method
	public boolean isLate(Date date){
		if(date.after(estDate)){
			this.status = Status.Late;
			return true;
		}
		return false;
	}
	//itemReturned method
	public void itemReturned(Date date) throws DateReturnedBeforeDateRented{
		if(date.before(rRentalDate())){ throw new DateReturnedBeforeDateRented(); }
		this.status = Status.Closed;
		this.returnedDate = date;
	}
	//getLateFee
	public double getLateFees(){
		//get time in between in milliseconds, turn into days, round to integer and pass
		if (isLate(returnedDate)){
		double total = this.item.getLateFees((int)((returnedDate.getTime() - estDate.getTime()) / 86400000));
		return total;
		}
		else{ return 0; }
	}
	//getRentalCost
	public double getRentCosts(){
		double total;
		if (isLate(returnedDate) || !(rItem() instanceof Device)){
		if(customer.rType() == Type.Students){
			total = (((Device)this.item).getRentFees() * 0.75);
		}
		else { total = ((Device)this.item).getRentFees(); }
		return total;
		} else{ return 0; }
	}
	//getTotalToBePaid
	public double getTotalToBePaid(){
		double total = getLateFees() + getRentCosts();
		return total;
	}
	//Constructor with literally everything
	public Rental(Item item, Customer customer, Date rentalDate, Date estDate, Date retDate, int id){
		this.item = item;
		this.customer = customer;
		this.tId = id;
		this.rentalDate = rentalDate;
		this.estDate = estDate;
		this.returnedDate = retDate; 
		if (retDate == null){this.status = Status.Active;} //Inactive if constructed with no returnDate
		else{ this.status = Status.Closed; }
	}
	//Constructor with all the attributes + ID
	public Rental(Item item, Customer customer, Date rentalDate, Date estDate, int id){
		this.item = item;
		this.customer = customer;
		this.tId = id;
		this.rentalDate = rentalDate;
		this.estDate = estDate;
		this.returnedDate = null; //Assumed null since new transactions wont have been returned
		this.status = Status.Active; //Assumed active since new transactions are active
	}
	//Constructor with all the attributes
	public Rental(Item item, Customer customer, Date rentalDate, Date estDate){
		this.item = item;
		this.customer = customer;
		this.tId = transactionId;
		transactionId = transactionId + 1;
		this.rentalDate = rentalDate;
		this.estDate = estDate;
		this.returnedDate = null; //Assumed null since new transactions wont have been returned
		this.status = Status.Active; //Assumed active since new transactions are active
	}
	//Copy constructor
	public Rental(Rental rental){
		this.item = item;
		this.customer = customer;
		this.tId = tId;
		this.status = status;
		this.rentalDate = rentalDate;
		this.estDate = estDate;
		this.returnedDate = returnedDate;
	}
	
	//Equals method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (estDate == null) {
			if (other.estDate != null)
				return false;
		} else if (!estDate.equals(other.estDate))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (rentalDate == null) {
			if (other.rentalDate != null)
				return false;
		} else if (!rentalDate.equals(other.rentalDate))
			return false;
		if (returnedDate == null) {
			if (other.returnedDate != null)
				return false;
		} else if (!returnedDate.equals(other.returnedDate))
			return false;
		if (status != other.status)
			return false;
		if (tId != other.tId)
			return false;
		return true;
	}
	//ToString method
	public String toString() {
		return "Rental [item=" + item + ", customer=" + customer + ", rentalDate=" + rentalDate + ", estDate=" + estDate
				+ ", returnedDate=" + returnedDate + ", status=" + status + ", tId=" + tId + "]";
	}
	//Clone method
	public Rental Clone(){
		return new Rental(this);
	}
	//Setters methods
	public void setItem(Item item){ this.item = item; }
	public void setCustomer(Customer customer){ this.customer = customer; }
	public void setRentalDate(Date date){ this.rentalDate = date; }
	public void setEstDate(Date date){ this.estDate = date; }
	public void setReturnedDate(Date date){ this.returnedDate = date; }
	public void setStatus(Status status){ this.status = status; }
	public void setTId(int id){ this.tId = id; }
	//Getters methods
	public Item rItem(){ return item; }
	public Customer rCustomer(){ return customer; }
	public Date rRentalDate(){ return rentalDate; }
	public Date rEstDate(){ return estDate; }
	public Date rReturnedDate(){ return returnedDate; }
	public Status rStatus(){ return status; }
	public int rTId(){ return tId; }
}
