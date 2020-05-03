package com.cg.plp.serviceImpl;

import java.util.List;

import com.cg.plp.dao.ValidatePolicyNumberServiceDao;
import com.cg.plp.daoImpl.ValidatePolicyNumberServiceImplDao;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.service.ValidatePolicyNumberService;

public class ValidatePolicyNumberServiceImpl implements ValidatePolicyNumberService {
	ValidatePolicyNumberServiceDao validatePolicyDao=new ValidatePolicyNumberServiceImplDao();
	@Override
	public List<PolicyDetails> validatePolicyNumber() throws InsuranceException {
		
		return validatePolicyDao.validatePolicyNumber();
	}

}
