package com.library.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.library.bean.Book;
import com.library.bean.Member;
import com.library.data.DataStore;
import com.library.exception.MaxBorrowExceededException;
import com.library.exception.RefBookBorrowException;
import com.library.util.LibraryUtil;

/**
 * Service class which has all the methods required by library
 * @author Somanath
 *
 */
public class LibraryService {

	private static final Logger logger = Logger.getLogger(LibraryService.class);
	private static final DataStore dataStore =DataStore.getInstance();
	private static final LibraryService service = new LibraryService();

	private LibraryService(){
	}

	public static LibraryService getInstance(){
		return service;
	}

	/**
	 * Method to find books by author
	 * 
	 * @param author - author name as search criteria
	 * @return Set<Book> - search result
	 */
	public Set<Book> findBooksByAuthor(String author){
		logger.info("Executing findBooksByAuthor");
		Set<Book> resultSet = null;
		if(null == author || author.isEmpty()){
			if(logger.isDebugEnabled()){
				logger.debug("author is null or empty");
			}
			return resultSet;
		}
		resultSet = new HashSet<Book>();
		Set<Book> bookStore = dataStore.getBookStore();
		for(Book book : bookStore){
			if(book.getAuthor().toLowerCase().contains(author.toLowerCase())){
				resultSet.add(book);
			}
		}
		return resultSet;
	}

	/**
	 * Method to search books by title
	 * 
	 * @param title - title of the book
	 * @return Set<Book> - search result
	 */
	public Set<Book> findBooksByTitle(String title){
		logger.info("Executing findBooksByTitle");
		Set<Book> resultSet = null;
		if(null == title || title.isEmpty()){
			if(logger.isDebugEnabled()){
				logger.debug("title is null or empty");
			}
			return resultSet;
		}
		resultSet = new HashSet<Book>();
		Set<Book> bookStore = dataStore.getBookStore();
		for(Book book : bookStore){
			if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
				resultSet.add(book);
			}
		}
		return resultSet;
	}

	/**
	 * Method to join member in library
	 * 
	 * @param member - member details
	 * @return true if member is added otherwise false
	 */
	public boolean addMember(Member member){
		logger.info("Executing add member");
		if(null == member){
			if(logger.isDebugEnabled()){
				logger.debug("Member is null/invalid and could not be added to store");
			}
			return false;
		}
		return dataStore.getUserData().add(member);
	}
	

	/**
	 * Method to issue books
	 * 
	 * @param borrower - Borrower details
	 * @param booksTobeIssued - list of books to be issued
	 * @return Set<Book> - list of issued books
	 * @throws MaxBorrowExceededException
	 * @throws RefBookBorrowException
	 */
	public Set<Book> issueBooks(Member borrower, Set<Book> booksTobeIssued)
			throws MaxBorrowExceededException, RefBookBorrowException {
		logger.info("Executing issueBooks");
		if (null == borrower || null == booksTobeIssued) {
			logger.debug("Borrower or Books are null/invalid");
			return null;
		}
		Set<Book> books = dataStore.getBooks(booksTobeIssued);
		LibraryUtil.validateBorrowableBooks(books);

		Member member = (Member) dataStore.getMember(borrower);
		LibraryUtil.checkBookIssueThreshold(member, books);

		// Update the Book details like borrowedBy and borrowedOn
		for (Book book : books) {
			book.setBorrowedBy(borrower);
			book.setBorrowedOn(new Date());
			member.getBorrowedBooks().add(book);
		}

		return books;
	}

	/**
	 * Method to return books
	 * 
	 * @param member
	 *            - member details who is returning books
	 * @param returnedBooks
	 *            - list of books to be returned
	 * @return Set<Book> - list of overdue books
	 */
	public Set<Book> returnBooks(Member member, Set<Book> returnedBooks) {
		logger.info("Executing returnBooks");
		if (null == member || null == returnedBooks) {
			logger.debug("Borrower or returned books are null/invalid");
			return null;
		}
		Set<Book> overDueBooks = new HashSet<Book>();
		for(Book returnBook : returnedBooks){
			if(LibraryUtil.isBookOverdue(returnBook)){ //prepare list of overdue books
				overDueBooks.add(returnBook);
			}else{ //else set borrower as null and submit to library
				Book book = dataStore.getBook(returnBook);
				book.setBorrowedOn(null);
				book.setBorrowedBy(null);
			}
		}
		if(overDueBooks.size() == 0){ // there is no due on any of the books, then return null
			return null;
		}else{ // else respond with overdue books
			return overDueBooks;
		}
	}
}
