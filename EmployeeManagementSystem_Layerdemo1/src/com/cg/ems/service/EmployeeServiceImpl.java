package com.cg.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.ems.dao.EmployeeDAO;
import com.cg.ems.dao.EmployeeDaoImpl;
import com.cg.ems.exceptions.EMSException;
import com.cg.ems.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDAO empDao = new EmployeeDaoImpl();
	
	@Override
	public boolean validateFields(Employee employee) throws EMSException {

		boolean validateFlag = false;

		List<String> list = new ArrayList<>();

		if (!validateId(employee.getId())) {
			list.add("id should contain exactly 4 digits \n");
		}
		if (!validateName(employee.getName())) {
			list.add("name should start with capital and length should be 5 to 10");
		}
		if (!validateSalary(employee.getSalary())) {
			list.add("salary should not be less than 10k");
		}

		if (!list.isEmpty()) {
			throw new EMSException(list + "");
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public boolean validateId(Integer id) {
		String idRegEx = "\\d{4}";
		return Pattern.matches(idRegEx, Integer.toString(id));

	}

	public boolean validateName(String name) {
		String nameRegEx = "[A-Z]{1}[a-zA-Z]{4,9}";
		return Pattern.matches(nameRegEx, name);
	}

	public boolean validateSalary(Double salary) {

		boolean salaryFlag = false;
		if (salary > 10000) {
			salaryFlag = true;
		}
		return salaryFlag;
	}

	@Override
	public String insertEmployee(Employee employee) throws EMSException {
		return empDao.insertEmployee(employee);
	}

}





