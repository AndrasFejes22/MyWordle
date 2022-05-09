package myWordle;

import java.util.Scanner;

public class UserImputHandler {
	
	private final Scanner scanner;

	/**
	 * @param scanner
	 */
	public UserImputHandler(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	
	public Guess readWordFromUser() {
		boolean correctUserImput;
		String userImput;
		do {
			System.out.print("Please give me your tip: ");
			userImput = scanner.nextLine();
			correctUserImput = true;
			if(!userImput.matches("[a-z]{5}")) {//csak 5 betûs szavak
				correctUserImput = false;
				System.out.println("Invalid input!");
			}
			
		} while (!correctUserImput);
		//int userNumber = Integer.parseInt(userImput);
		return new Guess(userImput);
	}

	public int readDifficultyFromUser() {
		int choice = 0;
		do {
			System.out.println("Please select a difficulty level!");
			System.out.println();
			System.out.println("1 - easy: 4 bad solutions excluded");
			System.out.println("2 - medium: 2 bad solutions excluded");
			System.out.println("3 - hard: 0 bad solutions excluded");
			System.out.println();
			System.out.print("Chosen difficulty: ");

			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Only numbers between 1 and 3 can be entered!");
				//e.printStackTrace();
			}
			//System.out.println("Chosen game difficulty: ");
			
			switch(choice) { //switch expression a ->ak kizáró eseteket írnak le, nem kell break;
			case 1 -> System.out.println("Chosen game difficulty: 1 - easy"); //(mennyi rossz megoldás eleve ne legyen ott)
			case 2 -> System.out.println("Chosen game difficulty: 2 - medium");
			case 3 -> System.out.println("Chosen game difficulty: 3 - hard");
			}

		} while (choice < 1 || choice > 3); // csk az 1,2,3 fogadható el
		return choice;
		
		
	}

	public String kindOfGame() {
		//String choice = "";
		boolean correctUserImput;
		String userImput;
		do {
			System.out.println("Please select a game type!");
			System.out.print("Guess word only from dictionary?: y/n ");
			userImput = scanner.nextLine();
			correctUserImput = true;
			if(!userImput.matches("[yn]{1}")) {//csak y vagy n
				correctUserImput = false;
				System.out.println("Invalid input!");
			}
			
		} while (!correctUserImput);
		//int userNumber = Integer.parseInt(userImput);
		return userImput;
		
	}

}
