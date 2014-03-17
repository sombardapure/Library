package com.library.exception;

/**
 * Business Exception in case of reference book is being issued
 * 
 * @author Somanath
 *
 */
public class RefBookBorrowException extends Exception {

	private static final long serialVersionUID = -9078546836659094516L;

	public RefBookBorrowException(String message){
		super(message);
	}
}
