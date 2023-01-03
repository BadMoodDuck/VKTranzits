package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@DataJpaTest
class EmployeeTest {
/*
	Position pos = new Position();
	Department dep = new Department();
	MyUser user1 = new MyUser("admins" passwordEncoder2().encode("admins"));
	// right
	Employee employeeRight = new Employee("Janis", "Berzins", 20134567, "random@gmail.com", user1, dep, pos);

	CourseType type = new CourseType("Darba macibas", true, "Obligatie kursi");
	Course course = new Course(type, "Drosiba", "Viss par drosibu darba vieta");
	EmployeeCourse emCourse = new EmployeeCourse(employeeRight, null, 0);

	@Test
	void removeEmployeeCourseFromEmCoursesTest() {
		//Mockito.when(pa).then(new BCryptPasswordEncoder());
		employeeRight.addEmCourse(emCourse);

		assertNotNull(Arrays.asList(employeeRight.getEmCourses().contains(emCourse)));

		employeeRight.removeEmCourse(emCourse);

		assertFalse(employeeRight.getEmCourses().contains(emCourse));

	}

	@Test
	void EmployeeCreationTest() {
		assertEquals("Janis", employeeRight.getName());
		assertEquals("Berzins", employeeRight.getSurname());
		assertEquals(20134567, employeeRight.getPhone());
		assertEquals("random@gmail.com", employeeRight.getEmail());
		assertEquals(user1, employeeRight.getUser());
	}
*/

	
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

