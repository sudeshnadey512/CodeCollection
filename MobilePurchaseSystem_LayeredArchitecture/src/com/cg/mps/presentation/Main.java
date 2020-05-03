package com.cg.mps.presentation;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.mps.exception.MPSException;
import com.cg.mps.model.Mobiles;
import com.cg.mps.model.PurchaseDetails;
import com.cg.mps.service.MobileService;
import com.cg.mps.service.MobileServiceImpl;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);
	

	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		logger.info("log4j file configured");

		boolean outerFlag = false;
		Scanner scanner = null;

		MobileService service = new MobileServiceImpl();

		do {
			
			scanner = new Scanner(System.in);

			
			System.out.println("*** MObile Purchase System***");
			System.out.println("1.Login");
			System.out.println("2.Exit");

			int choice = 0;
			boolean choiceFlag = false;
			do {
				
				scanner = new Scanner(System.in);
				System.out.println("Enter your choice..");
				try {
					choice = scanner.nextInt();
					choiceFlag = true;
					
					do {
						switch (choice) {
						case 1:
							int mobileId = 0;

							boolean loginFlag = false;
							do {
								scanner = new Scanner(System.in);
								System.out.println("Enter mobile id");
								try {

									mobileId = scanner.nextInt();
									loginFlag = true;

								} catch (InputMismatchException e) {
									loginFlag = false;
									System.err.println("Enter only digits");
								}
							} while (!loginFlag);
							
							try {
								//System.out.println("I am inside");

								boolean loginResultFlag = service.validateLogin(mobileId);
								if (loginResultFlag == true ) {
									System.out.println("Validated,you are logged in");
									boolean checkFlag = false;
									do {

										/*scanner = new Scanner(System.in);*/
										System.out.println("2.Insert Data into mobile table");
										System.out.println("2.Insert Data into purchasedetails table");
										System.out.println("3.Update Data");
										System.out.println("4.Select Data");
										System.out.println("5.Delete Data");
										System.out.println("6.Exit");

										int input = 0;
										boolean inputFlag = false;

										do {
											scanner = new Scanner(System.in);
											System.out.println("Please again enter your choice");
											input = scanner.nextInt();

											switch (input) {
											case 1:
												int mobileId1 = 0;
												boolean mobileIdFlag = false;
												do {

													scanner = new Scanner(System.in);
													System.out.println("Enter mobile id");
													try {
														mobileId1 = scanner.nextInt();
														mobileIdFlag = true;

													} catch (InputMismatchException e) {
														System.err.println("Enter only digits");
													}
												} while (!mobileIdFlag);

												scanner.nextLine();
												String name = "";
												System.out.println("Enter mobile name");
												name = scanner.nextLine();

												double price = 0.0;
												boolean priceFlag = false;
												do {

													scanner = new Scanner(System.in);
													System.out.println("Enter mobile price");
													try {
														price = scanner.nextDouble();
														priceFlag = true;

													} catch (InputMismatchException e) {
														System.err.println("Enter only digits");
													}
												} while (!priceFlag);

												
												scanner.nextLine();
												String quantity = "";
												System.out.println("Enter quantity");
												quantity = scanner.nextLine();

												Mobiles mobiles1 = new Mobiles();
												mobiles1.setMobileId(mobileId1);
												mobiles1.setName(name);
												mobiles1.setPrice(price);
												mobiles1.setQuantity(quantity);

												try {
													boolean checkMobileFlag = service.checkMobileData(mobileId1);
													if (checkMobileFlag) {
														System.out.println("Unique constraint violated");
													} else {
														int genId = service.insertMobileDetails(mobiles1);
														System.out.println("Mobile details inserted with generated id: " + genId);
													}

												} catch (MPSException e) {
													System.err.println(e.getMessage());
												}

												break;
											case 2:
												
												scanner.nextLine();
												String quantity1 ="";
												System.out.println("Enter mobile quantity");
												quantity1 = scanner.nextLine();
												

												int purchaseId=0;
												boolean purchaseIdFlag = false;
												do {

													scanner = new Scanner(System.in);
													System.out.println("Enter mobile id:");
													try {
														purchaseId = scanner.nextInt();
														purchaseIdFlag = true;

													} catch (InputMismatchException e) {
														System.err.println("Enter only digits");
													}
												} while (!purchaseIdFlag);

												scanner.nextLine();
												String cName = "";
												System.out.println("Enter customer name:");
												cName = scanner.nextLine();

												String mailId = "";
												System.out.println("Enter mail id:");
												mailId = scanner.nextLine();

												String phoneNo = "";
												System.out.println("Enter phone no:");
												phoneNo = scanner.nextLine();

												scanner.nextLine();

												String purchaseDate = "";
												LocalDate localDate = null;
												DateTimeFormatter formatter = null;
												boolean dateFlag = false;
												do {
													scanner = new Scanner(System.in);
													System.out.println("Enter purchase date (dd-MM-yyyy)");
													purchaseDate = scanner.nextLine();
													formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

													try {
														localDate = LocalDate.parse(purchaseDate, formatter);
														dateFlag = true;
													} catch (DateTimeException e) {
														dateFlag = false;
														System.err.println("purchaseDate should be in the given format - (dd-MM-yyyy)");
													}

												} while (!dateFlag);

												int mobileId2 = 0;
												boolean mobileIdFlag1 = false;
												do {

													scanner = new Scanner(System.in);
													System.out.println("Enter mobile id:");
													try {
														mobileId2 = scanner.nextInt();
														mobileIdFlag1 = true;

													} catch (InputMismatchException e) {
														System.err.println("Enter only digits");
													}
												} while (!mobileIdFlag1);

												PurchaseDetails details = new PurchaseDetails(purchaseId, cName, mailId,phoneNo, localDate, mobileId2);
												
												
												try {
													
													boolean validateMobileFlag = service.checkQuantity(quantity1);
													if (!validateMobileFlag) {
														System.out.println("Quantity is less than 0");
														
													}
													else {
													
													try {
														
														boolean validatePurchaseFlag = service.validatePurchaseDetails(details);
														if (validatePurchaseFlag) {
															int genId1 = service.insertPurchaseDetails(details);
															System.out.println("Purchase details inserted with generated id: "+ genId1);
															
														}
														
													} catch (Exception e) {
														System.err.println(e.getMessage());

													}
																				
							
													
													}
													
											} catch (MPSException e) {
													System.err.println(e.getMessage());
											}

												break;
											case 3:
												
												
												break;
											case 4:
												break;
											case 5:

												System.out.println("Thank you,Visit again..");
												System.exit(0);
												break;
											default:
												System.err.println("Wrong choice,Please enter choice between(1-5)");
												break;
											}

										} while (!inputFlag);

									} while (!checkFlag);
								} else {
									System.err.println("Can't login visit again");
								}

							} catch (MPSException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 2:
							System.out.println("Exited");
							System.exit(0);
							break;
						default:
							System.out.println("Wrong choice please enter between (1-2)");
							break;
						}
						
					} while (!choiceFlag);
					
				} catch (InputMismatchException e) {
					System.err.println("Please enter only digits");
				}

			} while (!choiceFlag);

		} while (!outerFlag);

		scanner.close();
	}

}
