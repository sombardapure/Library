package com.barclays.library.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.barclays.library.bean.Book;
import com.barclays.library.bean.Member;

public class LibraryService {

	private static final Logger logger = Logger.getLogger(LibraryService.class);
	
	private static final LibraryService service = new LibraryService();
	
	private LibraryService(){
	}
	
	public static LibraryService getInstance(){
		return service;
	}
	
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
