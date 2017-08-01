package com.example.demo.exception;

public class ContentBaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public ContentBaseException() {
	}

	public ContentBaseException(String s) {
		super(s);
	}

	public ContentBaseException(String s, Throwable t) {
		super(s, t);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
