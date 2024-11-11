package boardgame.enums;

public enum Status {
	FREE(0),
	MARKED(1),
	PROPERTY(2);
	
	private int id;
	
	private Status(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
