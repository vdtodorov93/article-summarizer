package edu.vasil.nlp.summarizer.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ReutersCrawler implements Crawler {

	@Override
	public String getArticle(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Element text = doc.select("#article-text").first();
		
		return text.text();
	}

}
