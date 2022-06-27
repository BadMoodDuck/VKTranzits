package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Position;

public interface IEmployeeCRUDservice {

	// Izveidot darbinieku
	boolean insertNewEmployee(Employee employee);

	// Atjaunot darbinieku
	boolean updateEmployeeById(int employeeId, Employee employee);

	// Dzest darbinieku
	boolean deleteEmployeeById(int employeeId);

	// Parādīt visus struktūrvienības darbiniekus
	ArrayList<Employee> selectAllEmployeesFromDepartmentById(int departmentId);
	
	//paradit visus darbiniekus
	ArrayList<Employee> selectAllEmployees();

}
