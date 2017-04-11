package model;
public abstract class Item {
	//Private attributes
	private static int itemId = 0;
	private int id;
	private String name;
	
	//Unimplemented getLateFees
	public abstract double getLateFees(int days);
	
	//ID constructor
	public Item(String name, int id){
		this.id = id;
		this.name = name;
	}
	//Copy constructor
	public Item(Item item){
		this.id = id;
		this.name = name;
	}
	//Construtor with all the attributes
	public Item(String name){
		itemId = itemId + 1;
		this.id = itemId;
		this.name = name;
	}
	
	//Equals method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	//toString method
	public String toString() {
		return "Item [name=" + rName() + ", id=" + rId() + "]";
	}
	//Clone method
	public abstract Item Clone();

	//Setters methods with appropriate checking
	public void setName(String name){
		if(!name.isEmpty()){ this.name = name; }
		else { System.out.printf("No Name Entered"); }
	}
	//Getters methods
	public int rId(){ return id ; }
	public String rName(){ return name; }
}
