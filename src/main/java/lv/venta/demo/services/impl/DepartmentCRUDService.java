package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.services.IDepartmentCRUDService;

@Service
public class DepartmentCRUDService implements IDepartmentCRUDService{

	@Autowired
	private IDepartmentRepo departmentRepo;
	

	@Autowired
	private IEmployeeRepo emRepo;
	
	
	@Override
	public ArrayList<Department> getAllDepartments() {
		return (ArrayList<Department>) departmentRepo.findAll();
	}
	
	@Override
	public ArrayList<Employee> getAllEmployeesFromDepartment(int idDe){
		if (departmentRepo.existsById(idDe)) {
			return emRepo.findByDepartmentIdDe(idDe);
			
		}
		return new ArrayList<Employee>();
	}

}
