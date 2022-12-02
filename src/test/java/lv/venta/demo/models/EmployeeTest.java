package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class EmployeeTest {
	
	Position pos = new Position();
	
	Department dep = new Department();
	
	Employee employee = new Employee("Janis", "Berzins", 20134567, "random@gmail.com", "Password", dep, pos);
	
	CourseType type = new CourseType("Darba macibas", true, "Obligatie kursi");
	
	Course course = new Course(type, "Drosiba", "Viss par drosibu darba vieta");
	
	
	EmployeeCourse emCourse = new EmployeeCourse(employee, null, 0);

	@Test
	void removeEmployeeCourseFromEmCoursesTest() {
		
		employee.addEmCourse(emCourse);
		
		assertNotNull(Arrays.asList(employee.getEmCourses().contains(emCourse)));
		
		employee.removeEmCourse(emCourse);
		
		assertFalse(employee.getEmCourses().contains(emCourse));
		
	}

}
