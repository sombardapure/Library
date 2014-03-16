package com.library.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Somanath
 *
 */
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj ){
		if(obj == null)
			return false;
		if(!(obj instanceof Member))
			return false;
		if(this == obj)
			return true;
		Member other = (Member) obj;
		if(this.id == null){
			if(other.getId() != null){
				return false;
			}
		}else if(!id.equals(other.id)){
			return false;
		}
		return true;
	}
}
