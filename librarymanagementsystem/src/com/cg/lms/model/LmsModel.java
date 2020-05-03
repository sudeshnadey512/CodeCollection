package com.cg.lms.model;

public class LmsModel {
private Integer bookId;
private Integer studentId;
private String bookName;
private String studentName;
public Integer getBookId() {
	return bookId;
}
public void setBookId(Integer bookId) {
	this.bookId = bookId;
}
public Integer getStudentId() {
	return studentId;
}
public void setStudentId(Integer studentId) {
	this.studentId = studentId;
}
public String getBookName() {
	return bookName;
}
public void setBookName(String bookName) {
	this.bookName = bookName;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public LmsModel(Integer bookId, Integer studentId, String bookName, String studentName) {
	super();
	this.bookId = bookId;
	this.studentId = studentId;
	this.bookName = bookName;
	this.studentName = studentName;
}
public LmsModel() {
}
}
