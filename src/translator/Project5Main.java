package translator;

import java.io.FileNotFoundException;

public class Project5Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IUserInterface userInterface = new UserInterface();
		try {
			userInterface.runProgram();
		}
		catch (FileNotFoundException e) {
			System.out.println("FIle not found!");
		}
	}

}
