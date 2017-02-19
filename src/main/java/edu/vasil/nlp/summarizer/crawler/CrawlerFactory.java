package edu.vasil.nlp.summarizer.crawler;

public class CrawlerFactory {
	
	private static final String REUTERS_URL = "reuters.com";
	
	private CrawlerFactory() {
	}
	
	public static Crawler getCrawler(String url) {
		if(url.contains(REUTERS_URL)) {
			return new ReutersCrawler();
		} else {
			return new ReutersCrawler();
		}
	}
}
