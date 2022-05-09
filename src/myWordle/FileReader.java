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
				words.add(word); //az összes szót belerakjuk a listába
				
			}
			System.out.println(words.size() +" szó található a magyar_szavak_listaja.txt fájlban.");
			//System.out.println("HashSet.size(): "+new HashSet<>(words).size());//ugyanakkora mint a lista: nincs benne duplikáció
			return words;
		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem találja a megadott fájlt");
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
				solutions.put(counter, word);//az összes szót belerakjuk a map-ba is
				counter++;
			}
			/*
			Set<Entry<Integer, String>> entrySet = solutions.entrySet();
			//stream
			
			//stream vége, nem kell amúgy, mert lassabb
			//ez kell:
			//System.out.println(entrySet.size());
			for (Entry<Integer, String> entry : entrySet) {//entrySet
				//System.out.println(entry.getKey() + " -> " + entry.getValue());
			}
			*/
			return solutions;
		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem találja a megadott fájlt");
					}
		return Collections.emptyMap();
	}
	
	static boolean isIntheDictionary(String fileName, String word) {
		List<String> words = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File(fileName))) { // "UTF-8" : hungarian

			while (scanner.hasNext()) {
				String wordsInDictionary = scanner.nextLine();
				words.add(wordsInDictionary); // az összes szót belerakjuk a listába

				// System.out.println("lista mérete__:"+words.size()); //check

			}

		} catch (FileNotFoundException e) {
			System.out.println("A rendszer nem találja a megadott fájlt");
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
