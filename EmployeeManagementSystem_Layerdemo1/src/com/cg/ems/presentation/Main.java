package com.cg.ems.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.ems.exceptions.EMSException;
import com.cg.ems.model.Employee;
import com.cg.ems.service.EmployeeService;
import com.cg.ems.service.EmployeeServiceImpl;

public class Main {

	public static void main(String[] args) {

		System.out.println("******** Emoployee management System *********");
		System.out.println("1.insert Employee");
		System.out.println("2.select Employee");
		System.out.println("3.select All Employee");
		System.out.println("4.delete Employee");

		EmployeeService service = new EmployeeServiceImpl();

		Scanner scanner = null;
		int choice = 0;
		boolean choiceFlag = false;

		do {
			scanner = new Scanner(System.in);
			System.out.println("Select ur choice(1-4)");
			try {
				choice = scanner.nextInt();
				choiceFlag = true;

				switch (choice) {
				case 1:

					int id = 0;
					boolean idFlag = false;
					String name = "";
					double salary = 0.0;
					String designation = "";

					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter id:");
						try {
							id = scanner.nextInt();
							idFlag = true;

							scanner.nextLine();
							System.out.println("Enter name");
							name = scanner.nextLine();

							System.out.println("Enter Salary:");
							salary = scanner.nextDouble();
							scanner.nextLine();

							System.out.println("Enter designation");
							designation = scanner.nextLine();

							Employee employee = new Employee(id, name, salary, designation);

							try {
								boolean resultFlag = service.validateFields(employee);

								if (resultFlag == true) {

									String message = service.insertEmployee(employee);
									System.out.println(message);
								}

							} catch (EMSException e) {
								System.err.println(e.getMessage());
							}

						} catch (InputMismatchException e) {
							idFlag = false;
							System.err.println("Enter only digits");
						}
					} while (!idFlag);

					break;

				case 2:

					break;

				case 3:

					break;

				case 4:

					break;

				default:
					System.out.println("input should be in the range of 1-4");
					choiceFlag = false;
					break;
				}

			} catch (InputMismatchException e) {
				choiceFlag = false;
				System.err.println("please enter only digits");
			}
		} while (!choiceFlag);

		scanner.close();
	}
}
