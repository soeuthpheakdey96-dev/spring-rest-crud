package com.learning.dev.spring.myfirstapp.service;

import java.util.List;

import com.learning.dev.spring.myfirstapp.entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	Employee findById(int id);
	
	Employee save(Employee emp);
	
	void deleteById(int theId);
	
}

