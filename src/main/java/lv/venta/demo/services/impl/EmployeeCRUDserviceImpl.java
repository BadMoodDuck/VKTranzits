package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Position;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Service
public class EmployeeCRUDserviceImpl implements IEmployeeCRUDservice {

	@Autowired
	private IEmployeeRepo employeeRepo;
	@Autowired
	private IDepartmentRepo departmentRepo;

	// TODO pabeigt funkcijas ar visam parbaudem
	@Override
	public Employee insertNewEmployee(String name, String surname, int phone, String email, Department department,
			Position position) {
		Employee result = new Employee(name, surname, phone, email, department, position);
		if (!employeeRepo.equals(result)) {
			employeeRepo.save(result);
			return result;
		}
		return null;
	}

	@Override
	public Employee updateEmployeeById(int employeeId, String name, String surname, int phone, String email,
			Department department, Position position) {
		if (employeeRepo.existsByIdEm(employeeId)) {
			Employee result = employeeRepo.findByIdEm(employeeId);
			result = new Employee(name, surname, phone, email, department, position);
			return result;
		}
		return null;
	}

	@Override
	public ArrayList<Employee> deleteEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		/*if (employeeRepo.existsByIdEm(employeeId)) {
			employeeRepo.deleteByIdEm(employeeId);
			ArrayList<Employee> result = departmentRepo.findAllByEmployeesIdEm(employeeId);
			return result;
		}*/
		return null;
	}

	@Override
	public ArrayList<Employee> selectAllEmployeesFromDepartmentById(int departmentId) {
		// TODO Auto-generated method stub
		if (employeeRepo.existsByDepartmentIdDe(departmentId)) {
			ArrayList<Employee> result = employeeRepo.findAllByDepartmentIdDe(departmentId);
			return result;
		}
		return null;
	}

}
