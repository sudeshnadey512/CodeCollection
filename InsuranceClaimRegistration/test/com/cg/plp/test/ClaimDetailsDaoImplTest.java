package com.cg.plp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.plp.daoImpl.ClaimDetailsDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;

public class ClaimDetailsDaoImplTest {
	ClaimDetailsDaoImpl claimDetails = null;

	@Before
	public void setUp() throws Exception {
		claimDetails = new ClaimDetailsDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		claimDetails = null;
	}

	
	@Test
	public void testClaimQuestionsView() {
		
		List<ClaimQuestion> claimQuestion = new ArrayList<>();
		try {
			claimQuestion =claimDetails.claimQuestionsView();
			assertNull(claimQuestion);
		} catch (InsuranceException e) {

		}

	}
	

	@Test
	public void testClaimQuestionsView1() {
		List<ClaimQuestion> claimQuestion = new ArrayList<>();
	
		try {
			claimQuestion =claimDetails.claimQuestionsView();
			assertNotNull(claimQuestion);
		} catch (InsuranceException e) {

		}

	}
	
	@Test
	public void testClaimAnswerView() {
		List<PolicyDetails> claimAnswer = new ArrayList<>();
	
		try {
			claimAnswer =claimDetails.claimAnswerView();
			assertNull(claimAnswer);
			
		} catch (InsuranceException e) {

		}

	}

	@Test
	public void testClaimAnswerView1() {
		List<PolicyDetails> claimAnswer = new ArrayList<>();
	
		try {
			claimAnswer =claimDetails.claimAnswerView();
			assertNotNull(claimAnswer);
			
		} catch (InsuranceException e) {

		}

	}
	
}
