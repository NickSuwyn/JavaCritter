package suppliedCode;

public enum Move {
	
	CLOCKWISE 				("clockwise"), 
	COUNTERCLOCKWISE	("couterClockwise"), 
	STAY							("move"), 
	MOVE							("");
	
	private final String value;
	
	Move(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
