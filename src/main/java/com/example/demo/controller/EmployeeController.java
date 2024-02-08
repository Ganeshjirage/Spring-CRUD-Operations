package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository employeeRepository;

	// --------------------------------Add or save the data into database
//	@PostMapping("/saveEmployee")
//	public Employee saveData(@RequestBody Employee employee) {
//		employeeRepository.save(employee);
//		return employee;
//	}

	// --------------------------------Add or save the data into database
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.saveEmployee(employee);
		return ResponseEntity.ok().body(emp);
	}

	// ---------------------------------Read the data from database
	@GetMapping("/getEmployeeById/{id}")
	public Employee getEmployeeData(@PathVariable int id) {
		Employee employee = employeeRepository.findById(id).get();
		return employee;
	}

	// ---------------------------------Read the data from database
	@GetMapping("/getAllEmployee")
	public List<Employee> getAll() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	// ---------------------------------Delete data from database
	@DeleteMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable int id) {
		Employee employee = employeeRepository.findById(id).get();
		if (employee != null)
			employeeRepository.delete(employee);
		return "Deleted Successfully";
	}

	// ---------------------------------Update data into database
	@PutMapping("/updateData")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}

	// ---------------------------------Update data into database
	// Note: This is additional @PutMapping method to update the data into database
	// according to id
	@PutMapping("/updateData/{id}")
	public Employee updateEmployee(@PathVariable int id) {
		Employee employee = employeeRepository.findById(id).get();
		if (employee != null)
			employeeRepository.save(employee);
		return employee;
	}

}
