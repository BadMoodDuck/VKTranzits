package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeCourseTest {
	
	Employee emp = new Employee();
	
	CourseType cotype = new CourseType("Ugunsdrosiba", false, "Viss vajadzigais par ugunsdrosibu");
	Course course = new Course(cotype, "1.Ugunsdrosibas ievads", "Viss galvenais par uguni");
	
	EmployeeCourse emCoRight = new EmployeeCourse (emp, course, 32);
	EmployeeCourse emCoWrong = new EmployeeCourse (emp, course, -1);

	@Test
	void employeeCourseCreation() {
		//right
		assertEquals(emp, emCoRight.getEmployee());
		assertEquals(course, emCoRight.getCourse());
		assertEquals(32, emCoRight.getValuePr());
	}

}
