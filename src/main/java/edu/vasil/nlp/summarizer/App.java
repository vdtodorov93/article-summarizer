package edu.vasil.nlp.summarizer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import edu.vasil.nlp.summarizer.crawler.ReutersCrawler;

public class App {
	public static void main(String[] args) throws IOException {
		
		Arguments arguments = new Arguments(args);
		ReutersCrawler c = new ReutersCrawler();
		String article = c.getArticle("http://www.reuters.com/article/us-snap-inc-ipo-idUSKBN15H2VB");
		String summary = new Summarizer().getSummary(arguments.getPercentCompressRatio(), article);

		try {
			Files.write(Paths.get(arguments.getDestination()), Arrays.asList(summary), Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
