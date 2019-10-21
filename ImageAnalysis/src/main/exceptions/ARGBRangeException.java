package main.exceptions;

public class ARGBRangeException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496420543224501897L;

	public ARGBRangeException() {
	}

	public ARGBRangeException(String arg0) {
		super(arg0);
	}

	public ARGBRangeException(Throwable arg0) {
		super(arg0);
	}

	public ARGBRangeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
