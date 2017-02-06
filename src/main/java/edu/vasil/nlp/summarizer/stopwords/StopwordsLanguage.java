package edu.vasil.nlp.summarizer.stopwords;

public enum StopwordsLanguage {
	ENGLISH("EN");

	private String name;
	
	StopwordsLanguage(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
