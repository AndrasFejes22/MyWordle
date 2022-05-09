package myWordle;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Guess {
	
	private final List<String> letters = new ArrayList<>(5);

	private RandomWordGenerator randomNumberGenerator;
	private Letter letter;
	
	public Guess(RandomWordGenerator randomNumberGenerator) {
		super();
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public Guess(String guess) {
		String[] stringArray = guess.split("");
		for (String s : stringArray) {
			letters.add(s);
		}
	}
	
	
	
	public List<String> getLetters() {
		return letters;
	}
	
	public String getLetter(int index) {
		return letters.get(index);
		
	}
	
	@Override
	public String toString() {
		StringJoiner stringJoiner = new StringJoiner(" ");
		for (String d : letters) {
			stringJoiner.add(d.toString());
		}
		return stringJoiner.toString();
	}
	
	

	
	

}
