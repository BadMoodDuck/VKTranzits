package lv.venta.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	

	@GetMapping("/employees") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return getPageEmployees(model, 1);
	}
	
	@GetMapping("/employees/{pageNr}") // All Employees
	public String getPageEmployees(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Employee> page = employeeService.getPageList(currentPage);
		model.addAttribute("employee", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
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

		if (result.hasErrors()) { 
			System.out.println(result); 
			return "emplyee-add";
		} else {
			employeeService.insertNewEmployee(employee);
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
