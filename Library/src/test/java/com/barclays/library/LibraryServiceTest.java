package com.barclays.library;

import org.junit.BeforeClass;
import org.junit.Test;

import com.barclays.library.service.LibraryService;

public class LibraryServiceTest {

	private static LibraryService libraryService;
	
	@BeforeClass
	public static void setUp(){
		libraryService = LibraryService.getInstance();
	}
	@Test
	public void findBooksByAuthor(){
		libraryService.findBooksByAuthor("");
	}
	
}
