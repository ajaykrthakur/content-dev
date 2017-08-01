package com.example.demo.exception;

public class ContentValidationException extends ContentBaseException {

	private static final long serialVersionUID = 1L;

	public ContentValidationException() {
	}

	public ContentValidationException(String s) {
		super(s);
	}

	public ContentValidationException(String s, Throwable t) {
		super(s, t);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
