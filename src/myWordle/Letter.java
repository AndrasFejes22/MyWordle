package myWordle;



public class Letter {
	
	private final String value;
	private  Color color;
	private final Coordinates coordinates;
	
	/**
	 * @param value
	 * @param coordinates
	 */
	public Letter (String value, Coordinates coordinates, Color color) {
		this.value = value;
		this.coordinates = coordinates;
		this.color = color;
	}
	
	public String getValue() {
		return value;
	}
	public Color getColor() {
		return color;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setColor(Color color) {
		this.color = color;
		
	}
	
	
	
	

}
