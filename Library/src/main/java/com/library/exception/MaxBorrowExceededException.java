package com.library.exception;

/**
 * Business Exception in case of book has overdue
 * 
 * @author Somanath
 *
 */
public class MaxBorrowExceededException extends Exception {

	private static final long serialVersionUID = 1112718532543096637L;

	public MaxBorrowExceededException(String message){
		super(message);
	}
}
