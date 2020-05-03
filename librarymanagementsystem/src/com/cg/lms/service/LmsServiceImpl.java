package com.cg.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.lms.doa.*;
import com.cg.lms.exception.LmsException;
import com.cg.lms.model.LmsModel;

public class LmsServiceImpl implements LmsService {
	LmsDoa doa = new LmsDoaImpl();
	public boolean validateBookId(Integer bookId) {
		String regEx="[0-9]{4}";
		return Pattern.matches(regEx, Integer.toString(bookId));
	}
	public boolean validateStudentId(Integer studentId) {
		String regEx="[0-9]{6}";
		return Pattern.matches(regEx, Integer.toString(studentId));
	}
	public boolean validateBookName(String bookName) {
		String regEx="[A-Z]{1}[a-z]{4,9}";
		return Pattern.matches(regEx, bookName);
	}
	public boolean validateStudentName(String studentName) {
		String regEx="[A-Z]{1}[a-z]{4,}";
		return Pattern.matches(regEx,studentName);
	}
	public boolean validateFields(LmsModel lmsModel)throws LmsException {
		boolean check=true;
		List<String> list = new ArrayList<String>();
		if(validateBookId(lmsModel.getBookId())==false) {
			list.add("BookID Should be 4 digits");
		}
		if(validateStudentId(lmsModel.getStudentId())==false) {
			list.add("StudentID Should be 6 digits ");
		}
		if(validateStudentName(lmsModel.getStudentName())==false) {
			list.add("First letter in caps and min 5 chars ");
		}
		if(validateBookName(lmsModel.getBookName())==false) {
			list.add("first letter in caps, min 5 chars, max 10 chars");
		}
		if(list.isEmpty()==false) {
			check=false;
			throw new LmsException(list+"");
		}
		
		return check;
		
	}
	public String insertValues(LmsModel lmsModel) {
		return doa.insertValues(lmsModel);
	}

}
