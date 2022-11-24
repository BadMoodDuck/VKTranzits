package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
<<<<<<< HEAD
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
=======
import lv.venta.demo.models.Department;
>>>>>>> 03e5bf7a73f7983721924a652ccb19a1a7ce2e99
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.services.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseRepo courseRepo;
	@Autowired
	private IDepartmentRepo departmentRepo;
	
	//TODO pabeigt funkcijas ar visam parbaudem
	@Override
	public Course getCourseById(int courseId) {
		if (courseRepo.existsById(courseId)) {
			return courseRepo.findById(courseId).get();
		}
		return null;
	}

	@Override
	public ArrayList<Course> selectAllCourses() {
		return (ArrayList<Course>) courseRepo.findAll();
	}

	@Override
	public boolean insertNewCourse(Course course) {
		//if (!courseRepo.existsByTitleIgnoreCase(title)) { TODO MAKE VALIDATION THAT COURSE ALREADY DOESNT EXIST
			courseRepo.save(course);
		//}
		return true;
	}

	@Override
	public boolean updateExistingCourseById(int courseId, Course course) {
		
		Course result = new Course();
		if (courseRepo.existsById(courseId)) {
			result = courseRepo.findById(courseId).get();
			result.setCoType(course.getCoType());
			result.setTitle(course.getTitle());
			result.setDescription(course.getDescription());
			courseRepo.save(result);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Course readCourseById(int id) throws Exception {
		
		if(courseRepo.existsById(id))
		{
			Course course = courseRepo.findByIdCou(id);
			return course;
		}
		
		throw new Exception("Course doesn't exist");
		
	}

	
	@Override
	public boolean deleteCourseById(int courseId) {
		if (courseRepo.existsById(courseId)) {
			Course course = courseRepo.findById(courseId).get();
			
			ArrayList<Department> allDepForThisCourse = departmentRepo.findByCoursesIdCou(courseId);
			for (Department department : allDepForThisCourse) {
				department.removeCourse(course);
				departmentRepo.save(department);
			}
			
//			ArrayList<Employee> allEmpForThisCourse = employeeRepo.findByCoursesIdCou(courseId);
//			for (Employee employee : allEmpForThisCourse) {
//				employee.removeCourse(course);
//				employeeRepo.save(employee);
//			} 
			courseRepo.deleteById(courseId);
			return true;
		}
		return false;
	}
 
	@Override
	public Page<Course> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr - 1, 10);
		return courseRepo.findAll(pageable);
	}
	

	@Override
	public Page<Course> getPageListWithSort(int pageNr, String field, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(field).ascending(): Sort.by(field).descending();
		
		Pageable pageable = PageRequest.of(pageNr - 1, 10, sort);
		return courseRepo.findAll(pageable);
	
	}


}
