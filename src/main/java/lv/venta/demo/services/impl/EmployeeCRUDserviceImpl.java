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
	public boolean insertNewEmployee(Employee employee) {
		if (!employeeRepo.existsByEmailOrPhone(employee.getEmail(), employee.getPhone())) {
			employeeRepo.save(employee);
			return true;
		}

		return false;
	}

	@Override // TODO Remove most variables add Employee employee
	public Employee updateEmployeeById(int employeeId, String name, String surname, int phone, String email,
			Department department, Position position) {
		if (employeeRepo.existsByIdEm(employeeId)) {
			Employee result = employeeRepo.findByIdEm(employeeId);
			result = new Employee(name, surname, phone, email, department, position);
			return result;
		}
		return new Employee(name, surname, phone, email, department, position);
	}

	@Override
	public ArrayList<Employee> deleteEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		if (employeeRepo.existsByIdEm(employeeId)) {
			employeeRepo.deleteByIdEm(employeeId);
		}
		ArrayList<Employee> result = (ArrayList<Employee>) employeeRepo.findAll();
		return result;
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
	
	public ArrayList<Employee> selectAllEmployees(){
		return (ArrayList<Employee>) employeeRepo.findAll();
	}

}
