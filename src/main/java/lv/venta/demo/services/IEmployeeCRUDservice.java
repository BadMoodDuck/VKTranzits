package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Position;

public interface IEmployeeCRUDservice {

	// Izveidot darbinieku
	public abstract Employee insertNewEmployee(String name, String surname, int phone, String email,
			Department department, Position position);

	// Atjaunot darbinieku
	public abstract Employee updateEmployeeById(int employeeId, String name, String surname, int phone, String email,
			Department department, Position position);

	// Dzest darbinieku
	public abstract ArrayList<Employee> deleteEmployeeById(int employeeId);

	// Parādīt visus struktūrvienības darbiniekus
	public abstract ArrayList<Employee> selectAllEmployeesFromDepartmentById(int departmentId);

}
