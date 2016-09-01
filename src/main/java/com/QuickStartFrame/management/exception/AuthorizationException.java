package com.QuickStartFrame.management.exception;

public class AuthorizationException extends Exception {
	private static final long serialVersionUID = -6121126444520237830L;
	
	public AuthorizationException() {
		super();
	}
	
	public AuthorizationException(String message) {
		super(message);
	}
	
	public AuthorizationException(Throwable t) {
		super(t);
	}
	
	public AuthorizationException(String message, Throwable t) {
		super(message, t);
	}
}
