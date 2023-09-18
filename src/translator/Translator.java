package translator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * The translator class handles processing the information passed
 * from the user interface and translating the sentence to pass
 * back to the user interface. Also reads the corpus file to know
 * what language to translate from and to
 * 
 * @author jackrutherford
 * @date 3/3/22
 * @class CSCI 235
 *
 */
public class Translator implements ITranslator {

	private String filename;
	private String sentence;
	private String translatedSentence;
	private String[][] corpus;
	private int fileLength;
	private Scanner readFileLength;

	/**
	 * Constructor for translator class
	 * Sets the name of the corpus file, and the sentence to be translated
	 * @param filename corpus filename for what language to translate
	 * @param sentence sentence from UserInterface to translate
	 */
	public Translator(String filename, String sentence) {
		this.sentence = sentence;
		this.filename = filename;
		translatedSentence = "";

	}
	/**
	 * Goes through the corpus file using a buffered reader and puts the english and 
	 * french words into a 2D array to access when we translate a sentence later 
	 */
	@Override
	public void readCorpus() throws FileNotFoundException {
		File file = new File(filename);
		try {
			readFileLength = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		fileLength = Integer.parseInt(readFileLength.next());
		BufferedReader br = new BufferedReader(new FileReader(filename));
		corpus = new String[fileLength][2];
		try {
			// makes it so that it starts reading on the second line of the corpus
			br.readLine(); 
		} catch (IOException e1) {
			System.out.println("This message shouldn't ever pop up...");
		}
		for(int i = 0; i < corpus.length; i++) {
			String corpusLine = null;
			try {
				corpusLine = br.readLine();
			} catch (IOException e) {
				System.out.println("No line to read");
			}
			//make the inner index of corpus array equal to the split line
			corpus[i] = corpusLine.split(","); 
		}
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("I don't think this will ever print, but if it does the "
					+ "buffered reader didn't close correctly");
		}

	}
	/**
	 * Translates the original sentence, and deals with checking capitalization
	 * of the first letter
	 */
	@Override
	public String translate() {

		consultLookup();

		String firstLetter = sentence.substring(0,1);
		char[] fL = firstLetter.toCharArray();
		Character fl = fL[0];

		if(Character.isUpperCase(fl) ==  true) {
			String capitalLetter = translatedSentence.substring(0,1).toUpperCase();
			String restOfSentence = translatedSentence.substring(1, translatedSentence.length()-1);
			translatedSentence = capitalLetter + restOfSentence;
			translatedSentence = translatedSentence.trim();
		}

		return translatedSentence;
	}

	/**
	 * Breaks up the sentence and translates it word by word
	 * This method deals with handling punctuation and updating the
	 * translated sentence field for the translate method to return
	 */
	@Override
	public void consultLookup() {

		String[] arrSentence = sentence.split(" ");
		for(int i = 0; i < arrSentence.length; i++) {

			String word = arrSentence[i].trim().toLowerCase();
			char punctuation = ' ';
			//check for punctuation
			if(!word.matches("[a-zA-Z]+")) { //check if the String is only letters
				//get the punctuation at the end of the word, to add on later
				String punc = word.substring(word.length()-1, word.length());
				//get the word without punctuation
				word = arrSentence[i].substring(0, arrSentence[i].length()-1);
				word = word.trim().toLowerCase();
				punctuation = punc.charAt(0);
			}
			//translate word
			String translatedWord = lookup(word);
			if(translatedWord == "") {
				translatedSentence += word + " ";
			}
			else {
				translatedSentence += translatedWord + " ";
			}
			if(punctuation != ' ') {
				translatedSentence = translatedSentence.substring(0, translatedSentence.length()-1);
				translatedSentence += punctuation + " ";
			}

		}

	}

	/**
	 * Goes through the corpus 2D array, finds the original word and
	 * returns the translated word from the 2D array
	 * @param key word to be translated
	 * @return translated key
	 */
	private String lookup(String key) {
		//interacts with file
		//gets a word and returns a translated word
		String word = "";
		for(int i = 0; i < corpus.length; i++) {
			if(corpus[i][0].trim().toLowerCase().equals(key)) {
				word = corpus[i][1];
				break;
			}
		}
		return word;
	}

	/**
	 * Accessor method for the 2D corpus array
	 * @return 2D corpus array
	 */
	public String[][] getCorpus(){
		return corpus;
	}

	/**
	 * Accessor method method for the translated sentence
	 * @return translated sentence
	 */
	public String getTranslatedSentence() {
		return translatedSentence;
	}

}
