package com.cg.ems.service;

import com.cg.ems.exceptions.EMSException;
import com.cg.ems.model.Employee;

public interface EmployeeService {

	boolean validateFields(Employee employee)throws EMSException;

	String insertEmployee(Employee employee) throws EMSException;

}
