package myWordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
	
	private List<String> loadWordsFromFile2(String fileName) {
		
		List<String> words = new ArrayList<>();
		
		try(Scanner scanner = new Scanner(new File(fileName), "UTF-8")){ //"UTF-8" :  hungarian
			//List<String> words = new ArrayList<>();
			while(scanner.hasNext()){
				String word = scanner.nextLine();
				//System.out.println(word);
				words.add(word); //az �sszes sz�t belerakjuk a list�ba
				
			}
			System.out.println(words.size() +" sz� tal�lhat� a magyar_szavak_listaja.txt f�jlban.");
			//System.out.println("HashSet.size(): "+new HashSet<>(words).size());//ugyanakkora mint a lista: nincs benne duplik�ci�
			return words;
		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem tal�lja a megadott f�jlt");
		}
		return Collections.emptyList();
	}
	
	public Map<Integer, String> wordsInAMap(String fileName){
		int counter = 0;
		Map<Integer, String> solutions = new HashMap<>();
		try(Scanner scanner = new Scanner(new File(fileName))){ //"UTF-8" :  hungarian
			//List<String> words = new ArrayList<>();
			while(scanner.hasNext()){
				String word = scanner.nextLine();
				solutions.put(counter, word);//az �sszes sz�t belerakjuk a map-ba is
				counter++;
			}
			/*
			Set<Entry<Integer, String>> entrySet = solutions.entrySet();
			//stream
			
			//stream v�ge, nem kell am�gy, mert lassabb
			//ez kell:
			//System.out.println(entrySet.size());
			for (Entry<Integer, String> entry : entrySet) {//entrySet
				//System.out.println(entry.getKey() + " -> " + entry.getValue());
			}
			*/
			return solutions;
		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem tal�lja a megadott f�jlt");
					}
		return Collections.emptyMap();
	}
	
	static boolean isIntheDictionary(String fileName, String word) {
		List<String> words = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File(fileName))) { // "UTF-8" : hungarian

			while (scanner.hasNext()) {
				String wordsInDictionary = scanner.nextLine();
				words.add(wordsInDictionary); // az �sszes sz�t belerakjuk a list�ba

				// System.out.println("lista m�rete__:"+words.size()); //check

			}

		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem tal�lja a megadott f�jlt");
			// e.printStackTrace();
			// e.getMessage();
		}
		if (words.contains(word)) {
			return true;
		} else {
			System.out.println("FOOBAR");
		}
		return false;

	}
	
	
}
