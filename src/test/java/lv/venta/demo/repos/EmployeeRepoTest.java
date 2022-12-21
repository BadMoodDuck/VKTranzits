package lv.venta.demo.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Position;

import lv.venta.demo.repos.IEmployeeRepo;

@DataJpaTest
class EmployeeRepoTest {
	/*
	Position pos = new Position("Pozicija", "Pozicijas apraksts");
	Company com = new Company("Kompanija");
	Department dep = new Department(com, "IT");
	Employee employeeRight = new Employee("Janis", "Berzins", 20134567, "random@gmail.com", "Password", dep, pos);
	
	@Autowired
	IEmployeeRepo employeeRepo;
	
	@Test
	void employeeSaveTest() {
		employeeRepo.save(employeeRight);
		
		assertNotNull(employeeRepo.existsByEmailOrPhone("random@gmail.com", 20134567));
	}
	
	*/

}
