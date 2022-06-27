package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.EmployeeCourse;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.services.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseRepo courseRepo;

	// TODO pabeigt funkcijas ar visam parbaudem
	@Override
	public Course getCourseById(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourseByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeeCourse> getAllEmployeeCoursesbyEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> getAllCoursesByTypeId(int coTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> selectAllCourses() {
		// TODO Auto-generated method stub
		ArrayList<Course> result = (ArrayList<Course>) courseRepo.findAll();
		return result;
	}

	@Override
	public Course insertNewCourse(CourseType coType, String title, String description) {
		Course result = new Course(coType, title, description);
		if (!courseRepo.existsByTitleIgnoreCase(title)) {
			courseRepo.save(result);
			return result;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> getAllCoursesFromDepartmentByID(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course updateExistingCourseById(int courseId, CourseType coType, String title, String description) {
		// TODO Auto-generated method stub
		Course result = new Course();
		if (courseRepo.existsByIdCou(courseId)) {
			result = courseRepo.findByIdCou(courseId);
		}
		result = new Course(coType, title, description);
		return result;
	}

	@Override
	public ArrayList<Course> deleteCourseById(int courseId) {
		// TODO Auto-generated method stub
		if (courseRepo.existsByIdCou(courseId)) {
			courseRepo.deleteByIdCou(courseId);
		}
		ArrayList<Course> result = selectAllCourses();
		return result;
	}

}
