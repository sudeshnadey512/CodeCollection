package com.cg.ems.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.ems.exceptions.EMSException;
import com.cg.ems.model.Employee;

public class EmployeeDaoImpl implements EmployeeDAO {

	List<Employee> list = new ArrayList<>();

	@Override
	public String insertEmployee(Employee employee) throws EMSException {

		String message = "";

		list.add(employee);

		if (!list.isEmpty()) {
			message = "employee added";
		}

		return message;
	}

}
