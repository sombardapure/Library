package com.barclays.library.service;

import java.util.Set;

import com.barclays.library.bean.Book;
import com.barclays.library.bean.Member;

public class LibraryService {

	private static LibraryService service = new LibraryService();
	
	private LibraryService(){
	}
	
	public LibraryService getInstance(){
		return service;
	}
	
	public Set<Book> findBooksByAuthor(String author){
		return null;
	}
	
	public Set<Book> findBooksByTitle(String title){
		return null;
	}
	
	public boolean addMember(Member member){
		return false;
	}
	
}
