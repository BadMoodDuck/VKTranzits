package lv.venta.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping("")
	public String getDefault() {
		return "redirect:/home";
	}
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("homepage");
		return "home";
	}
	
<<<<<<< HEAD
	@GetMapping("/course") // All Courses
	public String getAllCourses(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		return "course-all";
	}
	
	
	@GetMapping("/employee") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return "employee-all";
	}
	@GetMapping("/employee/addNew") // Add Employee
	public String getAddEmployee(Employee employee) {
		return "employee-add";
	}
	@PostMapping("/employee/addNew") // Papildinat ar Department 
	public String postAddEmployee(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) { return "error";}
		if (employeeService.insertNewEmployee(employee)) {
			return "redirect:/employee";
		}
			return "redirect:/employee";
	}
	
	
	
=======
>>>>>>> cf911d0a82199a81e6ee4ff96fd2adde38ad9ff5
}
