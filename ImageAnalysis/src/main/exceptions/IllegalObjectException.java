package main.exceptions;

public class IllegalObjectException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6763358759812329984L;

	public IllegalObjectException() {
	}

	public IllegalObjectException(String s) {
		super(s);
	}

	public IllegalObjectException(Throwable cause) {
		super(cause);
	}

	public IllegalObjectException(String message, Throwable cause) {
		super(message, cause);
	}

}
