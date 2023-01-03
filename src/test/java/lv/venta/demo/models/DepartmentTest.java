package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DepartmentTest {
	
	CourseType type = new CourseType("Tehniskas apmacibas", true, "Tehnisko jautajumu risinasana");
	
	Course course = new Course (type, "Datorprogrammu apmaciba", "Uznemuma izmantojamu datorprogrammu izmantosana");
	
	Company company = new Company("Kompanija");
	
	Department depRight = new Department(company, "IT");

	@Test
	void removeCourseFromDepartmentTest() {
		depRight.addNewCourse(course);
	
		assertNotNull(Arrays.asList(depRight.getCourses().contains(course)));
		
		depRight.removeCourse(course);
		
		assertFalse(depRight.getCourses().contains(course));

	}
	
	@Test
	void DepartmentCreationTest() {
		//right
		assertEquals(company, depRight.getCompany());
		assertEquals("IT", depRight.getName());

	}

}
