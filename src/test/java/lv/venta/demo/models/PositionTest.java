package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PositionTest {
	
	Position posRight = new Position("Gramatvedis", "Veic uznemuma finansialo operaciju pilnu uzskaiti.");

	@Test
	void postionCreationTest() {
		//right
		assertEquals("Gramatvedis", posRight.getTitle());
		assertEquals("Veic uznemuma finansialo operaciju pilnu uzskaiti.", posRight.getDescription());
	}

}
