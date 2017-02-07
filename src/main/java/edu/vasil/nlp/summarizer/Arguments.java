package edu.vasil.nlp.summarizer;

public class Arguments {

	private static final int DEFAULT_COMPRESS_RATIO_IN_PERCENT = 20;

	private static final String DEFAULT_DESTINATION = "summary.txt";

	private int percentCompressRatio;

	private String destination;

	public Arguments(String[] commandLineArgs) {
		percentCompressRatio = DEFAULT_COMPRESS_RATIO_IN_PERCENT;
		destination = DEFAULT_DESTINATION;

		parseArgs(commandLineArgs);
	}

	public int getPercentCompressRatio() {
		return percentCompressRatio;
	}

	public void setPercentCompressRatio(int percentCompressRatio) {
		this.percentCompressRatio = percentCompressRatio;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	private void parseArgs(String[] commandLineArgs) {
		try {
			for (int i = 0; i < commandLineArgs.length; i++) {
				if (commandLineArgs[i].equals("-d")) {
					destination = commandLineArgs[++i];
				} else if (commandLineArgs[i].equals("-p")) {
					percentCompressRatio = Integer.parseInt(commandLineArgs[++i]);
				}
			}
		} catch (IndexOutOfBoundsException ioobe) {
			System.err.println("Bad arguments format");
		} catch (NumberFormatException e) {
			System.err.println("Argument -p must be an Integer");
		}

	}
}
