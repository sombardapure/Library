package com.library.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.library.bean.Book;
import com.library.bean.Member;

/**
 * Service class which has all the methods required by library
 * @author Somanath
 *
 */
public class LibraryService {

	private static final Logger logger = Logger.getLogger(LibraryService.class);
	
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
		return null;
	}

	public Set<Book> findBooksByTitle(String title){
		logger.info("Executing findBooksByTitle");
		return null;
	}

	public boolean addMember(Member member){
		logger.info("Executing add member");
		return false;
	}

	
}
