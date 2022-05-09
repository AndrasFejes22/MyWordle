package myWordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		
		FileReader fr = new FileReader();
		
		
		wordsInABCOrder("words/five_letter_words.txt");
		
		isIntheDictionary("words/five_letter_words.txt", "dates"); //check
		
		try (Scanner scanner = new Scanner(System.in)) {
			
			new Main().run(scanner);
		}
	}

	

	private void run(Scanner scanner) {
		System.out.println("Welcome in MyWordle Application 1.0! Developer John Doe");
		
		String rules = "Wordle is simple: "+ "\n"
				+""+ "\n"
				+ "You have six chances to guess the"+ "\n"
				+ "secret five-letter word." + "\n"
				+ "Type in a word as a guess, and the game "+ "\n"
				+ "tells you which letters are "+ "\n"
				+ "or aren�t in the word." + "\n"+ "\n"
				+ "When you make a guess in the game, "+ "\n"
				+ "the letter tiles change colors "+ "\n"
				+ "to show how close you are to the secret word." + "\n"
				+""+ "\n"
				+ "When you guess, and the letter turns green, "+ "\n"
				+ "the letter is in the word and in that spot. "+ "\n"
				+ "When you guess and the letter turns yellow, "+ "\n"
				+ "the letter is in the word but not in that spot. "+ "\n";
				
		System.out.println(rules);
		FileReader fileReader = new FileReader();
		RandomWordGenerator randomNumberGenerator = new RandomWordGenerator(fileReader);
		
		String guess = randomNumberGenerator.generate("words/five_letter_words.txt");
		Guess solution = new Guess(guess); //ez a Filereaderb�l kell egy egy random sz�
		//System.out.println("solution is: "+solution); //ez csak ellen�rz�s
		KeyPad keyPad = new KeyPad();
		GameState gameState = new GameState(solution, keyPad);
		//ez a blokk csak a keyPad beszinez�s ellen�rz�se
		//keyPad.setColorOfDigit(0, Color.YELLOW);
		//keyPad.setColorOfDigit(4, Color.GREEN);
		//keyPad.setColorOfDigit(8, Color.RED);
		//System.out.println(keyPad);
		//tipp bek�r�se: ("Please give me your tip: ")
		UserImputHandler in = new UserImputHandler(scanner);
		//System.out.println("solution is: "+solution); //ez csak ellen�rz�s
		//bek�rj�k, hogy milyen neh�zs�gi fokon szeretn�nk j�tszani:
		int difficult = in.readDifficultyFromUser(); //"Please select a difficulty level!"
	
		switch(difficult) { //switch expression a ->ak kiz�r� eseteket �rnak le, nem kell break;
		case 1 -> removeRandomWrongLetters(4, solution, keyPad); //(mennyi rossz megold�s eleve ne legyen ott)
		case 2 -> removeRandomWrongLetters(2, solution, keyPad);
		case 3 -> removeRandomWrongLetters(0, solution, keyPad);
		}
		//System.out.println("elso keypad: "+ "\n"+keyPad);
		String wordsOnlyFromDictionaryOrNot = in.kindOfGame();
		
		switch(wordsOnlyFromDictionaryOrNot) {
		case "y": 
			System.out.println("Chosen game type: guess words only from dictionary.");
			for (int round = 1; round <= 5 && !gameState.isWon(); round++) {
				System.out.println();
				System.out.println(gameState);
				System.out.println(keyPad);
				System.out.println(round + ". round:");
				//System.out.println(gameState);
				//System.out.println(keyPad);
				String searchedWord;
				do {
					Guess guessWord = in.readWordFromUser(); //("Please give me your tip: ");
					searchedWord = guessWord.toString().replaceAll(" ", "");
					//System.out.println("searchedWord: "+searchedWord); //check
					
					if (isIntheDictionary("words/five_letter_words.txt", searchedWord)) {
						gameState.update(guessWord);
					} else {
						
						System.out.println(new AnsiColoredString("The word is not in the dictionary!").inRed());
					}
				} while (!isIntheDictionary("words/five_letter_words.txt", searchedWord));
			}
			break;
			
		case "n":
			System.out.println("Chosen game type: guess words are five-letter strings."); //for testing
			for (int round = 1; round <= 5 && !gameState.isWon(); round++) {
				System.out.println();
				System.out.println(gameState);
				System.out.println(keyPad);
				System.out.println(round + ". round:");
				Guess word = in.readWordFromUser();   
				gameState.update(word); 
			}
			break;
		}
		/*
		for (int round = 1; round <= 5 && !gameState.isWon(); round++) {
			System.out.println();
			System.out.println(gameState);
			System.out.println(keyPad);
			System.out.println(round + ". round:");
			
			
			/////////do-while, kell/////////
			
			//do-while eleje; ez a do-while csin�lja az ellen�rz�st, hogy csak sz�t�ri szavakkal lehessen pr�b�lkozni
			
			//Guess word; //komment ha teszt     
				
			
			
			
				String searchedWord;
				do {
					Guess guessWord = in.readWordFromUser(); //("Please give me your tip: ");
					searchedWord = guessWord.toString().replaceAll(" ", "");
					//System.out.println("searchedWord: "+searchedWord); //check
					
					if (isIntheDictionary("words/five_letter_words.txt", searchedWord)) {
						gameState.update(guessWord);
					} else {
						
						System.out.println(new AnsiColoredString("The word is not in the dictionary!").inRed());
					}
				} while (!isIntheDictionary("words/five_letter_words.txt", searchedWord));
				
				
			
				
			
			
			
			
			
			//do-while v�ge
			
			
			/////////do-while, kell/////////
			
			
			
		}//for v�ge
		*/
		System.out.println(gameState);
		System.out.println(keyPad);
		
		if(gameState.isWon()) {
			System.out.println("Congratulations, you win! :-)");
		} else {
			System.out.println("Sorry, you loose. :-(");
			System.out.println("The solution was: "+solution);
		}
		

		
	}
	
	

	private void removeRandomWrongLetters(int numberOfLettersToRemove, Guess solution, KeyPad keyPad) {
		String solution2 = solution.toString().replaceAll(" ", "");// stringet csin�lunk a solutionb�l, (mert a sol az
																	// Guess), hogy a hossz�t elk�rhess�k
		int solutionSize = solution2.length();// **
		System.out.println("megold�s m�rete: " + solutionSize);
		List<String> letters = solution.getLetters(); // lek�rj�k a megold�st list�ba (solution method)

		System.out.println("letters in the Set: " + letters); //check
		Set<String> randoms = new HashSet<>();// lek�rj�k a megold�st
		randoms.addAll(letters);// belerakjuk egy setbe
		System.out.println("set: " + randoms);
		
		int setSize = randoms.size(); //the size of the set may vary depending on whether there are duplicate letters in it

		while (randoms.size() < (numberOfLettersToRemove + setSize)) {
			String randomLetter = RandomWordGenerator.getRandomLetter();
			System.out.println("randomLetters: " + randomLetter); //check
			if (randoms.add(randomLetter)) {// if the letter can be put in the set, it is not in the solution --> can be colored red							
				// it can now be exploited that add () returns the boolean
				keyPad.setColorOfLetter(randomLetter, Color.RED);
				System.out.println("set n�vekv� m�rete: " + randoms.size());
			}
		}
		//System.out.println("set2: " + randoms);
		
	}
	
	public static boolean isIntheDictionary(String fileName, String word) {
		List<String> words = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File(fileName))) { // "UTF-8" : hungarian
			// List<String> words = new ArrayList<>();
			while (scanner.hasNext()) {
				String wordsInDictionary = scanner.nextLine();
				words.add(wordsInDictionary); // az �sszes sz�t belerakjuk a list�ba
			}

		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem tal�lja a megadott f�jlt");
		}
		if (words.contains(word)) {
			//System.out.println("A(z) '" + word + "' sz� szerepel a sz�t�rban.");
			return true;
		} else {
			//System.out.println("A(z) '" + word + "' sz� nem szerepel a sz�t�rban.");
			return false;
		}
	
	}
	
	public static Map<Integer, String> wordsInABCOrder(String fileName) {
		
		int counter = 0;
		Map<Integer, String> solutions = new HashMap<>();
		try (Scanner scanner = new Scanner(new File(fileName))) { // "UTF-8" : hungarian
			// List<String> words = new ArrayList<>();
			while (scanner.hasNext()) {
				String word = scanner.nextLine();
				//System.out.println("word: " + word);
				solutions.put(counter, word);// az �sszes sz�t belerakjuk a map-ba is
				counter++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem tal�lja a megadott f�jlt");
		}
		System.out.println("solutions size: " + solutions.size());
		Map<Integer, String> sortedMap2 = new HashMap<>();
		solutions.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
				.forEachOrdered(x -> sortedMap2.put(x.getKey(), x.getValue()));

		sortedMap2.entrySet().forEach(entry->{
			//System.out.println(entry.getKey() + " -> " + entry.getValue());  
		}); 
		
		return sortedMap2;

	}
	
}
 