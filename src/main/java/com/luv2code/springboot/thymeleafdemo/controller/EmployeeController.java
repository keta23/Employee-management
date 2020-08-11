package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//get employee from db
		List<Employee> theemployee=employeeservice.findAll();
		//add to spring model so that thymeleaf can use it 
		theModel.addAttribute("employees", theemployee);
		
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		
		//create model attribute to bind it with form data so that thymeleaf form can use properties
		Employee theemEmployee=new Employee();
		
		theModel.addAttribute("employee", theemEmployee);
		
		return "employees/employe-form";
		
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee")Employee theEmployee)
	{
		//save the employee
		employeeservice.save(theEmployee);
		//use redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId")int theId,Model theModel)
	{
		//find employee by id which is clicked for update
		Employee theemployee=employeeservice.findById(theId);
		
		//add that employee into model so that thymeleaf can use it to prepopulate
		theModel.addAttribute("employee", theemployee);
		//redirect to employe-form
		return "employees/employe-form";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId")int theId,Model theModel)
	{
		employeeservice.deleteById(theId);
		
		return "redirect:/employees/list";
	}
	
	
}









