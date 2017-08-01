package com.example.demo.exception;

public class UnsupportedDocTypeException extends ContentBaseException {

	private static final long serialVersionUID = 1L;

	public UnsupportedDocTypeException() {
	}

	public UnsupportedDocTypeException(String s) {
		super(s);
	}

	public UnsupportedDocTypeException(String s, Throwable t) {
		super(s, t);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
