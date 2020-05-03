package com.cg.bms.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Account;
import com.cg.bms.service.BankService;
import com.cg.bms.service.BankServiceImpl;

public class Main {

	public static void main(String[] args) {

		System.out.println("*******Bank Details*******");
		System.out.println("1.Create Table");
		System.out.println("2.Exit");

		BankService service = new BankServiceImpl();
		Scanner scanner = null;
		int choice = 0;
		boolean choiceFlag = false;
		do {

			scanner = new Scanner(System.in);
			System.out.println("Select our choice(1-2)");
			try {
				choice = scanner.nextInt();
				choiceFlag = true;
				switch (choice) {
				case 1:
					Long custId = (long) 0;
					String accountNo = "";
					String ifscCode = "";
					Double balance = 0.0;
					String customerName = "";
					String city = "";
					boolean checkFlag = false;

					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter customer id: ");
						try {
							custId = scanner.nextLong();
							checkFlag = true;
							scanner.nextLine();

						} catch (InputMismatchException e) {
							checkFlag = false;
							System.err.println("Enter digits only");
						}

					} while (!checkFlag);

					scanner = new Scanner(System.in);
					System.out.println("Enter account number: ");
					accountNo = scanner.nextLine();
					System.out.println("Enter IFSC Code: ");
					ifscCode = scanner.nextLine();

					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter balance: ");
						try {
							balance = scanner.nextDouble();
							checkFlag = true;
							scanner.nextLine();

						} catch (InputMismatchException e) {
							checkFlag = false;
							System.err.println("Enter digits only");
						}
					} while (!checkFlag);
					System.out.println("Enter customer name: ");
					customerName = scanner.nextLine();
					System.out.println("Enter city: ");
					city = scanner.nextLine();

					Account account = new Account(custId, accountNo, ifscCode, balance, customerName, city);

					try {
						boolean resultFlag = service.validateFields(account);

						if (resultFlag == true) {

							try {
								service.createAccountTable(account);

							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

						}
					} catch (BMSException e) {
						System.err.println(e.getMessage());
					}

					break;
				case 2:
					System.exit(0);
					break;

				default:
					System.out.println("------Wrong choice------");
					break;
				}

			} catch (InputMismatchException e) {
				choiceFlag = false;
				System.err.println("Enter digits only");

			}

		} while (!choiceFlag);

		scanner.close();

	}

}
