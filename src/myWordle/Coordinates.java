package myWordle;

public class Coordinates {
	
	private final int row;
	private final int column;
	/**
	 * @param row
	 * @param column
	 */
	public Coordinates(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	@Override
	public String toString() {
		return "Coordinates [row=" + row + ", column=" + column + "]";
	}
	
	

}
