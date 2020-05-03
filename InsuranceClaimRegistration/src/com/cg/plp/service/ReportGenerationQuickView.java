package com.cg.plp.service;

import java.util.List;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;


public interface ReportGenerationQuickView {
	

	List<Claim> selectQuickView()throws InsuranceException;

}
