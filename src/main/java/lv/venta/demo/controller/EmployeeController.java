package lv.venta.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Employee;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeCRUDservice employeeService;
	

	@GetMapping("/employee") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return "employee-all";
	}
	@GetMapping("/employee/addNew") // Add Employee
	public String getAddEmployee(Employee employee) {
		return "employee-add";
	}
	@PostMapping("/employee/addNew")
	public String postAddEmployee(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) { return "error";}
		if (employeeService.insertNewEmployee(employee)) {
			return "redirect:/employee";
		}
			return "redirect:/employee";
	}
	
}
