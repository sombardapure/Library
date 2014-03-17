package com.library.util;

import java.util.Calendar;
import java.util.Date;

import com.library.bean.Book;
import com.library.constants.ApplicationConstants;

/**
 * 
 * Util class to hold utility methods used by service layer
 * @author Somanath
 *
 */
public class LibraryUtil {
	
	public static boolean isBookOverdue(Book book){
		if(null != book && null != book.getBorrowedOn()){
			Calendar cal = Calendar.getInstance();
			Date currentDate = cal.getTime();
			Long diff = currentDate.getTime() - book.getBorrowedOn().getTime();
			Long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays > ApplicationConstants.OVERDUE_DAY ? true : false;
		}
		return false;
	}

}
