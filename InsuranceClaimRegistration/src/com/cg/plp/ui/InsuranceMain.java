package com.cg.plp.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.Policy;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.model.UserRole;
import com.cg.plp.service.AgentService;

import com.cg.plp.service.ClaimDetailsService;
import com.cg.plp.service.ClaimHandlerService;
import com.cg.plp.service.ClaimViewService;
import com.cg.plp.service.LogInService;
import com.cg.plp.service.ReportGenerationDetailedView;
import com.cg.plp.service.ReportGenerationQuickView;
import com.cg.plp.service.UserCreationService;
import com.cg.plp.service.ValidatePolicyNumberService;
import com.cg.plp.service.ViewClaimUser;
import com.cg.plp.serviceImpl.AgentServiceImpl;
import com.cg.plp.serviceImpl.ClaimDetailsServiceImpl;
import com.cg.plp.serviceImpl.ClaimHandlerServiceImpl;
import com.cg.plp.serviceImpl.ClaimViewServiceImpl;
import com.cg.plp.serviceImpl.LogInServiceImpl;
import com.cg.plp.serviceImpl.ReportGenerationQuickViewImpl;
import com.cg.plp.serviceImpl.ReportgenerationDetailedViewImpl;
import com.cg.plp.serviceImpl.UserCreationServiceImpl;
import com.cg.plp.serviceImpl.ValidatePolicyNumberServiceImpl;
import com.cg.plp.serviceImpl.ViewClaimUserImpl;

public class InsuranceMain {
	static Logger logger = Logger.getLogger(InsuranceMain.class);
	static String userId = "";

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		Scanner scanner = null;
		PropertyConfigurator.configure("resources/log4j.properties");
		logger.info("log4j file configured..");
		String roleCode = "";
		boolean logInflag = false;

