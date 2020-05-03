package com.cg.plp.serviceImpl;

import java.util.List;

import com.cg.plp.dao.ReportGenerationQuickViewDao;
import com.cg.plp.daoImpl.ReportGenerationQuickViewDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;


import com.cg.plp.service.ReportGenerationQuickView;

public class ReportGenerationQuickViewImpl implements ReportGenerationQuickView {

	ReportGenerationQuickViewDao quickViewDao=new  ReportGenerationQuickViewDaoImpl();

	@Override
	public List<Claim> selectQuickView() throws InsuranceException{
		
		return quickViewDao.selectQuickView();
	}

}
