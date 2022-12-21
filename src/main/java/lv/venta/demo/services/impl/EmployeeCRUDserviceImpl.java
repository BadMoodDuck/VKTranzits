package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
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
	public Page<Employee> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr-1, 10);
		return employeeRepo.findAll(pageable);
	}
	
	@Override
	public boolean insertNewEmployee(Employee employee) {
		//if (!employeeRepo.existsByEmailOrPhone(employee.getEmail(), employee.getPhone())) {  //TODO VALIDATION FIX
			employeeRepo.save(employee);
		return true;
		//}

		//return false;
	}

	@Override 
	public boolean updateEmployeeById(int employeeId, Employee employee) {
		Employee result = new Employee();
		if (employeeRepo.existsById(employeeId)) {
			result = employeeRepo.findById(employeeId).get();
			result.setName(employee.getName());
			result.setSurname(employee.getSurname());
			result.setPhone(employee.getPhone());
			result.setEmail(employee.getEmail());
			result.setDepartment(employee.getDepartment());
			result.setPosition(employee.getPosition());
			employeeRepo.save(result);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteEmployeeById(int employeeId) {
		if (employeeRepo.existsById(employeeId)) {
			Employee employee = employeeRepo.findById(employeeId).get();
			
			employeeRepo.deleteById(employeeId);
			return true;
		}
		return false;
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

	@Override
	public Object readEmployeeById(int id) throws Exception {
		if(employeeRepo.existsById(id))
		{
			Employee em = employeeRepo.findByIdEm(id);
			return em;
		}
		
		throw new Exception("Employee doesn't exist");
		
	
	}
	@Override
	public Page<Employee> getPageListWithSort(int pageNr, String field, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(field).ascending(): Sort.by(field).descending();
		
		Pageable pageable = PageRequest.of(pageNr - 1, 10, sort);
		return employeeRepo.findAll(pageable);
	
	}


}

