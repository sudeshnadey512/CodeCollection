package com.cg.plp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import com.cg.plp.daoImpl.ViewClaimUserDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;


public class ViewClaimUserDaoImplTest {
	ViewClaimUserDaoImpl viewClaim=null;
	@Before
	public void setUp() throws Exception {
		viewClaim=new ViewClaimUserDaoImpl();
	}
	

	@After
	public void tearDown() throws Exception {
		viewClaim=null;
	}
	
	@Test
	public void  viewClaimByPolicyNumber1(){
		Long policyNumber1=4521635241l;
	List<Claim> viewClaimbyPolicyNumber=new ArrayList<>();
	try {
		viewClaimbyPolicyNumber=viewClaim.viewClaimByPolicyNumber(policyNumber1);
		assertNotNull(viewClaimbyPolicyNumber);
	} catch (InsuranceException e) {

	}
	}

}
