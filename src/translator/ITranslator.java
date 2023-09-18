package translator;

import java.io.FileNotFoundException;

public interface ITranslator {
	// Reads in the data from the input file and stores the data in a two dimensional array for easy retrieval
	// during the translation process.
	void readCorpus() throws FileNotFoundException;
	// Does some processing on the source text (to be translated) and calls the method consultLookup to
	// perform the translation. It returns the translated text.
	String translate();
	// Uses a private method: “private String lookup(String key);” to look up the words in the source
	// text (i.e. the words to be translated), builds up the translated text a word at a time, and updates the
	// field for the translated word.
	void consultLookup();
	}
