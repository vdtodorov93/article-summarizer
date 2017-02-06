package edu.vasil.nlp.summarizer.stopwords;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Stopwords {

	/**
	 * The stopwords files are in the format ENstopwords.txt
	 */
	private final static String STOPWORDS_FILE = "stopwords.txt";
	
	private final static Map<StopwordsLanguage, Set<String>> STOPWORDS;

	private Stopwords() {
	}

	static {
		STOPWORDS = new HashMap<>();

		fillForLanguage(StopwordsLanguage.ENGLISH);
	}
	
	public static Set<String> get(StopwordsLanguage language) {
		return STOPWORDS.get(language);
	}
	
	private static void fillForLanguage(StopwordsLanguage language) {
		STOPWORDS.put(language, new HashSet<>());
		Set<String> stopWords = null;
		try {
		ClassLoader classLoader = new Stopwords().getClass().getClassLoader();
		
		File file = new File(classLoader.getResource(language + STOPWORDS_FILE).getFile());
		stopWords = new HashSet<>();
		
			try(Scanner scanner = new Scanner(file)) {
				while(scanner.hasNextLine()) {
					String word = scanner.nextLine();
					stopWords.add(word);
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		STOPWORDS.put(language, stopWords);
	}
	

}
