package alaposztalyok;

import javafx.scene.layout.Region;

public class Territory {
	private int territoryID, regionID;
	private String description;
	private Region region;
	public Territory(int territoryID, int regionID, String description) {
		this.territoryID = territoryID;
		this.regionID = regionID;
		this.description = description;
	}
	public int getTerritoryID() {
		return territoryID;
	}
	public int getRegionID() {
		return regionID;
	}
	public String getDescription() {
		return description;
	}
}
