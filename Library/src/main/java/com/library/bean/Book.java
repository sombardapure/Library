package com.library.bean;

import java.util.Calendar;
import java.util.Date;

import com.library.constants.ApplicationConstants;
import com.library.enums.BookType;

/**
 * 
 * @author Somanath
 *
 */
public class Book {

	private Long bookId;
	private String author;
	private String title;
	private BookType bookType;
	private Member borrowedBy;
	private Date borrowedOn; 
	
	public Book(Long bookId, String author, String title, BookType bookType){
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.bookType = bookType;
	}
	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public Member getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(Member borrowedBy) {
		this.borrowedBy = borrowedBy;
	}

	public Date getBorrowedOn() {
		return borrowedOn;
	}

	public void setBorrowedOn(Date borrowedOn) {
		this.borrowedOn = borrowedOn;
	}
	public boolean isBorrowable(){
		return bookType.name().equals(BookType.BORROWABLE.name());
	}
	public Boolean isOverDue(){
		if(null != borrowedOn){
			Calendar cal = Calendar.getInstance();
			Date currentDate = cal.getTime();
			Long diff = currentDate.getTime() - borrowedOn.getTime();
			Long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays > ApplicationConstants.OVERDUE_DAY ? true : false;
		}
		return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj ){
		if(obj == null)
			return false;
		if(!(obj instanceof Book))
			return false;
		if(this == obj)
			return true;
		Book other = (Book) obj;
		if(this.bookId == null){
			if(other.getBookId() != null){
				return false;
			}
		}else if(!bookId.equals(other.bookId)){
			return false;
		}
		return true;
	}
}
