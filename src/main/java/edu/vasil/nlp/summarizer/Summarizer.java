package edu.vasil.nlp.summarizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.ibm.icu.text.BreakIterator;

import edu.vasil.nlp.summarizer.stopwords.Stopwords;
import edu.vasil.nlp.summarizer.stopwords.StopwordsLanguage;

public class Summarizer {
	
	/**
	 * 
	 1. Get text
	 2. To lower
	 3. Remove stop words
	 . Break into sentences
	 . Break into words and count occurances
	 . eval sentences
	 . pick (percentage / 100 * #sentencesCount) most-important sentences.
		 
	 * @param percentage the compression ratio of the original and summary text
	 * @return
	 */
	public String getSummary(int percentage, String article) {
		Set<String> stopWords = Stopwords.get(StopwordsLanguage.ENGLISH);
		
		List<String> sentences = new ArrayList<>();
		
		//break to sentences
		BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
		sentenceIterator.setText(article.toLowerCase()); // step *2
		int start = sentenceIterator.first();
		for (int end = sentenceIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
			sentences.add(article.substring(start, end));
		}
		
		//break sentences to words and remove stop words
		List<List<String>> tokenizedWords = new ArrayList<>();
		for(String sentence: sentences) {
			List<String> words = new ArrayList<>();
			BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
			start = wordIterator.first();
			
			for(int end = wordIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
				String word = sentence.substring(start, end);
				if(!stopWords.contains(word)) {
					words.add(word);
				}
			}
			
			tokenizedWords.add(words);
		}
		
		return null;
	}

}
