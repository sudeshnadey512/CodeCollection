package com.cg.plp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.cg.plp.daoImpl.LogInDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.UserRole;

public class LogInDaoImplTest {
	LogInDaoImpl loginImpl=new LogInDaoImpl();

	@Before
	public void setUp() throws Exception {
		loginImpl = new LogInDaoImpl();
	}
	@After
	public void tearDown() throws Exception {
		loginImpl=null;
	}
	
	@Test
	public void testLogInValidate() {
		UserRole module=new UserRole();
		module.setUsername("pbharti");
		module.setPassword("pb@789");
		
		
		try {
			String result;
			result=loginImpl.logInValidate(module.getUsername(), module.getPassword());
			
			assertNotNull(result);
		
			
		}catch(InsuranceException e) {
			
		}
	}
	@Test
	public void testLogInValidateNew() {
		UserRole module=new UserRole();
		module.setUsername("anjali");
		module.setPassword("Ah@78");
		
		String roleCode;
		try {
			roleCode=loginImpl.logInValidate(module.getUsername(), module.getPassword());
			assertNotNull(roleCode);
			
		}catch(InsuranceException e) {
			
		}
	}

}
