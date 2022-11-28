package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTest {
	
	CourseType type = new CourseType("Tehniskas apmacibas", true, "Tehnisko jautajumu risinasana");
	
	Course courseRight = new Course(type, "Datorprogrammu apmaciba", "Uznemuma izmantojamu datorprogrammu izmantosana");
	Course courseWrong = new Course(type, "f4+_-,<", "0");

	@Test
	void CourseAddTest() {
		
		//right
		assertEquals(type, courseRight.getCoType());
		assertEquals("Datorprogrammu apmaciba", courseRight.getTitle());
		assertEquals("Uznemuma izmantojamu datorprogrammu izmantosana", courseRight.getDescription());
		
		//wrong
		
	}

}
