package lv.venta.demo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Course;
import lv.venta.demo.services.ICourseService;
import lv.venta.demo.services.IOtherServices;

@Controller
public class CourseController {

	@Autowired
	private ICourseService courseService;
	
	@Autowired IOtherServices otherService;

	
	@GetMapping("/course") // All Courses
	public String getAllCourse(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		return "course-all";
	}

	// localhost:8080/course/showAll
	@GetMapping("/course/showAll")
	public String getAllCourses(Model model) {
		model.addAttribute("course", courseService.selectAllCourses());
		return "course-all";
	}
	
	@GetMapping("/course/addNew")
	public String getAddCourses(Model model, Course course) {
		model.addAttribute("courseTypes", otherService.getAllCourseTypes());
		return "course-add";
	}
	@PostMapping("/course/addNew")
	public String postAddCourses(@Valid Course course, BindingResult result) {
		if (result.hasErrors()) { 
			System.out.println(result); 
			return "error";
		} else {
			courseService.insertNewCourse(course);
			return "redirect:/course";
		}
	}
	
	@GetMapping("/course/{id}")
	public String getOneCourse(Model model, @PathVariable(name = "id") int id) {
			model.addAttribute("course", courseService.getCourseById(id));
		return "course-one";
	}

	// localhost:8080/department/{id}/showAllCourses
	@GetMapping("/department/{id}/showAllCourses")
	public String getAllDepartmentCourses(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("course", courseService.getAllCoursesFromDepartmentByID(id));
		return "course-all";
	}

	// localhost:8080/course/delete/{id}
	@Transactional
	@GetMapping("/course/delete/{id}")
	public String getDeleteCourseById(@PathVariable(name = "id") int id) {
		courseService.deleteCourseById(id);
		return "redirect:/course";
	}
}
