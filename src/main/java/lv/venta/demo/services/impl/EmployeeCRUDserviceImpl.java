package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.demo.models.Employee;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Service
public class EmployeeCRUDserviceImpl implements IEmployeeCRUDservice {
	
	
	@Autowired
	private IEmployeeRepo employeeRepo;
	@Autowired
	private IDepartmentRepo departmentRepo;
	
	@Override
	public boolean insertNewEmployee(Employee employee) {
		//if (!employeeRepo.existsByEmailOrPhone(employee.getEmail(), employee.getPhone())) {  TODO VALIDATION FIX
			employeeRepo.save(employee);
		//	return true;
		//}

		return false;
	}

	@Override 
	public boolean updateEmployeeById(int employeeId, Employee employee) {
		if (employeeRepo.existsById(employeeId)) {
			Employee temp = employeeRepo.findById(employeeId).get();
			temp.setName(employee.getName());
			temp.setSurname(employee.getSurname());
			temp.setPhone(employee.getPhone());
			temp.setEmail(employee.getEmail());
			temp.setDepartment(employee.getDepartment());
			temp.setPosition(employee.getPosition());
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteEmployeeById(int employeeId) {
		if (employeeRepo.existsById(employeeId)) {
			employeeRepo.deleteById(employeeId);
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<Employee> selectAllEmployeesFromDepartmentById(int departmentId) {
		if (departmentRepo.existsById(departmentId)) {
			return (ArrayList<Employee>) employeeRepo.findAllByDepartmentIdDe(departmentId);
		}
		return null;
	}
	@Override
	public ArrayList<Employee> selectAllEmployees() {
		return (ArrayList<Employee>) employeeRepo.findAll();
	}

	
	@Override
	public Employee getEmployeeById(int employeId) {
		if (employeeRepo.existsById(employeId)) {
			return employeeRepo.findById(employeId).get();
		}
		return null;
	}

}

