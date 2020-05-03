package com.cg.plp.test;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.plp.daoImpl.AgentDaoImpl;
import com.cg.plp.exception.InsuranceException;

public class AgentDaoImplTest {
	AgentDaoImpl agentDetails=null;
	@Before
	public void setUp() throws Exception {
		agentDetails = new AgentDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		agentDetails=null;
	}

	@Test
	public void viewUserUnderAgent() {
		List<Long> result=new ArrayList<>();
		try {
			
			result=agentDetails.viewUserUnderAgent("hi");
			System.out.println(result);
			assertNull(result);
		
			
		}catch(InsuranceException e) {
			
		}
	}
}		
	


