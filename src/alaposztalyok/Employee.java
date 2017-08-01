package alaposztalyok;

public class Employee {
	private String firstName, lastName, city;
	private int id;
	
	public Employee(String firstName, String lastName, String city, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public int getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCity(String city) {
		this.city = city;
	}


	
	

}
