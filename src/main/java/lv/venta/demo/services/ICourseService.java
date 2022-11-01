package lv.venta.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.EmployeeCourse;

public interface ICourseService {

	// Kursa meklēšana pēc ID
	public abstract Course getCourseById(int courseId);
	
	// visi kursi
	public abstract ArrayList<Course> selectAllCourses();

	// Izveidot jaunu kursu
	public abstract boolean insertNewCourse(Course course);

	// Kursa atjaunošanas pēc ID funkcija
	public abstract Course updateExistingCourseById(int courseId, CourseType coType, String title, String description);

	// Kursa dzēšanas pēc ID funkcija
	public abstract boolean deleteCourseById(int courseId);
	
	Page<Course> getPageList(int pageNr);

}
