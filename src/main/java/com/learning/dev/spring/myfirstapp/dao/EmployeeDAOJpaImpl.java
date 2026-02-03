package com.learning.dev.spring.myfirstapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.dev.spring.myfirstapp.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
	
	private EntityManager entityManager;
	
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	@Override
	public List<Employee> findAll() {
		
	TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);
		
		return theQuery.getResultList();
	}


	@Override
	public Employee findById(int id) {
		
		Employee theEmp = entityManager.find(Employee.class, id);
		
		return theEmp;
	}


	@Override
	public Employee save(Employee emp) {
	
		Employee dbStudent = entityManager.merge(emp);
		
		return dbStudent;
	}


	@Override
	public void deleteById(int theId) {

		Employee theEmp = entityManager.find(Employee.class, theId);
		
		entityManager.remove(theEmp);
		
	}
}
