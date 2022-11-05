package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Department;

public interface IDepartmentCRUDService {

	
	ArrayList<Department> getAllDepartments();

	boolean deleteDepartmentById(int id);

	boolean updateDepartmentById(int id, Department dep);
}
