package com.library.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import com.library.bean.Book;
import com.library.bean.Member;
import com.library.constants.ApplicationConstants;
import com.library.exception.ExceptionMessages;
import com.library.exception.MaxBorrowExceededException;
import com.library.exception.RefBookBorrowException;

/**
 * 
 * Util class to hold utility methods used by service layer
 * @author Somanath
 *
 */
public class LibraryUtil {
	
	/**
	 * Method to validate if book is overdue
	 * 
	 * @param book
	 * @return true if book is overdue otherwise false
	 */
	public static boolean isBookOverdue(Book book){
		if(null != book && null != book.getBorrowedOn()){
			Calendar cal = Calendar.getInstance();
			Date currentDate = cal.getTime();
			Long diff = currentDate.getTime() - book.getBorrowedOn().getTime();
			Long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays > ApplicationConstants.OVERDUE_DAY ? true : false;
		}
		return false;
	}
	
	/**
	 * Method to validate that member can not borrow more than 3 books
	 * 
	 * @param borrower - borrower details
	 * @param booksTobeIssued - list of books to be issued
	 * @throws MaxBorrowExceededException  - Exception with valid message in case of validation failure
	 */
	public static void checkBookIssueThreshold(Member borrower, Set<Book> booksTobeIssued) throws MaxBorrowExceededException {
		int noOfBooks = borrower.getBorrowedBooks().size() +  booksTobeIssued.size(); // alreadyBorrowedBooks + booksToBeIssued
		if(noOfBooks > ApplicationConstants.MAX_NO_OF_BOOKS){
			throw new MaxBorrowExceededException(ExceptionMessages.MAX_NO_OF_BOOKS);
		}
	}
	/**
	 * Method to validate that books can be borrowed
	 * 
	 * @param books - list of books
	 * @throws RefBookBorrowException - Exception with valid message in case of validation failure
	 */
	public static void validateBorrowableBooks(Set<Book> books) throws RefBookBorrowException{
		for(Book book : books){
			if(!book.isBorrowable()){
				throw new RefBookBorrowException(ExceptionMessages.REF_BOOK_CAN_NOT_BE_ISSUED);
			}
		}
	}

}
