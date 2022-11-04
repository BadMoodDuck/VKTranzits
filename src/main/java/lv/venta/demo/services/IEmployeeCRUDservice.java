package lv.venta.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.venta.demo.models.Employee;

public interface IEmployeeCRUDservice {
	
	public Page<Employee> getPageList(int pageNr);
	
	// Izveidot darbinieku
	boolean insertNewEmployee(Employee employee);

	// Atjaunot darbinieku
	boolean updateEmployeeById(int employeeId, Employee employee);

	// Dzest darbinieku
	boolean deleteEmployeeById(int employeeId);
	
	//paradit visus darbiniekus
	ArrayList<Employee> selectAllEmployees();

	Employee getEmployeeById(int employeId);

	public Object readEmployeeById(int id) throws Exception;

}

