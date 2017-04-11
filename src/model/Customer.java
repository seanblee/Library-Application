package model;

public class Customer {
	//Customer Attributes
	private Type type;
	public enum Type {
		Students,
		Employees
	}
	private int id;
	private String name;
	private String department;
	
	//Customer Constructor
	public Customer(Type type, int id, String name, String department){
		this.type = type;
		this.id = id;
		this.name = name;
		this.department = department;
	}
	//Copy Constructor
	public Customer(Customer customer){
		this.type = type;
		this.id = id;
		this.name = department;
		this.department = department;
	}
	
	//Equals method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	//ToString method
	public String toString() {
		return "Customer [type=" + type + ", id=" + id + ", name=" + name + ", department=" + department + "]";
	}
	//Clone method
	public Customer Clone(){
		return new Customer(this);
	}
	//Setters
	public void setType(Type type){
		this.type = type;
	}
	public void setID(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setDepartment(String department){
		this.department = department;
	}
	//Getters
	public Type rType(){
		return this.type;
	}
	public int rId(){
		return this.id;
	}
	public String rName(){
		return this.name;
	}
	public String rDepartment(){
		return this.department;
	}
}
