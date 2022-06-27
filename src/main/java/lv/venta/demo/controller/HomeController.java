package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.demo.services.ICourseService;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Controller
public class HomeController {
	
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IEmployeeCRUDservice employeeService;
	
	@GetMapping("")
	public String getDefault() {
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("homepage");
		return "home";
	}
	
	@GetMapping("/course")
	public String getAllCourses(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		return "course-all";
	}
	
	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return "employee-all";
	}
}
