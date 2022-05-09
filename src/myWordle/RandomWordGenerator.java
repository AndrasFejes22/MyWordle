package myWordle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomWordGenerator {
	
	private FileReader filereader;
	
	public RandomWordGenerator(FileReader filereader) {
		super();
		this.filereader = filereader;
	}

	public String generate(String filename) {
		//FileReader filereader = new FileReader();
		Map<Integer, String> wordsInAMap = filereader.wordsInAMap(filename);
		int number = new Random().nextInt(wordsInAMap.size());
		return wordsInAMap.get(number); //kap egy map-et és visszaad egy szót
	}
	
	public static String getRandomLetter() {
		Random rand = new Random();
		int randomNum = rand.nextInt((25 - 1) + 1) + 1;
		//System.out.println("a random számok: " + randomNum);
		Map<Integer, String> mapOfLetters = new HashMap<>();
		mapOfLetters.put(1, "a");
		mapOfLetters.put(2, "b");
		mapOfLetters.put(3, "c");
		mapOfLetters.put(4, "d");
		mapOfLetters.put(5, "e");
		mapOfLetters.put(6, "f");
		mapOfLetters.put(7, "g");
		mapOfLetters.put(8, "h");
		mapOfLetters.put(9, "i");
		mapOfLetters.put(10, "j");
		mapOfLetters.put(11, "k");
		mapOfLetters.put(12, "l");
		mapOfLetters.put(13, "m");
		mapOfLetters.put(14, "n");
		mapOfLetters.put(15, "o");
		mapOfLetters.put(16, "p");
		mapOfLetters.put(17, "q");
		mapOfLetters.put(18, "r");
		mapOfLetters.put(19, "s");
		mapOfLetters.put(20, "t");
		mapOfLetters.put(21, "u");
		mapOfLetters.put(22, "v");
		mapOfLetters.put(23, "w");
		mapOfLetters.put(24, "x");
		mapOfLetters.put(25, "y");
		mapOfLetters.put(26, "z");
		
		return mapOfLetters.get(randomNum);
		
		
		 
	}
	

}
