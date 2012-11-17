package com.mohansrihari.exception;

public class DAOException extends BaseException {
	private static final long serialVersionUID = 1L;
	
	public DAOException() {
		super();
	}

	public DAOException(final String message) {
		super(message);
	}
	
	public DAOException(final Throwable exception) {
		super(exception);
	}
	
	public DAOException(final String message, final Throwable exception) {
		super(message, exception);
	}
	
}
