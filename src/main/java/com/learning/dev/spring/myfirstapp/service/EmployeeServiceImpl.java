package com.learning.dev.spring.myfirstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dev.spring.myfirstapp.dao.EmployeeDAO;
import com.learning.dev.spring.myfirstapp.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
	private EmployeeDAO employeeDAO;
	
    @Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}


	@Override
	public List<Employee> findAll() {

		return employeeDAO.findAll();
	}


	@Override
	public Employee findById(int id) {
		 
		return employeeDAO.findById(id);
	}


	@Transactional
	@Override
	public Employee save(Employee emp) {
		
		return employeeDAO.save(emp);
	}

	@Transactional
	@Override
	public void deleteById(int theId) {
		
		employeeDAO.deleteById(theId);
	}
	
}
