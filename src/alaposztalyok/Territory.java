package alaposztalyok;


public class Territory {
	private int territoryID, regionID;
	private String description;
	private alaposztalyok.Region region;

	public Territory(int territoryID, String description,int regionID, alaposztalyok.Region region) {
		this.territoryID = territoryID;	
		this.description = description;
		this.regionID = regionID;
		this.region =region;
	}
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
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
