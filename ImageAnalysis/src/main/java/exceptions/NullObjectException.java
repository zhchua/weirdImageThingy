package exceptions;

public class NullObjectException extends NullPointerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7132709963065894241L;

	public NullObjectException() {
	}

	public NullObjectException(String s) {
		super(s);
	}

}
