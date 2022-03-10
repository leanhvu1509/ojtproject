package com.lavu.ojtsource.exception;

public class StorageException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779336487329710440L;

	public StorageException(String message,Exception e) {
		super(message,e);
	}
	public StorageException(String message) {
		super(message);
	}
}
