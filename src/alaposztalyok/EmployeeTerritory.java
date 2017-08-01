package alaposztalyok;

public class EmployeeTerritory {
	private int employeeID;
	private int territoryID;
	
	public EmployeeTerritory(int employeeID, int territoryID) {
		this.employeeID = employeeID;
		this.territoryID = territoryID;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	public int getTerritoryID() {
		return territoryID;
	}

}
