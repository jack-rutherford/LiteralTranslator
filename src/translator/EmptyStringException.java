package translator;

public class EmptyStringException extends Exception {

	public String getMessage() {
		return "Please enter something more than an empty String";
	}

}
