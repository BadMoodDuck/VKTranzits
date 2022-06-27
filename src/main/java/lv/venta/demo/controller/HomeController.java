package lv.venta.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.services.impl.CourseServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private CourseServiceImpl courseService;
	
	@GetMapping("")
	public String getDefault() {
		return "redirect:/home";
	}
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("homepage");
		return "home";
	}
	
	@GetMapping("/course") // All Courses
	public String getAllCourses(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		return "course-all";
	}
	
	

}
