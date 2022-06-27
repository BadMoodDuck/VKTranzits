package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.demo.services.ICourseService;

@Controller
public class CourseController {

	@Autowired
	private ICourseService courseService;

	
	@GetMapping("/course") // All Courses
	public String getAllCourse(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		return "course-all";
	}

	// localhost:8080/course/showAll
	@GetMapping("/course/showAll")
	public String getAllCourses(Model model) {
		model.addAttribute("Course", courseService.selectAllCourses());
		return "course-all";
	}

	// localhost:8080/department/{id}/showAllCourses
	@GetMapping("/department/{id}/showAllCourses")
	public String getAllDepartmentCourses(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("Course", courseService.getAllCoursesFromDepartmentByID(id));
		return "course-all";
	}

	// localhost:8080/course/delete/{id}
	@GetMapping("/course/delete/{id}")
	public String getDeleteCourseById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("Course", courseService.deleteCourseById(id));
		return "course-all";
	}
}
