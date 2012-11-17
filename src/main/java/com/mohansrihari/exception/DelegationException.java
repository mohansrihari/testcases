package com.mohansrihari.exception;

public class DelegationException extends BaseException {
		private static final long serialVersionUID = 1L;
		
		public DelegationException() {
			super();
		}

		public DelegationException(final String message) {
			super(message);
		}
		
		public DelegationException(final Throwable exception) {
			super(exception);
		}
		
		public DelegationException(final String message,final Throwable exception) {
			super(message, exception);
		}

}
