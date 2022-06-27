package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.EmployeeCourse;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.services.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService{

	@Autowired
	private ICourseRepo courseRepo;
	
	//TODO pabeigt funkcijas ar visam parbaudem
	@Override
	public Course getCourseById(int courseId) {
		if (courseRepo.existsById(courseId)) {
			return courseRepo.findById(courseId).get();
		}
		return null;
	}

	@Override
	public Course getCourseByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeeCourse> getAllEmployeeCoursesByEmployeeId(int employeeId) {
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
		return (ArrayList<Course>) courseRepo.findAll();
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

	@Override
	public Course updateExistingCourseById(int courseId, CourseType coType, String title, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> deleteCourseById(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

}
