package com.cg.plp.dao;

import java.util.List;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;


public interface ReportGenerationDetailedViewDao {

	List<Claim> selectDetailedView() throws InsuranceException;

}
