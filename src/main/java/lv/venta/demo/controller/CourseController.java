package lv.venta.demo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Course;

import lv.venta.demo.msg.MQConfig;
import lv.venta.demo.msg.MyMessage;
import lv.venta.demo.services.ICourseService;
import lv.venta.demo.services.IOtherServices;

@Controller
public class CourseController {

	@Autowired
	private ICourseService courseService;

	@Autowired 
	private IOtherServices otherService;

	@Autowired
	private RabbitTemplate template;


	@GetMapping("/courses") // All Courses
	public String getAllCourses(Model model) {
		return getPageCourses(model, 1);
	}

	@GetMapping("/courses/{pageNr}") // All Employees
	public String getPageCourses(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Course> page = courseService.getPageList(currentPage);
		model.addAttribute("course", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		return "course-all";
	}

//	// localhost:8080/course/showAll
//	@GetMapping("/course/showAll")
//	public String getAllCourses(Model model) {
//		model.addAttribute("course", courseService.selectAllCourses());
//		return "course-all";
//	}

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
			return "redirect:/courses";
		}
	}

	@GetMapping("/course/{id}")
	public String getOneCourse(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("course", courseService.getCourseById(id));
		return "course-one";
	}

	// localhost:8080/department/{id}/showAllCourses
//	@GetMapping("/department/{id}/showAllCourses")
//	public String getAllDepartmentCourses(Model model, @PathVariable(name = "id") int id) {
//		model.addAttribute("course", courseService.getAllCoursesFromDepartmentByID(id));
//		return "course-all";
//	}

	// localhost:8080/course/delete/{id}
	@Transactional
	@GetMapping("/course/delete/{id}")
	public String getDeleteCourseById(@PathVariable(name = "id") int id) {
		courseService.deleteCourseById(id);
		return "redirect:/courses";
	}
	
	// localhost:8080/course/update/{id}
	@GetMapping("/course/update/{id}")
	public String getUpdateCourseById(@PathVariable(name="id") int id, Model model) throws Exception {
		try {
			model.addAttribute("course", courseService.readCourseById(id));
			model.addAttribute("courseTypes", otherService.getAllCourseTypes());
			return "course-update";
		} catch (Exception e){
			throw new Exception("can't find course");
		}
		
	}

	// localhost:8080/course/update/{id}
	@PostMapping("/course/update/{id}")
	public String postUpdateCourseById(@PathVariable(name = "id") int id, Course course, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if (courseService.updateExistingCourseById(id, course)) {
				return "redirect:/courses";
			} else {
				throw new Exception("can't update");
			}
		} else {
			return "course-update";
		}
	}
}
