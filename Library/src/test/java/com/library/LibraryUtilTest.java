package com.library;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.library.bean.Book;
import com.library.enums.BookType;
import com.library.util.LibraryUtil;

public class LibraryUtilTest {

	@Test
	public void whenBookIsInvalidThenReturnFalse(){
		boolean actualResult = LibraryUtil.isBookOverdue(null);
		assertFalse(actualResult);
	}
	@Test
	public void whenBookIsValidAndOverdueThenReturnTrue(){
		Long prevDay =  8L * 24 * 60 * 60 * 1000;
		Calendar calendar = Calendar.getInstance();
		Date borrowedOn = new Date(calendar.getTime().getTime() - prevDay);
		
		Book book = new Book(1L, "", "", BookType.BORROWABLE);
		book.setBorrowedOn(borrowedOn);
		boolean actualResult = LibraryUtil.isBookOverdue(book);
		
		assertTrue(actualResult);
	}
	@Test
	public void whenBookIsValidAndNoOverdueThenReturnFalse(){
		Long prevDay = 7L * 24 * 60 * 60 * 1000;
		Calendar calendar = Calendar.getInstance();
		Date borrowedOn = new Date(calendar.getTime().getTime() - prevDay);
		
		Book book = new Book(1L, "", "", BookType.BORROWABLE);
		book.setBorrowedOn(borrowedOn);
		boolean actualResult = LibraryUtil.isBookOverdue(book);
		
		assertFalse(actualResult);
	}
}
