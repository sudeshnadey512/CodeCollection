package com.cg.lms.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.lms.exception.LmsException;
import com.cg.lms.model.LmsModel;
import com.cg.lms.service.LmsService;
import com.cg.lms.service.LmsServiceImpl;

public class LmsMain {
	public static void main(String[] args) {
		Scanner scanner = null;
		System.out.println("1.Insert");
		System.out.println("2.Exit");
		int choice = 0;
		LmsModel lmsModel = null;
		LmsService service = new LmsServiceImpl();
		while (true) {
			try {
				lmsModel = new LmsModel();
				scanner = new Scanner(System.in);
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					try {
						while (true) {
							System.out.println("Enter Book ID");
							scanner = new Scanner(System.in);
							int bookId = scanner.nextInt();
							//scanner = new Scanner(System.in);
							System.out.println("Enter student Id");
							int studentId = scanner.nextInt();
							//scanner = new Scanner(System.in);
							System.out.println("Enter book name");
							scanner.nextLine();
							String bookName = scanner.nextLine();
							//scanner = new Scanner(System.in);
							System.out.println("Enter student name");
							String studentName = scanner.nextLine();
							lmsModel.setBookId(bookId);
							lmsModel.setBookName(bookName);
							lmsModel.setStudentId(studentId);
							lmsModel.setStudentName(studentName);
							try {
								boolean validate=service.validateFields(lmsModel);
								if(validate==true)
									System.out.println(service.insertValues(lmsModel));
								break;
							} catch (LmsException lmsException) {
								System.out.println(lmsException.getMessage());
							}
						}
					} catch (InputMismatchException e) {
						System.err.println("Enter only digits");
					}
					break;
				case 2 : System.exit(0);

				default: System.out.println("Range 1-2");
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Enter only digits");
			}
		}

	}
}
