package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Service
public class EmployeeCRUDserviceImpl implements IEmployeeCRUDservice {

	// TODO pabeigt funkcijas ar visam parbaudem
	@Override
	public Employee insertNewEmployee(String name, String surname, int phone, String email, Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeById(int employeeId, String name, String surname, int phone, String email,
			Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> deleteEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> selectAllEmployeesFromDepartmentById(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
