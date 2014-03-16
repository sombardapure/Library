package com.library;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.library.bean.Book;
import com.library.bean.Member;
import com.library.enums.BookType;
import com.library.exception.BusinessException;
import com.library.exception.ExceptionMessages;
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
	
	@Test
	public void issueBookForInvalidMember() throws BusinessException{
		Member borrower = null;
		Set<Book> booksTobeIssued = null;
		Set<Book> actualResults = libraryService.issueBooks(borrower, booksTobeIssued);
		assertEquals(null, actualResults);
	}
	
	@Test
	public void issueRefBook(){
		Member borrower = new Member(1L, "");
		
		Set<Book> booksTobeIssued = new HashSet<Book>();
		booksTobeIssued.add(new Book(1L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		booksTobeIssued.add(new Book(4L, "Joshua Bloch","Effective Java", BookType.REFERENCE));
		booksTobeIssued.add(new Book(2L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		booksTobeIssued.add(new Book(3L, "Joshua Bloch","Effective Java", BookType.REFERENCE));
		
		try{
			libraryService.issueBooks(borrower, booksTobeIssued);
			fail();
		}catch(BusinessException exception){
			assertEquals(ExceptionMessages.REF_BOOK_CAN_NOT_BE_ISSUED, exception.getMessage());
		}
	}
	
	@Test
	public void issueBooksMoreThanThreshold() {
		Member borrower = new Member(1L, "");
		
		Set<Book> booksTobeIssued = new HashSet<Book>();
		booksTobeIssued.add(new Book(1L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		booksTobeIssued.add(new Book(2L, "Joshua Bloch","Effective Java", BookType.BORROWABLE));
		booksTobeIssued.add(new Book(3L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		booksTobeIssued.add(new Book(6L, "Joshua Bloch","Effective Java", BookType.BORROWABLE));
		
		try{
			libraryService.issueBooks(borrower, booksTobeIssued);
			fail();
		}catch(BusinessException exception){
			assertEquals(ExceptionMessages.MAX_NO_OF_BOOKS, exception.getMessage());
		}
		
	}
	
}
