package com.reading.reading.exception;
/**
 * Exception to throw in the application
 * @author 
 *
 */
public class ReadingException extends RuntimeException {
	
	private static final long serialVersionUID = -6356802867498346979L;
	
	public ReadingException() {
		super();
	}
	
	public ReadingException(String message) {
		super(message);
	}

}
