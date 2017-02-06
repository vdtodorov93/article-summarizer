package edu.vasil.nlp.summarizer;

import java.io.IOException;

import edu.vasil.nlp.summarizer.crawler.ReutersCrawler;
import edu.vasil.nlp.summarizer.stopwords.Stopwords;
import edu.vasil.nlp.summarizer.stopwords.StopwordsLanguage;

public class App {
	public static void main(String[] args) throws IOException {
		System.out.println(Stopwords.get(StopwordsLanguage.ENGLISH));
		
		ReutersCrawler c = new ReutersCrawler();
		String article = c.getArticle("http://www.reuters.com/article/us-snap-inc-ipo-idUSKBN15H2VB");
		new Summarizer().getSummary(100, article);
		
	}
}
