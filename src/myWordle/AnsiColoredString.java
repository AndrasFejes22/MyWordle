package myWordle;

public class AnsiColoredString {
	
	private final String text;

	
	public AnsiColoredString(String text) {
		this.text = text;
	}
	
	public AnsiColoredString(Integer number) { //ha a konstruktor számot kap, megoldja, és hívja a másik konstruktort
		this(number.toString());
	}
	
	public String inRed() {
		return "\u001b[1;31m" + text + "\u001b[0m";
	}
	
	public String inGreen() {
		return "\u001b[1;32m" + text + "\u001b[0m";
	}
	
	public String inYellow() {
		return "\u001b[1;33m" + text + "\u001b[0m";
	}
	
	public String inBlack() {
		return text;
	}
}
