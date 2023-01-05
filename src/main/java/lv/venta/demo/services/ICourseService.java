package lv.venta.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.EmployeeCourse;
import lv.venta.demo.models.Quiz;

public interface ICourseService {

	// Kursa meklēšana pēc ID
	public abstract Course getCourseById(int courseId);
	
	// visi kursi
	public abstract ArrayList<Course> selectAllCourses();

	// Izveidot jaunu kursu
	public abstract boolean insertNewCourse(Course course);

	// Kursa dzēšanas pēc ID funkcija
	public abstract boolean deleteCourseById(int courseId);
	
	Page<Course> getPageList(int pageNr);

	boolean updateExistingCourseById(int courseId, Course course);

	Course readCourseById(int id) throws Exception;

	Page<Course> getPageListWithSort(int pageNr, String field, String sortDir);
	
	Page<CourseType> getPageListWithSortCourseType(int pageNr);
	
	boolean insertNewCourseType(CourseType courseType);

	boolean updateExistingCourseTypeById(int courseTypeId, CourseType courseType);
	
	CourseType getCourseTypeById(int courseTypeId);
	
	boolean deleteCourseTypeById(int courseTypeId);

	ArrayList<Course> getAllCourses();

}