		do {
			scanner = new Scanner(System.in);
			try {
				roleCode = InsuranceMain.logInValidater();
				logInflag = true;
				if (roleCode != null) {

					// scanner = new Scanner(System.in);
					System.out.println("Welcome  :" + userId);
					System.out.println("Logged in as " + roleCode);
					System.out.println("--------------------");
					if (roleCode.equalsIgnoreCase("admin")) {
						InsuranceMain.adminPortal();

					} else if (roleCode.equalsIgnoreCase("agent")) {
						InsuranceMain.agentPortal();

					} else if (roleCode.equalsIgnoreCase("user")) {
						InsuranceMain.userPortal();

					}
				} else {
					logInflag = false;
					System.err.println("userID or Password is not valid");

				}

			} catch (InsuranceException e) {
				System.out.println(e.getMessage());
			}
		} while (!logInflag);

	}

	private static void userPortal() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		boolean continueFlag = false;
		boolean switchFlag = true;
		do {
			do {
				scanner = new Scanner(System.in);
				System.out.println("1. Claim creation");
				System.out.println("2. Claim view");
				System.out.println("3. Exit");
				try {
					choice = scanner.nextInt();
					continueFlag = true;

					switch (choice) {
					case 1:
						InsuranceMain.ClaimCreation(userId);

						break;
					case 2:
						InsuranceMain.userClaimView(userId);

						break;
					case 3:
						System.out.println("---------Thank You User.------");
						System.exit(0);

						break;

					default:
						System.err.println("Please Enter between 1-3 ");
						switchFlag = false;

						break;
					}
				} catch (InputMismatchException e) {
					switchFlag = false;
					System.err.println("Enter proper user Input(in Range of digits)");
				}
			} while (!switchFlag);
			boolean invalidFlag = false;
			do {

				System.out.println("Do u want to continue?(Y|N)");
				String continueChoice = scanner.next();
				continueChoice = continueChoice.toUpperCase();
				if (continueChoice.equals("Y")) {
					continueFlag = false;
					invalidFlag = true;

				} else {
					if (continueChoice.equals("N")) {
						System.out.println("---Thank You Visit Again---");
						continueFlag = true;
						invalidFlag = true;
					}

					else {
						System.err.println("Please enter Y or N ");
						continueFlag = true;
						invalidFlag = false;
					}
				}
			} while (!invalidFlag);

		} while (!continueFlag);

	}

	private static void agentPortal() {
		Long accountNumber = 0l;

		Scanner scanner = new Scanner(System.in);
		@SuppressWarnings("resource")
		int i = 1;
		boolean choiceFlag = false;
		List<Long> agent = new ArrayList<>();
		AgentService agentService = new AgentServiceImpl();
		try {
			agent = agentService.viewUserUnderAgent(userId);
			System.out.println("users under " + userId);
			for (Long accountNumberObject : agent) {

				System.out.println(i + "  " + accountNumberObject);
				i++;
			}
			if (agent.isEmpty()) {
				System.out.println("No user is under You");

			}
			System.out.println("Please Enter the Account Number u wish to Claim Create or View for ");
			do {
				scanner = new Scanner(System.in);
				try {
					accountNumber = scanner.nextLong();
					choiceFlag = true;
					if (!agent.contains(accountNumber)) {
						System.err.println("Enter valid Account Number");
						choiceFlag = false;
					}

				} catch (InputMismatchException e) {
					System.err.println("Please enter only digits");
					choiceFlag = false;
				}
			} while (!choiceFlag);
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}
		String userName = "";
		try {
			userName = agentService.getUserName(accountNumber);

		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}

		scanner = new Scanner(System.in);
		int choice = 0;
		boolean continueFlag = false;
		boolean switchFlag = false;
		do {
			do {
				scanner = new Scanner(System.in);
				System.out.println("1. Claim creation");
				System.out.println("2. Claim view");
				System.out.println("3. Exit");
				try {
					choice = scanner.nextInt();
					switchFlag = true;
					switch (choice) {
					case 1:
						InsuranceMain.ClaimCreation(userName);

						break;
					case 2:
						InsuranceMain.userClaimView(userName);

						break;
					case 3:
						System.out.println("---Thank You agent---");
						System.exit(0);

						break;

					default:
						switchFlag = false;
						System.out.println("Please Enter Between 1-3");
						break;
					}
				} catch (InputMismatchException e) {
					switchFlag = false;
					System.err.println("Enter only digits in range");
				}
			} while (!switchFlag);
			boolean invalidFlag = false;
			do {
				scanner = new Scanner(System.in);
				System.out.println("Do u want to continue?(Y|N)");
				String continueChoice = scanner.next();
				continueChoice = continueChoice.toUpperCase();
				if (continueChoice.equals("Y")) {
					continueFlag = false;
					invalidFlag = true;

				} else {
					if (continueChoice.equals("N")) {
						System.out.println("---Thank You Visit Again---");
						continueFlag = true;
						invalidFlag = true;
					}

					else {
						System.err.println("Please enter Y or N ");
						continueFlag = true;
						invalidFlag = false;
					}
				}
			} while (!invalidFlag);

		} while (!continueFlag);
	}

	@SuppressWarnings("resource")
	private static void userClaimView(String userName) {
		ClaimHandlerService claimCreation = new ClaimHandlerServiceImpl();
		ViewClaimUser viewservice = new ViewClaimUserImpl();
		Scanner scanner = new Scanner(System.in);
		// List<Claim> list1 = new ArrayList<>();
		// List<Long> list5 = new ArrayList<>();
		// List<Long> list8 = new ArrayList<>();
		List<Claim> viewDetailedView = new ArrayList<>();
		List<ClaimQuestion> ClaimQuestions = new ArrayList<>();
		List<PolicyDetails> ClaimAnswers = new ArrayList<>();
		Map<Long, Long> claimPolicyMap = new HashMap<Long, Long>();
		Long policyNumber = 0l;

		Long claimNumber = 0l;
		Long accountNumber = 0l;
		boolean inputPolicyFlag = false;

		try {
			accountNumber = claimCreation.getAccountNumber(userName);

		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Account Number : " + accountNumber);
		System.out.println("------------------------");
		try {
			claimPolicyMap = claimCreation.getPolicyNumberClaimNumber(userName);

			if (claimPolicyMap == null) {
				System.out.println("You have no active Claims");
			}
			System.out.println(String.format("%-30s %s", "Claim Number", "Policy Number"));
			Set<Long> claimPolicySet = claimPolicyMap.keySet();
			Iterator<Long> claimPolicyIterator = claimPolicySet.iterator();
			while (claimPolicyIterator.hasNext()) {
				claimNumber = claimPolicyIterator.next();
				policyNumber = claimPolicyMap.get(claimNumber);
				System.out.println();
				System.out.println(String.format("%-30s %s", claimNumber, policyNumber));

			}
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}

		System.out.println("enter Claim number");
		do {
			scanner = new Scanner(System.in);
			try {
				claimNumber = scanner.nextLong();
				policyNumber = claimPolicyMap.get(claimNumber);

				inputPolicyFlag = true;
				if (!policyNumberValidate(policyNumber)) {

					inputPolicyFlag = false;
				}

			} catch (InputMismatchException e) {
				System.err.println("Please enter only digits");
				inputPolicyFlag = false;
			}
		} while (!inputPolicyFlag);

		try {
			viewDetailedView = viewservice.viewClaimByPolicyNumber(claimNumber);
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}
		try {
			ClaimQuestions = viewservice.claimQuestionView(policyNumber);
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}
		try {
			ClaimAnswers = viewservice.claimAnswer(policyNumber);
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(String.format(
				"%-20s %-30s %-35s %-20s %-25s %-20s %-18s %-29s %-35s %-29s %-32s %-34s %-34s %-29s %-34s ",
				"Policy Number", "ClaimReason", "AccidentLocationStreet", "AccidentCity", "Accidentstate",
				"AccidentZip", "ClaimType", "Question1", "Answer1", "Question2", "Answer2", "Question3", "Answer3",
				"Question4", "Answer4"));

		for (int i = 0; i < 1; i++) {
			System.out.print(String.format("%-20s %-30s %-35s %-20s %-25s %-20s %-20s", policyNumber,
					viewDetailedView.get(i).getClaimReason(), viewDetailedView.get(i).getAccidentLocationStreet(),
					viewDetailedView.get(i).getAccidentCity(), viewDetailedView.get(i).getAccidentstate(),
					viewDetailedView.get(i).getAccidentZip(), viewDetailedView.get(i).getClaimType()));
			for (int j = 0; j < ClaimQuestions.size(); j++) {
				System.out.print(String.format("%-29s %-35s", ClaimQuestions.get(j).getQuestionDesc(),
						ClaimAnswers.get(j).getAnswer()));

			}
			System.out.println();
		}

	}

	@SuppressWarnings("resource")
	private static void adminClaimView() {

		ClaimViewService viewClaimService = new ClaimViewServiceImpl();
		Scanner scanner = null;
		List<Claim> list1 = new ArrayList<>();
		List<Long> list5 = new ArrayList<>();
		List<Long> list8 = new ArrayList<>();
		List<Claim> viewDetailedView = new ArrayList<>();
		List<ClaimQuestion> ClaimQuestions = new ArrayList<>();
		List<PolicyDetails> ClaimAnswers = new ArrayList<>();
		int indexOfList = 0;
		Long policyNumber1 = 0l;

		boolean claimFlag = false;
		try {
			list1 = viewClaimService.viewClaimDetails();
		} catch (InsuranceException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(String.format("%-10s  %-10s", "Policy Number", "Claim Number"));
		for (Claim list3 : list1) {
			list5.add(list3.getClaimNumber());
			list8.add(list3.getPolicyNumber());
			System.out.println(String.format("%-10s %-10s", list3.getPolicyNumber(), list3.getClaimNumber()));

		}

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Claim number");

			try {

				Long claimNumber = scanner.nextLong();

				try {

					if (list5.contains(claimNumber)) {
						indexOfList = list5.indexOf(claimNumber);
						policyNumber1 = list8.get(indexOfList);
						claimFlag = true;
						viewDetailedView = viewClaimService.getDetailsByClaimNumber(claimNumber);
						ClaimQuestions = viewClaimService.claimDetailsView(policyNumber1);
						ClaimAnswers = viewClaimService.claimAnswer(policyNumber1);
						System.out.println(String.format(
								"%-20s %-30s %-35s %-20s %-25s %-20s %-18s %-29s %-35s %-29s %-32s %-34s %-34s %-29s %-34s ",
								"Policy Number", "ClaimReason", "AccidentLocationStreet", "AccidentCity",
								"Accidentstate", "AccidentZip", "ClaimType", "Question1", "Answer1", "Question2",
								"Answer2", "Question3", "Answer3", "Question4", "Answer4"));
						for (int i = 0; i < viewDetailedView.size(); i++) {
							System.out.print(String.format("%-20s %-30s %-35s %-20s %-25s %-20s %-20s", policyNumber1,
									viewDetailedView.get(i).getClaimReason(),
									viewDetailedView.get(i).getAccidentLocationStreet(),
									viewDetailedView.get(i).getAccidentCity(),
									viewDetailedView.get(i).getAccidentstate(),
									viewDetailedView.get(i).getAccidentZip(), viewDetailedView.get(i).getClaimType()));
							for (int j = 0; j < ClaimQuestions.size(); j++) {
								System.out.print(String.format("%-29s %-35s", ClaimQuestions.get(j).getQuestionDesc(),
										ClaimAnswers.get(j).getAnswer()));

							}
							System.out.println();
						}
					} else {
						claimFlag = false;
						System.err.println("enter a valid claim Number");
					}
				} catch (InsuranceException e) {
					System.out.println(e.getMessage());
				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter only digits");
				claimFlag = false;

			}
		} while (!claimFlag);

	}

	private static void adminPortal() {
		boolean adminCheckFlag = false;
		boolean continueFlag = false;
		Scanner scanner = new Scanner(System.in);
		do {
			scanner = new Scanner(System.in);
			do {
				scanner = new Scanner(System.in);
				System.out.println("Please enter your choice");
				System.out.println("1 New Profile Creation");
				System.out.println("2. Claim creation");
				System.out.println("3. Claim View ");
				System.out.println("4. Report generation ");
				System.out.println("5. Exit");
				try {
					int choice = scanner.nextInt();
					adminCheckFlag = true;
					switch (choice) {
					case 1:
						InsuranceMain.newProfileCreation();
						break;
					case 2:
						InsuranceMain.adminClaimCreation(userId);

						break;
					case 3:
						InsuranceMain.adminClaimView();
						break;
					case 4:
						System.out.println("generating report");
						InsuranceMain.reportGeneration();
						break;
					case 5:
						System.out.println("-------Thank you admin.--------");
						System.exit(0);

					default:
						System.err.println("Please enter between 1-5");
						adminCheckFlag = false;
						break;

					}
				} catch (InputMismatchException e) {
					adminCheckFlag = false;
					System.err.println("Please enter digits only");
				}
			} while (!adminCheckFlag);
			boolean invalidFlag = false;
			do {

				System.out.println("Do u want to continue?(Y|N)");
				String continueChoice = scanner.next();
				continueChoice = continueChoice.toUpperCase();
				if (continueChoice.equals("Y")) {
					continueFlag = false;
					invalidFlag = true;

				} else {
					if (continueChoice.equals("N")) {
						System.out.println("---Thank You Visit Again---");
						continueFlag = true;
						invalidFlag = true;
					}

					else {
						System.err.println("Please enter Y or N ");
						continueFlag = true;
						invalidFlag = false;
					}
				}
			} while (!invalidFlag);

		} while (!continueFlag);

	}

	private static void adminClaimCreation(String userName) {
		String claimReason;
		String accidentLocation;
		String accidentCity;
		String accidentState;
		int accidentZip = 0;
		boolean zipFlag = false;
		String claimType = "";
		long policyNumber;

		Scanner scanner = new Scanner(System.in);

		ClaimHandlerService claimCreation = new ClaimHandlerServiceImpl();

		System.out.println("------------------------");

		long policyNum = 0;
		boolean policyFlag = false;
		do {
			scanner = new Scanner(System.in);

			System.out.println("Enter Policy Number");
			try {
				policyNum = scanner.nextLong();

				policyFlag = true;
			} catch (InputMismatchException e) {
				policyFlag = false;
				System.err.println("Enter Only digits");
			}
			if (!InsuranceMain.policyNumberValidate(policyNum)) {
				policyFlag = false;
			}
		} while (!policyFlag);

		System.out.println("---Claim Creation---");
		scanner.nextLine();

		boolean ClaimReasonFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Claim Reason");
			claimReason = scanner.nextLine();
			try {
				ClaimReasonFlag = claimCreation.validateClaimReason(claimReason);
			} catch (InsuranceException e) {
				ClaimReasonFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!ClaimReasonFlag);

		boolean accidentLocationFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Accident Location");
			accidentLocation = scanner.nextLine();
			try {
				accidentLocationFlag = claimCreation.validateAccidentLocation(accidentLocation);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (!accidentLocationFlag);

		boolean accidentCityFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Accident City");
			accidentCity = scanner.nextLine();
			try {
				accidentCityFlag = claimCreation.validateAccidentCity(accidentCity);
			} catch (InsuranceException e) {
				accidentCityFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!accidentCityFlag);

		boolean accidentStateFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Accident State");
			accidentState = scanner.nextLine();
			try {
				accidentStateFlag = claimCreation.validateAccidentState(accidentState);
			} catch (InsuranceException e) {
				accidentStateFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!accidentStateFlag);

		do {
			scanner = new Scanner(System.in);

			System.out.println("Enter Accident Zip");
			try {
				accidentZip = scanner.nextInt();

			} catch (InputMismatchException e) {
				zipFlag = false;
				System.err.println("Enter only digits");
			}

			try {

				zipFlag = claimCreation.validateZip(accidentZip);
			} catch (InsuranceException e) {
				zipFlag = false;
				System.err.println(e.getMessage());
			}
		} while (!zipFlag);

		scanner.nextLine();

		boolean ClaimTypeFlag = false;
		int ClaimChoice = 0;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Claim Type");
			System.out.println("1.Hurricane 2.Fire 3.Earthquake");

			try {
				ClaimChoice = scanner.nextInt();
				ClaimTypeFlag = true;
				switch (ClaimChoice) {
				case 1:

					claimType = "Hurricane";

					break;
				case 2:
					claimType = "Fire";

					break;
				case 3:

					claimType = "Earthquake";

					break;

				default:
					ClaimTypeFlag = false;
					System.err.println("Enter between 1-3");
					break;
				}
			} catch (InputMismatchException e) {
				ClaimTypeFlag = false;
				System.err.println("Enter only digits ");
			}

		} while (!ClaimTypeFlag);

		policyNumber = policyNum;
		int questionId = 1;
		List<ClaimQuestion> ClaimQuestion = new ArrayList<>();
		try {
			ClaimQuestion = claimCreation.selectQuestion(policyNumber);
			System.out.println(String.format("%-30s %-10s %-10s %-10s %-10s", "Question", "Answer1", "Answer2",
					"Answer3", "Answer4"));
			for (ClaimQuestion question : ClaimQuestion) {
				System.out.println(String.format("%-30s %-10s %-10s %-10s %-10s", question.getQuestionDesc(),
						" " + question.getQuestionAns1(), " " + question.getQuestionAns2(),
						" " + question.getQuestionAns3(), " " + question.getQuestionAns4()));

				int ansChoice = 0;
				String answer = "";
				boolean ansFlag = false;

				do {
					System.out.println("Enter your choice");

					scanner = new Scanner(System.in);
					try {
						ansChoice = scanner.nextInt();
						ansFlag = true;
					} catch (InputMismatchException e) {
						System.err.println("Enter only digits");
					}

					switch (ansChoice) {
					case 1:
						answer = question.getQuestionAns1();

						break;
					case 2:
						answer = question.getQuestionAns2();
						break;
					case 3:
						answer = question.getQuestionAns3();
						break;
					case 4:
						answer = question.getQuestionAns4();
						break;

					default:
						ansFlag = false;
						System.out.println("Enter choice between (1-4)");
						break;
					}
				} while (!ansFlag);
				claimCreation.insertAnswer(policyNumber, questionId, answer);
				questionId++;

			}

		} catch (InsuranceException e1) {
			System.err.println(e1.getMessage());
		}

		Claim handler = new Claim(claimReason, accidentLocation, accidentCity, accidentState, accidentZip, claimType,
				policyNumber);
		long id;
		try {
			id = claimCreation.insertDetails(handler);
			System.out.println("Data Inserted With Claim Number :: " + id);
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}

	}

	private static void ClaimCreation(String userName) {
		String claimReason;
		String accidentLocation;
		String accidentCity;
		String accidentState;
		int accidentZip = 0;
		boolean zipFlag = false;
		String claimType = "";
		long policyNumber;
		long accountNumber = 0;
		Scanner scanner = new Scanner(System.in);

		ClaimHandlerService claimCreation = new ClaimHandlerServiceImpl();

		try {
			accountNumber = claimCreation.getAccountNumber(userName);

		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Account Number : " + accountNumber);
		System.out.println("------------------------");
		List<Policy> list = new ArrayList<>();

		try {
			list = claimCreation.getPolicyNumber(accountNumber);
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Policy Number  Policy Type");
		System.out.println("------------------------");
		for (Policy policy : list) {

			System.out.println(policy.getPolicyNumber() + " ---->   " + policy.getPolicyType());

		}
		List<Long> list2 = new ArrayList<>();

		for (Policy policy : list) {
			list2.add(policy.getPolicyNumber());

		}

		long policyNum = 0;
		boolean policyFlag = false;
		do {
			scanner = new Scanner(System.in);

			System.out.println("Enter Policy Number");
			try {
				policyNum = scanner.nextLong();

				policyFlag = true;
			} catch (InputMismatchException e) {
				policyFlag = false;
				System.err.println("Enter Only digits");
			}
			if (!list2.contains(policyNum)) {
				policyFlag = false;
				System.err.println("The policy number is incorrect");
			}
		} while (!policyFlag);

		System.out.println("---Claim Creation---");
		scanner.nextLine();

		boolean ClaimReasonFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Claim Reason");
			claimReason = scanner.nextLine();
			try {
				ClaimReasonFlag = claimCreation.validateClaimReason(claimReason);
			} catch (InsuranceException e) {
				ClaimReasonFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!ClaimReasonFlag);

		boolean accidentLocationFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Accident Location");
			accidentLocation = scanner.nextLine();
			try {
				accidentLocationFlag = claimCreation.validateAccidentLocation(accidentLocation);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (!accidentLocationFlag);

		boolean accidentCityFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Accident City");
			accidentCity = scanner.nextLine();
			try {
				accidentCityFlag = claimCreation.validateAccidentCity(accidentCity);
			} catch (InsuranceException e) {
				accidentCityFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!accidentCityFlag);

		boolean accidentStateFlag = false;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Accident State");
			accidentState = scanner.nextLine();
			try {
				accidentStateFlag = claimCreation.validateAccidentState(accidentState);
			} catch (InsuranceException e) {
				accidentStateFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!accidentStateFlag);

		do {
			scanner = new Scanner(System.in);

			System.out.println("Enter Accident Zip");
			try {
				accidentZip = scanner.nextInt();

			} catch (InputMismatchException e) {
				zipFlag = false;
				System.err.println("Enter only digits");
			}

			try {

				zipFlag = claimCreation.validateZip(accidentZip);
			} catch (InsuranceException e) {
				zipFlag = false;
				System.err.println(e.getMessage());
			}
		} while (!zipFlag);

		scanner.nextLine();

		boolean ClaimTypeFlag = false;
		int ClaimChoice = 0;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter Claim Type");
			System.out.println("1. Hurricane 2. Fire 3. Earthquake");

			try {
				ClaimChoice = scanner.nextInt();
				ClaimTypeFlag = true;
				switch (ClaimChoice) {
				case 1:

					claimType = "Hurricane";

					break;
				case 2:
					claimType = "Fire";

					break;
				case 3:

					claimType = "Earthquake";

					break;

				default:
					ClaimTypeFlag = false;
					System.err.println("Enter between 1-3");
					break;
				}
			} catch (InputMismatchException e) {
				ClaimTypeFlag = false;
				System.err.println("Enter only digits ");
			}

		} while (!ClaimTypeFlag);

		policyNumber = policyNum;
		int questionId = 1;
		List<ClaimQuestion> ClaimQuestion = new ArrayList<>();
		try {
			ClaimQuestion = claimCreation.selectQuestion(policyNumber);
			System.out.println(String.format("%-30s %-10s %-10s %-10s %-10s", "Question", "Answer1", "Answer2",
					"Answer3", "Answer4"));
			for (ClaimQuestion question : ClaimQuestion) {
				System.out.println(String.format("%-30s %-10s %-10s %-10s %-10s", question.getQuestionDesc(),
						" " + question.getQuestionAns1(), " " + question.getQuestionAns2(),
						" " + question.getQuestionAns3(), " " + question.getQuestionAns4()));

				int ansChoice = 0;
				String answer = "";
				boolean ansFlag = false;

				do {
					System.out.println("Enter your choice");

					scanner = new Scanner(System.in);
					try {
						ansChoice = scanner.nextInt();
						ansFlag = true;
					} catch (InputMismatchException e) {
						System.err.println("Enter only digits");
					}

					switch (ansChoice) {
					case 1:
						answer = question.getQuestionAns1();

						break;
					case 2:
						answer = question.getQuestionAns2();
						break;
					case 3:
						answer = question.getQuestionAns3();
						break;
					case 4:
						answer = question.getQuestionAns4();
						break;

					default:
						ansFlag = false;
						System.out.println("Enter choice between (1-4)");
						break;
					}
				} while (!ansFlag);
				claimCreation.insertAnswer(policyNumber, questionId, answer);
				questionId++;

			}

		} catch (InsuranceException e1) {
			System.err.println(e1.getMessage());
		}

		Claim handler = new Claim(claimReason, accidentLocation, accidentCity, accidentState, accidentZip, claimType,
				policyNumber);
		long id;
		try {
			id = claimCreation.insertDetails(handler);
			System.out.println("Data Inserted With Claim Number :: " + id);
		} catch (InsuranceException e) {
			System.err.println(e.getMessage());
		}

	}

	private static void reportGeneration() {

		boolean switchFlag = false;
		ReportGenerationQuickView quickView = new ReportGenerationQuickViewImpl();
		ReportGenerationDetailedView detailedService = new ReportgenerationDetailedViewImpl();
		ClaimDetailsService ClaimDetailsService = new ClaimDetailsServiceImpl();
		// ReportGenerationDetailedView detailedView = new
		// ReportgenerationDetailedViewImpl();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		do {
			System.out.println("please select a valid choice");
			System.out.println("1. quick view");
			System.out.println("2. detailed view");
			scanner = new Scanner(System.in);
			int view = scanner.nextInt();
			switchFlag = true;
			switch (view) {
			case 1:
				scanner = new Scanner(System.in);
				List<Claim> quickViewdetails = null;
				try {
					quickViewdetails = quickView.selectQuickView();

				} catch (InsuranceException e) {
					System.err.println(e.getMessage());
				}
				System.out.println(String.format("%-20s %-20s %-20s %-20s", "PolicyNumber", "ClaimNumber", "ClaimType",
						"AccountNumber"));
				for (Claim j : quickViewdetails) {
					System.out.println(String.format("%-20s %-20s %-20s %-20s", j.getPolicyNumber(), j.getClaimNumber(),
							j.getClaimType(), j.getAccountNumber()));
				}

				break;

			case 2:
				scanner = new Scanner(System.in);
				
				

				List<Claim> viewDetailedView = new ArrayList<>();
				List<ClaimQuestion> ClaimQuestions = new ArrayList<>();
				List<PolicyDetails> ClaimAnswers = new ArrayList<>();
				try {
					viewDetailedView = detailedService.selectDetailedView();
					ClaimQuestions = ClaimDetailsService.claimQuestionsView();
					ClaimAnswers = ClaimDetailsService.claimAnswerView();

				} catch (InsuranceException e) {
					System.err.println(e.getMessage());
				}
				System.out.println(String.format(
						"%-20s %-30s %-35s %-20s %-25s %-20s %-18s %-29s %-35s %-29s %-32s %-34s %-34s %-29s %-34s ",
						"Policy Number", "ClaimReason", "AccidentLocationStreet", "AccidentCity", "Accidentstate",
						"AccidentZip", "ClaimType", "Question1", "Answer1", "Question2", "Answer2", "Question3",
						"Answer3", "Question4", "Answer4"));
				for (int i = 0; i < viewDetailedView.size(); i++) {
					System.out.print(String.format("%-20s %-30s %-35s %-20s %-25s %-20s %-20s",
							viewDetailedView.get(i).getPolicyNumber(), viewDetailedView.get(i).getClaimReason(),
							viewDetailedView.get(i).getAccidentLocationStreet(),
							viewDetailedView.get(i).getAccidentCity(), viewDetailedView.get(i).getAccidentstate(),
							viewDetailedView.get(i).getAccidentZip(), viewDetailedView.get(i).getClaimType()));
					for (int j = 0; j < ClaimQuestions.size(); j++) {
						System.out.print(String.format("%-29s %-35s", ClaimQuestions.get(j).getQuestionDesc(),
								ClaimAnswers.get(j).getAnswer()));

					}
					System.out.println();
				}
				break;
			default:
				switchFlag = false;
				System.err.println("Please Enter between 1-2");
				break;

			}
		} while (!switchFlag);

	}

	private static void newProfileCreation() {
		UserCreationService userCreation = new UserCreationServiceImpl();

		Scanner scanner = new Scanner(System.in);
		// int input = 0;
		boolean inputFlag = false;
		do {
			scanner = new Scanner(System.in);
			try {

				String userName = "";
				boolean userFlag = false;
				boolean resultFlag = false;
				do {
					scanner = new Scanner(System.in);
					System.out.println("Enter your user name");
					userName = scanner.nextLine();
					userFlag = userCreation.validateUserName(userName);
					try {
						resultFlag = userCreation.checkUserUnique(userName);
					} catch (InsuranceException e) {
						System.out.println(e.getMessage());
					}
					if (resultFlag == true) {
						System.err.println("User Name is already present");
						userFlag = false;
					}
					if (!userFlag) {

						System.err.println("UserName: only small character MaxLength :20");
					}
				} while (!userFlag);
				boolean passwordFlag = false;
				String password;
				do {

					scanner = new Scanner(System.in);
					System.out.println("Enter your password");
					password = scanner.nextLine();

					passwordFlag = userCreation.validatePassword(password);

					if (passwordFlag) {
						break;
					} else {

						System.out.println("Password:1st letter is capital MinLength:4 Maxlength:8");
					}

				} while (!passwordFlag);

				String roleCode = "";
				boolean roleCodeFlag = false;
				int roleCodeChoice = 0;
				do {
					scanner = new Scanner(System.in);
					System.out.println("Enter your role code");
					System.out.println("1.admin 2.user 3.agent");

					try {
						roleCodeChoice = scanner.nextInt();
						roleCodeFlag = true;
						switch (roleCodeChoice) {
						case 1:

							roleCode = "admin";

							break;
						case 2:
							roleCode = "user";

							break;
						case 3:

							roleCode = "agent";

							break;

						default:
							roleCodeFlag = false;
							System.err.println("Enter between 1-3");
							break;
						}
					} catch (InputMismatchException e) {
						roleCodeFlag = false;
						System.err.println("Enter valid credentials ");
					}

				} while (!roleCodeFlag);

				UserRole userRole = new UserRole(userName, password, roleCode);

				try {

					userCreation.insertUserCreationDetails(userRole);
					System.out.println("User details inserted");

				} catch (InsuranceException e) {
					System.err.println(e.getMessage());
				}

				break;

			} catch (InputMismatchException e) {
				inputFlag = false;
				System.out.println("Please enter again");
				System.err.println("enter only digits");
			}
		} while (!inputFlag);

	}

	private static String logInValidater() throws InsuranceException {

		LogInService logInservice = new LogInServiceImpl();

		String password = "";
		String roleCode = "";
		System.out.println("----LogIn details-----");
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter UserId :");
		userId = scanner.next();
		System.out.println("Enter Password :");
		password = scanner.next();

		roleCode = logInservice.logInValidate(userId, password);
		if (roleCode != null) {

			return roleCode;
		}

		return null;

	}

	private static boolean policyNumberValidate(Long policyNumber) {
		ValidatePolicyNumberService validatePolicy = new ValidatePolicyNumberServiceImpl();
		List<PolicyDetails> list = new ArrayList<>();
		List<Long> list2 = new ArrayList<>();
		// Long policy = 0l;
		try {

			list = validatePolicy.validatePolicyNumber();
			for (PolicyDetails l : list) {
				list2.add(l.getPolicyNumber());
			}

			if (list2.contains(policyNumber)) {
				return true;

			} else {
				System.err.println("Enter a valid policy number");

			}

		} catch (InputMismatchException e) {
			System.err.println("Enter a valid policy number");
		} catch (InsuranceException e) {
			e.printStackTrace();
		}

		return false;
	}

}
