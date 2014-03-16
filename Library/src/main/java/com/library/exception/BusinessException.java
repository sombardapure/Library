package com.library.exception;

/**
 * Exception class to hold business exception messages
 * @author Somanath
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -4786067125511238708L;

	public BusinessException( String errorMessage){
		super(errorMessage);
	}
}
