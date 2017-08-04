package alaposztalyok;

import java.util.List;

public class Region {
	private int id;
	private String description;
	private List<Territory> territories;
	public Region(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}

	public List<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}
	
	
}
