package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.demo.services.IDepartmentCRUDService;

@Controller
public class OtherController {
	
	@Autowired
	IDepartmentCRUDService departmentService;
	
	@GetMapping("/departments") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("departments", departmentService.getAllDepartments());
		return "department-all";
	}
	
	@GetMapping("/department/{id}/employees") // All Employees
	public String getAllEmployeesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("employee", departmentService.getAllEmployeesFromDepartment(id));
		return "department-employee-all";
	}
	
	@GetMapping("/department/{id}/courses") // All Employees
	public String getAllCoursesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("coruses", departmentService.getAllCoursesFromDepartment(id));
		return "department-course-all";
	}
}
