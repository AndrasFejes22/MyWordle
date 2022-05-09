package myWordle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameState {
	
	private final Guess solution;
	private final KeyPad keyPad;
	
	private final List<String> state = new ArrayList<>();
	private final List<String> operators = new ArrayList<>();
	//private final List<String> correctValueInCorrectLocation = new ArrayList<>(); //z�ld
	private List<String> correctValueInCorrectLocation = new ArrayList<>(Arrays.asList("_", "_", "_", "_", "_"));
	private List<String> correctValueInCorrectLocationBlack = new ArrayList<>(Arrays.asList("_", "_", "_", "_", "_"));
	//private final List<Integer> correctValueInIncorrectLocation = new ArrayList<>(); //s�rga
	private final List<Integer> incorrectValue = new ArrayList<>(); //piros
	private StringBuilder builder = new StringBuilder();
	private int counter = 0;
	

	
	public GameState(Guess solution, KeyPad keyPad) {
		super();
		this.solution = solution;
		this.keyPad = keyPad;
	}

	
	
	public void update(Guess guess) {//azokat a bet�ket amit eltal�lt a j�t�kos fel kell fedni, �s be kell sz�nezni Z�LDRE
		counter = 0; //minden k�r elej�n kinull�czzuk, hogy**
		String solution2 = solution.toString().replaceAll(" ", "");
		String guess2 = guess.toString().replaceAll(" ", "");
		String joinedString;
		
		for (int i = 0; i < 5; i++) {
			if (solution2.charAt(i) == guess2.charAt(i)) {
				//System.out.println("i: " + i);
				//System.out.println("match3 van egyez�s, azonos helyen egy for loop");
				//System.out.println("bet�: "+guess2.charAt(i));
				//String s = new AnsiColoredString(solution.getLetter(i)).inGreen();
				correctValueInCorrectLocation.set(i, solution.getLetter(i));//itt csak belerak
				correctValueInCorrectLocationBlack.set(i, solution.getLetter(i));//itt csak belerak
				//System.out.println("list joined el�tt: "+correctValueInCorrectLocation);
				//System.out.println("letterStatistics_solution: "+letterStatistics(solution2, String.valueOf(solution2.charAt(i))));
				//System.out.println("solution_bet�: "+String.valueOf(solution2.charAt(i)));
				//String b = new AnsiColoredString(solution.getLetter(i)).inBlack();
				//correctValueInCorrectLocation.set(i, b);
				
				joinedString = listToString(correctValueInCorrectLocationBlack);
				//System.out.println("joined: "+joinedString);
				//System.out.println("joined_bet�: "+String.valueOf(joinedString.charAt(i)));
				//System.out.println("letterStatistics_list: "+letterStatistics(joinedString, String.valueOf(joinedString.charAt(i))));
				if (letterStatistics(solution2, String.valueOf(solution2.charAt(i))) == letterStatistics(joinedString,
						String.valueOf(joinedString.charAt(i)))) {
					keyPad.setColorOfLetter(solution.getLetter(i), Color.GREEN);
				} else {
					keyPad.setColorOfLetter(solution.getLetter(i), Color.YELLOW);
				}
				String s = new AnsiColoredString(solution.getLetter(i)).inGreen();
				correctValueInCorrectLocation.set(i, s);//itt kiszinez
			} 
		}
		//nem ism�tl�d� bet� �s rossz helyen --> S�RGA
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (solution2.charAt(i) == guess2.charAt(j) && keyPad.getColorOfLetter(solution.getLetter(i))!= Color.GREEN) {
					keyPad.setColorOfLetter(solution.getLetter(i), Color.YELLOW);
				}
			}
		}
	
		
		for (String string : correctValueInCorrectLocation) {
			if(!string.equals("_")) {
				counter++ ;
			}
		}
		
		for(int i = 0; i < 5; i++) {//RED-re
			boolean contains = false;
			for(int j = 0; j < 5; j++) {
				if(guess.getLetter(i).equals(solution.getLetter(j))) {
					
					contains = true;
				}
			}
			if(!contains) {
				//incorrectValue.add(guess.getDigit(i));
				keyPad.setColorOfLetter(guess.getLetter(i), Color.RED);
			}
		}
		
	}

	@Override
	public String toString() {
		StringBuilder builder1 = new StringBuilder();
		for(int i = 0; i < correctValueInCorrectLocation.size(); i++) { //**
			//if(keyPad.getColorOfLetter(solution.getLetter(i)) == Color.GREEN) {//
			//if(solution.getLetter(i).getColor() == Color.GREEN) {//
			if(correctValueInCorrectLocation.size() > 0) {
				//System.out.println("correctValueInCorrectLocation.size() : " + correctValueInCorrectLocation.size() );
				//System.out.println("toString_zold_i: " + i);
				//for(int j = 0; j < correctValueInCorrectLocation.size(); j++) {
				builder1.append(correctValueInCorrectLocation.get(i)); //new AnsiColoredString(solution.getLetter(i)).inGreen()
				//System.out.println("itt1");
				//}
			} else {
				builder1.append("_");
				//System.out.println("itt2"); //**emiatt nem �r ki semmit, mert a for ciklus 0-�n �ll
			}
			/*
			if(i < operators.size()) {
				//System.out.println("itt3"); //**emiatt nem �r ki semmit, mert a for ciklus 0-�n �ll
				builder1.append(" ");
				builder1.append(operators.get(i));
				builder1.append(" ");
			}
			*/
		}
		
		return builder1.toString();
	}

	public boolean isWon() { //5 z�ld bet� van a correctValueInCorrectLocation List-ben
		if(counter == 5) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static int letterStatistics(String text, String letter) {
		Map<String, Integer> lettersStatistics = new LinkedHashMap<>(); //eredeti sorrend
		char [] letters = text.toCharArray();
		
		for (char i : letters) {
			String letterAsString = Character.toString(i);
			
			if(lettersStatistics.containsKey(letterAsString)) {//m�r szerepelt
				Integer count = lettersStatistics.get(letterAsString);//megk�rdezz�k h�nyszor //L�NYEG
				count++; //azt megn�velj�k 1-el
				lettersStatistics.put(letterAsString, count); //�s �jra elt�roljuk, fel�l�rva a p�rban az �rt�ket //L�NYEG
			} else {
				lettersStatistics.put(letterAsString, 1); //3. (1 -> volt egy autoboxing (JAVA 5-t�l))
			}
		}
		//System.out.println(lettersStatistics.get(letter));
		return lettersStatistics.get(letter);
	}
	
	public static String listToString(List<String> list) {
		StringBuilder builder = new StringBuilder(); 
		for (int i = 0; i < list.size(); i++) {
			builder.append(list.get(i));
		}
		return builder.toString();
	}
	
	
	


	

	
	

}
