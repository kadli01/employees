package alaposztalyok;

public class Region {
	private int id;
	private String description;
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
}
