package com.cg.plp.serviceImpl;

import java.util.List;

import com.cg.plp.dao.ClaimViewDao;
import com.cg.plp.daoImpl.ClaimViewDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.service.ClaimViewService;

public class ClaimViewServiceImpl implements ClaimViewService {
	ClaimViewDao viewClaimDao=new ClaimViewDaoImpl();
	@Override
	public List<Claim> viewClaimDetails() throws InsuranceException {
		
		return viewClaimDao.viewClaimDetails();
	}

	@Override
	public List<Claim> getDetailsByClaimNumber(Long claimNumber1) throws InsuranceException {
	
		return viewClaimDao.getDetailsByClaimNumber(claimNumber1);
	}

	@Override
	public List<ClaimQuestion> claimDetailsView(Long policyNumber1) throws InsuranceException {
		
		return viewClaimDao.claimQuestionsView(policyNumber1);
	}

	@Override
	public List<PolicyDetails> claimAnswer(Long policyNumber1) throws InsuranceException {
		
		return viewClaimDao.claimAnswer(policyNumber1);
	}

}
