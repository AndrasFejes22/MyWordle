package myWordle;

import java.util.HashMap;
import java.util.Map;

public class KeyPad {
	
	//Minta:
	
	// a b c d e f
	// g h i j k l
	// m n o p q r
	// s t u v w x
	// y z
	
	

	private final Map<String, Letter> keyPadLetters = new HashMap<>();
	
	public KeyPad() {
		
		keyPadLetters.put("a", new Letter("a", new Coordinates(0,0), Color.BLACK));
		keyPadLetters.put("b", new Letter("b", new Coordinates(0,2), Color.BLACK));
		keyPadLetters.put("c", new Letter("c", new Coordinates(0,4), Color.BLACK));
		keyPadLetters.put("d", new Letter("d", new Coordinates(0,6), Color.BLACK));
		keyPadLetters.put("e", new Letter("e", new Coordinates(0,8), Color.BLACK));
		keyPadLetters.put("f", new Letter("f", new Coordinates(0,10), Color.BLACK));
		
		keyPadLetters.put("g", new Letter("g", new Coordinates(1,0), Color.BLACK));
		keyPadLetters.put("h", new Letter("h", new Coordinates(1,2), Color.BLACK));
		keyPadLetters.put("i", new Letter("i", new Coordinates(1,4), Color.BLACK));
		keyPadLetters.put("j", new Letter("j", new Coordinates(1,6), Color.BLACK));
		keyPadLetters.put("k", new Letter("k", new Coordinates(1,8), Color.BLACK));
		keyPadLetters.put("l", new Letter("l", new Coordinates(1,10), Color.BLACK));
		
		keyPadLetters.put("m", new Letter("m", new Coordinates(2,0), Color.BLACK));
		keyPadLetters.put("n", new Letter("n", new Coordinates(2,2), Color.BLACK));
		keyPadLetters.put("o", new Letter("o", new Coordinates(2,4), Color.BLACK));
		keyPadLetters.put("p", new Letter("p", new Coordinates(2,6), Color.BLACK));
		keyPadLetters.put("q", new Letter("q", new Coordinates(2,8), Color.BLACK));
		keyPadLetters.put("r", new Letter("r", new Coordinates(2,10), Color.BLACK));
		
		keyPadLetters.put("s", new Letter("s", new Coordinates(3,0), Color.BLACK));
		keyPadLetters.put("t", new Letter("t", new Coordinates(3,2), Color.BLACK));
		keyPadLetters.put("u", new Letter("u", new Coordinates(3,4), Color.BLACK));
		keyPadLetters.put("v", new Letter("v", new Coordinates(3,6), Color.BLACK));
		keyPadLetters.put("w", new Letter("w", new Coordinates(3,8), Color.BLACK));
		keyPadLetters.put("x", new Letter("x", new Coordinates(3,10), Color.BLACK));
	
		keyPadLetters.put("y", new Letter("y", new Coordinates(4,0), Color.BLACK));
		keyPadLetters.put("z", new Letter("z", new Coordinates(4,2), Color.BLACK));
		
	}
	
	public void setColorOfLetter (String letter, Color color) {
		keyPadLetters.put(letter, new Letter(keyPadLetters.get(letter).getValue(), keyPadLetters.get(letter).getCoordinates(), color));
	}
	
	public Color getColorOfLetter (String letter) {
		return keyPadLetters.get(letter).getColor();
	}
	
	 

	@Override
	public String toString() {
		int maxRowIndex = getMaximumRowIndex();
		int maxColumnIndex = getMaximumColumnIndex();
		int numberOfRows = maxRowIndex + 1;
		int numberOfColumns = maxColumnIndex + 1;
		String[][] printBuffer = new String[numberOfRows][numberOfColumns];
		fillBufferWithBlank(printBuffer);
		fillBufferWithDigits(printBuffer);
		StringBuilder builder = new StringBuilder();
		for(int row = 0; row < numberOfRows; row++) {
			for(int column = 0; column < numberOfColumns; column++) {
				builder.append(printBuffer[row][column]);
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}

	private int getMaximumRowIndex() {
		int max = 0;
		for(Letter digit : keyPadLetters.values()) {
			int currentDigitsRow = digit.getCoordinates().getRow();
			if(currentDigitsRow > max) {
				max = currentDigitsRow;
			}
		}
		return max;
	}
	
	private int getMaximumColumnIndex() {
		int max = 0;
		for(Letter digit : keyPadLetters.values()) {
			int currentDigitsColumn = digit.getCoordinates().getColumn();
			if(currentDigitsColumn > max) {
				max = currentDigitsColumn;
			}
		}
		return max;
	}

	private void fillBufferWithBlank(String[][] printBuffer) {
		for (int row = 0; row < printBuffer.length; row++) {
			for (int column = 0; column < printBuffer[row].length; column++) {
				printBuffer[row][column] = " ";
			}

		}

	}

	private void fillBufferWithDigits(String[][] printBuffer) {
		for(Letter digit : keyPadLetters.values()) {
			Coordinates coordinates = digit.getCoordinates();
			String value = switch (digit.getColor()) {
			case GREEN  -> new AnsiColoredString(digit.getValue()).inGreen();
			case YELLOW  -> new AnsiColoredString(digit.getValue()).inYellow();
			case RED  -> new AnsiColoredString(digit.getValue()).inRed();
			default  -> digit.getValue();
			};
			printBuffer[coordinates.getRow()][coordinates.getColumn()] = value;
		}
		
	}

	
	
	
	
	

}
