package com.library.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.library.bean.Book;
import com.library.bean.Member;
import com.library.data.DataStore;

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

	public Set<Book> findBooksByTitle(String title){
		logger.info("Executing findBooksByTitle");
		return null;
	}

	public boolean addMember(Member member){
		logger.info("Executing add member");
		return false;
	}

	
}
