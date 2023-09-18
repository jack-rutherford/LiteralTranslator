package translator;

public class InvalidInputException extends Exception {
	public String getMessage() {
		return "Invalid Input. Please try again.";
	}
}
