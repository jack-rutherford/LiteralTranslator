package translator;

import java.io.FileNotFoundException;

public interface IUserInterface {
	// Calls the necessary methods to: display a greeting, asks the user what they want to do; sets fields for
	// courpus filename and the text to be translated based on the user’s input, and uses these to initialize
	// a Translator object; it loops until the user no longer wants to translate any sentences.
	void runProgram() throws FileNotFoundException;
	// Displays a greeting to the user and indicates briefly what the program does.
	void greeting();
	// Displays the translation options to the user and, after reading the option selected by the user, sets
	// the name of the corpus file (initializes the field).
	// Uses try-catch block(s) to handle exceptions, and enforces correct input.
	void setCorpusFileName();
	// Requests for the text the user wants to translate (i.e. the source text) and sets the source text field.
	void setSourceText();
	// Uses the Translator object to translate the source text and displays the translated text to the user.
	void translate() throws FileNotFoundException;
	}