package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.demo.services.ICourseService;

@Controller
public class CourseController {

	@Autowired
	private ICourseService courseService;
	
	@GetMapping("/course") // All Courses
	public String getAllCourses(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		return "course-all";
	}
}
