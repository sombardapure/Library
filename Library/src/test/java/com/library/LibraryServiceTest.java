package com.library;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.library.bean.Book;
import com.library.bean.Member;
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
	@Test
	public void findBooksByTitleAsNull(){
		Set<Book> actualResult = libraryService.findBooksByTitle(null);
		assertEquals(null, actualResult);
	}
	
	@Test
	public void findBooksByTitleAsBlank(){
		Set<Book> actualResult = libraryService.findBooksByTitle("");
		assertEquals(null, actualResult);
	}
	
	@Test
	public void findBooksByTitle(){
		Set<Book> actualResult = libraryService.findBooksByTitle("Java");
		
		Set<Book> expectedResult = new HashSet<Book>();
		expectedResult.add(new Book(1L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		expectedResult.add(new Book(6L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		expectedResult.add(new Book(4L, "Joshua Bloch","Effective Java", BookType.REFERENCE));
		expectedResult.add(new Book(5L, "Bruce Tate","Bitter Java", BookType.REFERENCE));
		expectedResult.add(new Book(2L, "Bruce Ecke","Thinking In Java", BookType.BORROWABLE));
		
		assertNotNull(actualResult);
		assertArrayEquals(actualResult.toArray(), expectedResult.toArray());
	}
	
	@Test
	public void addMemberAsNull(){
		Member member = null;
		boolean actualResult = libraryService.addMember(member);
		assertFalse(actualResult);
	}
	
	@Test
	public void addMember(){
		Member member = new Member(7L, "TestUser7");
		boolean actualResult = libraryService.addMember(member);
		assertTrue(actualResult);
	}
	@Test
	public void addExistingMember(){
		Member member = new Member(1L, "TestUser1");
		boolean actualResult = libraryService.addMember(member);
		assertFalse(actualResult);
	}
	
}
