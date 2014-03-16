package com.library.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.library.bean.Book;
import com.library.bean.Member;
import com.library.data.DataStore;
import com.library.exception.BusinessException;

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
	 * 
	 * Method to issue books
	 * 
	 * @param borrower - Borrower details
	 * @param booksTobeIssued - list of books to be issued
	 * @return Set<Book> - list of issued books
	 * @throws BusinessException
	 */
	public Set<Book> issueBooks(Member borrower, Set<Book> booksTobeIssued) throws BusinessException{
		logger.info("Executing issueBooks");
		if(null == borrower || null == booksTobeIssued){
			logger.debug("Borrower or Books are null/invalid");
			return null;
		}
		Set<Book> books = dataStore.getBooks(booksTobeIssued);
		return books;
	}

	
}
