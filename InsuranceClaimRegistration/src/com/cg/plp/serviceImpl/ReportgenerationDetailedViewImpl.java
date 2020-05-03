package com.cg.plp.serviceImpl;

import java.util.List;

import com.cg.plp.dao.ReportGenerationDetailedViewDao;
import com.cg.plp.daoImpl.ReportGenerationDetailedViewDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;

import com.cg.plp.service.ReportGenerationDetailedView;

public class ReportgenerationDetailedViewImpl implements ReportGenerationDetailedView {

	ReportGenerationDetailedViewDao detailedView=new ReportGenerationDetailedViewDaoImpl();
	@Override
	public List<Claim> selectDetailedView() throws InsuranceException {
		
		return detailedView.selectDetailedView();
	}

}
