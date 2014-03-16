package com.library;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.library.bean.Book;
import com.library.enums.BookType;
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
	public void findBooksByAuthorAsBlank(){
		Set<Book> actualResult = libraryService.findBooksByAuthor("");
		assertEquals(null, actualResult);
	}
	
	@Test
	public void findBooksByAuthor(){
		Set<Book> actualResult = libraryService.findBooksByAuthor("Sierra");
		
		Set<Book> expectedResult = new HashSet<Book>();
		expectedResult.add(new Book(1L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		expectedResult.add(new Book(6L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		
		assertNotNull(actualResult);
		assertArrayEquals(actualResult.toArray(), expectedResult.toArray());
	}
	
}
