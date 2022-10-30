package lv.venta.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Employee;
import lv.venta.demo.services.IDepartmentCRUDService;
import lv.venta.demo.services.IEmployeeCRUDservice;
import lv.venta.demo.services.IOtherServices;

@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeCRUDservice employeeService;
	
	@Autowired 
	private IDepartmentCRUDService departmentsService;
	@Autowired
	private IOtherServices otherService;
	

	@GetMapping("/employee") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return "employee-all";
	}

	@GetMapping("/employee/addNew") // Add Employee
	public String getAddEmployee(Model model, Employee employee) {
		model.addAttribute("departments", departmentsService.getAllDepartments());
		model.addAttribute("positions", otherService.getAllPositions());
		return "employee-add";
	}
	
	@PostMapping("/employee/addNew") // Papildinat ar Department 
	public String postAddEmployee(@Valid Employee employee, BindingResult result) {
		System.out.println("Post employee "+ employee);
		if (result.hasErrors()) { 
			System.out.println(result); 
			System.out.println("ERROR : "+ employee);
			return "emplyee-add";
		} else {
			employeeService.insertNewEmployee(employee);
			System.out.println("SUCSESS  employee "+ employee);
			return "redirect:/employee";
		}
	}
	
	@GetMapping("/employee/{id}")
	public String getOneEmployee(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "employee-one";
	}
	
	

	// localhost:8080/department/{id}/showAllEmployees
	@GetMapping("/department/{id}/showAllEmployees")
	public String getAllEmployeesInDepartment(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("employee", employeeService.selectAllEmployeesFromDepartmentById(id));
		return "employee-all";
	}
}
