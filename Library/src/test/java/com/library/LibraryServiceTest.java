package com.library;

import static junit.framework.Assert.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.library.bean.Book;
import com.library.service.LibraryService;

/**
 * Test class to test library service
 * @author Somanath
 *
 */
public class LibraryServiceTest {

	private static LibraryService libraryService;

	@BeforeClass
	public static void setUp(){
		libraryService = LibraryService.getInstance();
	}
	@Test
	public void findBooksByAuthorAsNull(){
		Set<Book> actualResult = libraryService.findBooksByAuthor(null);
		assertEquals(null, actualResult);
	}
	
	@Test
	public void findBooksByAuthor(){
		libraryService.findBooksByAuthor("");
	}
	
}
