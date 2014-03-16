package com.library.data;

import java.util.HashSet;
import java.util.Set;

import com.library.bean.Book;
import com.library.bean.Librarian;
import com.library.bean.Member;
import com.library.bean.Person;
import com.library.enums.BookType;

/**
 * In memory database to hold the records
 * @author Somanath
 *
 */
public class DataStore {
	
	private static Set<Book> bookStore = new HashSet<Book>();
	private static Set<Person> userData = new HashSet<Person>();
	
	private static DataStore store = new DataStore();
	
	public static DataStore getInstance(){
		return store;
	}
	
	private DataStore(){
		loadData();
	}
	
	public Set<Book> getBookStore() {
		return bookStore;
	}

	public Set<Person> getUserData() {
		return userData;
	}
	
	public Set<Book> getBooks(Set<Book> books){
		Set<Book> booksInStore = new HashSet<Book>(bookStore);
		booksInStore.retainAll(books);
		return booksInStore;
	}
	
	public Book getBook(Book book){
		for(Book bookInDB : bookStore){
			if(bookInDB.getBookId().equals(book.getBookId())){
				return bookInDB;
			}
		}
		return null;
	}
	
	public Person getMember(Member member){
		for(Person person : userData){
			if(person.getId().equals(member.getId())){
				return person;
			}
		}
		return null;
	}
	
	private static void loadData() {
		bookStore.add(new Book(1L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		bookStore.add(new Book(2L, "Bruce Ecke","Thinking In Java", BookType.BORROWABLE));
		bookStore.add(new Book(3L, "Andrew Hunt","The Pragmatic Programmer", BookType.BORROWABLE));
		bookStore.add(new Book(4L, "Joshua Bloch","Effective Java", BookType.REFERENCE));
		bookStore.add(new Book(5L, "Bruce Tate","Bitter Java", BookType.REFERENCE));
		bookStore.add(new Book(6L, "Kathy Sierra", "Head First Java", BookType.BORROWABLE));
		
		userData.add(new Member(1L,"Chris"));
		userData.add(new Member(2L,"Jim"));
		userData.add(new Member(3L,"Steve"));
		userData.add(new Member(4L,"Mike"));
		userData.add(new Member(5L,"Alan"));
		userData.add(new Librarian(6L,"Brad"));
	}
}
