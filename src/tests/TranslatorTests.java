package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import translator.Translator;

public class TranslatorTests {

	String filename1 = "English-French.txt";
	String filename2 = "French-English.txt";

	@Test
	public void testReadCorpus() {
		Translator tr = new Translator(filename1, "The boy ate the jellyfish with the sister!");
		try {
			tr.readCorpus();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		String[][] corpus = tr.getCorpus();
		assertEquals("Expected corpus length is 15", 15, corpus.length);
		assertEquals("The first English word should be 'the'", "the", corpus[0][0]);
		assertEquals("The french translation of 'the' is 'le'", "le", corpus[0][1]);
	}

	@Test
	public void testTranslate() {
		//		fail("Not yet implemented");

		//Check capitalization
		Translator tr1 = new Translator(filename2, "Le");
		try {
			tr1.readCorpus();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tr1.translate();
		assertEquals("Checks if capitalization keeps throughout sentence, should be 'The'", 
				"The", tr1.getTranslatedSentence());

		//Check punctuation
		Translator tr2 = new Translator(filename1, "The lazy boy dances with the fun jellyfish, "
				+ "he gives the jellyfish a flower.");
		try {
			tr2.readCorpus();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tr2.translate();
		assertEquals("Checks if punctuation keeps throughout sentence, should be 1 commas, 1 period, "
				+ "and an exclamation point at the end", 
				"Le paresseux garcon danse avec le fun meduse, he gives le meduse a fleur."
				, tr2.getTranslatedSentence());

		//Check no punctuation or capitalization
		Translator tr3 = new Translator(filename1, "the smelly boy dances with the dog");
		try {
			tr3.readCorpus();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tr3.translate();
		assertEquals("Checks if capitalization keeps throughout sentence, should be 'The'", 
				"le puante garcon danse avec le chien ", tr3.getTranslatedSentence());
	}

}
