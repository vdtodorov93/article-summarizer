package edu.vasil.nlp.summarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.ibm.icu.text.BreakIterator;

import edu.vasil.nlp.summarizer.stopwords.Stopwords;
import edu.vasil.nlp.summarizer.stopwords.StopwordsLanguage;

public class Summarizer {

	/**
	 * 
	 * 1. Get text 2. To lower 3. Remove stop words . Break into sentences .
	 * Break into words and count occurances . eval sentences . pick (percentage
	 * / 100 * #sentencesCount) most-important sentences.
	 * 
	 * @param percentage
	 *            the compression ratio of the original and summary text
	 * @return
	 */
	public String getSummary(int percentage, String article) {
		Set<String> stopWords = Stopwords.get(StopwordsLanguage.ENGLISH);

		List<String> sentences = new ArrayList<>();

		// break to sentences
		BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
		sentenceIterator.setText(article); // step *2
		int start = sentenceIterator.first();
		for (int end = sentenceIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
			sentences.add(article.substring(start, end));
		}

		// break sentences to words and remove stop words
		List<List<String>> tokenizedWords = new ArrayList<>();
		for (String sentence : sentences) {
			List<String> words = new ArrayList<>();
			BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
			wordIterator.setText(sentence.toLowerCase());
			start = wordIterator.first();

			for (int end = wordIterator.next(); end != BreakIterator.DONE; start = end, end = wordIterator.next()) {
				String word = sentence.substring(start, end).toLowerCase();
				if (!stopWords.contains(word)) {
					words.add(word);
				}
			}

			tokenizedWords.add(words);
		}

		Map<String, Integer> occurancesInText = new HashMap<>();
		tokenizedWords.stream().flatMap(m -> m.stream()).forEach(word -> {
			if (!occurancesInText.containsKey(word)) {
				occurancesInText.put(word, 0);
			}

			occurancesInText.put(word, occurancesInText.get(word) + 1);
		});

		int[] evaluations = new int[sentences.size()];
		for(int i = 0; i < sentences.size(); i++) {
			List<String> wordsInSentence = tokenizedWords.get(i);
			for(String word: wordsInSentence) {
				evaluations[i] += occurancesInText.get(word);
			}
			
			//Do we need this?
//			evaluations[i] /= wordsInSentence.size(); 
		}
		
		
		int[] copyOfEvals = Arrays.copyOf(evaluations, evaluations.length);
		Arrays.sort(copyOfEvals);

		
		int numberOfSentencesInSummary = (int) ((percentage / 100.0) * sentences.size());
		
		Set<Integer> topSentencesScores = new HashSet<>();
		for(int i = copyOfEvals.length - 1; i > copyOfEvals.length - numberOfSentencesInSummary; i--) {
			topSentencesScores.add(copyOfEvals[i]);
		}
		
		StringBuilder result = new StringBuilder();

		for(int i = 0; i < sentences.size(); i++) {
			if(topSentencesScores.contains(evaluations[i])) {
				result.append(sentences.get(i));
			}
		}
		
		return result.toString();
	}

}
