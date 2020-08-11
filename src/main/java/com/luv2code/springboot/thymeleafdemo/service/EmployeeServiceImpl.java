package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	//inject DAO
	@Autowired
	private EmployeeRepository employeerepository;

	@Override
	
	public List<Employee> findAll() {
		
		return employeerepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		
		Optional<Employee> result = employeerepository.findById(theId);
		Employee theemployee=null;
		if(result.isPresent())
			theemployee=result.get();
		else
		{
			throw new RuntimeException("can't find............");
		}
		return theemployee;
		
	}

	@Override
	public void save(Employee theemployee) {
		
		employeerepository.save(theemployee);

	}

	@Override
	public void deleteById(int theId) {
		
		employeerepository.deleteById(theId);

	}

}
