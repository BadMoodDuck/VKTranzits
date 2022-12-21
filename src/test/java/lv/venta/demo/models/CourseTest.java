package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTest {
	
	CourseType cty = new CourseType("Ugunsdrosiba", false, "Viss vajadzigais par ugunsdrosiba");
	
	Course courseRight = new Course(cty,"1.Ugunsdrosibas ievads","Viss galvenais par uguni");

	@Test
	void courseCreationTest() {
		//right
		assertEquals(cty, courseRight.getCoType());
		assertEquals("1.Ugunsdrosibas ievads", courseRight.getTitle());
		assertEquals("Viss galvenais par uguni", courseRight.getDescription());
	}
	
	@Test
	void courseTypeRemovalTest() {
		courseRight.removeCourseType();
		
		assertNull(courseRight.getCoType());
	}

}
