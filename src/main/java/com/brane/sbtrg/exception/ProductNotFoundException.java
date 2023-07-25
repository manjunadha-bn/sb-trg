package com.brane.sbtrg.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9079454849611061074L;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(final String message) {
		super(message);
	}

}
