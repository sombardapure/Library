package com.barclays.library.bean;

import java.util.HashSet;
import java.util.Set;

public class Member  extends Person{
	
	private Set<Book> borrowedBooks;
	
	public Member(Long id, String name){
		this.id = id;
		this.name=name;
	}

	public Set<Book> getBorrowedBooks() {
		if(null == borrowedBooks){
			borrowedBooks = new HashSet<Book>();
		}
		return borrowedBooks;
	}
}
