package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTypeTest {
	
	CourseType typeRight = new CourseType("Tehniskas apmacibas", true, "Tehnisko jautajumu risinasana");
	CourseType typeWrong  = new CourseType("1?", false, "-");

	@Test
	void CourseTypeAddTest() {
		//right
		assertEquals("Tehniskas apmacibas", typeRight.getTitle());
		assertEquals(true, typeRight.getIsObligatory());
		assertEquals("Tehnisko jautajumu risinasana", typeRight.getDescription());
		
		//wrong

	}

}
