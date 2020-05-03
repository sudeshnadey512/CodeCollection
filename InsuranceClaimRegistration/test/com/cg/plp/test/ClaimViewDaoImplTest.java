package com.cg.plp.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cg.plp.daoImpl.ClaimViewDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;

public class ClaimViewDaoImplTest {
	ClaimViewDaoImpl claimView=null;
	@Before
	public void setUp() throws Exception {
		claimView = new ClaimViewDaoImpl();
	}

	@Test
	public void testviewClaimDetails() {
	List<Claim> claim=new ArrayList<>();
	
	try {
		claim=claimView.viewClaimDetails();
		assertNotNull(claim);
	} catch (InsuranceException e) {

	}
	}
	
	@Test
	public void testgetDetailsByClaimNumber() {
	List<Claim> claim=new ArrayList<>();
	Long claimNumber=4521635451l;
	try {
		claim=claimView.getDetailsByClaimNumber(claimNumber);
		assertNull(claim);
	} catch (InsuranceException e) {

	}
	}

}
