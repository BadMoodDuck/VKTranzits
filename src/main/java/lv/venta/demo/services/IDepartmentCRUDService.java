package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;

public interface IDepartmentCRUDService {

	
	ArrayList<Department> getAllDepartments();
	boolean createNewDepartment (Department dep);
	
	boolean deleteDepartmentById(int id);

	boolean updateDepartmentById(int id, Department dep);

	ArrayList<Employee> getAllEmployeesFromDepartment(int idDe);
}
 