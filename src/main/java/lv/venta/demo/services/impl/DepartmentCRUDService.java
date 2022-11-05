package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.services.IDepartmentCRUDService;

@Service
public class DepartmentCRUDService implements IDepartmentCRUDService{

	@Autowired
	private IDepartmentRepo departmentRepo;
	

	@Autowired
	private IEmployeeRepo emRepo;
	
	@Autowired
	private ICourseRepo cRepo;
	

	@Override
	public ArrayList<Department> getAllDepartments() {
		return (ArrayList<Department>) departmentRepo.findAll();
	}
	
	@Override
	public boolean deleteDepartmentById(int id) {
		if(departmentRepo.existsById(id)) {
			ArrayList<Employee> empl = emRepo.findByDepartmentIdDe(id);
			for(Employee temp: empl) {
				temp.setDepartment(null);
				emRepo.save(temp);
			}
			ArrayList<Course> cours= cRepo.findByDepartmentsIdDe(id);
			for(Course temp : cours) {
				temp.setDepartments(null);
				cRepo.save(temp);
			}
			departmentRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateDepartmentById(int id, Department dep) {
		if(departmentRepo.existsById(id)) {
			Department depart = new Department();
			depart.setCompany(dep.getCompany());
			depart.setName(dep.getName());
			//depart.setHeadDepName(dep.getHeadDepName());
			//depart.setHeadDepSurname(dep.getHeadDepSurname());
			departmentRepo.save(depart);
			return true;
		}
		return false;
	}



}
