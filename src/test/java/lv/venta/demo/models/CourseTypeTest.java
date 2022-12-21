package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTypeTest {
	
	CourseType typeRight = new CourseType("Darba drosiba", true, "Viss vajadzigais par darba drosibu");

	@Test
	void CourseTypeCreationTest() {
		//right
		assertEquals("Darba drosiba", typeRight.getTitle());
		assertEquals(true, typeRight.getIsObligatory());
		assertEquals("Viss vajadzigais par darba drosibu", typeRight.getDescription());
	}

}
