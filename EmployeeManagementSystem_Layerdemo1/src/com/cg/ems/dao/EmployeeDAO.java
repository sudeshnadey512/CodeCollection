package com.cg.ems.dao;

import com.cg.ems.exceptions.EMSException;
import com.cg.ems.model.Employee;

public interface EmployeeDAO {

	String insertEmployee(Employee employee) throws EMSException;

}
