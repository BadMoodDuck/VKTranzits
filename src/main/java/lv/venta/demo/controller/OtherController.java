package lv.venta.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
import lv.venta.demo.services.IDepartmentCRUDService;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Controller
public class OtherController {
	
	@Autowired
	private IDepartmentCRUDService departmentService;
	
	@GetMapping("/departments") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("departments", departmentService.getAllDepartments());
		return "department-all";
	}
	
	@GetMapping("/department/{id}/employees") // All Employees of department
	public String getAllEmployeesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("employee", departmentService.getAllEmployeesFromDepartment(id));
		return "department-employee-all";
	}
	
	@GetMapping("/department/{id}/courses") // All Courses of department
	public String getAllCoursesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("coruses", departmentService.getAllCoursesFromDepartment(id));
		return "department-course-all";
	}
	
	@GetMapping("/department/addNew") // TODO NEEDS Company
	public String getAdddepartment(Department department) {
		return "course-add";
	}

	@PostMapping("/department/addNew") 
	public String postAdddepartment(@Valid Department department, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "error";
		} else {
			departmentService.insertNewDepartment(department);
			return "redirect:/departments";
		}
	}
	

}