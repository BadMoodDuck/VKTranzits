package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.EmployeeCourse;
import lv.venta.demo.services.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService{

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
		return null;
	}

	@Override
	public Course insertNewCourse(CourseType coType, String title, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> getAllCoursesFromDepartmentByID(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
