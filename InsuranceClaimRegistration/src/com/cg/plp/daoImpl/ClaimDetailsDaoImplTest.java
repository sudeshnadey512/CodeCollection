package com.cg.plp.daoImpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.ClaimQuestion;

public class ClaimDetailsDaoImplTest {
	ClaimDetailsDaoImpl claimDetails=null;
	@Before
	public void setUp() throws Exception {
		claimDetails=new ClaimDetailsDaoImpl();
	}
	@After
	public void tearDown() throws Exception {
		claimDetails=null;
	}
	@Test
	public void testClaimQuestionsView() {
		Long policyNumber=(long) 1236547896;
		List<ClaimQuestion> claimQuestion=new ArrayList<>();
		ClaimQuestion claimQuestionObj=new ClaimQuestion();
		claimQuestionObj.setPolicyNumber(policyNumber);
		try {
			claimQuestion=claimDetails.claimQuestionsView();
			
			assertNull(claimQuestion);
		}catch(InsuranceException e) {
			
		}
		
	}

	@Test
	public void testClaimAnswerView() {
		
	}

}
