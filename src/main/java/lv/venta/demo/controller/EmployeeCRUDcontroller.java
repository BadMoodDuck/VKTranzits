package lv.venta.demo.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Employee;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Controller
public class EmployeeCRUDcontroller {

	@Autowired
	private IEmployeeCRUDservice employeeService;

	// localhost:8080/employee/addNew
	@GetMapping("/employee/addNew")
	public String getAddEmployee(Employee employee) {
		return "";
	}

	@PostMapping("/employee/addNew")
	public String postAddEmployee(@Valid Employee employee, BindingResult result) {
		// TODO nepabeigts
		if (!result.hasErrors()) {
			Employee newEmployee = employeeService.insertNewEmployee(employee.getName(), employee.getSurname(),
					employee.getPhone(), employee.getEmail(), employee.getDepartment(), employee.getPosition());
			return "";
		} else {
			return "";
		}
	}
}
