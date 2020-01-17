package exceptions;

public class NullConstructorException extends NullPointerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6510203461156462836L;

	public NullConstructorException() {
	}

	public NullConstructorException(String arg0) {
		super(arg0);
	}

}
