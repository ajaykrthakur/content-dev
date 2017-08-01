package com.example.demo.exception;

public class XMLParsingException extends ContentBaseException {
	private static final long serialVersionUID = 1L;

	public XMLParsingException() {
	}

	public XMLParsingException(String s) {
		super(s);
	}

	public XMLParsingException(String s, Throwable t) {
		super(s, t);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
