package com.cg.plp.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.cg.plp.dao.ClaimHandlerDao;
import com.cg.plp.daoImpl.ClaimHandlerDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.Policy;
import com.cg.plp.service.ClaimHandlerService;

public class ClaimHandlerServiceImpl implements ClaimHandlerService {
	ClaimHandlerDao claimCreationDao = new ClaimHandlerDaoImpl();

	@Override
	public long getAccountNumber(String userName) throws InsuranceException {

		return claimCreationDao.getAccountNumber(userName);
	}

	@Override
	public List<Policy> getPolicyNumber(long accountNumber) throws InsuranceException {

		return claimCreationDao.getPolicyNumber(accountNumber);
	}

	@Override
	public List<ClaimQuestion> selectQuestion(long policyNumber) throws InsuranceException {
		
		return claimCreationDao.selectQuestion(policyNumber);
	}

	@Override
	public boolean validateZip(int accidentZip) throws InsuranceException {

		boolean accidentZipFlag = false;
		String accidentZipRegEx = "[1-9]{1}[0-9]{4}";
		accidentZipFlag = Pattern.matches(accidentZipRegEx, Integer.toString(accidentZip));
		if (accidentZipFlag == false) {

			throw new InsuranceException("Enter Exactly 5 Digits");
		}

		return accidentZipFlag;

	}

	@Override
	public boolean validateAccidentState(String accidentState) throws InsuranceException {
		boolean accidentStateFlag = false;
		String accidentStateRegEx = "[A-Z]{1}[a-zA-Z ]{1,14}";
		accidentStateFlag = Pattern.matches(accidentStateRegEx, accidentState);
		if (accidentStateFlag == false) {
			throw new InsuranceException(
					" Enter only character and 1st letter Capital ,Total length should be between 2 and 15");

		}
		return accidentStateFlag;
	}

	@Override
	public boolean validateAccidentCity(String accidentCity) throws InsuranceException {
		boolean accidentCityFlag = false;
		String accidentCityRegEx = "[A-Z]{1}[a-zA-Z ]{2,14}";
		accidentCityFlag = Pattern.matches(accidentCityRegEx, accidentCity);
		if (accidentCityFlag == false) {
			throw new InsuranceException(" 1st letter Capital ,Total length should be between 3 and 15");

		}
		return accidentCityFlag;
	}

	@Override
	public boolean validateAccidentLocation(String accidentLocation) throws InsuranceException {
		boolean accidentLocationFlag = false;
		String accidentLocationRegEx = "[A-Za-z]{1}[a-zA-Z0-9\\,-_ ]{3,39}";
		accidentLocationFlag = Pattern.matches(accidentLocationRegEx, accidentLocation);
		if (accidentLocationFlag == false) {
			throw new InsuranceException(
					"Please enter character or digits having length in between 4 to 39 starts with 1 capital letter and ',' and space is allowed");

		}
		return accidentLocationFlag;
	}

	@Override
	public boolean validateClaimReason(String claimReason) throws InsuranceException {
		boolean claimReasonFlag = false;
		String claimReasonRegEx = "[A-Z]{1}[a-zA-Z ]{3,29}";
		claimReasonFlag = Pattern.matches(claimReasonRegEx, claimReason);
		if (claimReasonFlag == false) {
			throw new InsuranceException(
					"Please enter only character having length in between 4 to 30 starts with 1 capital letter");

		}
		return claimReasonFlag;
	}

	@Override
	public long insertDetails(Claim handler) throws InsuranceException {

		return claimCreationDao.insertDetails(handler);
	}

	@Override
	public void insertAnswer(long policyNumber, int questionId, String answer) throws InsuranceException {
		claimCreationDao.insertAnswer(policyNumber, questionId, answer);

	}

	@Override
	public Map<Long, Long> getPolicyNumberClaimNumber(String userName) throws InsuranceException {
	
		return claimCreationDao.getPolicyNumberClaimNumber(userName);
	}

}
