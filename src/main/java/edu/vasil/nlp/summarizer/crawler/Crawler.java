package edu.vasil.nlp.summarizer.crawler;

import java.io.IOException;

public interface Crawler {
	String getArticle(String url) throws IOException;
}
