package com.barclays.library.data;

import java.util.HashSet;
import java.util.Set;

import com.barclays.library.bean.Book;
import com.barclays.library.bean.Librarian;
import com.barclays.library.bean.Member;
import com.barclays.library.bean.Person;
import com.barclays.library.enums.BookType;

public class DataStore {
	
	private static Set<Book> bookStore = new HashSet<Book>();
	private static Set<Person> userData = new HashSet<Person>();
	
	private static DataStore store = new DataStore();
	
	public static DataStore getInstance(){
		return store;
	}
	
	private DataStore(){
		bookStore.add(new Book(1L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		bookStore.add(new Book(2L, "Bruce Ecke","Thinking In Java", BookType.BORROWABLE));
		bookStore.add(new Book(3L, "Andrew Hunt","The Pragmatic Programmer", BookType.BORROWABLE));
		bookStore.add(new Book(4L, "Joshua Bloch","Effective Java", BookType.REFERENCE));
		bookStore.add(new Book(5L, "Bruce Tate","Bitter Java", BookType.REFERENCE));
		
		userData.add(new Member(1L,"Chris"));
		userData.add(new Member(2L,"Jim"));
		userData.add(new Member(3L,"Steve"));
		userData.add(new Member(4L,"Mike"));
		userData.add(new Member(5L,"Alan"));
		userData.add(new Librarian(6L,"Brad"));
	}
	
	public static Set<Book> getBookStore() {
		return bookStore;
	}

	public static Set<Person> getUserData() {
		return userData;
	}
	
}
