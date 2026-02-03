package com.learning.dev.spring.myfirstapp.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dev.spring.myfirstapp.entity.Employee;
import com.learning.dev.spring.myfirstapp.service.EmployeeService;

import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	private JsonMapper jsonMapper;
	

	public EmployeeRestController(EmployeeService employeeService,JsonMapper jsonMapper) {
		
		this.employeeService = employeeService;
		this.jsonMapper = jsonMapper;
	}
	
	
	
	@GetMapping("employees")
	
	public List<Employee> fetchAll(){
		
		return employeeService.findAll();
	}
	
	
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }
    
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmp) {
    	
    	Employee request = new Employee();
    	
//    	request.setFirstName("Preab");
//    	request.setLastName("Sovat");
//    	request.setEmail("sovat@gmail.com");
    	
    	request.setFirstName(theEmp.getFirstName());
    	request.setLastName(theEmp.getLastName());
    	request.setEmail(theEmp.getEmail());
    	
    	Employee resEmp = employeeService.save(request);
    	
    	return resEmp;
    }
    
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmp) {
		
		/*
		 * Employee request = new Employee();
		 * 
		 * request.setId(theEmp.getId()); request.setFirstName(theEmp.getFirstName());
		 * request.setLastName(theEmp.getLastName());
		 * request.setEmail(theEmp.getEmail());
		 */
		
		Employee resEmp = employeeService.save(theEmp);
		
		return resEmp;
	}
    
    
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, 
    		@RequestBody Map<String, Object> patchPayLoad) {
	
	// Step 1: Retrieve the existing employee from database
	Employee tempEmp = employeeService.findById(employeeId);
	
	  if (tempEmp == null) {
		  
          throw new RuntimeException("Employee id not found - " + employeeId);
      }
	
	// Step 2: Security check - prevent ID modifications
	  
	  if(patchPayLoad.containsKey("id")) {
		  
		  throw new RuntimeException("Employee id cannot be modified. Remove 'id' from request body.");
	  }
    	  
	  Employee pathedEmp  = jsonMapper.updateValue(tempEmp, patchPayLoad);
	  
	// Step 4: Save the updated employee to database and return it
      Employee dbEmployee = employeeService.save(pathedEmp);
    	  
    return dbEmployee;
    }
    
    @DeleteMapping("/employees")
    public void deleteEmployee(@PathVariable int employeeId) {
    	
		Employee tempEmp = employeeService.findById(employeeId);
		
		if (tempEmp == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
    }
  
}
