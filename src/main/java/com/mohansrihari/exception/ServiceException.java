package com.mohansrihari.exception;

public class ServiceException extends BaseException {
	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(final String message) {
		super(message);
	}
	
	public ServiceException(final Throwable exception) {
		super(exception);
	}
	
	public ServiceException(final String message,final Throwable exception) {
		super(message, exception);
	}
	
	
}
