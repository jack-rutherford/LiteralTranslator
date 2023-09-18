package translator;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * User interface class, handles interacting with the user and passing data to the translator class
 * 
 * @author jackrutherford
 * @date 3/3/22
 * @class CSCI 235
 *
 */
public class UserInterface implements IUserInterface {

	private Translator tr;
	private String text;
	private String corpusName;
	private Scanner sc;

	/**
	 * Empty constructor for User Interface
	 */
	public UserInterface() {
		sc = new Scanner(System.in);
	}

	/**
	 * Method that handles calling all methods necessary for the
	 * program to run
	 */
	@Override
	public void runProgram() throws FileNotFoundException {
		greeting();
		setSourceText();
		System.out.println("Thank you for using the literal translator, goodbye! Au revoir!");
	}

	/**
	 * Prints out a default greeting for the translator
	 */
	@Override
	public void greeting() {
		System.out.println("Welcome to the literal translator! Bienvenue!");
		System.out.println("You can translate from English to French, or vice versa.");
	}

	/**
	 * Sets the filename for the corpus, either will translate French to English, or vice versa
	 */
	@Override
	public void setCorpusFileName() {
		System.out.println("What would you like to translate?\n>> Select 1 for English to French\n"
				+ ">> Select 2 for French to English");
		boolean flag = false;
		while(!flag) {
			try {
				String str = sc.nextLine();
				if(str.trim().equals("")){ //checks if the value is an empty string first
					throw new EmptyStringException();
				}
				int num = Integer.parseInt(str);
				if(num == 1) {
					//tr = new Translator("English-French.txt");
					corpusName = "English-French.txt";
					flag = true;
				}
				else if(num == 2) {
					//tr = new Translator("French-English.txt");
					corpusName = "French-English.txt";
					flag = true;
				}
				else { //doesn't match an answer, so value is outside of desired range
					throw new InvalidInputException();
				}
			}catch(InvalidInputException | NumberFormatException e) {
				System.out.println("That's not a valid number! Try again");
			}catch(EmptyStringException e) {		
				System.out.println(e.getMessage());			
			}
		}
//		sc.close();
	}

	/**
	 * Sets the sentence to be translated, also handles translating as long as the
	 * user wants to. Throws exceptions where needed to keep the program running smoothly
	 */
	@Override
	public void setSourceText() {
		boolean flag = false;
		// outer loop keeps translating until you select 'no'
		while(!flag) { 
			setCorpusFileName();
			System.out.println("What would you like to translate?");
			boolean done = false;
			//1st inner loop checks to make sure the entered text isn't empty
			while(!done) { 
				try {
					text = sc.nextLine();
					if(text.equals("")) {
						throw new EmptyStringException();
					}
					done = true;
				} catch (EmptyStringException e) {
					System.out.println("Please enter a valid sentence");
				}

			}
			// translate the sentence
			try {
				System.out.print("Your sentence translated is:\n>>");
				translate();
			} catch (FileNotFoundException e) {
				System.out.println("File was not found");
			}
			// 2nd inner loop for making sure choice for keep translating is valid
			boolean flag2 = false;
			while(!flag2) {
				try {
					System.out.println("Would you like to keep translating? (y/n)");
					String choice = sc.nextLine();
					if(!choice.equals("n") && !choice.equals("y")) {
						throw new InvalidInputException();
					}
					// will kill the other loop, ending the method and program
					else if(choice.equals("n")) {
						flag = true;
					}
					flag2 = true;
				} catch(InvalidInputException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

	/**
	 * Makes a new translator with the corpus name, and sentence to be translated
	 */
	@Override
	public void translate() throws FileNotFoundException {

		tr = new Translator(corpusName, text);
		tr.readCorpus();
		System.out.println(tr.translate());

	}

}
