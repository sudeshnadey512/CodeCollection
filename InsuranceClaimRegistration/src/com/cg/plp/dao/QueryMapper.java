package com.cg.plp.dao;

public interface QueryMapper {

	String logInQuery = "Select * from user_role where User_Name=? AND Password=?";
	String checkUserCreationQuery="select User_Name from User_Role where User_Name=?";
	String insertUserCreationQuery = "insert into User_Role(User_Name,Password,Role_Code) values(?,?,?) ";
	String ReportGenaratorQuickViewQuery = "select c.Policy_Number,c.Claim_Number,c.claim_Type,p.Account_Number from Claim c,Policy p where c.policy_number=p.policy_number order by p.Account_Number ";
	String ReportGenaratorDetailedQuery="select c.Policy_Number,c.Claim_Reason,c.Accident_Location_street,c.Accident_City,c.Accident_state,c.Accident_Zip,c.claim_Type,p.Account_Number from Claim c,policy p where c.policy_number=p.policy_number order by p.Account_Number ";
	String selectQuestionsView="select Question_Desc from Claim_Question order by policy_number";
	String selectAnswersView="select answer from policy_details order by policy_number";
	String policyNumberValidator="select Policy_Number from Policy";

	String selectAccountNo = "SELECT account_number FROM accounts WHERE user_name = ?";
	String selectPolicyNo = "SELECT policy_number,policy_type FROM policy WHERE account_number = ?";
	String insertClaim = "INSERT INTO claim(claim_number,claim_reason,accident_location_street,accident_city,accident_state,accident_zip,claim_type,policy_number)values(claim_seq.NEXTVAL,?,?,?,?,?,?,?)";
	String selectClaimNumber = "SELECT claim_seq.CURRVAL FROM dual";
	String selectQuestionQuery = "SELECT Question_Desc,QUES_ANS1,QUES_ANS2,QUES_ANS3,QUES_ANS4 FROM claim_question WHERE policy_number = ?";
	/*String selectQuestionQuery = "SELECT Question_Desc,QUES_ANS1,QUES_ANS2,QUES_ANS3,QUES_ANS4 FROM claim_question WHERE policy_type in(SELECT policy_type FROM policy WHERE policy_number = ?)";*/
	String insertAnswerQuery = "INSERT INTO Policy_Details(policy_number,question_id,answer) values(?,?,?)";
	String selectClaimByClaimNumber = "SELECT  Claim_Reason,Accident_Location_street,Accident_City,Accident_state,Accident_Zip,claim_Type from Claim where Claim_Number=? ";
	String selectPolicyDetails = "select policy_number,Claim_Number from Claim";
	String claimViewQuestionsView="select Question_Desc from Claim_Question where policy_number=?";
	String claimViewAnswerView="select answer from policy_details where policy_number=?";
	String claimViewUserView="SELECT  Claim_Reason,Accident_Location_street,Accident_City,Accident_state,Accident_Zip,claim_Type from Claim where claim_Number=?";
	String getPolicyClaimNumberQuery ="select claim_number,policy_number from claim where policy_number in(select policy_number from policy where account_number in(select account_number from accounts where user_name = ?))";
	String getUserUnderAgent = "select account_number from agent where user_name=?";
	String getUserName = "select user_name from Accounts where Account_Number=?";
	
}
