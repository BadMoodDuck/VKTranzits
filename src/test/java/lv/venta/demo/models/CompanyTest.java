package lv.venta.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompanyTest {
	
	Company companyRight = new Company("Kompanija");
	Company companyWrong = new Company("123?");

	@Test
	void companyCreationTest() {
		//right
		assertEquals("Kompanija", companyRight.getName());
		
		//wrong
		//assertEquals("", companyWrong.getName());
	}
	
}
