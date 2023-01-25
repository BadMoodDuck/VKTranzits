package lv.venta.demo.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lv.venta.demo.helpers.EmployeeHelper;
import lv.venta.demo.models.Employee;
import lv.venta.demo.repos.IEmployeeCourseRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.services.impl.EmployeeCRUDserviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EmployeeCRUDServiceImplTest {

	private EmployeeCRUDserviceImpl emplService;
	
	@Mock
	private IEmployeeRepo employeeRepo;
	@Mock
	private IEmployeeCourseRepo eCourseREpo;
	

	@BeforeEach
	void beforeEach() {
		emplService = new EmployeeCRUDserviceImpl(employeeRepo, eCourseREpo);
	}
	
	@AfterEach
	void afterEach() {
		reset(employeeRepo, eCourseREpo);
	}
	
	@Test
	void insertNewEmployeeTest() {
		Employee employee = EmployeeHelper.createEmployee();
		
		when(employeeRepo.existsById(employee.getIdEm())).thenReturn(false);
		
		emplService.insertNewEmployee(employee);
		
		verify(employeeRepo, times(1)).save(any());
	}
	
	@Test
	void updateEmployeeByIdTest() {
		Employee employee = EmployeeHelper.createEmployee();
		
		when(employeeRepo.existsById(employee.getIdEm())).thenReturn(true);
		when(employeeRepo.findById(employee.getIdEm())).thenReturn(Optional.of(employee));
		
		emplService.updateEmployeeById(employee.getIdEm(), employee);
		
		verify(employeeRepo, times(1)).save(any());
	}
	
	@Test
	void deleteEmployeeByIdTest() {
		Employee employee = EmployeeHelper.createEmployee();
		
		when(employeeRepo.existsById(employee.getIdEm())).thenReturn(true);
		
		emplService.deleteEmployeeById(employee.getIdEm());
		
		verify(employeeRepo, times(1)).deleteById(any());
	}
	
	@Test
	void readEmployeeByIdTest() throws Exception {
		Employee employee = EmployeeHelper.createEmployee();
		
		when(employeeRepo.existsById(employee.getIdEm())).thenReturn(true);
		when(employeeRepo.findByIdEm(employee.getIdEm())).thenReturn(employee);
		
		Employee result = emplService.readEmployeeById(employee.getIdEm());
		
		assertNotNull(result);
	}
	
	
}
