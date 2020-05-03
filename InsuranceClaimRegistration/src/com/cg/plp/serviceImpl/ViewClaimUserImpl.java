package com.cg.plp.serviceImpl;

import java.util.List;

import com.cg.plp.dao.ViewClaimUserDao;
import com.cg.plp.daoImpl.ViewClaimUserDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.service.ViewClaimUser;

public class ViewClaimUserImpl implements ViewClaimUser {
	ViewClaimUserDao viewClaimUserDao=new ViewClaimUserDaoImpl();
	@Override
	public List<Claim> viewClaimByPolicyNumber(Long claimNumber) throws InsuranceException {
		
		return viewClaimUserDao.viewClaimByPolicyNumber(claimNumber);
	}
	@Override
	public List<ClaimQuestion> claimQuestionView(Long policyNumber1) throws InsuranceException {
		
		return viewClaimUserDao.claimQuestionView(policyNumber1);
	}
	@Override
	public List<PolicyDetails> claimAnswer(Long policyNumber1) throws InsuranceException {
	
		return viewClaimUserDao.claimAnswer(policyNumber1);
	}

}
