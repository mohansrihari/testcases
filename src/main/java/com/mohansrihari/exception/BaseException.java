package com.mohansrihari.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException() {
		super();
	}

	public BaseException(final String message) {
		super(message);
	}

	public BaseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BaseException(final Throwable cause) {
		super(cause);
	}

}
