package com.mohansrihari.exception;

public class EmailServiceException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public EmailServiceException() {
		super();
	}

	public EmailServiceException(final String message) {
		super(message);
	}
	
	public EmailServiceException(final Throwable exception) {
		super(exception);
	}


}
