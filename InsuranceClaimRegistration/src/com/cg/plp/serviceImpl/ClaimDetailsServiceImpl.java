package com.cg.plp.serviceImpl;

import java.util.List;

import com.cg.plp.dao.ClaimDetailsDao;
import com.cg.plp.daoImpl.ClaimDetailsDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.service.ClaimDetailsService;

public class ClaimDetailsServiceImpl implements ClaimDetailsService {
	ClaimDetailsDao claimDetailsDao = new ClaimDetailsDaoImpl();
	@Override
	public List<ClaimQuestion> claimQuestionsView() throws InsuranceException {
		
		return claimDetailsDao.claimQuestionsView();
	}
	@Override
	public List<PolicyDetails> claimAnswerView() throws InsuranceException {
		return claimDetailsDao.claimAnswerView();
	}

}
