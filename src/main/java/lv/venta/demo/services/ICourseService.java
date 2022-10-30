package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.EmployeeCourse;

public interface ICourseService {

	// Kursa meklēšana pēc ID
	public abstract Course getCourseById(int courseId);

	// Kursa meklēšana pēc nosaukuma
	public abstract Course getCourseByTitle(String title);

	// Darbinieka kursi pēc darbinieka ID
	public abstract ArrayList<EmployeeCourse> getAllEmployeeCoursesByEmployeeId(int employeeId);

	// visi kursi pēc tipa ID
	public abstract ArrayList<Course> getAllCoursesByTypeId(int coTypeId);

	// visi kursi
	public abstract ArrayList<Course> selectAllCourses();

	// Izveidot jaunu kursu
	public abstract boolean insertNewCourse(Course course);

	// Struktūrvienības visu kursu attēlošanas funkcija
	public abstract ArrayList<Course> getAllCoursesFromDepartmentByID(int departmentId);

	// Kursa atjaunošanas pēc ID funkcija
	public abstract Course updateExistingCourseById(int courseId, CourseType coType, String title, String description);

	// Kursa dzēšanas pēc ID funkcija
	public abstract ArrayList<Course> deleteCourseById(int courseId);

}
