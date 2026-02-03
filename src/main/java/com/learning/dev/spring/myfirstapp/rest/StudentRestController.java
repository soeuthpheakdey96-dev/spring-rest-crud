package com.learning.dev.spring.myfirstapp.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dev.spring.myfirstapp.entity.Student;
import com.learning.dev.spring.myfirstapp.entity.StudentNotFoundException;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	
	private List<Student> theStudents;
	
	@PostConstruct
	public void loadData() {
		
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Pana","ma")); //index 0
		theStudents.add(new Student("John","Wick"));  //index 1
		theStudents.add(new Student("Keo","Seyha"));
		theStudents.add(new Student("Keo","Seyha"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudent(){
	
		return theStudents;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudentVariable(@PathVariable int studentId) {
		
		//return theStudents.get(studentId);
		
		  if ( (studentId >= theStudents.size()) || (studentId < 0)) {
			  
	            throw new StudentNotFoundException("Student id not found - " + studentId);
	        }

	        return theStudents.get(studentId);
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
