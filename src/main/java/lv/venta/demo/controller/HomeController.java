package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.demo.services.ICourseService;

@Controller
public class HomeController {
	
	@Autowired
	private ICourseService courseService;
	
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("homepage");
		return "home";
	}
	
	@GetMapping("/course")
	public String getAllEmployees(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		System.out.println("course");
		return "course-all";
	}
}
